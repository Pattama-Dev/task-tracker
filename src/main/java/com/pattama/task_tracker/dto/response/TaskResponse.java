package com.pattama.task_tracker.dto.response;

import com.pattama.task_tracker.entity.TaskList;
import com.pattama.task_tracker.entity.TaskPriority;
import com.pattama.task_tracker.entity.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {

    private String title;
    private String description;

    private LocalDateTime dueDate;
    private TaskStatus status;
    private TaskPriority priority;

    private TaskList taskList;
//    private String taskListTitle;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;


}
