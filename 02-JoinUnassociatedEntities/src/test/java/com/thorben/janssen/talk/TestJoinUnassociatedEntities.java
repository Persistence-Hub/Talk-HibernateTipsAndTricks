package com.thorben.janssen.talk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class TestJoinUnassociatedEntities {

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
	public void joinUnassociated() {
		log.info("... joinUnassociated ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query q = em.createQuery("SELECT b.title, count(r.id) FROM Book b INNER JOIN Review r ON r.fkBook = b.id GROUP BY b.title").setFirstResult(0).setMaxResults(5);
		Object[] r = (Object[]) q.getSingleResult();
		log.info(r[0] + " received " + r[1] + " reviews.");
		
		em.getTransaction().commit();
		em.close();
	}
}
