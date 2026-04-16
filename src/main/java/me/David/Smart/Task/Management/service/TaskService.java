package me.david.smart.task.management.service;

import lombok.AllArgsConstructor;
import me.david.smart.task.management.model.Status;
import me.david.smart.task.management.model.Task;
import me.david.smart.task.management.model.User;
import me.david.smart.task.management.model.dto.CreateTaskRequest;
import me.david.smart.task.management.model.dto.TaskDTO;
import me.david.smart.task.management.model.dto.TaskListDTO;
import me.david.smart.task.management.exception.TaskNotFoundException;
import me.david.smart.task.management.exception.UserNotFoundException;
import me.david.smart.task.management.model.mapper.TaskMapper;
import me.david.smart.task.management.repository.TaskRepository;
import me.david.smart.task.management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    public TaskDTO createTask(CreateTaskRequest request){
        User user = userRepository.findById(request.userId())
                .orElseThrow(()-> new UserNotFoundException("User not found with id: " + request.userId()));
        Task task = taskMapper.toTask(request, user);
        return taskMapper.toTaskDTO(taskRepository.save(task));
    }



    public TaskListDTO findTaskByStatus(Status status){
        List<Task> tasks = taskRepository.findByStatus(status);
        return taskMapper.toTaskListDTO(tasks);
    }

    public TaskDTO findTaskById(UUID id){
        return taskRepository.findById(id)
                .map(taskMapper::toTaskDTO)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

    }

    public void deleteTaskById(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

}
