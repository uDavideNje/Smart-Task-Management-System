package me.David.Smart.Task.Management.control;

import lombok.AllArgsConstructor;
import me.David.Smart.Task.Management.model.User;
import me.David.Smart.Task.Management.model.dto.CreateUserRequest;
import me.David.Smart.Task.Management.model.dto.TaskListDTO;
import me.David.Smart.Task.Management.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<TaskListDTO> getTasksForUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getTasksForUser(userId));
    }


}
