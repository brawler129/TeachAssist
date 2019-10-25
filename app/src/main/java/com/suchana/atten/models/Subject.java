package com.suchana.atten.models;

public class Subject {
    public String subject;
    public String section;
    public String sub_id;

    public Subject(String sub_id) {
//        this.subject = subject;
//        this.section = section;
        this.sub_id = sub_id;
    }

    public String getSub_id() {
        return sub_id;
    }
}