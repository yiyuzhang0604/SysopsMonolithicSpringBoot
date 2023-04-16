package com.sysops.controller;

import com.sysops.controller.request.CreateTicketRequest;
import com.sysops.entity.Ticket;
import com.sysops.service.Implementation.TicketServiceImpl;
import com.sysops.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("api/ticket")
public class TicketController {
    Logger logger = LoggerFactory.getLogger(TicketController.class);

    TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/ticket")
    public String ticketPage() {
        logger.info("TicketController.ticketPage()");
        // TODO: implement ticket page
        return "ticket";
    }

    @PostMapping("/createTicket")
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody Ticket ticket) {
        Ticket savedTicket = ticketService.createTicket(ticket);
        return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
    }
}
