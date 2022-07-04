package assignment.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import assignment.beans.OrderDetailModel;
import assignment.beans.OrderModel;
import assignment.entities.Account;
import assignment.entities.Order;
import assignment.entities.OrderDetail;
import assignment.entities.Product;
import assignment.repositories.AccountRepository;
import assignment.repositories.OrderDetailRepository;
import assignment.repositories.OrderRepository;



@Controller
@RequestMapping("/cart/orders")
public class OrderController {
	@Autowired
	OrderRepository orderDAO;

	@Autowired
	OrderDetailRepository orderDetailDAO;

	@Autowired
	AccountRepository accountDAO;

	@Autowired
	HttpSession session;

	@GetMapping("create/{id}")
	public String create(@ModelAttribute("entity") OrderModel orderModel, Model model,
			@PathVariable("id") Product product) {
		model.addAttribute("product", product);
		model.addAttribute("view", "/views/order/create.jsp");
		return "/formHomepage";
	}

	@PostMapping("store/{id}")
	public String store(@Valid @ModelAttribute("entity") OrderModel orderModel, BindingResult result,
			@PathVariable("id") Product product, Model model) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("product", product);
				model.addAttribute("view", "/views/order/create.jsp");
				return "/formHomepage";
			} else {
				// Thông tin thêm
				Account account = (Account) session.getAttribute("account");
				List<Order> listOrders = orderDAO.findAll();
				Order order = null;
				boolean check = true;

				for (int i = 0; i < listOrders.size(); i++) {
					if (listOrders.get(i).getAccount().getId() == account.getId()) {
						if (listOrders.get(i).getAddress().equals(orderModel.getAddress())) {
							check = false;
							order = listOrders.get(i);
							break;
						}
					}
				}

				if (check) {
					order = new Order();
					order.setAccount(account);
					order.setCreateDate(new Date());
					order.setAddress(orderModel.getAddress());
					orderDAO.save(order);
				}
				// --------------------------------
				List<OrderDetail> listOrderDetails = orderDetailDAO.findAll();
				OrderDetail orderDetail = null;
				boolean check2 = true;
				for (int i = 0; i < listOrderDetails.size(); i++) {
					if (listOrderDetails.get(i).getProduct().getId() == product.getId()) {
						if (listOrderDetails.get(i).getOrder().getAddress().equals(orderModel.getAddress())) {
							check2 = false;
							orderDetail = listOrderDetails.get(i);
							orderDetail.setQuatity(listOrderDetails.get(i).getQuatity() + orderModel.getQuatity());
							orderDetailDAO.save(orderDetail);
							break;
						}
					}
				}

				if (check2) {
					orderDetail = new OrderDetail();
					orderDetail.setOrder(order);
					orderDetail.setProduct(product);
					orderDetail.setPrice(product.getPrice());
					orderDetail.setQuatity(orderModel.getQuatity());
					orderDetailDAO.save(orderDetail);
				}

				session.setAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công!");
			}
		} catch (Exception e) {
			session.setAttribute("error", "Thêm sản phẩm vào giỏ hàng thất bại!");
			e.printStackTrace();
		}
		return "redirect:/cart/orders/cart";
	}

	@GetMapping("cart")
	public String cart(@ModelAttribute("entity") OrderDetailModel orderDetailModel, Model model) {
		Account account = (Account) session.getAttribute("account");
		List<OrderDetail> listOrderDetails = orderDetailDAO.findById(account.getId());

//		float sum = 0;
//		for (OrderDetail x : listOrderDetails) {
//			if (x.getOrder().getTrangThai() == 0) {
//				sum += (x.getPrice() * x.getQuatity());
//			}
//		}
//
//		model.addAttribute("sum", sum);
		model.addAttribute("cart", listOrderDetails);
		model.addAttribute("view", "/views/order/cart.jsp");
		return "/formHomepage";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") OrderDetail orderDetail) {
		try {
			orderDetailDAO.delete(orderDetail);
			session.setAttribute("message", "Xóa đơn hàng thành công!");
		} catch (Exception e) {
			session.setAttribute("error", "Xóa đơn hàng thất bại!");
			e.printStackTrace();
		}
		return "redirect:/cart/orders/cart";
	}

	@PostMapping("update/{id}")
	public String update(Model model, @PathVariable("id") Integer id,
			@Valid @ModelAttribute("entity") OrderDetailModel orderDetailModel, BindingResult result) {
		try {
			if (result.hasErrors()) {
				Account account = (Account) session.getAttribute("account");
				List<OrderDetail> listOrderDetails = orderDetailDAO.findById(account.getId());
				model.addAttribute("cart", listOrderDetails);
				model.addAttribute("view", "/views/order/cart.jsp");
				return "/formHomepage";
			} else {
				OrderDetail orderDetail = orderDetailDAO.getById(id);
				orderDetail.setQuatity(orderDetailModel.getQuatity());
				orderDetailDAO.save(orderDetail);

				session.setAttribute("message", "Cập nhật số lượng thành công!");
			}
		} catch (Exception e) {
			session.setAttribute("error", "Cập nhật số lượng thất bại!");
			e.printStackTrace();
		}
		return "redirect:/cart/orders/cart";
	}
}
