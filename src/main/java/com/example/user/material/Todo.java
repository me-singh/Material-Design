package com.example.user.material;

import java.util.Date;

/**
 * Created by user on 3/1/2018.
 */

public class Todo {
    private int todoId;
    private String title,description;
    private Date pendingDate;

    public Todo(String title, String description, Date pendingDate) {
        this.title = title;
        this.description = description;
        this.pendingDate = pendingDate;
    }

    public Todo(int todoId, String title, String description, Date pendingDate) {
        this.todoId = todoId;
        this.title = title;
        this.description = description;
        this.pendingDate = pendingDate;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPendingDate() {
        return pendingDate;
    }

    public void setPendingDate(Date pendingDate) {
        this.pendingDate = pendingDate;
    }
}
