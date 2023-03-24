package com.sysops.entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;

    @Column(name = "createdDate")
    private Date createdDate;

//    @ManyToOne//expert to category - many to one
//    @JoinColumn(name = "category_categoryId")
//    private Category category;
    @Column(name = "categoryId")
    private Integer categoryId;

    @ManyToOne//ticket to expert - many to one
    @JoinColumn(name = "expert_id")
    private Expert expert;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @Column(nullable = false, name = "resolved")
    private boolean resolved;

    @Column(name = "description")
    private String description;

    @Column(nullable = false, name = "location")
    private String location;

    public Ticket(Long ticketId, Ticket ticket, Integer categoryId, Expert expert, boolean resolved, String description, String location) {
        this.ticketId = ticketId;
        this.createdDate = Calendar.getInstance().getTime();
        this.categoryId = categoryId;
        this.expert = expert;
        this.resolved = resolved;
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


    public Integer getCategory() {
        return categoryId;
    }

    public void setCategory(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
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

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", createdDate=" + createdDate +
                ", categoryId=" + categoryId +
                ", expert=" + expert +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

