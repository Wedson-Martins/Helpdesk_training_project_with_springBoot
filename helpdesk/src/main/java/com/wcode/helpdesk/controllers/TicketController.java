package com.wcode.helpdesk.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.wcode.helpdesk.models.Interaction;
import com.wcode.helpdesk.models.Ticket;
import com.wcode.helpdesk.services.RoleService;
import com.wcode.helpdesk.services.TicketService;
import com.wcode.helpdesk.services.UserService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	private final String ROLE_ID = "ADMIN";
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private TicketService ticketService;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("list", this.ticketService.findAll());
		model.addAttribute("userLoggedIn", this.ticketService.findCurrent());
		return "tickets/index";
	}

	@GetMapping("/new")
	public String create(Model model) {

		model.addAttribute("ticket", new Ticket());
		model = this.ticketService.findAllTechinician(model);
		return "tickets/create";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "tickets/create";
		}
		this.ticketService.create(ticket);
		return "redirect:/tickets";
	}

	@GetMapping("{id}")
	public String show(@PathVariable(value = "id") Long id, Model model) {
		Ticket ticket = this.ticketService.findById(id);
		System.out.println("------------------------------------------: " + ticket.getUserOpen().getId());
		List<Interaction> interactions = ticket.getInteractions();
		model.addAttribute("ticket", ticket);
		model.addAttribute("interaction", new Interaction());
		model.addAttribute("interactions", interactions);
		model.addAttribute("userLoggedIn", this.userService.findCurrentUser());
		return "tickets/show";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable(value = "id") Long id, Model model) {
		Ticket ticket = this.ticketService.findById(id);
		List<Interaction> interactions = ticket.getInteractions();
		
		model = this.ticketService.findAllTechinician(model);
		
		model.addAttribute("ticket", ticket);
		model.addAttribute("interactionsSize", interactions.size());
		model.addAttribute("userLoggedIn", this.userService.findCurrentUser());
		
		return "tickets/edit";
	}

	@PutMapping("{id}")
	public String update(@PathVariable(value = "id", required = true) Long id, @ModelAttribute("ticket") Ticket ticket,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "tickets/edit";
		}
		Ticket ticketUpdate = this.ticketService.findById(id);
		if (ticketUpdate != null) {
			ticketUpdate.setName(ticket.getName());
			ticketUpdate.setDescription(ticket.getDescription());
			ticketUpdate.setTechnician(ticket.getTechnician());
			ticketUpdate.setFinished(ticket.getFinished());
			System.out.println("--------------: " + ticketUpdate.getFinished());
			System.out.println("++++++++++++++: " + ticket.getFinished());
			this.ticketService.update(id, ticketUpdate);

		}
		return "redirect:/tickets";
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable(value = "id", required = true) Long id, Model model) {
		this.ticketService.delete(id);
		return "redirect:/tickets";
	}

}
