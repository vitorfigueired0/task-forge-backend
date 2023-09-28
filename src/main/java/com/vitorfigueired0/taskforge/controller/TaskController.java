package com.vitorfigueired0.taskforge.controller;

import com.vitorfigueired0.taskforge.service.task.TaskService;
import com.vitorfigueired0.taskforge.service.task.form.TaskForm;
import com.vitorfigueired0.taskforge.service.task.view.TaskView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @PostMapping
  public ResponseEntity<TaskView> createTask(@Valid @RequestBody TaskForm taskForm) {
    TaskView task = taskService.createTask(taskForm);
    return new ResponseEntity<>(task, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskView> findTask(@PathVariable Long id) {
    TaskView task = taskService.findTaskById(id);
    return new ResponseEntity<>(task, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<TaskView>> findAllTasks() {
    List<TaskView> tasks = taskService.findAllTasks();
    return new ResponseEntity<>(tasks, HttpStatus.OK);
  }


}
