package com.example.springboot.tut.todos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Enter title for the task")
    @Size(min = 3,message = "the size of task title must be grater than 3")
    private String title;

    @NotNull(message = "Enter description for the task")
    private String description;

    private Integer userId;

    private long timestamp;

    public Todo(){
        this.timestamp = System.currentTimeMillis();
    }

    public Todo(Long id,String title,String description){
        this.id = id;
        this.title = title;
        this.description = description;
        this.timestamp = System.currentTimeMillis();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    public long getTimestamp() {
        return timestamp;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
