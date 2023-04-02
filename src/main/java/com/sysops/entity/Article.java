package com.sysops.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Calendar;

/**
 * Represents an article in the knowledge base.
 */
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articleId;

    @ManyToOne
    @JoinColumn(name = "expert_id", nullable = false)
    private Expert author;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull
    @Column(name = "categoryId", nullable = false)
    private Integer categoryId;

    @Column(name = "createdDate", nullable = false)
    private Date createdDate;

    public Article(Expert author, String title, String content, Integer categoryId) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.createdDate = Calendar.getInstance().getTime();
    }

    public Article() {
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Expert getAuthor() {
        return author;
    }

    public void setAuthor(Expert author) {
        this.author = author;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", expert=" + author +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", categoryId=" + categoryId +
                ", createdDate=" + createdDate +
                '}';
    }
}
