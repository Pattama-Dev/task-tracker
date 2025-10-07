package com.pattama.task_tracker.service;

import com.pattama.task_tracker.dto.request.TaskListRequest;
import com.pattama.task_tracker.dto.response.TaskListResponse;
import com.pattama.task_tracker.entity.TaskList;

import java.util.List;
import java.util.Optional;

public interface ITaskListService {

    TaskListResponse createTaskList(TaskListRequest request);

    List<TaskListResponse> getAllTaskList();

    TaskListResponse getTaskListById(Long taskListId);

    TaskListResponse updateTaskList(Long taskListId, TaskListRequest request);

    void deleteTaskList(Long id);


}
