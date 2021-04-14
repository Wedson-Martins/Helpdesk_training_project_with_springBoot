package com.wcode.helpdesk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.wcode.helpdesk.models.Interaction;
import com.wcode.helpdesk.models.Ticket;
import com.wcode.helpdesk.models.User;
import com.wcode.helpdesk.repositories.InteractionRepository;
import com.wcode.helpdesk.repositories.TicketRepository;
import com.wcode.helpdesk.repositories.UserRepository;

@Service
public class InteractionServiceImp implements InteractionService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private InteractionRepository interactionRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Interaction create(Interaction interaction, Long ticketId) {

		Ticket ticket = this.ticketRepository.findById(ticketId).get();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();

		User userLogged = this.userRepository.findByEmail(userName);

		interaction.setTicket(ticket);
		interaction.setUserInteraction(userLogged);

		return this.interactionRepository.save(interaction);

	}

	@Override
	public Boolean delete(Long id, Long ticketId) {
		Interaction interactionBase = this.interactionRepository.findById(id).get();
		if (interactionBase == null) {
			return false;
		}
		this.interactionRepository.delete(interactionBase);
		return true;
	}

}
