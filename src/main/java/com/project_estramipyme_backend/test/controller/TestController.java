package com.project_estramipyme_backend.test.controller;


import com.project_estramipyme_backend.answer.model.AnswerModel;
import com.project_estramipyme_backend.answer.service.AnswerService;
import com.project_estramipyme_backend.form.model.Question_Option;
import com.project_estramipyme_backend.test.dto.TestRequestDTO;
import com.project_estramipyme_backend.test.model.TestModel;
import com.project_estramipyme_backend.test.service.TestService;
import com.project_estramipyme_backend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/getTest")
    public ArrayList<TestModel> getTest() {
        return this.testService.getTest();
    }

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

    @GetMapping(path = "/{id}")
    public Optional<TestModel> getUserById(@PathVariable("id") Long id) {
        return this.testService.getById(id);
    }





}
