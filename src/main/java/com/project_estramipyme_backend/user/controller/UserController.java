package com.project_estramipyme_backend.user.controller;


import com.project_estramipyme_backend.user.model.UserModel;
import com.project_estramipyme_backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Tag(name = "Users", description = "API for system user management")
@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    } //Fin inyección de dependencias


    @Operation(summary = "List users",
            description = "Gets the list of all registered users")
    @ApiResponse(responseCode = "200", description = "List of users successfully obtained")
    @GetMapping(path = "/getUsers")
    public ArrayList<UserModel> getUser() {
        return this.userService.getUser();
    }


    @Operation(summary = "Create new user",
            description = "Register a new user in the system")
    @ApiResponse(responseCode = "200", description = "User successfully created")
    @PostMapping(path = "/newUser")
    public UserModel saveUser(@RequestBody UserModel user) {
        return this.userService.saveUser(user);
    }



    @Operation(summary = "Get user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(
            @Parameter(description = "User ID") @PathVariable("id") Long id) {
        return this.userService.getById(id);
    }

}
