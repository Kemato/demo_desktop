package com.todo.demo.demo_desktop.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    @NotNull
    private Long id;
    private Optional <String> name = Optional.empty();
    private Optional <String> password = Optional.empty();
}
