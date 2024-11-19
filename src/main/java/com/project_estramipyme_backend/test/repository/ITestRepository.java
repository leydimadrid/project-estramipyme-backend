package com.project_estramipyme_backend.test.repository;

import com.project_estramipyme_backend.test.dto.HeadlineReportDTO;
import com.project_estramipyme_backend.test.dto.InfoEsquemaReoDTO;
import com.project_estramipyme_backend.test.dto.ReportREODTO;
import com.project_estramipyme_backend.test.model.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITestRepository extends JpaRepository<TestModel, Long> {
    @Query("""
            SELECT new com.project_estramipyme_backend.test.dto.InfoEsquemaReoDTO(f.id, f.name, ROUND(AVG(qo.score)))
            FROM TestModel t
            JOIN AnswerModel ao ON ao.test = t
            JOIN Question_Option qo ON qo = ao.question_option
            JOIN Question q ON q = qo.question
            JOIN FormModel f ON f = q.form
            WHERE t.id = :testId AND f.id <> 6
            GROUP BY f.id, f.name
            """)
    List<InfoEsquemaReoDTO> getReportEsquemaReo(@Param("testId") Long testId);

    @Query("""
                   SELECT new com.project_estramipyme_backend.test.dto.ReportREODTO(
                           f.id, 
                           f.name, 
                           ROUND(AVG(COALESCE(qo.score, 0))), 
                           CASE 
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) <= 1 THEN 'Mal'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 2 THEN 'Medio'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 3 THEN 'Estable'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 4 THEN 'Súper Bien'
                               ELSE 'Desconocido'
                           END
                       )
                   FROM TestModel t
                   JOIN AnswerModel ao ON ao.test = t
                   JOIN Question_Option qo ON qo = ao.question_option
                   JOIN Question q ON q = qo.question
                   JOIN FormModel f ON f = q.form
                   WHERE t.id = :testId
                   AND f.id <> 6
                   GROUP BY f.id, f.name
            """)
    List<ReportREODTO> getReportReoDTO(@Param("testId") Long testId);

    @Query("""
                   SELECT new com.project_estramipyme_backend.test.dto.HeadlineReportDTO(
                         t.id, 
                         TO_CHAR(t.date, 'YYYY-MM-DD HH24:MI:SS'), 
                         u.name, u.lastname,
                         u.email
                   )
                   FROM TestModel t
                   JOIN UserModel u ON u.id = t.users.id
                   WHERE t.id = :testId
            """)
    List<HeadlineReportDTO> getHeadlineReportDTO(@Param("testId") Long testId);
}