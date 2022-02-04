package com.example.customerservice;

public class DataField {

    private String fieldName;
    private String description;

    public DataField() {

    }

    public DataField(String fieldName, String description) {
        this.fieldName = fieldName;
        this.description = description;
    }



    public String getFieldName() {
        return fieldName;
    }

    public String getDescription() {
        return description;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DataField{"
                + "fieldName='" + fieldName + "'" +
                ", description='" + description + "'" +
                "}";
    }
}
