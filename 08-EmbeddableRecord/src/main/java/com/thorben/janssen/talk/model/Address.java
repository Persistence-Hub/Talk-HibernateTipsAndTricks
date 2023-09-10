package com.thorben.janssen.talk.model;

import org.hibernate.annotations.EmbeddableInstantiator;

import jakarta.persistence.Embeddable;

@Embeddable
@EmbeddableInstantiator(AddressInstantiator.class)
public record Address (String street, String city, String postalCode) {}