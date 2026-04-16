package me.david.smart.task.management.control;

import lombok.AllArgsConstructor;
import me.david.smart.task.management.model.dto.CreateUserRequest;
import me.david.smart.task.management.model.dto.TaskListDTO;
import me.david.smart.task.management.model.dto.UpdateUserRequest;
import me.david.smart.task.management.model.dto.UserDTO;
import me.david.smart.task.management.service.UserService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserRequest request){
        UserDTO userDTO= userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id){

        UserDTO user = userService.getUserByID(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable UUID id, @RequestBody UpdateUserRequest request){
        userService.updateUser(id,request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUSer(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public String test() {
        return "working";
    }
}
