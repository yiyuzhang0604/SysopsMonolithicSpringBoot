package com.sysops.service;

import com.sysops.entity.Customer;
import com.sysops.entity.Ticket;

/**
 * CustomerService is responsible for managing customer-related operations.
 * It provides an interface to create, fetch, update, and delete customers.
 */
public interface CustomerService {
    /**
     * Create a new ticket for customer
     *
     * @param ticket     The ticket object containing ticket's information
     * @param customerId ID of the customer
     * @return The created ticket object
     */
    Ticket createTicketForCustomer(Ticket ticket, Long customerId);

    /**
     * Create a new customer with given info
     *
     * @param customer The customer object containing customer's info
     * @return The created customer object
     */
    Customer enroll(Customer customer);

    /**
     * Get the customer object by its id
     *
     * @param customerId ID of the customer
     * @return The fetched customer object
     */
    Customer getCustomerById(Long customerId);

    /**
     * Update the phone number of the customer
     *
     * @param customerId     ID of the customer
     * @param newPhoneNumber New phone number to update
     * @return The updated customer object
     */
    Customer updatePhoneNumber(Long customerId, String newPhoneNumber);

    /**
     * Update the email of the customer
     *
     * @param customerId ID of the customer
     * @param newEmail   new email to update
     * @return The updated customer object
     */
    Customer updateEmail(Long customerId, String newEmail);

    /**
     * Update the preference of the customer
     *
     * @param customerId  ID of the customer
     * @param preferEmail preference to update
     * @return The updated customer object
     */
    Customer updatePreference(Long customerId, boolean preferEmail);

    /**
     * Deregister the user. Delete the user with the given customer Id.
     *
     * @param customerId ID of the customer
     */
    void deregister(Long customerId);

    /**
     * Get the phone number of the customer
     *
     * @param customerId ID of the customer
     * @return The phone number as a string
     */
    String getNumberByCustomerId(Long customerId);

    /**
     * Get the email address of the customer
     *
     * @param customerId ID of the customer
     * @return The email address as a string
     */
    String getEmailByCustomerId(Long customerId);
}
