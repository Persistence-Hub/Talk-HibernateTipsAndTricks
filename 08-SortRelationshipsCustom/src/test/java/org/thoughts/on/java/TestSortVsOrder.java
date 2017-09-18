package org.thoughts.on.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughts.on.java.model.Author;
import org.thoughts.on.java.model.Book;

public class TestSortVsOrder {

    Logger log = Logger.getLogger(this.getClass().getName());

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
    public void sortBooks() {
        log.info("... testPersist ...");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Author> q = em.createQuery(
                "SELECT a FROM Author a JOIN FETCH a.books b WHERE a.id = :id",
                Author.class);
        q.setParameter("id", 1L);
        Author a = q.getSingleResult();

        log.info(a);
        for (Book b : a.getBooks()) {
            log.info(b);
        }

        em.getTransaction().commit();
        em.close();
    }
}
