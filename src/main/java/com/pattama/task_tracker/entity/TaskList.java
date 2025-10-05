package com.pattama.task_tracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_lists")
public class TaskList extends BaseEntity{

    @Column(nullable = false)
    private String title;

    private String description;

    @OneToMany(mappedBy = "taskList", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Task> tasks;

}
