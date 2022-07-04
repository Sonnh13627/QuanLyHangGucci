package assignment.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import assignment.beans.AccountModel;
import assignment.entities.Account;
import assignment.repositories.AccountRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Controller
@RequestMapping("accounts")
public class AccountController {
	@Autowired
	AccountRepository accountRepos;

	@Autowired
	ServletContext context;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("create")
	public String index(@ModelAttribute("entity") AccountModel accountModel ,Model model,  
			@RequestParam(name="page", defaultValue = "0") Integer page,
			@RequestParam(name="size", defaultValue = "3") Integer size
			) {
		model.addAttribute("url", "store");
		Pageable pageable = PageRequest.of(page, size);
		Page<Account> page2 = accountRepos.findAll(pageable);
		

		model.addAttribute("page", page2);
		model.addAttribute("view", "/views/accounts/form.jsp");
		return "formHomepage";
	}
	@ModelAttribute("admins")
	public Map<Integer, String> getAdmin(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(0, "Người dùng");
		map.put(1, "Quản trị viên");
		return map;
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Account account,
			@RequestParam(name="page", defaultValue = "0") Integer page,
			@RequestParam(name="size", defaultValue = "3") Integer size,
			@RequestParam(name="field", defaultValue = "fullname") String field) {
		model.addAttribute("url", "update/" + account.getId());
		model.addAttribute("entity", account);

		Pageable pageable = PageRequest.of(page, size, Sort.by(field));
		Page<Account> page2 = accountRepos.findAll(pageable);
		
		model.addAttribute("sort", field);
		model.addAttribute("page", page2);
		model.addAttribute("view", "/views/accounts/form.jsp");
		return "formHomepage";
	}

	@PostMapping("store")
	public String store(@Valid @ModelAttribute("entity") AccountModel accountModel, BindingResult result, Model model,
			@RequestParam(name="page", defaultValue = "0") Integer page,
			@RequestParam(name="size", defaultValue = "3") Integer size) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("url", "store");
				
				Pageable pageable = PageRequest.of(page, size);
				Page<Account> page2 = accountRepos.findAll(pageable);
				
				model.addAttribute("page", page2);
				model.addAttribute("view", "/views/accounts/form.jsp");
				return "formHomepage";
			} else {
				Account account = new Account();
				account.setFullname(accountModel.getFullname());
				account.setUsername(accountModel.getUsername());
				account.setPassword(accountModel.getPassword());
				account.setEmail(accountModel.getEmail());
				account.setActivated(0);
				account.setAdmin(accountModel.getAdmin());
				if(!accountModel.getPhotoFile().isEmpty()) {
					String path = context.getRealPath("/image");
					File file = new File(path);
					if (!file.exists()) {
						file.mkdirs();
					}
					try {
						String fileName = accountModel.getPhotoFile().getOriginalFilename();
						File finalFile = new File(file.getAbsoluteFile() + File.separator + fileName);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(finalFile));
						stream.write(accountModel.getPhotoFile().getBytes());
						stream.close();
						
						account.setPhoto(fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					account.setPhoto("nullAccount.jpg");
				}
				accountRepos.save(account);
				session.setAttribute("message", "Create Account successfully!");
			}
		} catch (Exception e) {
			session.setAttribute("error", "Create Account failed!");
			e.printStackTrace();
		}
		return "redirect:/accounts/create";
	}
	@PostMapping("update/{id}")
	public String update(@PathVariable("id") Integer id,@Valid @ModelAttribute("entity") AccountModel accountModel,
			BindingResult result, Model model,
			@RequestParam(name="page", defaultValue = "0") Integer page,
			@RequestParam(name="size", defaultValue = "3") Integer size) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("url", "store");
				
				Pageable pageable = PageRequest.of(page, size);
				Page<Account> page2 = accountRepos.findAll(pageable);
				
				model.addAttribute("page", page2);
				model.addAttribute("view", "/views/accounts/form.jsp");
				return "formHomepage";
			} else {
				Account account = new Account();
				account.setId(id);
				account.setFullname(accountModel.getFullname());
				account.setUsername(accountModel.getUsername());
			
				account.setPassword(accountModel.getPassword());
				account.setEmail(accountModel.getEmail());
				account.setActivated(0);
				account.setAdmin(accountModel.getAdmin());
				if(!accountModel.getPhotoFile().isEmpty()) {
					String path = context.getRealPath("/image");
					File file = new File(path);
					if (!file.exists()) {
						file.mkdirs();
					}
					try {
						String fileName = accountModel.getPhotoFile().getOriginalFilename();
						File finalFile = new File(file.getAbsoluteFile() + File.separator + fileName);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(finalFile));
						stream.write(accountModel.getPhotoFile().getBytes());
						stream.close();
						
						account.setPhoto(fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					account.setPhoto("nullAccount.jpg");
				}
				
				accountRepos.save(account);
				session.setAttribute("message", "Update Account successfully!");
			}
		} catch (Exception e) {
			session.setAttribute("error", "Delete Account failed!");
			e.printStackTrace();
		}
		return "redirect:/accounts/create";
	}
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Account account) {
		try {
			accountRepos.delete(account);
			session.setAttribute("message", "Delete Account successfully!");
		} catch (Exception e) {
			session.setAttribute("error", "Delete Account failed!");
			e.printStackTrace();
		}
		return "redirect:/accounts/create";
	}

}
