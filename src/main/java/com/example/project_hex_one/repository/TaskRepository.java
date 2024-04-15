package com.example.project_hex_one.repository;

import com.example.project_hex_one.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
