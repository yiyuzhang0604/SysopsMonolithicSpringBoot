package com.sysops.controller;

import com.sysops.controller.request.CreateTicketRequest;
import com.sysops.entity.Ticket;
import com.sysops.service.Implementation.TicketServiceImpl;
import com.sysops.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TicketController {
    Logger logger = LoggerFactory.getLogger(TicketController.class);

    TicketService ticketService = new TicketServiceImpl();

    @GetMapping("/ticket")
    public String ticketPage() {
        logger.info("TicketController.ticketPage()");
        // TODO: implement ticket page
        return "ticket";
    }

    // create ticket
    @PostMapping("/createTicket")
    public String createTicket(@RequestBody CreateTicketRequest createTicketRequest) {
        logger.info("TicketController.createTicket()");

        return ticketService.createTicket(createTicketRequest);
    }
}
