package com.example.blog;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blogposts")

public class BlogPost {
    private Long id;
    @Id
    private String title;
    private String content;
    private String author;


    //Constructor
    public BlogPost(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    //ToString
    @Override
    public String toString() {
        return "BlogPost [id=" + id + ", title=" + title + ", content=" + content + "]";
    }


    public String getAuthor() {
        return author;
    }
}
