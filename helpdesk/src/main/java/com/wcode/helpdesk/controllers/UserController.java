package com.wcode.helpdesk.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wcode.helpdesk.models.Role;
import com.wcode.helpdesk.models.User;
import com.wcode.helpdesk.services.RoleServiceImp;
import com.wcode.helpdesk.services.UserServiceImp;

@Controller
@RequestMapping(value = "/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

	@Autowired
	private UserServiceImp userService;
	@Autowired
	private RoleServiceImp roleService;

	@GetMapping
	public String index(Model model) {
		List<User> users = this.userService.findAll();
		model.addAttribute("users", users);
		return "users/index";
	}

	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("user", new User());
		return "users/create";
	}

	@GetMapping("/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		List<Role> roles = this.roleService.findAll();
		User userCreated = this.userService.findOne(id);
		model.addAttribute("id", id);
		model.addAttribute("user", userCreated);
		model.addAttribute("roles", roles);
		return "users/edit";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "users/create";
		}
		User userCreated = this.userService.create(user);
		return "redirect:users";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable(value = "id", required = true) Long id, @Valid @ModelAttribute("user") User user,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("ERRO PUT");
			return "users/edit";
		}
		User userUpdated = this.userService.update(id, user);

		return "redirect:/users";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable(value = "id", required = true) Long id, Model model) {
		this.userService.delete(id);
		return "redirect:/users";
	}

}
