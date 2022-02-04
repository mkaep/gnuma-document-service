package com.example.documentservice.dto;

import com.example.documentservice.DataField;
import com.example.documentservice.Task;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DocumentDTO {

    private String title;
    private String domain;
    private String source;
    private String contributor;
    private String citationInformation;
    private List<DataField> dataFields = new ArrayList<DataField>();
    private List<Task> tasks;
    private File data;
    private boolean augmented;
    private UUID rootDocument;


    public DocumentDTO() {

    }

    public DocumentDTO(String title, String domain, String source, String contributor, String citationInformation,
                       List<DataField> dataFields, List<Task> tasks, MultipartFile data,
                       boolean augmented, UUID rootDocument)  {
        this.title = title;
        this.domain = domain;
        this.source = source;
        this.contributor = contributor;
        this.citationInformation = citationInformation;
        this.dataFields = dataFields;
        this.tasks = tasks;
        File file = new File("src/main/resources/data.tmp");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.data = file;

        this.augmented = augmented;
        this.rootDocument = rootDocument;
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

    public File getData() {
        return data;
    }

    public boolean getAugmented() {
        return augmented;
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

    public void setData(MultipartFile data) {
        File file = new File("src/main/resources/data.tmp");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.data = file;
    }

    public void setAugmented(boolean augmented) {
        this.augmented = augmented;
    }

    public void setRootDocument(UUID rootDocument) {
        this.rootDocument = rootDocument;
    }

    public UUID getRootDocument() {
        return rootDocument;
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "title='" + title + '\'' +
                ", domain='" + domain + '\'' +
                ", source='" + source + '\'' +
                ", contributor='" + contributor + '\'' +
                ", citationInformation='" + citationInformation + '\'' +
                ", dataFields=" + dataFields +
                ", tasks=" + tasks +
                ", data=" + data +
                ", augmented=" + augmented +
                ", rootDocument=" + rootDocument +
                '}';
    }
}
