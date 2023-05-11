package com.example.course.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.course.Auth.*;
import com.example.course.User.User;
import com.example.course.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public  class AuthControllerTest {

    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authenticationController = new AuthenticationController(authenticationService, new User());
    }

    @Test
    public void testRegister() throws UserAlreadyExistsException {
        RegisterRequest request = new RegisterRequest();
        AutheticationResponse response = new AutheticationResponse("token", null);
        when(authenticationService.register(any(RegisterRequest.class))).thenReturn(response);

        ResponseEntity<AutheticationResponse> result = authenticationController.register(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(response);
    }

    @Test
    public void testRegisterUserAlreadyExists() throws UserAlreadyExistsException {
        RegisterRequest request = new RegisterRequest();
        when(authenticationService.register(any(RegisterRequest.class))).thenThrow(new UserAlreadyExistsException());

        ResponseEntity<AutheticationResponse> result = authenticationController.register(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(result.getBody().getError()).isNotNull();
    }

    @Test
    public void testAuthenticate() {
        AutheticationRequest request = new AutheticationRequest();
        AutheticationResponse response = new AutheticationResponse("token", null);
        when(authenticationService.authenticate(any(AutheticationRequest.class))).thenReturn(response);

        ResponseEntity<AutheticationResponse> result = authenticationController.autheticate(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(response);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "test@example.com";
        User user = new User();
        when(authenticationService.getUserByEmail(email)).thenReturn(user);

        ResponseEntity<User> result = authenticationController.getUserByEmail(email);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(user);
    }

    @Test
    public void testGetUserByEmailNotFound() {
        String email = "test@example.com";
        when(authenticationService.getUserByEmail(email)).thenReturn(null);

        try {
            authenticationController.getUserByEmail(email);
        } catch (ResponseStatusException e) {
            assertThat(e.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            assertThat(e.getReason()).isEqualTo("Пользователь с указанной электронной почтой не найден");
        }
    }
}