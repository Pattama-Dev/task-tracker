package com.pattama.task_tracker.dto.request;

import com.pattama.task_tracker.dto.response.TaskResponse;
import com.pattama.task_tracker.entity.Task;
import lombok.Data;

import java.util.List;

@Data
public class TaskListRequest {

    private String title;
    private String description;

}
