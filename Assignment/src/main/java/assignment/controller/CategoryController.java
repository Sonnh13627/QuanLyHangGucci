package assignment.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import assignment.beans.CategoryModel;

import assignment.entities.Category;
import assignment.repositories.CategoryRepository;

@Controller
@RequestMapping("categories")
public class CategoryController {
	@Autowired
	CategoryRepository categoryRepos;
	
	@Autowired
	HttpSession session;
	@GetMapping("create")
	public String index(@ModelAttribute("entity") CategoryModel categoryModel, Model model,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "3") Integer size,
			@RequestParam(name = "field", defaultValue = "name") String field) {
		model.addAttribute("url", "store");

		Pageable pageable = PageRequest.of(page, size, Sort.by(field));
		Page<Category> page2 = categoryRepos.findAll(pageable);

		model.addAttribute("sort", field);
		model.addAttribute("page", page2);
		model.addAttribute("view", "/views/categories/form.jsp");
		return "/formHomepage";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Category category,
			@RequestParam(name="page", defaultValue = "0") Integer page,
			@RequestParam(name="size", defaultValue = "3") Integer size,
			@RequestParam(name="field", defaultValue = "name") String field) {
		model.addAttribute("url", "update/" + category.getId());
		model.addAttribute("entity", category);

		Pageable pageable = PageRequest.of(page, size, Sort.by(field));
		Page<Category> page2 = categoryRepos.findAll(pageable);
		
		model.addAttribute("sort", field);
		model.addAttribute("page", page2);
		
		model.addAttribute("view", "/views/categories/form.jsp");
		return "/formHomepage";
	}

	@PostMapping("store")
	public String store(@Valid @ModelAttribute("entity") CategoryModel categoryModel, BindingResult result, Model model,
			@RequestParam(name="page", defaultValue = "0") Integer page,
			@RequestParam(name="size", defaultValue = "3") Integer size) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("url", "store");
				
				Pageable pageable = PageRequest.of(page, size);
				Page<Category> page2 = categoryRepos.findAll(pageable);
				
				model.addAttribute("page", page2);
				model.addAttribute("view", "/views/categories/create.jsp");
				return "/formHomepage";
			} else {
				Category category = new Category();
				category.setName(categoryModel.getName());
				categoryRepos.save(category);
				session.setAttribute("message", "Create category successfully!");
			}
		} catch (Exception e) {
			session.setAttribute("error", "Create category failed!");
			e.printStackTrace();
		}
		return "redirect:/categories/create";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") Integer id,@Valid @ModelAttribute("entity") CategoryModel categoryModel,
			BindingResult result, Model model,
			@RequestParam(name="page", defaultValue = "0") Integer page,
			@RequestParam(name="size", defaultValue = "3") Integer size) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("url", "store");
				
				Pageable pageable = PageRequest.of(page, size);
				Page<Category> page2 = categoryRepos.findAll(pageable);
				
				model.addAttribute("page", page2);
				model.addAttribute("view", "/views/category/create.jsp");
				return "formHomepage";
			} else {
				Category category = new Category();
				category.setId(id);
				category.setName(categoryModel.getName());
				categoryRepos.save(category);
				session.setAttribute("message", "Update category successfully!");
			}
		} catch (Exception e) {
			session.setAttribute("error", "Update category failed!");
			e.printStackTrace();
		}
		return "redirect:/categories/create";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Category category) {
		try {
			categoryRepos.delete(category);
			session.setAttribute("message", "Xóa Category thành công!");
		} catch (Exception e) {
			session.setAttribute("error", "Xóa Category thất bại!");
			e.printStackTrace();
		}
		return "redirect:/categories/create";
	}

}
