package com.project_estramipyme_backend.test.service;

import com.project_estramipyme_backend.test.dto.InfoEsquemaReoDTO;
import com.project_estramipyme_backend.test.dto.InfoResultadoCirculoDoradoDTO;
import com.project_estramipyme_backend.test.model.TestModel;
import com.project_estramipyme_backend.test.repository.ITestRepository;
import com.project_estramipyme_backend.user.model.UserModel;
import com.project_estramipyme_backend.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<InfoEsquemaReoDTO> getReportEsquemaReo(Long formId) {
        return testRepository.getReportEsquemaReo(formId);
    }

    public InfoResultadoCirculoDoradoDTO getPorqueInfoCirculo(Long formId){
        return testRepository.getPorqueInfoCirculo(formId);
    }

    public InfoResultadoCirculoDoradoDTO getComoInfoCirculo(Long formId){
        return testRepository.getComoInfoCirculo(formId);
    }

    public InfoResultadoCirculoDoradoDTO getQueInfoCirculo(Long formId){
        return testRepository.getQueInfoCirculo(formId);
    }
}
