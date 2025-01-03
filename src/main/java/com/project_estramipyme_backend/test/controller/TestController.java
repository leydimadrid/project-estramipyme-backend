package com.project_estramipyme_backend.test.controller;


import com.project_estramipyme_backend.answer.model.AnswerModel;
import com.project_estramipyme_backend.answer.service.AnswerService;
import com.project_estramipyme_backend.form.model.Question_Option;
import com.project_estramipyme_backend.test.dto.InfoEsquemaReoDTO;
import com.project_estramipyme_backend.test.dto.InfoResultadoCirculoDoradoDTO;
import com.project_estramipyme_backend.test.dto.TestRequestDTO;
import com.project_estramipyme_backend.test.model.TestModel;
import com.project_estramipyme_backend.test.service.TestService;
import com.project_estramipyme_backend.user.model.UserModel;
import com.project_estramipyme_backend.user.repository.IUserRepository;

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
    private IUserRepository userRepository;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @Operation(summary = "Get all test",
            description = "Return list of all tests performed")
    @ApiResponse(responseCode = "200", description = "Successful tests")
    @GetMapping(path = "/getTest")
    public ArrayList<TestModel> getTest() {
        return this.testService.getTest();
    }

    @Operation(summary = "Report Esquema Reo",
            description = "Return list of score")
    @ApiResponse(responseCode = "200", description = "Successful tests")
    @GetMapping(path = "/getReportRadarEstrategico/{id}")
    public List<InfoEsquemaReoDTO> reportRadarEstrategico(@Parameter(description = "Test ID") @PathVariable("id") Long id) {
        List<InfoEsquemaReoDTO> response = this.testService.getReportEsquemaReo(id);
        return response;
    }

    @Operation(summary = "Report Circulo dorado",
            description = "Return list of total")
    @ApiResponse(responseCode = "200", description = "Successful tests")
    @GetMapping(path = "/getReportCirculoDorado/{id}")
    public List<InfoResultadoCirculoDoradoDTO> reportCirculoDorado(@Parameter(description = "Test ID") @PathVariable("id") Long id) {

        List<InfoResultadoCirculoDoradoDTO> result = new ArrayList<>();

        InfoResultadoCirculoDoradoDTO infoPorQue = this.testService.getPorqueInfoCirculo(id);
        InfoResultadoCirculoDoradoDTO infoComo = this.testService.getComoInfoCirculo(id);
        InfoResultadoCirculoDoradoDTO infoQue = this.testService.getQueInfoCirculo(id);

        result.add(infoPorQue);
        result.add(infoComo);
        result.add(infoQue);

        return result;
    }

    @Operation(summary = "Create new test",
            description = "Register a new test in the system")
    @ApiResponse(responseCode = "200", description = "Test successfully created")
    @PostMapping(path = "/newTest")
    public TestModel saveTest(@RequestBody TestRequestDTO test) {

        TestModel testModel = new TestModel();
        testModel.setUsers(new UserModel());
        testModel.setDate(test.getDate());

        UserModel user = this.userRepository.findByEmail(test.getUserEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        testModel.setUsers(user);

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
