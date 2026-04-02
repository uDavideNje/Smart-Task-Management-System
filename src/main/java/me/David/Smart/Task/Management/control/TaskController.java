package me.David.Smart.Task.Management.control;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.David.Smart.Task.Management.model.Status;
import me.David.Smart.Task.Management.model.Task;
import me.David.Smart.Task.Management.model.TaskList;
import me.David.Smart.Task.Management.model.dto.TaskDTO;
import me.David.Smart.Task.Management.model.dto.TaskListDTO;
import me.David.Smart.Task.Management.model.mapper.TaskMapper;
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
    private final TaskMapper taskMapper;
    //private final UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody Task task){

        TaskDTO savedTask = taskService.createTask(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);

    }

    @GetMapping("/getTaskById")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable UUID id){
        TaskDTO retrivedTask = taskService.findTaskById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(retrivedTask);
    }

    @GetMapping("/getTaskByStatus")
    public ResponseEntity<TaskListDTO> getTaskByStatus(@RequestParam Status status){
        TaskListDTO retrivedTasks = taskService.findTaskByStatus(status);

        return ResponseEntity.status(HttpStatus.FOUND).body(retrivedTasks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable UUID id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
