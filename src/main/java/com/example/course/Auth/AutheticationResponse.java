package com.example.course.Auth;

import com.example.course.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutheticationResponse {
    private String token;
    private UserAlreadyExistsException e;



}
