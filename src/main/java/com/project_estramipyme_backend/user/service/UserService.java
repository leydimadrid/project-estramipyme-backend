package com.project_estramipyme_backend.user.service;

import com.project_estramipyme_backend.user.repository.IUserRepository;
import com.project_estramipyme_backend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public ArrayList<UserModel> getUser() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    public Optional<UserModel> getById(Long id) {
        return userRepository.findById(id);
    }

    public UserModel updateById(UserModel request, Long id) {
        UserModel user = userRepository.findById(id).get();

        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setTypeUser(request.getTypeUser());
        user.setTypeDocument(request.getTypeDocument());
        user.setNumberDocument(request.getNumberDocument());
        user.setBusinessName(request.getBusinessName());
        user.setSector(request.getSector());
        user.setOtherSector(request.getOtherSector());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);

        return user;
    }

    public Boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
