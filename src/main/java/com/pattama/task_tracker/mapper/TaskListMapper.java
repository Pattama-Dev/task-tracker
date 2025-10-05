package com.pattama.task_tracker.mapper;

import com.pattama.task_tracker.dto.request.TaskListRequest;
import com.pattama.task_tracker.dto.request.TaskRequest;
import com.pattama.task_tracker.dto.response.TaskListResponse;
import com.pattama.task_tracker.entity.Task;
import com.pattama.task_tracker.entity.TaskList;
import com.pattama.task_tracker.entity.TaskStatus;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface TaskListMapper {

    TaskList toEntity(TaskListRequest taskListRequest);

    TaskListResponse toTaskListResponse(TaskList taskList);

    @AfterMapping
    default void calculateProgress(
            TaskList taskList,
            @MappingTarget TaskListResponse taskListResponse
    ) {

        List<Task> tasks = taskList.getTasks() != null ? taskList.getTasks() : Collections.emptyList();

        int totalTasks = tasks.size();

        if (totalTasks == 0) {
            taskListResponse.setTotalTasks(0);
            taskListResponse.setCompleteTasks(0);
            taskListResponse.setProgress(0.0);
            return;
        }

        int completeTasks = (int) tasks.stream()
                .filter(t -> t.getStatus() == TaskStatus.COMPLETED)
                .count();

        double progress = (double) (completeTasks / totalTasks) * 100.0;

        taskListResponse.setTotalTasks(totalTasks);
        taskListResponse.setCompleteTasks(completeTasks);
        taskListResponse.setProgress(progress);
    }

}
