package com.wcode.helpdesk.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.wcode.helpdesk.models.Role;
import com.wcode.helpdesk.models.Ticket;
import com.wcode.helpdesk.models.User;
import com.wcode.helpdesk.repositories.TicketRepository;

@Service
public class TicketServiceImp implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Override
	public List<Ticket> findAll() {
		return (List<Ticket>) this.ticketRepository.findAll();
	}

	@Override
	public Model findAllTechinician(Model model) {
		Role adminRole = this.roleService.findByName("ADMIN");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String userName = auth.getName();

		User userLogged = this.userService.findByEmail(userName);

		model.addAttribute("techs", this.userService.findAllWhereRoleEquals(adminRole.getId(), userLogged.getId()));

		return model;
	}

	@Override
	public Ticket create(Ticket ticket) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String userName = auth.getName();

		User userLogged = this.userService.findByEmail(userName);

		ticket.setUserOpen(userLogged);

		return this.ticketRepository.save(ticket);

	}

	@Override
	public Ticket update(Long id, Ticket ticket) {
		Ticket ticketBase = this.ticketRepository.findById(id).get();
		if (ticketBase != null) {
			ticketBase.setName(ticket.getName());
			ticketBase.setdescription(ticket.getdescription());
			ticketBase.setTechnician(ticket.getTechnician());
			if (ticket.getFinished()) {
				ticketBase.setClosed(new Date());
			}

			return this.ticketRepository.save(ticketBase);
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		this.ticketRepository.deleteById(id);
		return false;
	}

	@Override
	public Ticket show(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket findById(Long id) {
		return this.ticketRepository.findById(id).get();
	}

	@Override
	public User findCurrent() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User userLogged = this.userService.findByEmail(userName);
		return userLogged;
	}

	@Override
	public List<Ticket> reportTicketByDays(Integer day) {

		return this.ticketRepository.findAllTicketsyDay(day);
	}

}
