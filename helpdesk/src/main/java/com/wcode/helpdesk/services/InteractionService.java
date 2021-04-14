package com.wcode.helpdesk.services;

import com.wcode.helpdesk.models.Interaction;

public interface InteractionService {

	public Interaction create(Interaction interaction, Long ticketId);

	public Boolean delete(Long id, Long ticketId);

}
