package me.David.Smart.Task.Management.model.mapper;

import me.David.Smart.Task.Management.model.Status;
import me.David.Smart.Task.Management.model.Task;
import me.David.Smart.Task.Management.model.dto.CreateTaskRequest;
import me.David.Smart.Task.Management.model.dto.TaskDTO;
import me.David.Smart.Task.Management.model.dto.TaskListDTO;
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
                task.getPriority()
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

    public Task toTask(CreateTaskRequest request) {
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .deadline(request.deadline())
                .priority(request.priority())
                .status(Status.TODO) // sensible default
                .build();
    }
}
