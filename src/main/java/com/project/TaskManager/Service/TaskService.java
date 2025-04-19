package com.project.TaskManager.Service;

import com.project.TaskManager.DTOs.TaskDTO;
import com.project.TaskManager.Model.User;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getAllTasks(User currentuser);
}
