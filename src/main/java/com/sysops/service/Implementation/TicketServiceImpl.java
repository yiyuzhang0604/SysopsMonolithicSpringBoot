package com.sysops.service.Implementation;

import com.sysops.dao.TicketDao;
import com.sysops.entity.Expert;
import com.sysops.entity.Ticket;
import com.sysops.exceptions.TicketNotFoundException;
import com.sysops.service.ExpertService;
import com.sysops.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of TicketService
 */
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;
    private final ExpertService expertService;

    @Autowired
    public TicketServiceImpl(TicketDao ticketDao, ExpertService expertService) {
        this.ticketDao = ticketDao;
        this.expertService = expertService;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        Ticket savedTicket = ticketDao.save(ticket);
        List<Expert> matchingExperts = expertService.findMatchExpert(savedTicket);
        Expert assignedExpert = expertService.getFirstAvailableExpert(matchingExperts);
        savedTicket = assignTicketToExpert(assignedExpert.getExpertId(), savedTicket.getTicketId());
        return savedTicket;
    }

    @Override
    public Ticket assignTicketToExpert(Long expertId, Long ticketId) {
        Ticket ticket = getTicketById(ticketId);
        Expert expert = expertService.getExpertById(expertId);
        ticket.setExpert(expert);
        expert.addTicket(ticket);
        return ticketDao.save(ticket);
    }

    @Override
    public void updateTicketStatus(Long ticketId, Ticket.TicketStatus status) {
        Ticket ticket = getTicketById(ticketId);
        ticket.setStatus(status);
        ticketDao.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long ticketId) {
        return ticketDao.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found with Id: " + ticketId));
    }
}