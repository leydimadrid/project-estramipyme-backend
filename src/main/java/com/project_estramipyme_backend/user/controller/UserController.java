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

@Tag(name = "Usuarios", description = "API para gestión de usuarios del sistema")
@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    } //Fin inyección de dependencias


    @Operation(summary = "Listar usuarios",
            description = "Obtiene la lista de todos los usuarios registrados")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente")
    @GetMapping(path = "/getUsers")
    public ArrayList<UserModel> getUser() {
        return this.userService.getUser();
    }


    @Operation(summary = "Crear nuevo usuario",
            description = "Registra un nuevo usuario en el sistema")
    @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente")
    @PostMapping(path = "/newUser")
    public UserModel saveUser(@RequestBody UserModel user) {
        return this.userService.saveUser(user);
    }



    @Operation(summary = "Obtener usuario por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(
            @Parameter(description = "ID del usuario") @PathVariable("id") Long id) {
        return this.userService.getById(id);
    }

}
