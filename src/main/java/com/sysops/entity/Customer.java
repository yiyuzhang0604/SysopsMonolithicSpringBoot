package com.sysops.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    @Column(nullable = false, name = "phoneNumber")
    private String phoneNumber;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "preferEmail")
    private boolean preferEmail;//prefer email notification or text message

    @OneToMany(mappedBy = "customer")//customer to ticket - one to many
    private List<Ticket> tickets;

    public Customer(Long customerId, String phoneNumber, String email, boolean preferEmail, List<Ticket> tickets) {
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.preferEmail = preferEmail;
        this.tickets = tickets;
    }

    public Customer(){}

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPreferEmail() {
        return preferEmail;
    }

    public void setPreferEmail(boolean preferEmail) {
        this.preferEmail = preferEmail;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", preferEmail=" + preferEmail +
                ", tickets=" + tickets +
                '}';
    }
}
