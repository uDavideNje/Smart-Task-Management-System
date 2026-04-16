package me.david.smart.task.management.model.dto;

public record UpdateUserRequest(
        String username,
        String email
) {}
