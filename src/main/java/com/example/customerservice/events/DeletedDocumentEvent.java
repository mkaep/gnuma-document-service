package com.example.customerservice.events;

import java.util.UUID;

public class DeletedDocumentEvent {

    private final UUID id;

    public DeletedDocumentEvent(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DeletedDocumentEvent{"
                + "id=" + id.toString() +
                "}";
    }
}
