package com.pattama.task_tracker.controller;

import com.pattama.task_tracker.dto.request.TaskListRequest;
import com.pattama.task_tracker.dto.response.TaskListResponse;
import com.pattama.task_tracker.service.Impl.TaskListServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasklists")
public class TaskListController {

    private final TaskListServiceImpl taskListService;

    public TaskListController(TaskListServiceImpl taskListService) {
        this.taskListService = taskListService;
    }

    @PostMapping
    public ResponseEntity<TaskListResponse> createTaskList(
            @RequestBody TaskListRequest request
    ) {

        TaskListResponse response = taskListService.createTaskList(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskListResponse>> getAllTaskList() {
        return ResponseEntity.ok(taskListService.getAllTaskList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskListResponse> getTaskListById(@PathVariable Long id) {
        return ResponseEntity.ok(taskListService.getTaskListById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskListResponse> updateTaskList(
            @PathVariable Long id,
            @RequestBody TaskListRequest request) {

        TaskListResponse response = taskListService.updateTaskList(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskList(
            @PathVariable Long id
    ) {
        taskListService.deleteTaskList(id);
        return ResponseEntity.ok("task list deleted successfully");

    }

}
