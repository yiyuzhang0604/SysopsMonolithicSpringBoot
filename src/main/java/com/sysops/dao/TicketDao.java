package com.sysops.dao;

import com.sysops.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

/**
 * DAO for managing Ticket entities.
 */
public interface TicketDao extends JpaRepository<Ticket, Long> {

    /**
     * Create a new ticket and save to the databse
     * @param ticket ticket to be saved
     * @return the saved ticket
     */
    Ticket save(Ticket ticket);

    /**
     * Find tickets with given status
     * @param status status of the ticket
     * @return list of the tickets with given status
     */
    List<Ticket> findTicketsByStatus(Ticket.TicketStatus status);

    Ticket findTicketByTicketId(Long id);
}
