package com.project.TaskManager.Controller;

import com.project.TaskManager.DTOs.TaskDTO;
import com.project.TaskManager.Model.User;
import com.project.TaskManager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")   //
    public ResponseEntity<List<TaskDTO>> getAllTasks(@AuthenticationPrincipal User currentuser){
          List<TaskDTO> taskDTO=taskService.getAllTasks(currentuser);
          return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }
}
