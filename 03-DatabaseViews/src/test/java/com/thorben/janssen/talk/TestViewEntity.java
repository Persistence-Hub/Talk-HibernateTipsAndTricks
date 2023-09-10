package com.thorben.janssen.talk;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thorben.janssen.talk.model.BookView;

public class TestViewEntity {

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
	public void selectFromView() {
		log.info("... selectFromView ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<BookView> bvs = em.createQuery("SELECT v FROM BookView v", BookView.class)
				.getResultList();

		for (BookView bv : bvs) {
			log.info(bv.getTitle() + " was written by "+bv.getAuthors());
		}
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void updateView() {
	    log.info("... updateView ...");

	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();

	    BookView bv = em.find(BookView.class, 1L);
	    log.info(bv);
	    // bv.setTitle("updated");
	     
	    em.getTransaction().commit();
	    em.close();
	 
	    em = emf.createEntityManager();
		em.getTransaction().begin();
		
	    BookView bookupdate = em.find(BookView.class, 1L);
	    log.info(bookupdate);  
		
		em.getTransaction().commit();
		em.close();
	}
}
