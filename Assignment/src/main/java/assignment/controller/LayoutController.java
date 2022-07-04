	package assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import assignment.entities.Product;
import assignment.repositories.ProductRepository;

@Controller
@RequestMapping("home")
public class LayoutController {
	@Autowired
	ProductRepository productRepos;
	
	
	
 @GetMapping("index")
 public String index( Model model) {
	 	List<Product> listproduct = productRepos.findAll();
	 
	 	model.addAttribute("listProducts", listproduct);
		String main = "/views/homes/homepage.jsp" ;
		model.addAttribute("view", main) ;
		
		return "formHomepage";
	}
}
