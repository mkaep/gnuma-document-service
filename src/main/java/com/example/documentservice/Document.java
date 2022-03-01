package com.example.documentservice;

import com.example.documentservice.commands.*;
import com.example.documentservice.events.CreatedAugmentedDocumentEvent;
import com.example.documentservice.events.CreatedDocumentEvent;
import com.example.documentservice.events.DeletedDocumentEvent;
import com.example.documentservice.events.UpdatedDocumentEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Aggregate
public class Document {

    @AggregateIdentifier
    private UUID id;
    private String title;
    private String domain;
    private String source;
    private String contributor;
    private String citationInformation;
    private List<DataField> dataFields;
    private List<Task> tasks;
    private File data;
    private List<Sentence> sentences;
    private boolean augmented;
    private List<AugmentationInfo> augmentationInfos;
    private UUID rootDocument;

    //Axon Framework needs a non-arg constructor to create an empty aggregate instance beofre initializing it using past events
    public Document() {
    }

    // Command handler contains the business logic and validations.
    @CommandHandler
    public Document(CreateDocumentCommand cmd) {
        //Publish the message internally to this aggregate and aggregate members and later on to the event bus
        UUID documentId = UUID.randomUUID();
        this.title = cmd.getTitle();
        this.domain = cmd.getDomain();
        this.source = cmd.getSource();
        this.contributor = cmd.getContributor();
        this.citationInformation = cmd.getCitationInformation();
        this.dataFields = cmd.getDataFields();
        this.tasks = cmd.getTasks();
        this.data = cmd.getData();
        System.out.println("Create Command has received the following file: " + cmd.getData());
        this.sentences = null;
        this.augmented = false;
        this.augmentationInfos = null;
        this.rootDocument = null;
        AggregateLifecycle.apply(new CreatedDocumentEvent(documentId, title, domain, source, contributor,
                citationInformation, dataFields, tasks, data));
    }

    @CommandHandler
    public Document(CreateAugmentedDocumentCommand cmd) {
        //Publish the message internally to this aggregate and aggregate members and later on to the event bus
        UUID documentId = UUID.randomUUID();
        this.title = cmd.getTitle();
        this.domain = cmd.getDomain();
        this.source = cmd.getSource();
        this.contributor = cmd.getContributor();
        this.citationInformation = cmd.getCitationInformation();
        this.dataFields = cmd.getDataFields();
        this.tasks = cmd.getTasks();
        this.sentences = cmd.getSentences();
        System.out.println("Create Augmented Document Command has received the following file: " + cmd.getSentences());
        this.augmented = cmd.getAugmented();
        this.augmentationInfos = cmd.getAugmentationInfos();
        this.rootDocument = cmd.getRootDocument();
        AggregateLifecycle.apply(new CreatedAugmentedDocumentEvent(documentId, title, domain, source, contributor,
                citationInformation, dataFields, tasks, sentences, augmented, augmentationInfos, rootDocument));
    }

    @CommandHandler
    public void handle(DeleteDocumentCommand cmd) {
        AggregateLifecycle.apply(new DeletedDocumentEvent(id));
    }

    @CommandHandler
    public void handle(UpdateDocumentCommand cmd) {
        this.title = cmd.getTitle();
        this.domain = cmd.getDomain();
        this.source = cmd.getSource();
        this.contributor = cmd.getContributor();
        this.citationInformation = cmd.getCitationInformation();
        this.dataFields = cmd.getDataFields();
        this.tasks = cmd.getTasks();
        this.data = cmd.getData();
        this.augmented = cmd.isAugmented();
        this.augmentationInfos = cmd.getAugmentationInfos();
        this.rootDocument = cmd.getRootDocument();
        AggregateLifecycle.apply(new UpdatedDocumentEvent(id, title, domain, source, contributor,
                citationInformation, dataFields, tasks, data, augmented, augmentationInfos, rootDocument));
    }

    @CommandHandler
    public void handle(PatchDocumentCommand cmd) {
        // Build a DTO Object, convert it to JSON, apply patch and the update the aggregate
    /*
        try {
            //DocumentDTO currentState = new DocumentDTO(this.domain, this.source, this.contributor, this.citationInformation,
            //        this.dataFields, this.tasks, this.data, this.augmented, this.rootDocument);

            JsonStructure target = cmd.getObjectMapper().convertValue(this, JsonStructure.class);
            JsonValue patchedDocument = cmd.getPatch().apply(target);
            //DocumentDTO modifiedDocument = cmd.getObjectMapper().convertValue(patchedDocument, this.getClass());

            //System.out.println(modifiedDocument);



        } catch (IOException e) {
            e.printStackTrace();
        }
*/

    }

    @EventSourcingHandler
    public void handle(UpdatedDocumentEvent event) {
        id = event.getId();
    }

    @EventSourcingHandler
    public void handle(CreatedDocumentEvent event) {
        //Its mandatory in an EventSourcing Handler to set the aggregate identifier
        id = event.getId();
    }

    @EventSourcingHandler
    public void handle(CreatedAugmentedDocumentEvent event) {
        //Its mandatory in an EventSourcing Handler to set the aggregate identifier
        id = event.getId();
    }

    @EventSourcingHandler
    public void handle(DeletedDocumentEvent event) {
        id = event.getId();
        //Will mark the Aggregate instance calling the function as being 'deleted'. According to the Axon documentation this function
        //should be called from an @EventSourcingHandler annotated function
        markDeleted();
    }
}
