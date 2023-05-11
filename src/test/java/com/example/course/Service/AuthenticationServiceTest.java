package com.example.course.Service;

import com.example.course.Auth.AuthenticationService;
import com.example.course.Auth.AutheticationRequest;
import com.example.course.Auth.AutheticationResponse;
import com.example.course.Auth.RegisterRequest;
import com.example.course.Cart.Cart;
import com.example.course.Configuration.JwtService;
import com.example.course.Roles.Role;
import com.example.course.User.User;
import com.example.course.User.UserRepository;
import com.example.course.UserAlreadyExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        // Инициализация мок-объектов
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        // Очистка тестовых данных после запуска тестов
    }


    @Test
    public void testRegister() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@test.com");
        request.setPassword("test123");

        User user = User.builder()
                .id(1L) // set a dummy ID
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(user)).thenReturn(user);

        Cart newCart = new Cart(user);
        user.setCart(newCart);
        Mockito.when(jwtService.generateToken(user)).thenReturn("jwtToken");

        AutheticationResponse response = authenticationService.register(request);

       // assertEquals("jwtToken", response.getToken());
    }

    @Test
    public void testRegisterThrowsUserAlreadyExistsException() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@test.com");
        request.setPassword("test123");

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));

//        assertThrows(UserAlreadyExistsException.class, () -> {
//            authenticationService.register(request);
//        });
    }

    @Test
    public void testAuthenticate() {
        AutheticationRequest request = new AutheticationRequest();
        request.setEmail("test@test.com");
        request.setPassword("test123");

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        Mockito.when(jwtService.generateToken(user)).thenReturn("jwtToken");

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

//        AutheticationResponse response = authenticationService.authenticate(request);

      //  assertEquals("jwtToken", response.getToken());
    }

    @Test
    public void testAuthenticateThrowsRuntimeException() {
        AutheticationRequest request = new AutheticationRequest();
        request.setEmail("test@test.com");
        request.setPassword("test123");

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            authenticationService.authenticate(request);
        });
    }
}