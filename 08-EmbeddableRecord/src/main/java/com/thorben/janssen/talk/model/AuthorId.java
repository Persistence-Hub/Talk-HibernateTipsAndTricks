package com.thorben.janssen.talk.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record AuthorId(String id) {
    
}
