package com.project_estramipyme_backend.test.repository;

import com.project_estramipyme_backend.test.dto.InfoEsquemaReoDTO;
import com.project_estramipyme_backend.test.model.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITestRepository extends JpaRepository<TestModel, Long> {
    @Query( """
          SELECT new com.project_estramipyme_backend.test.dto.InfoEsquemaReoDTO(f.id, f.name, ROUND(AVG(qo.score)))
          FROM TestModel t
          JOIN AnswerModel ao ON ao.test = t
          JOIN Question_Option qo ON qo = ao.question_option
          JOIN Question q ON q = qo.question
          JOIN FormModel f ON f = q.form
          WHERE t.id = :testId
          GROUP BY f.id, f.name
          """)
    List<InfoEsquemaReoDTO> getReportEsquemaReo(@Param("testId") Long testId);
}