package com.project_estramipyme_backend.user.controller;


import com.project_estramipyme_backend.user.model.UserModel;
import com.project_estramipyme_backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    } //Fin inyección de dependencias

    @GetMapping(path = "/getUsers")
    public ArrayList<UserModel> getUser() {
        return this.userService.getUser();
    }

    @PostMapping(path = "/newUser")
    public UserModel saveUser(@RequestBody UserModel user) {
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id) {
        return this.userService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public UserModel updateUserById(@RequestBody UserModel request, @PathVariable Long id) {
        return this.userService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean isOk = this.userService.deleteUser(id);

        if (isOk) {
            return "User with id " + id + " delete!";
        } else {
            return "Error";
        }
    }

}
