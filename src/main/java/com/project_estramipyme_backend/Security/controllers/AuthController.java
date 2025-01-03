package com.project_estramipyme_backend.Security.controllers;

import com.project_estramipyme_backend.Security.models.AuthRequest;
import com.project_estramipyme_backend.Security.models.AuthResponse;
import com.project_estramipyme_backend.Security.security.CustomUserDetailsService;
import com.project_estramipyme_backend.Security.security.JwtUtil;
import com.project_estramipyme_backend.user.model.UserModel;
import com.project_estramipyme_backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Autenticación", description = "API de autenticación de usuarios")
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserService personService ;

    public AuthController(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserService personService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.personService = personService;
    }

    @Operation(summary = "User registration",
            description = "Create a new user in the system")
    @ApiResponse(responseCode = "200", description = "User successfully registered")

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));  // Codifica la contraseña
        personService.saveUser(person);
        return ResponseEntity.ok().body("{\"message\": \"User successfully registered\"}");
    }


    @Operation(summary = "User login",
            description = "Authenticate a user and return JWT token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful login"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
