package com.thorben.janssen.talk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thorben.janssen.talk.model.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestEnvers {

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
    public void testUpdate() {
        log.info("... testUpdate ...");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Book b = new Book();
        b.setTitle("Hibernate Tips");
        em.persist(b);

        em.getTransaction().commit();
        em.close();
        em = emf.createEntityManager();
        em.getTransaction().begin();

        b = em.find(Book.class, b.getId());
        b.setTitle("Hibernate Tips - More than 70 solutions for common Hibernate problems");

        em.getTransaction().commit();
        em.close();
    }
}
