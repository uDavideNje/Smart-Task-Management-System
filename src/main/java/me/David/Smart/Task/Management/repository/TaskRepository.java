package me.david.smart.task.management.repository;

import me.david.smart.task.management.model.Status;
import me.david.smart.task.management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByUserId(UUID userId);
    List<Task> findByStatus(Status status);
}
