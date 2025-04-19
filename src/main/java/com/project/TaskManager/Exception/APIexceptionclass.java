package com.project.TaskManager.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIexceptionclass {
    private String message;
    private boolean status;
}
