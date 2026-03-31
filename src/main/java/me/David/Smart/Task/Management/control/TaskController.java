package me.David.Smart.Task.Management.control;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.David.Smart.Task.Management.model.Status;
import me.David.Smart.Task.Management.model.Task;
import me.David.Smart.Task.Management.model.TaskList;
import me.David.Smart.Task.Management.model.dto.TaskListDTO;
import me.David.Smart.Task.Management.repository.TaskRepository;
import me.David.Smart.Task.Management.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    //private final UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task){

        Task savedTask = taskService.createTask(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);

    }

    @GetMapping("/getTaskById")
    public ResponseEntity<Task> getTaskById(@Valid UUID id){
        Task retrivedTask = taskService.findTaskById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(retrivedTask);
    }

    @GetMapping("/getTaskById")
    public ResponseEntity<TaskListDTO> getTaskByStatus(@Valid Status status){
        TaskListDTO retrivedTasks = new TaskListDTO(taskService.findTaskByStatus(status));

        return ResponseEntity.status(HttpStatus.FOUND).body(retrivedTasks);
    }

    @DeleteMapping("/delete")
    public void deleteTaskById(UUID id){
        taskService.deleteTaskById(id);
    }
}
