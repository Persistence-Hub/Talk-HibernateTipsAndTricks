package com.thorben.janssen.talk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thorben.janssen.talk.model.Address;
import com.thorben.janssen.talk.model.Author;
import com.thorben.janssen.talk.model.AuthorId;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestPrimaryKeyRecord {

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
    public void testPrimaryKeyRecord() {
        log.info("... testPrimaryKeyRecord ...");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Author a = new Author();
        a.setId(new AuthorId("Author-42"));
        a.setFirstName("Thorben");
        a.setLastName("Janssen");
        a.setAddress(new Address("Dorfstrasse 13", "MainTown", "12345"));

        log.info("Persist new Author entity.");
        em.persist(a);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        a = em.find(Author.class, a.getId());
        log.info(a);

        em.getTransaction().commit();
        em.close();
    }
}
