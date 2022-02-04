package com.example.documentservice.queries;

import java.util.UUID;

public class FindDocumentQuery {

    private final UUID id;

    public FindDocumentQuery(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
