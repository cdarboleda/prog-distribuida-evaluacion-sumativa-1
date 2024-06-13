package com.distribuida.db;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    private Integer id;

    private String isbn;

    private String title;

    private Float price;

    public Integer getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Float getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
