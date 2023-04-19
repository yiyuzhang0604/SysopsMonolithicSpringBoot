package com.sysops.controller;

import com.sysops.controller.request.CreateTicketRequest;
import com.sysops.controller.response.HttpBodyResponse;
import com.sysops.dao.CustomerDao;
import com.sysops.dao.TicketDao;
import com.sysops.entity.Customer;
import com.sysops.entity.Ticket;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/ticket")

public class TicketController {

    @Autowired
    CustomerDao customerDao;


    @Autowired
    TicketDao ticketDao;

    @PostMapping("/create")
    public ResponseEntity<?> createNewTicket(@Valid @RequestBody CreateTicketRequest createTicketRequest)
            throws ParseException {

        //get info from request
        String ticketDescription = createTicketRequest.getDescription();
        String ticketLocation = createTicketRequest.getLocation();
        Long ticketCustomerId = Long.valueOf(createTicketRequest.getCustomerId());

        //fetch customer from db
        Customer customer = customerDao.findCustomerByCustomerId(ticketCustomerId);
        if (customer == null) {
            return ResponseEntity.badRequest().body(new HttpBodyResponse("Customer not found"));
        }

        //create new ticket
        Ticket ticket = new Ticket(customer, ticketDescription, ticketLocation);
        ticketDao.save(ticket);

        return ResponseEntity.ok(new HttpBodyResponse ("Ticket Created: ticketId-" + ticket.getTicketId()));
    }
}