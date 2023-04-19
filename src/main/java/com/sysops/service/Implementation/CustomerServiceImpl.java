package com.sysops.service.Implementation;

import com.sysops.dao.CustomerDao;
import com.sysops.entity.Customer;
import com.sysops.entity.Ticket;
import com.sysops.exceptions.CustomerNotFoundException;
import com.sysops.service.CustomerService;
import com.sysops.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of CustomerService
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;
    private final TicketService ticketService;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, TicketService ticketService) {
        this.customerDao = customerDao;
        this.ticketService = ticketService;
    }

    @Override
    public Ticket createTicketForCustomer(Ticket ticket, Long customerId) {
        Customer customer = getCustomerById(customerId);
        ticket.setCustomer(customer);
        customer.addTicket(ticket);
        return ticketService.createTicket(ticket);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerDao.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
    }

    @Override
    public Customer enroll(Customer customer) {
        return customerDao.save(customer);

    }

    @Override
    public Customer updatePhoneNumber(Long customerId, String newPhoneNumber) {
        Customer customer = getCustomerById(customerId);
        customer.setPhoneNumber(newPhoneNumber);
        return customerDao.save(customer);
    }

    @Override
    public Customer updateEmail(Long customerId, String newEmail) {
        Customer customer = getCustomerById(customerId);
        customer.setEmail(newEmail);
        return customerDao.save(customer);
    }

    @Override
    public Customer updatePreference(Long customerId, boolean preferEmail) {
        Customer customer = getCustomerById(customerId);
        customer.setPreferEmail(preferEmail);
        return customerDao.save(customer);
    }

    @Override
    public void deregister(Long customerId) {
        Customer customer = getCustomerById(customerId);
        customerDao.delete(customer);
    }

    @Override
    public String getNumberByCustomerId(Long customerId) {
        return getCustomerById(customerId).getPhoneNumber();
    }

    @Override
    public String getEmailByCustomerId(Long customerId) {
        return getCustomerById(customerId).getEmail();
    }
}
