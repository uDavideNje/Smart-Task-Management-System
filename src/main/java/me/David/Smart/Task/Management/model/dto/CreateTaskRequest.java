package me.David.Smart.Task.Management.model.dto;

import jakarta.validation.constraints.*;
import me.David.Smart.Task.Management.model.Priority;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTaskRequest( @NotBlank String title,
                                 String description,
                                 LocalDateTime deadline,
                                 @NotNull Priority priority,
                                 @NotNull UUID userId  ) {
}
