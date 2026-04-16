package me.david.smart.task.management.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.david.smart.task.management.model.Task;
import me.david.smart.task.management.model.User;
import me.david.smart.task.management.model.dto.CreateUserRequest;
import me.david.smart.task.management.model.dto.TaskListDTO;
import me.david.smart.task.management.model.dto.UserDTO;
import me.david.smart.task.management.exception.EmailAlreadyExistsException;
import me.david.smart.task.management.exception.UserNotFoundException;
import me.david.smart.task.management.model.mapper.TaskMapper;
import me.david.smart.task.management.model.mapper.UserMapper;
import me.david.smart.task.management.repository.TaskRepository;
import me.david.smart.task.management.model.dto.UpdateUserRequest;
import me.david.smart.task.management.repository.UserRepository;
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
