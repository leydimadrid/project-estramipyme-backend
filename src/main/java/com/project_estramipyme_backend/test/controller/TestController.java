package com.project_estramipyme_backend.test.controller;


import com.project_estramipyme_backend.test.model.TestModel;
import com.project_estramipyme_backend.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    } //Fin inyección de dependencias

    @GetMapping(path = "/getTest")
    public ArrayList<TestModel> getTest() {
        return this.testService.getTest();
    }

    @PostMapping(path = "/newTest")
    public TestModel saveTest(@RequestBody TestModel test) {
        return this.testService.saveTest(test);
    }

    @GetMapping(path = "/{id}")
    public Optional<TestModel> getUserById(@PathVariable("id") Long id) {
        return this.testService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public TestModel updateTestById(@RequestBody TestModel request, @PathVariable Long id) {
        return this.testService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean isOk = this.testService.deleteTest(id);

        if (isOk) {
            return "Test with id " + id + " delete!";
        } else {
            return "Error";
        }
    }


}
