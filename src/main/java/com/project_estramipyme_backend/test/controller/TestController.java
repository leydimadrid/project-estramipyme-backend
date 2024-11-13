package com.project_estramipyme_backend.test.controller;


import com.project_estramipyme_backend.answer.model.AnswerModel;
import com.project_estramipyme_backend.answer.service.AnswerService;
import com.project_estramipyme_backend.form.model.Question_Option;
import com.project_estramipyme_backend.test.dto.TestRequestDTO;
import com.project_estramipyme_backend.test.model.TestModel;
import com.project_estramipyme_backend.test.service.TestService;
import com.project_estramipyme_backend.user.model.UserModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Tests", description = "API for evaluation test management")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private final TestService testService;
    @Autowired
    private AnswerService answerService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    } //Fin inyección de dependencias

    @Operation(summary = "Get all test",
            description = "Return list of all tests performed")
    @ApiResponse(responseCode = "200", description = "Successful tests")
    @GetMapping(path = "/getTest")
    public ArrayList<TestModel> getTest() {
        return this.testService.getTest();
    }


    @Operation(summary = "Create new test",
            description = "Register a new test in the system")
    @ApiResponse(responseCode = "200", description = "Test successfully created")
    @PostMapping(path = "/newTest")
    public TestModel saveTest(@RequestBody TestRequestDTO test) {

        TestModel testModel = new TestModel();
        testModel.setUsers(new UserModel());
        testModel.setDate(test.getDate());
        testModel.getUsers().setId(test.getUser_id());

        TestModel testBd = this.testService.saveTest(testModel);

        for (Long answer_option_id : test.getAnswers_option_ids()) {
            AnswerModel answerModel = new AnswerModel();
            answerModel.setTest(new TestModel());
            answerModel.setQuestion_option(new Question_Option());
            answerModel.getTest().setId(testBd.getId());
            answerModel.getQuestion_option().setId(answer_option_id);
            this.answerService.saveAnswer(answerModel);
        }

        return testBd;
    }


    @Operation(summary = "Get test by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Test found"),
            @ApiResponse(responseCode = "404", description = "Test not found")
    })
    @GetMapping(path = "/{id}")
    public Optional<TestModel> getUserById(
            @Parameter(description = "Test ID") @PathVariable("id") Long id) {
        return this.testService.getById(id);
    }
}
