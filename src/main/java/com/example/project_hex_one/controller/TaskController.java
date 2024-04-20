package com.example.project_hex_one.controller;

import com.example.project_hex_one.dto.TaskDto;
import com.example.project_hex_one.model.Task;
import com.example.project_hex_one.repository.TaskRepository;
import com.example.project_hex_one.service.TaskService;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${base-url}" + TaskController.TASK_CONTROLLER_PATH)
public class TaskController {
    public static final String TASK_CONTROLLER_PATH = "/tasks";
    public static final String ID = "/{id}";

    private final TaskService taskService;
    private final TaskRepository taskRepository;

    @PostMapping()
    public Task createNewTask(@RequestBody TaskDto taskDto) {
        return taskService.createNewTask(taskDto);
    }

    @GetMapping()
    public Iterable<Task> getAllTasks(@QuerydslPredicate Predicate predicate) {
        return predicate == null ? taskService.getAllTasks() : taskService.getAllTasks(predicate);
    }

    @GetMapping(path = ID)
    public Task getTaskById(@PathVariable(name = "id") Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping(path = ID)
    public Task updateTask(@PathVariable(name = "id") Long id, @RequestBody TaskDto taskDto) {
        return taskService.updateTask(id, taskDto);
    }

    @DeleteMapping(path = ID)
    public void deleteTask(@PathVariable(name = "id") Long id) {
        taskRepository.deleteById(id);
    }
}
