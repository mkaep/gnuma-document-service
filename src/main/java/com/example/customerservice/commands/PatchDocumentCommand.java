package com.example.customerservice.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.json.JsonPatch;
import java.util.Objects;
import java.util.UUID;

public class PatchDocumentCommand {
    @TargetAggregateIdentifier
    private final UUID id;
    private final JsonPatch patch;
    private final ObjectMapper objectMapper;

    public PatchDocumentCommand(UUID id, JsonPatch patch, ObjectMapper objectMapper) {
        this.id = id;
        this.patch = patch;
        this.objectMapper = objectMapper;
    }

    public UUID getId() {
        return id;
    }

    public JsonPatch getPatch() {
        return patch;
    }

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatchDocumentCommand that = (PatchDocumentCommand) o;
        return id.equals(that.id) && patch.equals(that.patch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patch);
    }

    @Override
    public String toString() {
        return "PatchDocumentCommand{" +
                "id=" + id +
                ", patch=" + patch +
                '}';
    }
}
