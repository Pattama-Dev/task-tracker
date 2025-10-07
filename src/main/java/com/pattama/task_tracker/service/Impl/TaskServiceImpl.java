package com.pattama.task_tracker.service.Impl;

import com.pattama.task_tracker.dto.request.TaskRequest;
import com.pattama.task_tracker.dto.response.TaskResponse;
import com.pattama.task_tracker.entity.Task;
import com.pattama.task_tracker.entity.TaskList;
import com.pattama.task_tracker.exception.TaskException;
import com.pattama.task_tracker.exception.TaskListException;
import com.pattama.task_tracker.mapper.TaskMapper;
import com.pattama.task_tracker.repository.TaskListRepository;
import com.pattama.task_tracker.repository.TaskRepository;
import com.pattama.task_tracker.service.ITaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements ITaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;


    public TaskServiceImpl(TaskMapper taskMapper, TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public TaskResponse createTask(Long taskListId, TaskRequest request) {

        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw TaskException.titleNull();
        }

        taskRepository.findByTitle(request.getTitle())
                .ifPresent(t -> {throw TaskException.titleDuplicate();});


        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(TaskException::taskNotFound);

        Task task = taskMapper.toEntity(request);

        task.setTaskList(taskList);

        Task savedTask = taskRepository.save(task);

        return taskMapper.toTaskResponse(savedTask);

    }


    @Override
    public List<TaskResponse> getTasksByTaskListId(Long taskListId) {

        boolean taskListExists = taskListRepository.existsById(taskListId);
        if (!taskListExists) {
            throw TaskException.taskNotFound();
        }

        List<Task> tasks = taskRepository.findByTaskListId(taskListId);

//        return tasks.stream()
//                .map(taskMapper::toTaskResponse)
//                .collect(Collectors.toList());

        return taskMapper.toResponseList(tasks);
    }

    @Override
    public TaskResponse getTaskByTaskListIdAndId(Long taskListId, Long taskId) {

        boolean taskListExists = taskListRepository.existsById(taskListId);
        if (!taskListExists) {
            throw TaskException.taskNotFound();
        }

        Task task = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(TaskException::taskNotFound);

        return taskMapper.toTaskResponse(task);
    }


    @Override
    public TaskResponse updateTask(Long taskListId, Long taskId, TaskRequest request) {

        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(TaskException::taskNotFound);

        if (!existingTask.getTitle().equals(request.getTitle())) {
            taskRepository.findByTitle(request.getTitle())
                    .ifPresent(t -> {throw TaskException.titleDuplicate();});
        }

        taskMapper.updateEntityFromRequest(request, existingTask);

        Task updated = taskRepository.save(existingTask);

        return taskMapper.toTaskResponse(updated);
    }

    @Override
    public void deleteTask(Long taskListId, Long taskId) {

        Task existing = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(TaskException::taskNotFound);

        taskRepository.delete(existing);

    }


}
