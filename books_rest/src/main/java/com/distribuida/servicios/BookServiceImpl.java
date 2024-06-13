package com.distribuida.servicios;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;


import io.helidon.webserver.Routing;


import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements IBookService{

    @Inject
    EntityManager em;

    @Override
    public Book findById(Integer id) {
        try {
            Book book = em.find(Book.class, id);
            if(book!=null){
                return book;
            }else{
                throw new RuntimeException("No se pudo encontrar el libro");
            }
        }catch (Exception ex){
            throw ex;
        }

    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from Book b order by b.id asc",  Book.class).getResultList();
    }

    @Override
    public void insert(Book book) {
        var tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(book);
            tx.commit();
        }catch (Exception ex){
            tx.rollback();
        }
    }

    @Override
    public void update(Book book) {
        var tx = em.getTransaction();
        try{
            tx.begin();

            Book bookActual = em.find(Book.class, book.getId());
            if(bookActual!=null){
                em.merge(book);
                tx.commit();
            }else{
                tx.rollback();
                throw new RuntimeException("No se pudo actualizar el libro");
            }
        }catch (Exception ex){
            tx.rollback();
            throw ex;
        }
    }

    @Override
    public void remove(Integer id) {
        var tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.find(Book.class, id));
            tx.commit();
        }catch (Exception ex){
            tx.rollback();
            throw ex;
        }
    }

    @Override
    public String helloWorld() {
        return "Hello world";
    }
}
