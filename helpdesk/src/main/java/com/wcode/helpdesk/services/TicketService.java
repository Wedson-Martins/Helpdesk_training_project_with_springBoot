package com.wcode.helpdesk.services;

import java.util.List;

import org.springframework.ui.Model;

import com.wcode.helpdesk.models.Ticket;
import com.wcode.helpdesk.models.User;

public interface TicketService {

	public List<Ticket> findAll();

	public Ticket findById(Long id);

	public Ticket create(Ticket ticket);

	public Ticket update(Long id, Ticket ticket);

	public boolean delete(Long id);

	public Ticket show(Long id);

	public Model findAllTechinician(Model model);

	public User findCurrent();
	
	public List<Ticket> reportTicketByDays(Integer day);
}
