package com.example.course.Auth;
import com.example.course.Cart.Cart;
import com.example.course.Configuration.JwtService;
import com.example.course.Roles.Role;
import com.example.course.User.User;
import com.example.course.User.UserRepository;
import com.example.course.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AutheticationResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists.");
        }
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        Cart newCart = new Cart(user);
        user.setCart(newCart);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AutheticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AutheticationResponse authenticate(AutheticationRequest request) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        var user  = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AutheticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Пользователь с указанной электронной почтой не найден"));
    }
}
