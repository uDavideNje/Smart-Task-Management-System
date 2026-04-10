package me.David.Smart.Task.Management.repository;

import me.David.Smart.Task.Management.model.Status;
import me.David.Smart.Task.Management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByUserId(UUID userId);
    List<Task> findByStatus(Status status);
}
