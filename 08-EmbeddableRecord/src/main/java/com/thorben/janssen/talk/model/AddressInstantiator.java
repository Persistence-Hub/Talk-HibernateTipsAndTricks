package com.thorben.janssen.talk.model;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.spi.EmbeddableInstantiator;
import org.hibernate.metamodel.spi.ValueAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressInstantiator implements EmbeddableInstantiator {

    Logger log = LogManager.getLogger(this.getClass().getName());

    public boolean isInstance(Object object, SessionFactoryImplementor sessionFactory) {
        return object instanceof Address;
    }

    public boolean isSameClass(Object object, SessionFactoryImplementor sessionFactory) {
        return object.getClass().equals( Address.class );
    }

    public Object instantiate(ValueAccess valuesAccess, SessionFactoryImplementor sessionFactory) {
        // valuesAccess contains attribute values in alphabetical order
        final String city = valuesAccess.getValue(0, String.class);
        final String postalCode = valuesAccess.getValue(1, String.class);
        final String street = valuesAccess.getValue(2, String.class);
        log.info("Instantiate Address embeddable for "+street+" "+postalCode+" "+city);
        return new Address( street, city, postalCode );
    }

}
