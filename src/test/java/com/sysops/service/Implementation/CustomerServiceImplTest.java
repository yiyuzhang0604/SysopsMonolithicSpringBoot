package com.sysops.service.Implementation;

import com.sysops.dao.CustomerDao;
import com.sysops.entity.Customer;
import com.sysops.entity.Ticket;
import com.sysops.exceptions.CustomerNotFoundException;
import com.sysops.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerDao customerDao;
    @Mock
    private TicketService ticketService;
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerDao, ticketService);
    }

    @Test
    public void createTicketForCustomerTest() {
        Customer customer = new Customer("123-456-7890", "email", false);
        Ticket ticket = new Ticket(1, customer, "Description", "Seattle");
        Long customerId = 1L;

        when(customerDao.findById(customerId)).thenReturn(Optional.of(customer));
        when(ticketService.createTicket(ticket)).thenReturn(ticket);

        Ticket createdTicket = customerService.createTicketForCustomer(ticket, customerId);

        assertEquals(ticket, createdTicket);
        verify(customerDao, times(1)).findById(customerId);
        verify(ticketService, times(1)).createTicket(ticket);
        verifyNoMoreInteractions(customerDao, ticketService);
    }

    @Test
    public void getCustomerById_success() {
        Customer customer = new Customer("123-456-7890", "email", false);
        Long customerId = 1L;

        when(customerDao.findById(customerId)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerById(customerId);

        assertEquals(customer, result);
        verify(customerDao, times(1)).findById(customerId);
        verifyNoMoreInteractions(customerDao);
    }

    @Test
    public void getCustomerById_CustomerNotFoundException() {
        Long customerId = 1L;

        when(customerDao.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(customerId));
        verify(customerDao, times(1)).findById(customerId);
        verifyNoMoreInteractions(customerDao);
    }

    @Test
    public void enrollTest() {
        Customer customer = new Customer("123-456-7890", "email", false);

        when(customerDao.save(customer)).thenReturn(customer);

        Customer savedCustomer = customerService.enroll(customer);

        assertEquals(customer, savedCustomer);
        verify(customerDao, times(1)).save(customer);
        verifyNoMoreInteractions(customerDao);
    }

    @Test
    public void updatePhoneNumberTest() {
        String oldNumber = "123-456-7890";
        String newNumber = "111-111-1111";
        Customer originalCustomer = new Customer(oldNumber, "email", false);
        Customer updatedCustomer = new Customer(newNumber, "email", false);
        Long customerId = 1L;

        when(customerDao.findById(customerId)).thenReturn(Optional.of(originalCustomer));
        when(customerDao.save(originalCustomer)).thenReturn(updatedCustomer);

        Customer result = customerService.updatePhoneNumber(customerId, newNumber);

        assertEquals(newNumber, result.getPhoneNumber());
        verify(customerDao, times(1)).findById(customerId);
        verify(customerDao, times(1)).save(originalCustomer);
        verifyNoMoreInteractions(customerDao);
    }

    @Test
    public void updateEmailTest() {
        String oldEmail = "old email";
        String newEmail = "new email";
        Customer originalCustomer = new Customer("111-111-1111", oldEmail, false);
        Customer updatedCustomer = new Customer("111-111-1111", newEmail, false);
        Long customerId = 1L;

        when(customerDao.findById(customerId)).thenReturn(Optional.of(originalCustomer));
        when(customerDao.save(originalCustomer)).thenReturn(updatedCustomer);

        Customer result = customerService.updateEmail(customerId, newEmail);

        assertEquals(newEmail, result.getEmail());
        verify(customerDao, times(1)).findById(customerId);
        verify(customerDao, times(1)).save(originalCustomer);
        verifyNoMoreInteractions(customerDao);
    }

    @Test
    public void updatePreferenceTest() {
        boolean oldPreference = false;
        boolean newPreference = true;
        Customer originalCustomer = new Customer("111-111-1111", "email", oldPreference);
        Customer updatedCustomer = new Customer("111-111-1111", "email", newPreference);
        Long customerId = 1L;

        when(customerDao.findById(customerId)).thenReturn(Optional.of(originalCustomer));
        when(customerDao.save(originalCustomer)).thenReturn(updatedCustomer);

        Customer result = customerService.updatePreference(customerId, newPreference);

        assertEquals(newPreference, result.isPreferEmail());
        verify(customerDao, times(1)).findById(customerId);
        verify(customerDao, times(1)).save(originalCustomer);
        verifyNoMoreInteractions(customerDao);
    }

    @Test
    public void deregisterTest() {
        Long customerId = 1L;
        Customer customer = new Customer("123-456-7890", "email", false);

        when(customerDao.findById(customerId)).thenReturn(Optional.of(customer));

        customerService.deregister(customerId);

        verify(customerDao, times(1)).findById(customerId);
        verify(customerDao, times(1)).delete(customer);
        verifyNoMoreInteractions(customerDao);
    }

    @Test
    public void getNumberByCustomerIdTest() {
        Long customerId = 1L;
        String phoneNumber = "123-456-7890";
        Customer customer = new Customer(phoneNumber, "email", false);

        when(customerDao.findById(customerId)).thenReturn(Optional.of(customer));

        String result = customerService.getNumberByCustomerId(customerId);

        assertEquals(phoneNumber, result);
        verify(customerDao, times(1)).findById(customerId);
        verifyNoMoreInteractions(customerDao);
    }

    @Test
    public void getEmailByCustomerIdTest() {
        Long customerId = 1L;
        String email = "email";
        Customer customer = new Customer("123-456-7890", email, false);

        when(customerDao.findById(customerId)).thenReturn(Optional.of(customer));

        String result = customerService.getEmailByCustomerId(customerId);

        assertEquals(email, result);
        verify(customerDao, times(1)).findById(customerId);
        verifyNoMoreInteractions(customerDao);
    }

}
