package com.example.project_hex_one.repository;

import com.example.project_hex_one.model.Task;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAll(Predicate predicate);
}
