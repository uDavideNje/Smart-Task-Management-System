package me.david.smart.task.management.model.mapper;

import me.david.smart.task.management.model.User;
import me.david.smart.task.management.model.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toUserDTO(User user){
        return new UserDTO(user.getId(),
                user.getUsername(),
                user.getEmail());
    }
}
