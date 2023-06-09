package com.example.course.Auth;


import com.example.course.User.User;
import com.example.course.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final User user;

    @PostMapping("/register")
    public ResponseEntity<AutheticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (UserAlreadyExistsException e){
            return ResponseEntity.badRequest().body(new AutheticationResponse (null, e));
        }

    }
    @PostMapping("/authenticate")
    public ResponseEntity<AutheticationResponse> autheticate(
            @RequestBody AutheticationRequest request
    ){

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = authenticationService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанной электронной почтой не найден");
        }
    }
}
