package me.David.Smart.Task.Management.repository;

import me.David.Smart.Task.Management.model.Status;
import me.David.Smart.Task.Management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    Task getTaskById(UUID id);
    List<Task> getTaskByStatus(Status status);
}
