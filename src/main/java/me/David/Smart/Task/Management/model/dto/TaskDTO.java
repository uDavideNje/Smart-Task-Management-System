package me.David.Smart.Task.Management.model.dto;

import me.David.Smart.Task.Management.model.Priority;
import me.David.Smart.Task.Management.model.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(UUID id,
                      String title,
                      String description,
                      LocalDateTime deadline,
                      Status status,
                      Priority priority) {

}
