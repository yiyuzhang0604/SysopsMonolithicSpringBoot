package com.sysops.service.Implementation;

import com.sysops.dao.CustomerDao;
import com.sysops.dao.ExpertDao;
import com.sysops.dao.TicketDao;
import com.sysops.entity.Expert;
import com.sysops.entity.Ticket;
import com.sysops.exceptions.TicketNotFoundException;
import com.sysops.service.TicketService;
import com.sysops.service.TicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TicketServiceInterfaceImplementation implements TicketServiceInterface {
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ExpertServiceImpl expertService;

    @Override
    public Ticket createTicket(Ticket ticket) {
//        if(customerDao.existsById(ticket.getCustomer().getCustomerId())){
//            throw new CustomerNotFoundException("Customer not found with the ticket");
//        }
        return ticketDao.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long ticketId) {
//        if(ticketId == null){
//            throw new IllegalArgumentException("Ticket Id must not be null");
//        }//controller
        return ticketDao.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found with Id: " + ticketId));
    }

    @Override
    public Ticket assignTicket(Long expertId, Long ticketId) {
//        if(ticketId == null){
//            throw new IllegalArgumentException("Ticket Id must not be null");
//        }//controller
//        if(expertId == null){
//            throw new IllegalArgumentException("Expert Id must not be null");
//        }//controller
        Ticket ticket = getTicketById(ticketId);
        Expert expert = expertService.getExpertById(expertId);
        ticket.setExpert(expert);
        expert.addTicket(ticket);
        return ticketDao.save(ticket);
    }

    @Override
    public Ticket resolveTicket(Long ticketId) {
        if (ticketId == null) {
            throw new IllegalArgumentException("Ticket Id must not be null");
        }
        Ticket ticket = getTicketById(ticketId);
//        ticket.setResolved(true);
        return ticketDao.save(ticket);
    }
}


//package com.sysops.service.Implementation;
//
//        import com.sysops.controller.request.CreateTicketRequest;
//        import com.sysops.dao.CustomerDao;
//        import com.sysops.dao.ExpertDao;
//        import com.sysops.dao.TicketDao;
//        import com.sysops.entity.Customer;
//        import com.sysops.entity.Expert;
//        import com.sysops.entity.Ticket;
//        import com.sysops.exceptions.TicketNotFoundException;
//        import com.sysops.service.TicketService;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.stereotype.Component;
//        import org.springframework.stereotype.Service;
//
//        import java.util.Comparator;
//        import java.util.List;

//@Component
//public class TicketServiceImpl implements TicketService {
//    @Autowired
//    private TicketDao ticketDao;
//    @Autowired
//    private ExpertDao expertDao;
//    @Autowired
//    private CustomerDao customerDao;
//    @Autowired
//    private ExpertServiceImpl expertService;
//
//    @Override
//    public Ticket createTicket(Ticket ticket) {
//        return ticketDao.save(ticket);
//    }
//
//    @Override
//    public void assignTicket(Long ticketId) {
//        Ticket ticket = getTicketById(ticketId);
//
//        List<Expert> experts = expertDao.findAvailableExperts(ticket.getLocation());
//        experts.sort(Comparator.comparingInt(e -> e.getTickets().size()));
//        if (experts.isEmpty()) {
//            throw new RuntimeException("No expert available");
//        }
//        // TODO: check for race condition here
//        Expert expert = experts.get(0);
//        ticket.setExpert(expert);
//        ticket.setStatus(Ticket.TicketStatus.ASSIGNED);
//        ticketDao.save(ticket);
//    }
//
//    @Override
//    public void updateTicketStatus(Long ticketId, Ticket.TicketStatus status) {
//        Ticket ticket = getTicketById(ticketId);
//        ticket.setStatus(status);
//        ticketDao.save(ticket);
//    }
//
//    @Override
//    public Ticket getTicketById(Long ticketId) {
//        return ticketDao.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found with Id: " + ticketId));
//    }
//}
