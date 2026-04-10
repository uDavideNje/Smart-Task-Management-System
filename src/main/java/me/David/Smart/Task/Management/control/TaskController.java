package me.David.Smart.Task.Management.control;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.David.Smart.Task.Management.model.Status;
import me.David.Smart.Task.Management.model.dto.CreateTaskRequest;
import me.David.Smart.Task.Management.model.dto.TaskDTO;
import me.David.Smart.Task.Management.model.dto.TaskListDTO;
import me.David.Smart.Task.Management.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody CreateTaskRequest task){
        TaskDTO savedTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable UUID id){
        TaskDTO retrievedTask = taskService.findTaskById(id);
        return ResponseEntity.ok(retrievedTask);
    }

    @GetMapping
    public ResponseEntity<TaskListDTO> getTasksByStatus(@RequestParam Status status){
        TaskListDTO retrievedTasks = taskService.findTaskByStatus(status);
        return ResponseEntity.ok(retrievedTasks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable UUID id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}