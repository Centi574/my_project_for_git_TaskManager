package com.example.project_hex_one.service;

import com.example.project_hex_one.dto.TaskStatusDto;
import com.example.project_hex_one.exception.TaskStatusNotFoundException;
import com.example.project_hex_one.model.TaskStatus;
import com.example.project_hex_one.repository.TaskStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskStatusService {

    private final TaskStatusRepository taskStatusRepository;

    public TaskStatus createNewTaskStatus(TaskStatusDto taskStatusDto) {
        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setName(taskStatusDto.getName());
        return taskStatusRepository.save(taskStatus);
    }

    @Transactional
    public TaskStatus updateTaskStatus(Long id, TaskStatusDto taskStatusDto) {
        TaskStatus taskStatus = taskStatusRepository.findById(id).orElseThrow(() -> new TaskStatusNotFoundException("Task Status not found"));
        taskStatus.setName(taskStatusDto.getName());
        return taskStatus;
    }

    @Transactional
    public TaskStatus getTaskStatusById(Long id) {
        return taskStatusRepository.findById(id).orElseThrow(() -> new TaskStatusNotFoundException("Task Status not found"));
    }
}
