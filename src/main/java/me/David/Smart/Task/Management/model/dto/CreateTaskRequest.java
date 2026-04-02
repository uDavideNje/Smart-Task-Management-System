package me.David.Smart.Task.Management.model.dto;

import me.David.Smart.Task.Management.model.Priority;

import java.time.LocalDateTime;

public record CreateTaskRequest(String title,
                                String description,
                                LocalDateTime deadline,
                                Priority priority) {
}
