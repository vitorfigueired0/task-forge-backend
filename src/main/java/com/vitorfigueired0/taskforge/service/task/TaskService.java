package com.vitorfigueired0.taskforge.service.task;

import com.vitorfigueired0.taskforge.domain.Task;
import com.vitorfigueired0.taskforge.domain.User;
import com.vitorfigueired0.taskforge.repository.task.TaskRepository;
import com.vitorfigueired0.taskforge.service.task.form.TaskForm;
import com.vitorfigueired0.taskforge.service.task.mapper.TaskFormMapper;
import com.vitorfigueired0.taskforge.service.task.mapper.TaskViewMapper;
import com.vitorfigueired0.taskforge.service.task.view.TaskView;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

  private final TaskFormMapper taskFormMapper;
  private final TaskRepository repository;
  private final TaskViewMapper taskViewMapper;

  public TaskService(TaskFormMapper taskFormMapper,
                     TaskRepository repository,
                     TaskViewMapper taskViewMapper) {
    this.taskFormMapper = taskFormMapper;
    this.repository = repository;
    this.taskViewMapper = taskViewMapper;
  }

  public TaskView createTask(TaskForm taskForm) {
    User actualUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Task task = taskFormMapper.map(taskForm);
    task = repository.create(task.withOwner(actualUser));

    return taskViewMapper.map(task);
  }

  public TaskView findTaskById(Long id) {
    Task task = repository.findById(id);
    return taskViewMapper.map(task);
  }

  public List<TaskView> findAllTasks() {
    List<Task> tasks = repository.findAll();

    return tasks.stream()
      .map(taskViewMapper::map)
      .collect(Collectors.toList());
  }
}
