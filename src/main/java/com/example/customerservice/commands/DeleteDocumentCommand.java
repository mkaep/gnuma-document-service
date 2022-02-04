package com.example.customerservice.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;
import java.util.UUID;

public class DeleteDocumentCommand {
    @TargetAggregateIdentifier
    private final UUID id;

    public DeleteDocumentCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof DeleteDocumentCommand)) {
            return false;
        }
        else {
            DeleteDocumentCommand cmd = (DeleteDocumentCommand) o;
            return ((id == cmd.getId()));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DeleteDocumentCommand{"
                + "id=" + id.toString() +
                "}";

    }
}
