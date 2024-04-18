package com.example.project_hex_one.service;

import com.example.project_hex_one.dto.TaskDto;
import com.example.project_hex_one.model.Label;
import com.example.project_hex_one.model.Task;
import com.example.project_hex_one.model.TaskStatus;
import com.example.project_hex_one.model.User;
import com.example.project_hex_one.repository.TaskRepository;
import com.example.project_hex_one.repository.UserRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TaskStatusService taskStatusService;
    private final LabelService labelService;

    public Task createNewTask(TaskDto taskDto) {
        Task newTask = constructFromDto(taskDto);
        return taskRepository.save(newTask);
    }

    public Task updateTask(Long id, TaskDto updatedTaskDto) {
        Task taskToBeUpdated = constructFromDto(updatedTaskDto);
        taskToBeUpdated.setId(id);
        return taskRepository.save(taskToBeUpdated);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasks(Predicate predicate) {
        return StreamSupport.stream(taskRepository.findAll(predicate).spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    private Task constructFromDto(TaskDto taskDto) {
        User executor = Optional.ofNullable(taskDto.getExecutorId())
                .map(userService::getUserById)
                .orElse(null);

        TaskStatus taskStatus = Optional.of(taskDto.getTaskStatusId())
                .map(taskStatusService::getTaskStatusById)
                .orElse(null);

        List<Label> labels = Optional.ofNullable(taskDto.getLabelIds())
                .orElse(List.of())
                .stream()
                .filter(Objects::nonNull)
                .map(labelService::getLabelById)
                .collect(Collectors.toList());

        return Task.builder()
                .author(userService.getCurrentUser())
                .executor(executor)
                .taskStatus(taskStatus)
                .labels(labels)
                .name(taskDto.getName())
                .description(taskDto.getDescription())
                .build();
    }
}
