package com.pattama.task_tracker.service.Impl;

import com.pattama.task_tracker.dto.request.TaskListRequest;
import com.pattama.task_tracker.dto.response.TaskListResponse;
import com.pattama.task_tracker.entity.TaskList;
import com.pattama.task_tracker.exception.TaskListException;
import com.pattama.task_tracker.mapper.TaskListMapper;
import com.pattama.task_tracker.repository.TaskListRepository;
import com.pattama.task_tracker.service.ITaskListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskListServiceImpl implements ITaskListService {

    private final TaskListMapper taskListMapper;
    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListMapper taskListMapper, TaskListRepository taskListRepository) {
        this.taskListMapper = taskListMapper;
        this.taskListRepository = taskListRepository;
    }


    @Override
    public TaskListResponse createTaskList(TaskListRequest request) {

        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw TaskListException.titleNull();
        }

        taskListRepository.findByTitle(request.getTitle())
                .ifPresent(t -> { throw TaskListException.titleDuplicate(); });

        TaskList entity = taskListMapper.toEntity(request);

        TaskList saved = taskListRepository.save(entity);

        return taskListMapper.toTaskListResponse(saved);
    }

    @Override
    public List<TaskListResponse> getAllTaskList() {
        List<TaskList> taskLists = taskListRepository.findAll();
        return taskLists.stream()
                .map(taskListMapper::toTaskListResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskListResponse getTaskListById(Long taskListId) {

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(TaskListException::taskListNotFound);

        return taskListMapper.toTaskListResponse(taskList);
    }

    @Override
    public TaskListResponse updateTaskList(Long taskListId, TaskListRequest request) {

        TaskList existing = taskListRepository.findById(taskListId)
                .orElseThrow(TaskListException::taskListNotFound);

        if (!existing.getTitle().equals(request.getTitle())) {
            taskListRepository.findByTitle(request.getTitle())
                    .ifPresent(t -> { throw TaskListException.titleDuplicate(); });
        }

       taskListMapper.updateEntityFromRequest(request, existing);

        TaskList updated = taskListRepository.save(existing);

        return taskListMapper.toTaskListResponse(updated);
    }

    @Override
    public void deleteTaskList(Long id) {

        TaskList existing = taskListRepository.findById(id)
                .orElseThrow(TaskListException::taskListNotFound);

        taskListRepository.delete(existing);

    }
}
