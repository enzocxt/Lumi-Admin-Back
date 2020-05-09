package com.tao.lumiadmin.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "book")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @ManyToOne
    @JoinColumn(name="cid")
    private Category category;

    String abbr;
    String title;
    String author;
    // String date;
    // String press;
    @Column(name="abstract")
    String abs;

    public Category getCategory() {
        return category;
}

    public void setCategory(Category category) {
        this.category = category;
    }

    // public String getDate() {
    //     return date;
    // }

    // public void setDate(String date) {
    //     this.date = date;
    // }

    // public String getPress() {
    //     return press;
    // }

    // public void setPress(String press) {
    //     this.press = press;
    // }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
