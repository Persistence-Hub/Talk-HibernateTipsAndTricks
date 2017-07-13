package org.thoughts.on.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughts.on.java.model.Book;

public class TestEnvers {

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
