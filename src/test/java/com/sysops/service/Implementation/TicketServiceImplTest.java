package com.sysops.service.Implementation;

import com.sysops.controller.request.CreateTicketRequest;
import com.sysops.dao.CustomerDao;
import com.sysops.dao.ExpertDao;
import com.sysops.dao.TicketDao;
import com.sysops.entity.Customer;
import com.sysops.entity.Expert;
import com.sysops.entity.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
class TicketServiceImplTest {

    @Autowired
    private TicketServiceImpl ticketService;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ExpertDao expertDao;


    @Test
    void createTicket() {

        Customer customer = new Customer();
        customer.setCustomerId(1241241L);
        customer.setEmail("dsakfjla@gmail.com");
        customer.setPhoneNumber("123123123");
        customerDao.save(customer);

        // construct the request
        CreateTicketRequest createTicketRequest = new CreateTicketRequest();
        createTicketRequest.setCreatedDate(new Date());
        createTicketRequest.setDescription("test");
        createTicketRequest.setLocation("test");
        createTicketRequest.setCustomerId(1241241L);
        // call the function
        String ticketId = ticketService.createTicket(createTicketRequest);

        Ticket actualTicket = ticketDao.findTicketByTicketId(Long.parseLong(ticketId));

        // assert the ticket
        assertEquals(createTicketRequest.getDescription(), actualTicket.getDescription());
        assertEquals(createTicketRequest.getCreatedDate(), actualTicket.getCreatedDate());
        assertEquals(createTicketRequest.getLocation(), actualTicket.getLocation());
    }

    @Test
    void assignTicket() {
        Expert expert1 = new Expert("111-111-1111", "Seattle", 1);
        Expert expert2 = new Expert("222-222-2222", "Seattle", 2);
        Expert expert3 = new Expert("333-333-3333", "test2141", 1);
        Expert expert4 = new Expert("444-444-4444", "Seattle", 1);
        expert4.setAvailable(false);
        Expert expert5 = new Expert("555-555-5555", "Portland", 2);
        expertDao.save(expert1);
        expertDao.save(expert2);
        expertDao.save(expert3);
        expertDao.save(expert4);
        expertDao.save(expert5);

        CreateTicketRequest createTicketRequest = new CreateTicketRequest();
        createTicketRequest.setCreatedDate(new Date());
        createTicketRequest.setDescription("test222");
        createTicketRequest.setLocation("test2141");
        createTicketRequest.setCustomerId(43957L);
        // call the function
        String ticketId = ticketService.createTicket(createTicketRequest);

        ticketService.assignTicket(Long.parseLong(ticketId));

        Ticket actualTicket = ticketDao.findTicketByTicketId(Long.parseLong(ticketId));

        assertEquals(Ticket.TicketStatus.ASSIGNED, actualTicket.getStatus());
    }

    @Test
    void assignTicket_fail_nonAvailable() {
        Expert expert1 = new Expert("111-111-1111", "Seattle", 1);
        Expert expert2 = new Expert("222-222-2222", "Seattle", 2);
        Expert expert3 = new Expert("333-333-3333", "Seattle", 1);
        Expert expert4 = new Expert("444-444-4444", "test2141", 1);
        expert4.setAvailable(false);
        Expert expert5 = new Expert("555-555-5555", "Portland", 2);
        expertDao.save(expert1);
        expertDao.save(expert2);
        expertDao.save(expert3);
        expertDao.save(expert4);
        expertDao.save(expert5);

        CreateTicketRequest createTicketRequest = new CreateTicketRequest();
        createTicketRequest.setCreatedDate(new Date());
        createTicketRequest.setDescription("test222");
        createTicketRequest.setLocation("test2141");
        createTicketRequest.setCustomerId(43957L);
        // call the function
        String ticketId = ticketService.createTicket(createTicketRequest);

        // assert exception will throw
        assertThrows(RuntimeException.class, () -> {
            ticketService.assignTicket(Long.parseLong(ticketId));
        });

    }

    @Test
    void updateTicketStatus() {
        CreateTicketRequest createTicketRequest = new CreateTicketRequest();
        createTicketRequest.setCreatedDate(new Date());
        createTicketRequest.setDescription("test222");
        createTicketRequest.setLocation("test2141");
        createTicketRequest.setCustomerId(43957L);
        // call the function
        String ticketId = ticketService.createTicket(createTicketRequest);

        ticketService.updateTicketStatus(Long.parseLong(ticketId), Ticket.TicketStatus.IN_PROGRESS);

        Ticket actualTicket = ticketDao.findTicketByTicketId(Long.parseLong(ticketId));

        assertEquals(Ticket.TicketStatus.IN_PROGRESS, actualTicket.getStatus());
    }
}