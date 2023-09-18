package com.todone.tasks;

import java.util.List;

public class Task {
    private String status;
    private String doneDate;
    private String priority;
    private String creationDate;
    private List<String> projects;
    private List<String> tags;
    private String dueDate;
    private List<String> attachments;
    private String title;
    private String description;


    public void setStatus(String status) {
        this.status = status;
    }
    public void setDoneDate(String doneDate) {
        this.doneDate = doneDate;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    public void setProjects(List<String> projects) {
        this.projects = projects;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }
    public String getDoneDate() {
        return doneDate;
    }
    public String getPriority() {
        return priority;
    }
    public String getCreationDate() {
        return creationDate;
    }
    public List<String> getProjects() {
        return projects;
    }
    public List<String> getTags() {
        return tags;
    }
    public String getDueDate() {
        return dueDate;
    }
    public List<String> getAttachments() {
        return attachments;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}
