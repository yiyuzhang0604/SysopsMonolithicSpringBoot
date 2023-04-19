package com.sysops.entity;

import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Sysops Squad Expert.
 */
@Entity
@Table(name = "expert")
public class Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long expertId;

    @NotNull
    @Column(nullable = false, name = "phoneNumber")
    private String phoneNumber;

    @NotNull
    @Column(nullable = false, name = "location")
    private String location;

    @Column(nullable = false, name = "isAvailable")
    private boolean isAvailable;

    @ElementCollection
    private List<Integer> categories;

    @OneToMany(mappedBy = "expert")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "author")
    private List<Article> articles;

    public Expert(String phoneNumber, String location) {
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.isAvailable = true;
        this.categories = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.articles = new ArrayList<>();
    }

    public Expert() {
    }

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

    public List<Integer> getCategories() {
        return categories;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void addArticle(Article article) {
        if (article != null) {
            articles.add(article);
            article.setAuthor(this);
        }
    }

    public void addCategory(Integer categoryId) {
        if (categoryId != null) {
            categories.add(categoryId);
        }
    }

    public void removeArticle(Article article) {
        if (article != null) {
            articles.remove(article);
            article.setAuthor(null);
        }
    }

    public void addTicket(Ticket ticket) {
        if (ticket != null) {
            tickets.add(ticket);
            ticket.setExpert(this);
        }
    }

    public void removeTicket(Ticket ticket) {
        if (ticket != null) {
            tickets.remove(ticket);
            ticket.setExpert(null);
        }
    }

    @Override
    public String toString() {
        return "Expert{" +
                "expertId=" + expertId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", location='" + location + '\'' +
                ", isAvailable=" + isAvailable +
                ", categories=" + categories +
                ", tickets=" + tickets +
                '}';
    }
}
