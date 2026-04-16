package me.david.smart.task.management.control;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.david.smart.task.management.model.Status;
import me.david.smart.task.management.model.dto.CreateTaskRequest;
import me.david.smart.task.management.model.dto.TaskDTO;
import me.david.smart.task.management.model.dto.TaskListDTO;
import me.david.smart.task.management.service.TaskService;
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