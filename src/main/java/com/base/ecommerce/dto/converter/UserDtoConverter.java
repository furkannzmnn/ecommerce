package com.base.ecommerce.dto.converter;

import com.base.ecommerce.dto.UserDto;
import com.base.ecommerce.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto convertToUserDtoToUser(User from) {
        return new UserDto(
                from.getId(),
                from.getName(),
                from.getUsername(),
                from.getEmail(),
                from.getPassword()
        );
    }
}
