package com.distribuida.config;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

@ApplicationScoped
public class JpaConfig {

    private EntityManagerFactory emf;

    @Produces
    public EntityManager getEntityManager(){
        return this.emf.createEntityManager();
    }
    @PostConstruct
    public void setEntityManager(){
        emf =  Persistence.createEntityManagerFactory("evaluacion");
    }

}
