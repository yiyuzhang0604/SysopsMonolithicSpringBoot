package com.sysops.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Calendar;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articleId;

    @ManyToOne//article to expert - many to one
    @JoinColumn(name = "expert_expertId")
    private Expert expert;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne//expert to category - many to one
    @JoinColumn(name = "category_categoryId")
    private Category category;

    //keyword?
    @Column(name = "createdDate")
    private Date createdDate;

    public Article(Long articleId, Expert expert, String title, String content, Category category) {
        this.articleId = articleId;
        this.expert = expert;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdDate = Calendar.getInstance().getTime();
    }

    public Article(){}

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", expert=" + expert +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", category=" + category +
                ", createdDate=" + createdDate +
                '}';
    }
}
