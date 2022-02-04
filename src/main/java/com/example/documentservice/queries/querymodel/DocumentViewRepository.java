package com.example.documentservice.queries.querymodel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DocumentViewRepository extends MongoRepository<DocumentView, UUID> {
    public List<DocumentView> findDocumentViewByDomain(String domain);
    public List<DocumentView> findDocumentViewBySource(String source);
    public List<DocumentView> findDocumentViewBySourceAndDomain(@Param("source") String source, @Param("domain") String domain);
    public List<DocumentView> findDocumentViewByTitle(String title);
    public List<DocumentView> findDocumentViewBySourceAndTitle(@Param("source") String source, @Param("title") String title);
    public List<DocumentView> findDocumentViewByDomainAndTitle(@Param("domain") String domain, @Param("title") String title);
    public List<DocumentView> findDocumentViewByDomainAndSourceAndTitle(@Param("domain") String domain, @Param("source") String source, @Param("title") String title);
}
