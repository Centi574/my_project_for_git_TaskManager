package com.example.project_hex_one.controller;

import com.example.project_hex_one.dto.TaskStatusDto;
import com.example.project_hex_one.model.TaskStatus;
import com.example.project_hex_one.repository.TaskStatusRepository;
import com.example.project_hex_one.service.TaskStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${base-url}" + TaskStatusController.TASK_STATUS_CONTROLLER_PATH)
public class TaskStatusController {
    public static final String TASK_STATUS_CONTROLLER_PATH = "/statuses";
    public static final String ID = "/{id}";
    private final TaskStatusService taskStatusService;
    private final TaskStatusRepository taskStatusRepository;

    @PostMapping()
    public TaskStatus createNewTaskStatus(@RequestBody @Valid TaskStatusDto taskStatusDto) {
        return taskStatusService.createNewTaskStatus(taskStatusDto);
    }

    @GetMapping()
    public List<TaskStatus> getAllTaskStatus() {
        return taskStatusRepository.findAll().stream().toList();
    }

    @GetMapping(path = ID)
    public TaskStatus getTaskStatusById(@PathVariable(name = "id") Long id) {
        return taskStatusService.getTaskStatusById(id);
    }

    @PutMapping(path = ID)
    public TaskStatus updateTaskStatus(@PathVariable(name = "id") Long id,
                                       @RequestBody @Valid TaskStatusDto taskStatusDto) {
        return taskStatusService.updateTaskStatus(id, taskStatusDto);
    }

    @DeleteMapping(path = ID)
    public void deleteTaskStatus(@PathVariable(name = "id") Long id) {
        taskStatusRepository.deleteById(id);
    }
}
