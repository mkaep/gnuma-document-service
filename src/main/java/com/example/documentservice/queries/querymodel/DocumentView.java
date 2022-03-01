package com.example.documentservice.queries.querymodel;

import com.example.documentservice.AugmentationInfo;
import com.example.documentservice.DataField;
import com.example.documentservice.Sentence;
import com.example.documentservice.Task;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

public class DocumentView {
    @Id
    private UUID id;

    private String title;
    private String domain;
    private String source;
    private String contributor;
    private String citationInformation;
    private List<DataField> dataFields;
    private List<Task> tasks;
    private List<Sentence> sentences;
    private boolean augmented;
    private List<AugmentationInfo> augmentationInfos;
    private UUID rootDocument;
    private String uri;

    public DocumentView(UUID id, String title, String domain, String source, String contributor, String citationInformation,
                        List<DataField> dataFields, List<Task> tasks, List<Sentence> sentences, boolean augmented, List<AugmentationInfo> augmentationInfos,
                        UUID rootDocument) {
        this.id = id;
        this.title = title;
        this.domain = domain;
        this.source = source;
        this.contributor = contributor;
        this.citationInformation = citationInformation;
        this.dataFields = dataFields;
        this.tasks = tasks;
        this.sentences = sentences;
        this.augmented = augmented;
        this.augmentationInfos = augmentationInfos;
        this.rootDocument = rootDocument;
        this.uri = "/documents/" + id.toString();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDomain() {
        return domain;
    }

    public String getSource() {
        return source;
    }

    public String getContributor() {
        return contributor;
    }

    public String getCitationInformation() {
        return citationInformation;
    }

    public List<DataField> getDataFields() {
        return dataFields;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public boolean getAugmented() {
        return augmented;
    }

    public List<AugmentationInfo> getAugmentationInfos() {
        return augmentationInfos;
    }

    public UUID getRootDocument() {
        return rootDocument;
    }

    public String getUri() {
        return uri;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public void setCitationInformation(String citationInformation) {
        this.citationInformation = citationInformation;
    }

    public void setDataFields(List<DataField> dataFields) {
        this.dataFields = dataFields;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public void setAugmented(boolean augmented) {
        this.augmented = augmented;
    }

    public void setAugmentationInfos(List<AugmentationInfo> augmentationInfos) {
        this.augmentationInfos = augmentationInfos;
    }

    public void setRootDocument(UUID rootDocument) {
        this.rootDocument = rootDocument;
    }
}
