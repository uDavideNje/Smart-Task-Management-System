package me.David.Smart.Task.Management.service;

import lombok.AllArgsConstructor;
import me.David.Smart.Task.Management.model.Status;
import me.David.Smart.Task.Management.model.Task;
import me.David.Smart.Task.Management.model.dto.TaskDTO;
import me.David.Smart.Task.Management.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(Task task){
        return taskRepository.save(task);
    }



    public List<Task> findTaskByStatus(Status status){
        return taskRepository.getTaskByStatus(status);
    }

    public Task findTaskById(UUID id){
        return taskRepository.getTaskById(id);
    }

    public void deleteTaskById(UUID id){
        taskRepository.deleteById(id);
    }

}
