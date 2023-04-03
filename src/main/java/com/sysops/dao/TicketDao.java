package com.sysops.dao;

import com.sysops.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketDao extends JpaRepository<Ticket, Long> {

    // create ticket
    Ticket save(Ticket ticket);

    // find unassigned tickets
    List<Ticket> findTicketsByStatus(Ticket.TicketStatus status);

    // find ticket by id
    Ticket findTicketByTicketId(Long id);
}
