package com.thorben.janssen.talk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thorben.janssen.talk.model.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestMapOptionalAssociation {

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
	public void testOptional() {
		log.info("... testOptional ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);
		Assert.assertTrue(b.getPublisher().isPresent());
		log.info(b.getTitle() + " was published by " + b.getPublisher().get().getName());
		
		b = em.find(Book.class, 2L);
		Assert.assertFalse(b.getPublisher().isPresent());
		log.info(b.getTitle() + " has no publisher");

		em.getTransaction().commit();
		em.close();
	}
}
