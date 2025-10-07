package com.pattama.task_tracker.controller;

import com.pattama.task_tracker.dto.request.TaskRequest;
import com.pattama.task_tracker.dto.response.TaskResponse;
import com.pattama.task_tracker.service.Impl.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasklists/{taskListId}/tasks")
public class TaskController {

    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @PathVariable Long taskListId,
            @RequestBody TaskRequest request
    ) {
        TaskResponse task = taskService.createTask(taskListId, request);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTaskByTaskListId(
            @PathVariable Long taskListId
    ) {
        List<TaskResponse> allTasks = taskService.getTasksByTaskListId(taskListId);
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskByTaskListIdAndId(
            @PathVariable Long taskListId,
            @PathVariable("id") Long taskId
    ) {
        TaskResponse taskById = taskService.getTaskByTaskListIdAndId(taskListId, taskId);
        return ResponseEntity.ok(taskById);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long taskListId,
            @PathVariable Long taskId,
            @RequestBody TaskRequest taskRequest
    ) {

        TaskResponse taskUpdated = taskService.updateTask(taskListId, taskId, taskRequest);
        return ResponseEntity.ok(taskUpdated);

    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(
            @PathVariable Long taskListId,
            @PathVariable Long taskId
    ) {
        taskService.deleteTask(taskListId, taskId);
        return ResponseEntity.ok("task deleted successfully");
    }
}
