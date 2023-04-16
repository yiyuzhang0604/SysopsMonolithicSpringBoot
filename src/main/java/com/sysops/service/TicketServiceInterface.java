package com.sysops.service;

import com.sysops.entity.Ticket;

public interface TicketServiceInterface {

    Ticket createTicket(Ticket ticket);

    Ticket getTicketById(Long ticketId);

    Ticket assignTicket(Long expertId, Long ticketId);

    Ticket resolveTicket(Long ticketId);

}