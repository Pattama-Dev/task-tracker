package com.pattama.task_tracker.service;

import com.pattama.task_tracker.dto.request.TaskRequest;
import com.pattama.task_tracker.dto.response.TaskResponse;

import java.util.List;

public interface ITaskService {

    TaskResponse createTask(Long taskListId, TaskRequest request);

    List<TaskResponse> getTasksByTaskListId(Long taskListId);

    TaskResponse getTaskByTaskListIdAndId(Long taskListId, Long taskId);

    TaskResponse updateTask(Long taskListId, Long taskId, TaskRequest request);

    void deleteTask(Long taskListId, Long taskId);

}
