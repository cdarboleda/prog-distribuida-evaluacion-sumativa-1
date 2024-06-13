package com.distribuida;

import com.distribuida.controller.BookController;

import io.helidon.webserver.WebServer;

import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;

import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.spi.ContainerLifecycle;


public class Main {

    private static ContainerLifecycle lifecycle = null;

    public static void main(String... args) throws Exception {

        lifecycle = WebBeansContext.currentInstance().getService(ContainerLifecycle.class);
        lifecycle.startApplication(null);

        BeanManager beanManager = lifecycle.getBeanManager();
        Bean<?> bean = beanManager.getBeans("bookController").iterator().next();

        BookController bookController = (BookController) beanManager.getReference(bean, BookController.class, beanManager.createCreationalContext(bean));

        WebServer server = WebServer.builder()
                .port(9001)
                .routing(builder -> builder
                                .get("/books", bookController::findAll)
                                .get("/books/{id}", bookController::findById)
                                .post("/books", bookController::insert)
                                .put("/books/{id}", bookController::update)
                                .delete("/books/{id}", bookController::remove)

                                //.get("/hello", bookController::helloWorld)
                        )
                .build()
                .start();

    }

}