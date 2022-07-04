package assignment.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;

import org.springframework.data.domain.Sort;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.cj.Session;

import assignment.beans.CategoryModel;
import assignment.beans.ProductModel;
import assignment.entities.Category;
import assignment.entities.Product;
import assignment.repositories.CategoryRepository;
import assignment.repositories.ProductRepository;

@Controller
@RequestMapping("products")
public class ProductController {
	@Autowired
	ProductRepository productRepos;
	
	@Autowired
	CategoryRepository categoryRepos;

	@Autowired
	HttpSession session;
	
	@Autowired
	ServletContext context;
	@GetMapping("create")
	public String index(@ModelAttribute("entity") ProductModel productModel, Model model,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "3") Integer size,
			@RequestParam(name = "field", defaultValue = "name") String field) {
		model.addAttribute("url", "store");

		Pageable pageable = PageRequest.of(page, size, Sort.by(field));
		Page<Product> page2 = productRepos.findAll(pageable);

		model.addAttribute("sort", field);
		model.addAttribute("page", page2);
		model.addAttribute("view", "/views/products/form.jsp");
		return "formHomepage";
	}

	@ModelAttribute("categoryIds")
	public Map<Integer, String> getCategory() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<Category> list = categoryRepos.findAll();
		for (Category x : list) {
			map.put(x.getId(), x.getName());
		}
		return map;
	}
	@ModelAttribute("availibles")
	public Map<Integer, String> getAvailible() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(0, "Hết hàng");
		map.put(1, "Còn hàng");
		return map;
	}
	@PostMapping("store")
	public String store(@Valid @ModelAttribute("entity") ProductModel productModel, BindingResult result, Model model,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "3") Integer size) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("url", "store");

				Pageable pageable = PageRequest.of(page, size);
				Page<Product> page2 = productRepos.findAll(pageable);

				model.addAttribute("page", page2);
				model.addAttribute("view", "/views/products/create.jsp");
				return "formHomepage";
			} else {
				Product product = new Product();
				product.setName(productModel.getName());
				product.setPrice(productModel.getPrice());
				product.setCreateDate(new Date());
				product.setAvailable(productModel.getAvailable());

				Category category = categoryRepos.getById(productModel.getCategoryId());
				product.setCategory(category);
				if (!productModel.getPhotoFile().isEmpty()) {
					String path = context.getRealPath("/image");
					File file = new File(path);
					if (!file.exists()) {
						file.mkdirs();
					}
					try {
						String fileName = productModel.getPhotoFile().getOriginalFilename();
						File finalFile = new File(file.getAbsoluteFile() + File.separator + fileName);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(finalFile));
						stream.write(productModel.getPhotoFile().getBytes());
						stream.close();

						product.setImage(fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				productRepos.save(product);
				session.setAttribute("message", "Create product successfully!");
			}
		} catch (Exception e) {
			session.setAttribute("error", "Create product failed!");
			e.printStackTrace();
		}
		return "redirect:/products/create";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Product product,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "3") Integer size,
			@RequestParam(name = "field", defaultValue = "name") String field) {
		
		ProductModel productModel = new ProductModel();
		productModel.setName(product.getName());
		productModel.setPrice(product.getPrice());
		productModel.setCategoryId(product.getCategory().getId());
		productModel.setAvailable(product.getAvailable());
		
		model.addAttribute("url", "update/" + product.getId());
		model.addAttribute("entity", productModel);

		Pageable pageable = PageRequest.of(page, size, Sort.by(field));
		Page<Product> page2 = productRepos.findAll(pageable);

		model.addAttribute("sort", field);
		model.addAttribute("page", page2);
		model.addAttribute("view", "/views/products/form.jsp");
		return "formHomepage";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute("entity") ProductModel productModel,
			BindingResult result, Model model, @RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "3") Integer size) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("url", "store");

				Pageable pageable =  PageRequest.of(page, size);
				Page<Product> page2 = productRepos.findAll(pageable);

				model.addAttribute("page", page2);
				model.addAttribute("view", "/views/products/form.jsp");
				return "formHomepage";
			} else {
				Product product = new Product();
				product.setId(id);
				product.setName(productModel.getName());
				product.setPrice(productModel.getPrice());
				product.setCreateDate(new Date());
				product.setAvailable(productModel.getAvailable());

				Category category = categoryRepos.getById(productModel.getCategoryId());
				product.setCategory(category);
				if (!productModel.getPhotoFile().isEmpty()) {
					String path = context.getRealPath("/image");
					File file = new File(path);
					if (!file.exists()) {
						file.mkdirs();
					}
					try {
						String fileName = productModel.getPhotoFile().getOriginalFilename();
						File finalFile = new File(file.getAbsoluteFile() + File.separator + fileName);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(finalFile));
						stream.write(productModel.getPhotoFile().getBytes());
						stream.close();

						product.setImage(fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				productRepos.save(product);
				session.setAttribute("message", "Update product successfully!");
			}
		} catch (Exception e) {
			session.setAttribute("error", "Update product failed!");
			e.printStackTrace();
		}
		return "redirect:/products/create";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Product product) {
		try {
			productRepos.delete(product);
			session.setAttribute("message", "Delete product successfully!");
		} catch (Exception e) {
			session.setAttribute("error", "Delete product failed");
			e.printStackTrace();
		}
		return "redirect:/products/create";
	}
}
		
	
