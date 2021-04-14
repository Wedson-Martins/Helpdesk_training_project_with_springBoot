package com.wcode.helpdesk.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wcode.helpdesk.models.Role;
import com.wcode.helpdesk.services.RoleServiceImp;

@Controller
@RequestMapping(value = "/roles")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController {

	@Autowired
	private RoleServiceImp roleService;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("roles", this.roleService.findAll());
		return "roles/index";
	}

	@GetMapping(value = "/new")
	public String create(Model model) {
		model.addAttribute("role", new Role());
		return "roles/create";
	}

	@GetMapping("/{id}")
	public String edit(@PathVariable(value = "id") Long id, Model model) {
		Role role = this.roleService.findOne(id);
		model.addAttribute("id", id);
		model.addAttribute("role", role);
		System.out.println("OK!!!");
		return "roles/edit";
	}

	@PostMapping
	public String save(@Valid Role role, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {

			for (ObjectError erro : bindingResult.getAllErrors()) {
				System.out.println("Erro: " + erro.getDefaultMessage());
			}

			System.out.println("Com Errros!!!!");
			return "roles/create";
		}
		Role roleCreated = this.roleService.create(role);
		System.out.println("Sem Erros!!!!");
		return "redirect:/roles";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable(value = "id", required = true) Long id, @ModelAttribute Role role, Model model) {
		System.out.println("QQQQQQQQQQQ");
		this.roleService.update(id, role);
		return "redirect:/roles";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		this.roleService.delete(id);
		return "redirect:/roles";
	}

}
