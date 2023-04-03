package com.sysops.service;

import com.sysops.controller.request.CreateTicketRequest;
import com.sysops.entity.Ticket;

public interface TicketService {
    // create ticket
    public String createTicket(CreateTicketRequest createTicketRequest);

    // assign ticket to expert
    public void assignTicket(Long ticketId);

    // update ticket status
    public void updateTicketStatus(Long ticketId, Ticket.TicketStatus status);
}
