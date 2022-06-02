package com.base.ecommerce.api;

import com.base.ecommerce.core.utils.ResponseApi;
import com.base.ecommerce.dto.UserDto;
import com.base.ecommerce.dto.request.UserSaveRequest;
import com.base.ecommerce.model.user.User;
import com.base.ecommerce.model.user.VerificationToken;
import com.base.ecommerce.service.registiration.UserSaveService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseRestController{

    private final UserSaveService userSaveService;
    private final ApplicationEventPublisher publisher;

    public UserController(UserSaveService userSaveService, ApplicationEventPublisher publisher) {
        this.userSaveService = userSaveService;
        this.publisher = publisher;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseApi> register(@RequestBody UserSaveRequest user, HttpServletRequest request) {

        UserDto userDto = userSaveService.register(user);

        return ResponseEntity.ok(new ResponseApi
                .ResponseBuilder()
                .timestamps(LocalDateTime.now())
                .Data(Map.of("user", userDto))
                .builder());
    }

    @GetMapping("/activate/{token}")
    public ResponseEntity<String> activateUser(@PathVariable String token, HttpServletRequest request) {

        Locale locale = request.getLocale();
        VerificationToken verificationToken = userSaveService.getVerificationToken(token);

        if (verificationToken == null) {
            return ResponseEntity.badRequest().body("redirect:/badUser.html?lang=" + locale.getLanguage());
        }

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();

        if (verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime() <= 0) {
            return ResponseEntity.badRequest().body("redirect:/badUser.html?lang=" + locale.getLanguage());
        }
        user.setEnabled(true);
        UserSaveRequest userSaveRequest = new UserSaveRequest(
                user.getName(), user.getUsername(), user.getEmail(), user.getPassword());
        userSaveService.saveUser(userSaveRequest);

        return ResponseEntity.ok("redirect:/login.html?lang=" + request.getLocale().getLanguage());
    }

}
