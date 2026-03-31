package me.David.Smart.Task.Management.control;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.David.Smart.Task.Management.model.Task;
import me.David.Smart.Task.Management.repository.TaskRepository;
import me.David.Smart.Task.Management.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    //private final UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task){

        Task savedTask = taskService.createTask(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(task);

    }
}
