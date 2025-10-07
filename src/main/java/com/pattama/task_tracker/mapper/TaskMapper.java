package com.pattama.task_tracker.mapper;

import com.pattama.task_tracker.dto.request.TaskRequest;
import com.pattama.task_tracker.dto.response.TaskResponse;
import com.pattama.task_tracker.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface TaskMapper {

    @Mapping(target = "taskList", ignore = true)
    Task toEntity(TaskRequest taskRequest);

    @Mapping(target = "taskListId", source = "taskList.id")
    @Mapping(target = "taskListTitle", source = "taskList.title")
    TaskResponse toTaskResponse(Task task);

    List<TaskResponse> toResponseList(List<Task> tasks);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "taskList", ignore = true)
    void updateEntityFromRequest(TaskRequest request, @MappingTarget Task entity);

}
