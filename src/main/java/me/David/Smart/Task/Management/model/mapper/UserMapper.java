package me.David.Smart.Task.Management.model.mapper;

import me.David.Smart.Task.Management.model.User;
import me.David.Smart.Task.Management.model.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toUserDTO(User user){
        return new UserDTO(user.getId(),
                user.getUsername(),
                user.getEmail());
    }
}
