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

		Book b = em.find(Book.class, 2L);
		
		Author[] authors = b.getAuthors().toArray(new Author[3]);
		Assert.assertEquals("Bauer", authors[0].getLastName());
		Assert.assertEquals("Gregory", authors[1].getLastName());
		Assert.assertEquals("King", authors[2].getLastName());
		for (Author a : authors) {
			log.info(a.getLastName() + ", id: " + a.getId());
		}
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void fetchBooksAndAuthors() {
		log.info("... fetchBooksAndAuthors ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.createQuery("SELECT b FROM Book b JOIN FETCH b.authors a WHERE b.id = 2", Book.class).getSingleResult();
		Author[] authors = b.getAuthors().toArray(new Author[3]);
		Assert.assertEquals("Bauer", authors[0].getLastName());
		Assert.assertEquals("Gregory", authors[1].getLastName());
		Assert.assertEquals("King", authors[2].getLastName());
		for (Author a : authors) {
			log.info(a.getLastName() + ", id: " + a.getId());
		}
		
		em.getTransaction().commit();
		em.close();
	}
}
