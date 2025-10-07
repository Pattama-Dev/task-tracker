package com.pattama.task_tracker.dto.request;

import com.pattama.task_tracker.entity.TaskPriority;
import com.pattama.task_tracker.entity.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequest {

    private String title;
    private String description;
    private LocalDateTime dueDate;
    private TaskStatus status;
    private TaskPriority priority;

//    private Long taskList;
}
