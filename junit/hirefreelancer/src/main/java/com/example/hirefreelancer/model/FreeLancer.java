package com.example.hirefreelancer.model;

public class FreeLancer {

    private  String id;
    private  int experience;
    private  String subject;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public FreeLancer() {
    }

    public FreeLancer(String id, int experience, String subject) {
        this.id = id;
        this.experience = experience;
        this.subject = subject;
    }
}