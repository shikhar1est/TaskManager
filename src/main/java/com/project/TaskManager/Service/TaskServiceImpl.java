package com.project.TaskManager.Service;

import com.project.TaskManager.DTOs.TaskDTO;
import com.project.TaskManager.Exception.APIException;
import com.project.TaskManager.Model.Task;
import com.project.TaskManager.Model.User;
import com.project.TaskManager.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskDTO> getAllTasks(User currentuser) {
        List<Task> tasks=taskRepository.findAll();

        if(tasks.size()==0){
            throw new APIException("No tasks found");
        }


        return List.of();
    }
}
