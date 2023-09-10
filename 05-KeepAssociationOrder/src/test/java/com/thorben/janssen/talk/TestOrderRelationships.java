package com.thorben.janssen.talk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thorben.janssen.talk.model.Author;
import com.thorben.janssen.talk.model.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestOrderRelationships {

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
	public void orderAuthors() {
		log.info("... orderAuthors ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = new Book();
		b.setTitle("My Book");
		em.persist(b);

		Author a1 = new Author();
		a1.setFirstName("Author 1");
		em.persist(a1);

		Author a2 = new Author();
		a2.setFirstName("Author 2");
		em.persist(a2);

		a2.getBooks().add(b);
		b.getAuthors().add(a2);
		a1.getBooks().add(b);
		b.getAuthors().add(a1);

		em.getTransaction().commit();
		em.close();

		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		b = em.find(Book.class, b.getId());
		
		Author[] authors = b.getAuthors().toArray(new Author[2]);
		Assert.assertEquals("Author 2", authors[0].getFirstName());
		Assert.assertEquals("Author 1", authors[1].getFirstName());
		for (Author a : authors) {
			log.info(a.getLastName() + ", id: " + a.getId());
		}
		
		em.getTransaction().commit();
		em.close();
	}
	
}
