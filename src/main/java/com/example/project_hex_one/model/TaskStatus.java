package com.example.project_hex_one.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "task_statuses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name should not be empty")
    @Size(min = 1)
    @Column(unique = true, name = "name")
    private String name;

    @CreationTimestamp
    @Temporal(TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    public TaskStatus(Long id) {
        this.id = id;
    }
}
