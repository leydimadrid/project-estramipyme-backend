package com.project_estramipyme_backend.test.service;

import com.project_estramipyme_backend.test.model.TestModel;
import com.project_estramipyme_backend.test.repository.ITestRepository;
import com.project_estramipyme_backend.user.model.UserModel;
import com.project_estramipyme_backend.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    ITestRepository testRepository;

    public ArrayList<TestModel> getTest() {
        return (ArrayList<TestModel>) testRepository.findAll();
    }

    public TestModel saveTest(TestModel test) {
        return testRepository.save(test);
    }

    public Optional<TestModel> getById(Long id) {
        return testRepository.findById(id);
    }

    public TestModel updateById(TestModel request, Long id) {
        TestModel test = testRepository.findById(id).get();

        test.setDate(request.getDate());
        test.setUsers(request.getUsers());
        testRepository.save(test);

        return test;
    }

    public Boolean deleteTest(Long id) {
        try {
            testRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
