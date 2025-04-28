package org.example;

import java.util.Objects;

public class Task implements Comparable<Task> {

    private String project;
    private String description;
    private Priority priority;
    private Status status;
    private String memberName;

    public Task(String project, String description, Priority priority, Status status, String memberName) {
        this.project = project;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.memberName = memberName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return project.equals(task.project) &&
                description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, description);
    }

    @Override
    public int compareTo(Task other) {
        int result = this.project.compareTo(other.project);
        if (result == 0) {
            result = this.description.compareTo(other.description);
        }
        return result;
    }

    @Override
    public String toString() {
        return project + " - " + description + " - " + priority + " - " + status + " - " + memberName;
    }
}
