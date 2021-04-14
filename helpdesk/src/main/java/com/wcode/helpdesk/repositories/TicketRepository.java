package com.wcode.helpdesk.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wcode.helpdesk.models.Ticket;

@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {
	
//	@Query(value = "select t.* from tickets as t where t.created >= date(now()) - interval - 0 day", nativeQuery = true)
	@Query(value = "select * from tickets where created >= date(now() - interval + (:day) day)", nativeQuery = true)
	public List<Ticket> findAllTicketsyDay(@Param("day") Integer day);
	
}
