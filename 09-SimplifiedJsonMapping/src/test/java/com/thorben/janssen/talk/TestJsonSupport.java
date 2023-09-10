package com.thorben.janssen.talk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thorben.janssen.talk.model.Address;
import com.thorben.janssen.talk.model.Author;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJsonSupport {

	Logger log = LogManager.getLogger(this.getClass().getName());

    private EntityManagerFactory emf;

    @Before
    public void init() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    @After
    public void close() {
        emf.close();
    }

    @Test
    public void testJsonSupport() {
        log.info("... testJsonSupport ...");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Author a = new Author();
        a.setFirstName("Thorben");
        a.setLastName("Janssen");
        a.setAddress(new Address("Dorfstrasse 13", "MainTown", "12345"));

        log.info("Persist new Author entity.");
        em.persist(a);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        a = em.createQuery("SELECT a FROM Author a WHERE a.address.street=:street", Author.class)
              .setParameter("street", "Dorfstrasse 13")
              .getSingleResult();
        log.info(a);

        em.getTransaction().commit();
        em.close();
    }
}
