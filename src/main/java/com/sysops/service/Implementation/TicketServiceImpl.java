package com.sysops.service.Implementation;

import com.sysops.controller.request.CreateTicketRequest;
import com.sysops.dao.CustomerDao;
import com.sysops.dao.ExpertDao;
import com.sysops.dao.TicketDao;
import com.sysops.entity.Customer;
import com.sysops.entity.Expert;
import com.sysops.entity.Ticket;
import com.sysops.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Component
public class TicketServiceImpl implements TicketService {
        @Autowired
        private TicketDao ticketDao;
        @Autowired
        private ExpertDao expertDao;
        @Autowired
        private CustomerDao customerDao;

        @Override
        public String createTicket(CreateTicketRequest createTicketRequest) {
                Ticket ticket = new Ticket();
                ticket.setDescription(createTicketRequest.getDescription());
                ticket.setCreatedDate(createTicketRequest.getCreatedDate());
                ticket.setLocation(createTicketRequest.getLocation());
                ticket.setStatus(Ticket.TicketStatus.OPEN);
                Customer customer = customerDao.findCustomerByCustomerId(createTicketRequest.getCustomerId());
                ticket.setCustomer(customer);

                Ticket created = ticketDao.save(ticket);
                return created.getTicketId().toString();
        }

        @Override
        public void assignTicket(Long ticketId) {
                Ticket ticket = ticketDao.findTicketByTicketId(ticketId);

                List<Expert> experts = expertDao.findAvailableExperts(ticket.getLocation());
                experts.sort(Comparator.comparingInt(e -> e.getTickets().size()));
                if (experts.isEmpty()) {
                        throw new RuntimeException("No expert available");
                }
                // TODO: check for race condition here
                Expert expert = experts.get(0);
                ticket.setExpert(expert);
                ticket.setStatus(Ticket.TicketStatus.ASSIGNED);
                ticketDao.save(ticket);
        }

        @Override
        public void updateTicketStatus(Long ticketId, Ticket.TicketStatus status) {
                Ticket ticket = ticketDao.findTicketByTicketId(ticketId);
                ticket.setStatus(status);
                ticketDao.save(ticket);
        }
}
