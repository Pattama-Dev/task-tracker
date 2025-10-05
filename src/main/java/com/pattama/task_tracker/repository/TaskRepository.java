package com.pattama.task_tracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pattama.task_tracker.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTaskListId(Long taskListId);

    Optional<Task> findByTaskListIdAndId(Long taskListId, Long id);

    Optional<Task> findByTitle(String title);

}
