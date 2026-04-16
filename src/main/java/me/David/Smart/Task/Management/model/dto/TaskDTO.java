package me.david.smart.task.management.model.dto;

import me.david.smart.task.management.model.Priority;
import me.david.smart.task.management.model.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(UUID id,
                      String title,
                      String description,
                      LocalDateTime deadline,
                      Status status,
                      Priority priority,
                      UUID userId  ) {

}
