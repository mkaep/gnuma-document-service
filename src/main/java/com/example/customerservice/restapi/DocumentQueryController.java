package com.example.customerservice.restapi;

import com.example.customerservice.queries.*;
import com.example.customerservice.queries.querymodel.DocumentView;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value="/api/v1/documents")
public class DocumentQueryController {

    private final QueryGateway queryGateway;

    @Autowired
    public DocumentQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public CompletableFuture<DocumentView> getDocument(@PathVariable String id) {
        System.out.println("Received a request for getting Document with ID " + id);
        return queryGateway.query(new FindDocumentQuery(UUID.fromString(id)), ResponseTypes.instanceOf(DocumentView.class));
    }

    @CrossOrigin
    @GetMapping()
    public CompletableFuture<List<DocumentView>> getDocuments(@RequestParam(required = false) String domain, @RequestParam(required = false) String source, @RequestParam(required = false) String title) {
        if(domain == null && source == null && title == null) {
            System.out.println("Received a request for getting all documents");
            return queryGateway.query(new RetrieveAllDocumentsQuery(), ResponseTypes.multipleInstancesOf(DocumentView.class));
        }
        else {
            if(domain == null && title == null && source != null) {
                System.out.println("Received a request for getting all documents of source " + source);
                return queryGateway.query(new RetrieveSourceDocumentsQuery(source), ResponseTypes.multipleInstancesOf(DocumentView.class));
            }
            else {
                if(domain != null && source == null && title == null) {
                    System.out.println("Received a request for getting all documents of domain " + domain);
                    return queryGateway.query(new RetrieveDomainDocuments(domain), ResponseTypes.multipleInstancesOf(DocumentView.class));
                }
                else {
                    if (domain == null && source == null && title != null) {
                        System.out.println("Recevied a request for getting all documents with title " + title);
                        return queryGateway.query(new RetrieveTitleDocumentsQuery(title), ResponseTypes.multipleInstancesOf(DocumentView.class));
                    }
                    else {
                        if(domain != null && source != null && title == null) {
                            System.out.println("Recevied a request for getting all documents of domain " + domain + " and source " + source);
                            return queryGateway.query(new RetrieveSourceDomainDocumentsQuery(source, domain), ResponseTypes.multipleInstancesOf(DocumentView.class));
                        }
                        else {
                            if (domain != null && source == null && title != null) {
                                System.out.println("Recevied a request for getting all documents of domain " + domain + " and title " + title);
                                return queryGateway.query(new RetrieveDomainTitleDocumentsQuery(domain, title), ResponseTypes.multipleInstancesOf(DocumentView.class));
                            }
                            else {
                                if (domain == null && source != null && title != null) {
                                    System.out.println("Recevied a request for getting all documents of source " + source + " and title " + title);
                                    return queryGateway.query(new RetrieveSourceTitleDocumentsQuery(source, title), ResponseTypes.multipleInstancesOf(DocumentView.class));
                                }
                                else {
                                    System.out.println("Recevied a request for getting all documents of domain " + domain +" and source " + source + " and title " + title);
                                    //alles
                                    return queryGateway.query(new RetrieveDomainSourceTitleDocumentsQuery(domain, source, title), ResponseTypes.multipleInstancesOf(DocumentView.class));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}