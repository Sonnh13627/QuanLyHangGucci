package assignment.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import assignment.entities.Order;
import assignment.entities.OrderDetail;
import assignment.repositories.OrderDetailRepository;
import assignment.repositories.OrderRepository;

public class ManagerOrderController {
	@Autowired
	OrderRepository orderDAO;
	
	@Autowired 
	OrderDetailRepository orderDetailDAO;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("views")
	public String views(Model model) {
		List<Order> listOrders = orderDAO.findAll();
		List<OrderDetail> listOrderDetails = orderDetailDAO.findAll();
		
		model.addAttribute("list1", listOrders);
		model.addAttribute("list2", listOrderDetails);
		
		model.addAttribute("view", "/views/manager/views.jsp");
		return "formHomepage";
	}
}
