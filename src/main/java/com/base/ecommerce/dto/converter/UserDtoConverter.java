package com.base.ecommerce.dto.converter;

import com.base.ecommerce.dto.UserDto;
import com.base.ecommerce.model.user.User;

public final class UserDtoConverter {

    private UserDtoConverter() {
        throw new RuntimeException("util");
    }

    public static UserDto convertToUserDtoToUser(User from) {
        return new UserDto(
                from.getId(),
                from.getName(),
                from.getUsername(),
                from.getEmail(),
                from.getPassword()
        );
    }
}
