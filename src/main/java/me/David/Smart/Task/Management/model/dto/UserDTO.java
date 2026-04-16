package me.david.smart.task.management.model.dto;

import java.util.UUID;

public record UserDTO(UUID id, String username, String email) {}
