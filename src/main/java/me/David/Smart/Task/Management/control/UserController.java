package me.David.Smart.Task.Management.control;

import me.David.Smart.Task.Management.model.dto.TaskListDTO;
import me.David.Smart.Task.Management.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public class UserController {

    private UserService userService;

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<TaskListDTO> getTasksForUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getTasksForUser(userId));
    }
}
