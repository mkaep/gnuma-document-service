package com.example.documentservice.restapi;

import com.example.documentservice.commands.CreateDocumentCommand;
import com.example.documentservice.commands.DeleteDocumentCommand;
import com.example.documentservice.commands.PatchDocumentCommand;
import com.example.documentservice.commands.UpdateDocumentCommand;
import com.example.documentservice.dto.DocumentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonPatch;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value="/api/v1/documents")
public class DocumentCommandController {

    private final CommandGateway commandGateway;
    private final ObjectMapper objectMapper;

    @Autowired
    public DocumentCommandController(CommandGateway commandGateway, ObjectMapper objectMapper) {
        this.commandGateway = commandGateway;
        this.objectMapper = objectMapper;
    }

    @CrossOrigin
    @PostMapping
    public CompletableFuture<String> createDocument(@ModelAttribute DocumentDTO dto) {
        return commandGateway.send(new CreateDocumentCommand(dto.getTitle(), dto.getDomain(), dto.getSource(), dto.getContributor(),
                dto.getCitationInformation(), dto.getDataFields(), dto.getTasks(), dto.getData(),
                dto.getAugmented(), dto.getRootDocument()));
    }

    @CrossOrigin
    @PutMapping(value="/{id}")
    public CompletableFuture<String> updateDocument(@PathVariable String id, @ModelAttribute DocumentDTO dto) {
        System.out.println("Received a request for updating a new document");
        return commandGateway.send(new UpdateDocumentCommand(UUID.fromString(id), dto.getTitle(), dto.getDomain(), dto.getSource(), dto.getContributor(),
                dto.getCitationInformation(), dto.getDataFields(), dto.getTasks(), dto.getData(),
                dto.getAugmented(), dto.getRootDocument()));
    }

    @CrossOrigin
    @PatchMapping(value="/{id}", consumes = "application/json-patch+json")
    public CompletableFuture<String> patchDocument(@PathVariable String id, @RequestBody JsonPatch patch) {
        System.out.println("Recevied a request for patching a new document");
        System.out.println("Json Patch: " + patch);
        return commandGateway.send(new PatchDocumentCommand(UUID.fromString(id), patch, objectMapper));
    }

    @CrossOrigin
    @DeleteMapping(value="/{id}")
    public CompletableFuture<String> deleteDocument(@PathVariable String id) {
        System.out.println("Received a request for deleting Document with ID "+ id);
        return commandGateway.send(new DeleteDocumentCommand(UUID.fromString(id)));
    }


}
