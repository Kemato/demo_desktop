package com.todo.demo.demo_desktop.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {
    Long id;
    String title;
    String description;
    String status;
    String priority;
    String assignee;
    String author;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime dateCreated;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime dateUpdated;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime deadline;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDateTime dateFinished;
}
