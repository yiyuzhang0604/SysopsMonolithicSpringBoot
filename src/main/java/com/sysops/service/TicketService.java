package com.sysops.service;

import com.sysops.entity.Ticket;

/**
 * TicketService responsible for managing ticket-related operations.
 * It provides an interface to create, fetch, and update tickets.
 */
public interface TicketService {
    /**
     * Create a new ticket with given information
     *
     * @param ticket The ticket object containing ticket's information
     * @return The created ticket object
     */
    Ticket createTicket(Ticket ticket);

    /**
     * Assign the ticket to an expert
     *
     * @param expertId ID of the expert
     * @param ticketId ID of the ticket
     * @return Ticket object after assigning expert
     */
    Ticket assignTicketToExpert(Long expertId, Long ticketId);

    /**
     * Update the status of a ticket
     *
     * @param ticketId ID of the ticket
     * @param status   status to update
     */
    void updateTicketStatus(Long ticketId, Ticket.TicketStatus status);

    /**
     * Get the ticket object by ID
     *
     * @param ticketId ID of the ticket
     * @return The ticket object retrieved
     */
    Ticket getTicketById(Long ticketId);
}