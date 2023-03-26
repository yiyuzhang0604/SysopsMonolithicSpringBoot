package com.sysops.entity;

import javax.persistence.*;
import java.util.ArrayList;
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

//    @ManyToOne//expert to category - many to one
//    @JoinColumn(name = "category_categoryId")
//    private Category category;

    @Column
    private Integer categoryId;

    @OneToMany(mappedBy = "expert")//expert to ticket - one to many
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "expert")//expert to ticket - one to many
    private List<Article> articles;

    public Expert(String phoneNumber, String location,Integer categoryId) {
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.isAvailable = true;
        this.categoryId = categoryId;
        this.tickets = new ArrayList<>();
        this.articles = new ArrayList<>();
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void addArticle(Article article){
        if(article != null){
            articles.add(article);
            article.setExpert(this);
        }
    }

    public void removeArticle(Article article){
        if(article != null){
            articles.remove(article);
            article.setExpert(null);
        }
    }

    public void addTicket(Ticket ticket){
        if(ticket != null){
            tickets.add(ticket);
            ticket.setExpert(this);
        }
    }

    public void removeTicket(Ticket ticket){
        if(ticket != null){
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
                ", categoryId=" + categoryId +
                ", tickets=" + tickets +
                '}';
    }
}
