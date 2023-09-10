package com.thorben.janssen.talk.model;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thorben.janssen.talk.multitenancy.TenantIdResolver;

public class TestMultiTenancy {

	Logger log = LogManager.getLogger(this.getClass().getName());

    private SessionFactory sessionFactory;

    @Before
    public void init() {
        ServiceRegistry standardRegistry
                = new StandardServiceRegistryBuilder().build();
        MetadataSources sources = new MetadataSources(standardRegistry).addAnnotatedClass(Book.class);
        Metadata metadata = sources.buildMetadata();
        sessionFactory = metadata.buildSessionFactory();
    }

    @After
    public void close() {
        sessionFactory.close();
    }

    @Test
    public void testMultiTenancy() {
        log.info("... testMultiTenancy ...");

        // Tenant 1
        ((TenantIdResolver) ((SessionFactoryImplementor) sessionFactory).getCurrentTenantIdentifierResolver()).setTenantIdentifier("tenant1");
        Session session1 = sessionFactory.openSession();
        Transaction tx1 = session1.getTransaction();
        tx1.begin();

        Book b1 = new Book();
        b1.setTitle("Book of tenant 1");
        session1.persist(b1);

        tx1.commit();
        session1.close();

        // Tenant 2
        ((TenantIdResolver) ((SessionFactoryImplementor) sessionFactory).getCurrentTenantIdentifierResolver()).setTenantIdentifier("tenant2");
        Session session2 = sessionFactory.openSession();
        Transaction tx2 = session2.getTransaction();
        tx2.begin();

        Book b2 = new Book();
        b2.setTitle("Book of tenant 2");
        session2.persist(b2);

        tx2.commit();
        session2.close();

        // Tenant 1
        ((TenantIdResolver) ((SessionFactoryImplementor) sessionFactory).getCurrentTenantIdentifierResolver()).setTenantIdentifier("tenant1");
        session1 = sessionFactory.openSession();
        tx1 = session1.getTransaction();
        tx1.begin();

        List<Book> books = session1.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        books.forEach(b -> log.info(b));

        tx1.commit();
        session1.close();
    }
}
