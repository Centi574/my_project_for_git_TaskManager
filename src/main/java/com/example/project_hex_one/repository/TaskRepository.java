package com.example.project_hex_one.repository;

import com.example.project_hex_one.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
