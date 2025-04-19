package com.project.TaskManager.DTOs;

import com.project.TaskManager.Model.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDTO {
    @NotBlank
    private String title;

    private String description;

    @FutureOrPresent
    private LocalDate dueDate;

    private TaskStatus status;
}
