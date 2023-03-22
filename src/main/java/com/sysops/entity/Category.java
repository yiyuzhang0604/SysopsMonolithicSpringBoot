package com.sysops.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @Column(nullable = false, name = "categoryName")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Expert> experts;

    @OneToMany(mappedBy = "category")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;

    public Category(Long categoryId, String categoryName, List<Expert> experts, List<Ticket> tickets, List<Article> articles) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.experts = experts;
        this.tickets = tickets;
        this.articles = articles;
    }

    //https://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor
    public Category(){}

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Expert> getExperts() {
        return experts;
    }

    public void setExperts(List<Expert> experts) {
        this.experts = experts;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", experts=" + experts +
                ", tickets=" + tickets +
                ", articles=" + articles +
                '}';
    }
}
