package com.todo.demo.demo_desktop.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class TaskUpdateDTO {
        @NotNull
        private Long id;
        private Optional <String> title = Optional.empty();
        private Optional <String> description = Optional.empty();
        private Optional <String> status = Optional.empty();
        private Optional <String> priority = Optional.empty();
        private Optional <Long> assignee = Optional.empty();
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        private Optional <LocalDateTime> deadline = Optional.empty();
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm", shape = JsonFormat.Shape.STRING)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Optional <LocalDateTime> dateFinished = Optional.empty();
}
