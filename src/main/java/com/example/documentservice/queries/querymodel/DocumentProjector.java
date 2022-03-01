package com.example.documentservice.queries.querymodel;

import com.example.documentservice.Sentence;
import com.example.documentservice.events.CreatedAugmentedDocumentEvent;
import com.example.documentservice.events.CreatedDocumentEvent;
import com.example.documentservice.events.DeletedDocumentEvent;
import com.example.documentservice.events.UpdatedDocumentEvent;
import com.example.documentservice.parser.DocumentParser;
import com.example.documentservice.queries.*;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentProjector {
    private final DocumentViewRepository repository;
    private DocumentParser parser;

    public DocumentProjector(DocumentViewRepository repository) {
        this.parser = new DocumentParser();
        this.repository = repository;
        this.repository.deleteAll();
    }

    @EventHandler
    public void handle(CreatedDocumentEvent event) {
        List<Sentence> sentences = parser.parseDocument(event.getData());

        DocumentView documentView = new DocumentView(event.getId(), event.getTitle(), event.getDomain(), event.getSource(),
                event.getContributor(), event.getCitationInformation(), event.getDataFields(), event.getTasks(),
                sentences, false, null, null);
        repository.save(documentView);
    }

    @EventHandler
    public void handle(CreatedAugmentedDocumentEvent event) {

        DocumentView documentView = new DocumentView(event.getId(), event.getTitle(), event.getDomain(), event.getSource(),
                event.getContributor(), event.getCitationInformation(), event.getDataFields(), event.getTasks(),
                event.getSentences(), event.getAugmented(), event.getAugmentationInfos(), event.getRootDocument());
        repository.save(documentView);
    }

    @EventHandler
    public void handle(DeletedDocumentEvent event) {
        DocumentView documentView = repository.findById(event.getId()).orElse(null);
        if(documentView != null) {
            repository.delete(documentView);
        }
    }

    @EventHandler
    public void handle(UpdatedDocumentEvent event) {
        DocumentView documentView = repository.findById(event.getId()).orElse(null);
        if(documentView != null) {
            List<Sentence> sentences = parser.parseDocument(event.getData());
            documentView.setTitle(event.getTitle());
            documentView.setDomain(event.getDomain());
            documentView.setSource(event.getSource());
            documentView.setContributor(event.getContributor());
            documentView.setCitationInformation(event.getCitationInformation());
            documentView.setDataFields(event.getDataFields());
            documentView.setTasks(event.getTasks());
            documentView.setSentences(sentences);
            documentView.setAugmented(event.getAugmented());
            documentView.setRootDocument(event.getRootDocument());
            repository.save(documentView);
        }
    }

    @QueryHandler
    public DocumentView handle(FindDocumentQuery query) {
        return repository.findById(query.getId()).orElse(null);
    }

    @QueryHandler
    public List<DocumentView> handle(RetrieveDomainDocuments query) {
        return repository.findAll().stream().filter(documentView -> (documentView.getDomain().contains(query.getDomain()))).collect(Collectors.toList());
    }

    @QueryHandler
    public List<DocumentView> handle(RetrieveAllDocumentsQuery query) {
        return repository.findAll();
    }

    @QueryHandler
    public List<DocumentView> handle(RetrieveSourceDocumentsQuery query) {
        return repository.findDocumentViewBySource(query.getSource());
    }

    @QueryHandler
    public List<DocumentView> handle(RetrieveSourceDomainDocumentsQuery query) {
        return repository.findDocumentViewBySourceAndDomain(query.getSource(), query.getDomain());
    }

    @QueryHandler
    public List<DocumentView> handle(RetrieveTitleDocumentsQuery query) {
        return repository.findDocumentViewByTitle(query.getTitle());
    }

    @QueryHandler
    public List<DocumentView> handle(RetrieveSourceTitleDocumentsQuery query) {
        return repository.findDocumentViewBySourceAndTitle(query.getSource(), query.getTitle());
    }

    @QueryHandler
    public List<DocumentView> handle(RetrieveDomainTitleDocumentsQuery query) {
        return repository.findDocumentViewByDomainAndTitle(query.getDomain(), query.getTitle());
    }

    @QueryHandler
    public List<DocumentView> handle(RetrieveDomainSourceTitleDocumentsQuery query) {
        return repository.findDocumentViewByDomainAndSourceAndTitle(query.getDomain(), query.getSource(), query.getTitle());
    }
}
