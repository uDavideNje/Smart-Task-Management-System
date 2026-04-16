package me.david.smart.task.management.model.mapper;

import me.david.smart.task.management.model.Status;
import me.david.smart.task.management.model.Task;
import me.david.smart.task.management.model.User;
import me.david.smart.task.management.model.dto.CreateTaskRequest;
import me.david.smart.task.management.model.dto.TaskDTO;
import me.david.smart.task.management.model.dto.TaskListDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {

    public TaskDTO toTaskDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDeadline(),
                task.getStatus(),
                task.getPriority(),
                task.getUser().getId()
        );
    }

    public List<TaskDTO> toTaskDTOList(List<Task> tasks) {
        return tasks.stream()
                .map(this::toTaskDTO)
                .toList();
    }

    public TaskListDTO toTaskListDTO(List<Task> tasks) {
        int count = tasks.size();

        double progress = count == 0 ? 0 :
                tasks.stream()
                        .filter(t -> t.getStatus() == Status.COMPLETED)
                        .count() * 100.0 / count;

        return new TaskListDTO(
                null, // or some grouping ID if you have one
                "Task List",
                "Generated list",
                count,
                progress,
                toTaskDTOList(tasks)
        );
    }

    public Task toTask(CreateTaskRequest request, User user) {
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .deadline(request.deadline())
                .priority(request.priority())
                .status(Status.TODO)
                .user(user)
                .build();
    }
}
