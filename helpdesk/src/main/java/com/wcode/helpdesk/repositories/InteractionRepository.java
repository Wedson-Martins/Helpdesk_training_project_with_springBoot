package com.wcode.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wcode.helpdesk.models.Interaction;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {

}
