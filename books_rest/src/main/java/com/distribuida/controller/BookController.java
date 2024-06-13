package com.distribuida.controller;

import com.distribuida.db.Book;
import com.distribuida.servicios.BookServiceImpl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
//import io.helidon.webserver.json.JsonSupport;
import jakarta.inject.Named;

import org.eclipse.persistence.oxm.json.JsonArrayBuilderResult;
import org.eclipse.persistence.oxm.json.JsonObjectBuilderResult;

import java.net.http.HttpResponse;


//import javax.json.JsonObject;
//import javax.json.JsonObjectBuilder;

@Named
@ApplicationScoped

public class BookController {

    @Inject
    BookServiceImpl bookService;

    private static final ObjectMapper mapper = new ObjectMapper();

    public void helloWorld(ServerRequest request,
                            ServerResponse response) throws JsonProcessingException {

        String msg = mapper.writeValueAsString(this.bookService.helloWorld());
        response.send(msg);
    }

    public void insert(ServerRequest request,
                           ServerResponse response) throws JsonProcessingException {

        try {
            Book book = mapper.readValue(request.content().as(String.class), Book.class);
            this.bookService.insert(book);
            response.status(200).send("Se ha insertado con éxito");
        }catch (Exception e){
            response.status(404).send("No se pudo insertar el libro");
        }

    }

    public void findAll(ServerRequest request,
                       ServerResponse response) throws JsonProcessingException {
        try {
            String jsonArray = mapper.writeValueAsString(this.bookService.findAll());
            response.status(200).send(jsonArray);
        }catch (Exception e){
            response.status(404).send("No se pudo realizar la búsqueda");
        }

    }

    public void findById(ServerRequest request,
                       ServerResponse response) throws JsonProcessingException {
        try {
            Integer id = Integer.valueOf(request.path().pathParameters().get("id"));
            String json = mapper.writeValueAsString(this.bookService.findById(id));
            response.status(200).send(json);
        }catch (Exception e){
            response.status(404).send("No se pudo encontrar el libro");
        }

    }

    public void update(ServerRequest request,
                        ServerResponse response) throws JsonProcessingException {

        try {
            Integer id = Integer.valueOf(request.path().pathParameters().get("id"));
            Book book = mapper.readValue(request.content().as(String.class), Book.class);
            book.setId(id);
            this.bookService.update(book);
            response.status(200).send("Libro actualizado");
        }catch (Exception e){
            response.status(404).send("No se pudo actualizar el libro");
        }

    }

    public void remove(ServerRequest request,
                         ServerResponse response) throws JsonProcessingException {

        try {
            Integer id = Integer.valueOf(request.path().pathParameters().get("id"));
            String json = mapper.writeValueAsString(this.bookService.findById(id));
            this.bookService.remove(id);
            response.status(200).send("Se ha eliminado el libro: " + json);
        }catch (Exception e){
            response.status(404).send("No se pudo eliminar el libro");
        }


    }
}

