package me.David.Smart.Task.Management.control;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.David.Smart.Task.Management.model.Task;
import me.David.Smart.Task.Management.model.dto.TaskDTO;
import me.David.Smart.Task.Management.repository.TaskRepository;
import me.David.Smart.Task.Management.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    //private final UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody Task taskDTO){

        Task task = Task.builder()
                .title(taskDTO.getTitle())
                .priority(taskDTO.getPriority())
                .status(taskDTO.getStatus())
                .build();

        return taskRepository.save(task);

    }
}
