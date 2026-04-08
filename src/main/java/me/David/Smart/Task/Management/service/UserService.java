package me.David.Smart.Task.Management.service;

import me.David.Smart.Task.Management.model.Task;
import me.David.Smart.Task.Management.model.dto.TaskListDTO;
import me.David.Smart.Task.Management.model.exception.UserNotFoundException;
import me.David.Smart.Task.Management.model.mapper.TaskMapper;
import me.David.Smart.Task.Management.repository.TaskRepository;
import me.David.Smart.Task.Management.repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class UserService {
    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;

    public TaskListDTO getTasksForUser(UUID userId){
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        List<Task> tasks = taskRepository.findByUserId(userId);
        return taskMapper.toTaskListDTO(tasks);

    }
}
