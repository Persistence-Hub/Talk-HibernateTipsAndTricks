package com.thorben.janssen.talk;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thorben.janssen.talk.model.Author;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestUUIDPrimaryKey {

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
    public void testUUIDPrimaryKeyV4() {
        log.info("... testUUIDPrimaryKeyV4 ...");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Author a = new Author();
        a.setFirstName("Thorben");
        a.setLastName("Janssen");

        log.info("Persist new Author entity.");
        em.persist(a);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        UUID uuid = a.getId();

        a = em.find(Author.class, uuid);
        Assert.assertEquals(uuid, a.getId());

        em.getTransaction().commit();
        em.close();
    }
}
