package com.sysops.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "expert")
public class Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long expertId;

    @Column(nullable = false, name = "phoneNumber")
    private String phoneNumber;

    @Column(nullable = false, name = "location")
    private String location;

    @Column(nullable = false, name = "isAvailable")
    private boolean isAvailable;

    @ManyToOne//expert to category - many to one
    @JoinColumn(name = "category_categoryId")
    private Category category;

    @OneToMany(mappedBy = "category")//expert to ticket - one to many
    private List<Ticket> tickets;

    public Expert(Long expertId, String phoneNumber, String location, boolean isAvailable, Category category, List<Ticket> tickets) {
        this.expertId = expertId;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.isAvailable = isAvailable;
        this.category = category;
        this.tickets = tickets;
    }

    public Expert(){}

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Expert{" +
                "expertId=" + expertId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", location='" + location + '\'' +
                ", isAvailable=" + isAvailable +
                ", category=" + category +
                ", tickets=" + tickets +
                '}';
    }
}
