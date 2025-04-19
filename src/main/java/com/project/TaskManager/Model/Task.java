package com.project.TaskManager.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private int priority;
    private LocalDate dueDate;
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
