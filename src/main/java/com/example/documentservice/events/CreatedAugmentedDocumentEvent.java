package com.example.documentservice.events;

import com.example.documentservice.AugmentationInfo;
import com.example.documentservice.DataField;
import com.example.documentservice.Sentence;
import com.example.documentservice.Task;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class CreatedAugmentedDocumentEvent {

    private final UUID id;
    private final String title;
    private final String domain;
    private final String source;
    private final String contributor;
    private final String citationInformation;
    private final List<DataField> dataFields;
    private final List<Task> tasks;
    private final List<Sentence> sentences;
    private final boolean augmented;
    private final List<AugmentationInfo> augmentationInfos;
    private final UUID rootDocument;


    public CreatedAugmentedDocumentEvent(UUID id, String title, String domain, String source, String contributor, String citationInformation,
                                         List<DataField> dataFields, List<Task> tasks, List<Sentence> sentences, boolean augmented, List<AugmentationInfo> augmentationInfos, UUID rootDocument) {
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

        System.out.println("Hello from the Created Augmented Document Event " + this.sentences);
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

    @Override
    public String toString() {
        return "CreatedDocumentEvent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", domain='" + domain + '\'' +
                ", source='" + source + '\'' +
                ", contributor='" + contributor + '\'' +
                ", citationInformation='" + citationInformation + '\'' +
                ", dataFields=" + dataFields +
                ", tasks=" + tasks +
                ", sentences=" + sentences +
                ", augmented=" + augmented +
                ", augmentationInfos=" + augmentationInfos +
                ", rootDocument=" + rootDocument +
                '}';
    }
}
