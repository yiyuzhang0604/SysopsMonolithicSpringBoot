package com.sysops.entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "ticket")
public class Ticket {

    // ticket status enum
    public enum TicketStatus {
        OPEN, ASSIGNED, IN_PROGRESS, RESOLVED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;

    @Column(name = "createdDate")
    private Date createdDate;

//    @ManyToOne//expert to category - many to one
//    @JoinColumn(name = "category_categoryId")
//    private Category category;
//    @Column(name = "categoryId")
//    private Integer categoryId;

    @ManyToOne//ticket to expert - many to one
    @JoinColumn(name = "expert_id")
    private Expert expert;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @Column(name = "description")
    private String description;

    @Column(nullable = false, name = "location")
    private String location;

    @Column(nullable = false, name = "status")
    private TicketStatus status;

    public Ticket(Expert expert, Customer customer, String description, String location) {
        this.createdDate = Calendar.getInstance().getTime();
//        this.categoryId = categoryId;
        this.expert = expert;
        this.customer = customer;
        this.status = TicketStatus.OPEN;
        this.description = description;
        this.location = location;
    }

    public Ticket(){}

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    public Integer getCategoryId() {
//        return categoryId;
//    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    public void setCategoryId(Integer categoryId) {
//        this.categoryId = categoryId;
//    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", createdDate=" + createdDate +
//                ", categoryId=" + categoryId +
                ", expert=" + expert +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", status=" + status.name() +
                '}';
    }
}

