package com.patdimby.simplerest.mapper;

import com.patdimby.simplerest.dto.UserDto;
import com.patdimby.simplerest.model.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UserConverter {

    public User convertDtoToModel(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setRole(userDto.getRole());
        user.setConfirmpassword(userDto.getConfirmpassword());
        user.setSex(userDto.getSex());
        return user;
    }

}
