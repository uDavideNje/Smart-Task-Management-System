package me.David.Smart.Task.Management.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.David.Smart.Task.Management.model.Task;
import me.David.Smart.Task.Management.model.User;
import me.David.Smart.Task.Management.model.dto.CreateUserRequest;
import me.David.Smart.Task.Management.model.dto.TaskListDTO;
import me.David.Smart.Task.Management.model.dto.UserDTO;
import me.David.Smart.Task.Management.exception.EmailAlreadyExistsException;
import me.David.Smart.Task.Management.exception.UserNotFoundException;
import me.David.Smart.Task.Management.model.mapper.TaskMapper;
import me.David.Smart.Task.Management.model.mapper.UserMapper;
import me.David.Smart.Task.Management.repository.TaskRepository;
import me.David.Smart.Task.Management.model.dto.UpdateUserRequest;
import me.David.Smart.Task.Management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;
    private UserMapper userMapper;

    public UserDTO createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email already in use: " + request.email());
        }
        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .build();
        return userMapper.toUserDTO(userRepository.save(user));
    }

    public UserDTO getUserByID(UUID userId){
        return userRepository.findById(userId).
                map(userMapper::toUserDTO).
                orElseThrow(()-> new UserNotFoundException("User not found with id: "+ userId));
    }

    @Transactional
    public TaskListDTO getTasksForUser(UUID userId){
        userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found with id: " + userId));

        List<Task> tasks = taskRepository.findByUserId(userId);
        return taskMapper.toTaskListDTO(tasks);

    }

    @Transactional
    public void updateUser(UUID userId, UpdateUserRequest request) {
        if (request.email() != null && userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email already in use: " + request.email());
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        if (request.username() != null) user.setUsername(request.username());
        if (request.email() != null) user.setEmail(request.email());
    }

    @Transactional
    public void deleteUser(UUID userId){
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }


}
