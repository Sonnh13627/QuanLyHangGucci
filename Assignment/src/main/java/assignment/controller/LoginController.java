package assignment.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import assignment.beans.LoginModel;
import assignment.entities.Account;
import assignment.repositories.AccountRepository;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("form")
	public String loginForm(Model model, @ModelAttribute("login") LoginModel loginModel) {
		model.addAttribute("view", "/views/login.jsp");
		return "formHomepage";
	}
	
	@PostMapping("login")
	public String loginLogin(@Valid @ModelAttribute("login") LoginModel loginModel, BindingResult result, Model model) {
		try {
			String email = loginModel.getEmail();
			String password = loginModel.getPassword();
			
			Account account = accountRepository.findByEmail(email);
			
			
			if (result.hasErrors()) {
				model.addAttribute("view", "/views/login.jsp");
				return "formHomepage";
			}
				else {
					if (!account.getEmail().equals(email)) {
						session.setAttribute("error", "Email không chính xác!");
					}				
					else if (!account.getPassword().equals(password)) {
						session.setAttribute("error", "Password không chính xác!");
				} else {
					session.setAttribute("account", account);
					return "redirect:/home/index";
				}
			}
		} catch (Exception e) {
			session.setAttribute("error", "Đăng nhập thất bại!");
			e.printStackTrace();
		}
		model.addAttribute("view", "/views/login.jsp");
		return "formHomepage";
	}
	
	@GetMapping("logout")
	public String logout(Model model) {
		session.removeAttribute("account");
		return "redirect:/login/form";
	}
}
