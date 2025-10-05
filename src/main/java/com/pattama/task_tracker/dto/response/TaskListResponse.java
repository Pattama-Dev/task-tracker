package com.pattama.task_tracker.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskListResponse {

    private String title;
    private String description;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    private List<TaskResponse> tasks;

    private Integer totalTasks;
    private Integer completeTasks;
    private Double progress;
}
