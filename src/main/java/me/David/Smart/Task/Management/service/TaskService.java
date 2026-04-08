package me.David.Smart.Task.Management.service;

import lombok.AllArgsConstructor;
import me.David.Smart.Task.Management.model.Status;
import me.David.Smart.Task.Management.model.Task;
import me.David.Smart.Task.Management.model.User;
import me.David.Smart.Task.Management.model.dto.CreateTaskRequest;
import me.David.Smart.Task.Management.model.dto.TaskDTO;
import me.David.Smart.Task.Management.model.dto.TaskListDTO;
import me.David.Smart.Task.Management.model.exception.TaskNotFoundException;
import me.David.Smart.Task.Management.model.exception.UserNotFoundException;
import me.David.Smart.Task.Management.model.mapper.TaskMapper;
import me.David.Smart.Task.Management.repository.TaskRepository;
import me.David.Smart.Task.Management.repository.UserRepository;
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
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.userId()));

        Task task = taskMapper.toTask(request, user);
        return taskMapper.toTaskDTO(taskRepository.save(task));
    }



    public TaskListDTO findTaskByStatus(Status status){
        List<Task> tasks = taskRepository.getTaskByStatus(status);
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
