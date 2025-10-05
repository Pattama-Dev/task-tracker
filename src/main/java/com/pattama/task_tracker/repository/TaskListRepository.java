package com.pattama.task_tracker.repository;

import com.pattama.task_tracker.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {

    Optional<TaskList> findByTitle(String name);

}
