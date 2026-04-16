package me.david.smart.task.management.model.dto;

import jakarta.validation.constraints.*;
import me.david.smart.task.management.model.Priority;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTaskRequest( @NotBlank String title,
                                 String description,
                                 LocalDateTime deadline,
                                 @NotNull Priority priority,
                                 @NotNull UUID userId  ) {
}
