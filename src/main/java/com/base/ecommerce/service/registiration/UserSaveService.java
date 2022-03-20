package com.base.ecommerce.service.registiration;

import com.base.ecommerce.dto.UserDto;
import com.base.ecommerce.dto.converter.UserDtoConverter;
import com.base.ecommerce.dto.request.UserSaveRequest;
import com.base.ecommerce.model.user.User;
import com.base.ecommerce.model.user.VerificationToken;
import com.base.ecommerce.repository.UserRepository;
import com.base.ecommerce.repository.VerificationTokenRepository;
import com.base.ecommerce.service.MailService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserSaveService {

    private final UserRepository userRepository;
    private final MailService mailService;
    private final UserDtoConverter converter;
    private final VerificationTokenRepository tokenRepository;

    public UserSaveService(UserRepository userRepository, MailService mailService, UserDtoConverter converter, VerificationTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.converter = converter;
        this.tokenRepository = tokenRepository;
    }

    public UserDto register (UserSaveRequest user)  {
        User userEntity = new User.Builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .username(user.getUserName())
                .build();

        try {
            userRepository.save(userEntity);
            mailService.sendGenericMessage("user save successfully", "","");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return converter.convertToUserDtoToUser(userEntity);

    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);

        tokenRepository.save(myToken);

    }

    public VerificationToken getVerificationToken(String token) {
       return tokenRepository.findByToken(token);
    }

    public void saveUser(UserSaveRequest user) {
        User userEntity = new User.Builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .username(user.getUserName())
                .build();
        userRepository.save(userEntity);
    }
}
