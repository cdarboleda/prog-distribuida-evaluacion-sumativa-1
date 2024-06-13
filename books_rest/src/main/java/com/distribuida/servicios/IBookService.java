package com.distribuida.servicios;

import com.distribuida.db.Book;

import java.util.List;

public interface IBookService {

    public Book findById(Integer id);
    public List<Book> findAll();
    public void insert(Book book);
    public void update(Book book);
    public void remove(Integer id);
    public String helloWorld();
}
