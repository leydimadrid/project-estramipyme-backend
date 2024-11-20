package com.project_estramipyme_backend.test.repository;

import com.project_estramipyme_backend.test.dto.*;

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
                SELECT new com.project_estramipyme_backend.test.dto.InfoResultadoCirculoDoradoDTO('Por Que', (SUM(qo.score) * 100 / 16))
                FROM TestModel t
                JOIN AnswerModel ao ON ao.test = t
                JOIN Question_Option qo ON qo = ao.question_option
                JOIN Question q ON q = qo.question
                JOIN FormModel f ON f = q.form
                WHERE t.id = :testId AND f.id = 6 AND q.id IN(50,51,52,53)
            """)
    InfoResultadoCirculoDoradoDTO getPorqueInfoCirculo(@Param("testId") Long testId);

    @Query("""
                SELECT new com.project_estramipyme_backend.test.dto.InfoResultadoCirculoDoradoDTO('Como', (SUM(qo.score) * 100 / 16))
                FROM TestModel t
                JOIN AnswerModel ao ON ao.test = t
                JOIN Question_Option qo ON qo = ao.question_option
                JOIN Question q ON q = qo.question
                JOIN FormModel f ON f = q.form
                WHERE t.id = :testId AND f.id = 6 AND q.id IN(54,55,56,57)
            """)
    InfoResultadoCirculoDoradoDTO getComoInfoCirculo(@Param("testId") Long testId);

    @Query("""
                SELECT new com.project_estramipyme_backend.test.dto.InfoResultadoCirculoDoradoDTO('Que', (SUM(qo.score) * 100 / 16))
                FROM TestModel t
                JOIN AnswerModel ao ON ao.test = t
                JOIN Question_Option qo ON qo = ao.question_option
                JOIN Question q ON q = qo.question
                JOIN FormModel f ON f = q.form
                WHERE t.id = :testId AND f.id = 6 AND q.id IN(58,59,60,61)
            """)
    InfoResultadoCirculoDoradoDTO getQueInfoCirculo(@Param("testId") Long testId);

    //Reporte en PDF
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

    @Query("""
                   SELECT new com.project_estramipyme_backend.test.dto.ReportDTO(
                           f.name, 
                           ROUND(AVG(COALESCE(qo.score, 0))), 
                    CASE
                    WHEN f.name = 'Coherencia del Modelo de Negocio' THEN
                           CASE 
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) <= 1 THEN 'Realiza estudios de mercado para obtener un conocimiento más profundo de quiénes son tus clientes, lo que permitirá una mejor segmentación y enfoque de tu modelo de negocio.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 2 THEN 'Expande tu segmentación para incluir aspectos como estilo de vida, hábitos de consumo y comportamientos, lo que te permitirá adaptar mejor tu propuesta de valor.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 3 THEN 'Aprovecha este conocimiento profundo del cliente para personalizar tu oferta y mejorar la propuesta de valor, aumentando así la satisfacción y fidelización del cliente.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 4 THEN 'Desarrolla estrategias de segmentación y personalización específicas para cada tipo de cliente, asegurando que tu negocio esté alineado con las necesidades de diferentes segmentos del mercado.'
                           END
                    WHEN f.name = 'Conocimiento del Negocio' THEN
                           CASE 
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) <= 1 THEN 'Establece un sistema básico para evaluar la competencia de manera regular, lo que te permitirá adaptar tus estrategias y mejorar tu posicionamiento en el mercado.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 2 THEN 'Asegúrate de que la evaluación de la competencia sea sistemática y se integre en tus procesos de planificación estratégica, mejorando así la capacidad de respuesta y anticipación a los movimientos del mercado.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 3 THEN 'Utiliza la información obtenida de la evaluación sistemática para ajustar y mejorar continuamente tus estrategias, asegurando que tu negocio se mantenga competitivo.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 4 THEN 'Utiliza la anticipación de los movimientos de la competencia para innovar y liderar en el mercado, asegurando que tu negocio no solo responda, sino que también marque el ritmo en su sector.'
                           END
                    WHEN f.name = 'Salud Financiera' THEN
                           CASE 
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) <= 1 THEN 'Trabaja en definir claramente lo que hace único a tu producto o servicio, y cómo esto se traduce en un beneficio claro para el cliente.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 2 THEN 'Complementa el posicionamiento en precio con atributos de valor que destaquen la calidad, servicio o experiencia que ofrece tu producto, aumentando así el margen y la lealtad del cliente.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 3 THEN 'Comunica de manera más efectiva cómo los beneficios funcionales de tu producto superan a los de la competencia, mejorando la percepción y aceptación del mercado.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 4 THEN 'Refuerza los valores emocionales que conectan a tu marca con los clientes, asegurando una alineación fuerte y coherente a través de todos los puntos de contacto.'
                           END
                    WHEN f.name = 'Conocimiento del cliente' THEN
                           CASE 
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) <= 1 THEN 'Establece una segmentación básica para dividir tu mercado en grupos más manejables, lo que facilitará un enfoque más dirigido y efectivo.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 2 THEN 'Expande la segmentación para incluir factores psicográficos y comportamentales, lo que permitirá un enfoque más preciso y personalizado.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 3 THEN 'Aprovecha la segmentación basada en comportamiento de compra para personalizar las ofertas y mejorar la experiencia del cliente.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 4 THEN 'Utiliza los insights predictivos para anticipar las necesidades de los clientes y personalizar las ofertas antes de que el cliente las demande.'
                           END       
                    WHEN f.name = 'Alineación en la Comunicación Interna' THEN
                           CASE 
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) <= 1 THEN 'Define una estrategia de ventas inicial con objetivos claros, métodos de captación y retención de clientes, y un plan de acción concreto para guiar las actividades de venta.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 2 THEN 'Complementa las promociones con tácticas como la venta cruzada o programas de lealtad, optimizando así la efectividad y el margen de tus ventas.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 3 THEN 'Mantén y mejora las relaciones a largo plazo con los clientes mediante un enfoque en el servicio, la personalización y la atención continua.'
                               WHEN ROUND(AVG(COALESCE(qo.score, 0))) = 4 THEN 'Asegúrate de que las estrategias de venta estén alineadas con los perfiles de cliente, maximizando la relevancia y efectividad de las interacciones de venta.'
                           END 
                        ELSE 'Sin recomendación disponible.'
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
    List<ReportDTO> getReportReoDTO(@Param("testId") Long testId);

    @Query("""
                    SELECT new com.project_estramipyme_backend.test.dto.ReportDTO('¿Por Qué?', (SUM(qo.score) * 100 / 16.0),
                    CASE
                                WHEN (SUM(qo.score) * 100 / 16) <= 25 THEN 'Si bien hay algún grado de definición del propósito, es necesario clarificarlo aún más y alinearlo con los objetivos a mediano plazo. Involucra a todas las partes interesadas en este proceso para asegurar que el propósito sea relevante y compartido.'
                                WHEN (SUM(qo.score) * 100 / 16) <= 50 THEN 'Con un puntaje moderado, es importante evaluar qué tan alineado está realmente tu equipo con el propósito del negocio. Realiza encuestas internas o focus groups para identificar áreas donde la alineación puede ser mejorada.'
                                WHEN (SUM(qo.score) * 100 / 16) <= 75 THEN 'Con un buen nivel de claridad y alineación, enfócate en reforzar el propósito en todos los aspectos de la cultura organizacional. Esto incluye desde el reclutamiento y la inducción hasta las evaluaciones de desempeño.'
                                WHEN (SUM(qo.score) * 100 / 16) <= 80 THEN 'Con un propósito bien definido y comprendido, es hora de escalar su impacto. Considera iniciativas de responsabilidad social corporativa (RSC) que refuercen el propósito y lo expandan a nuevos públicos y comunidades.'
            
                                ELSE 'Cómo: Excelente nivel. Ejecución impecable.'
                            END)
                    FROM TestModel t
                    JOIN AnswerModel ao ON ao.test = t
                    JOIN Question_Option qo ON qo = ao.question_option
                    JOIN Question q ON q = qo.question
                    JOIN FormModel f ON f = q.form
                    WHERE t.id = :testId AND f.id = 6 AND q.id IN(50,51,52,53)
            
                    UNION ALL
            
                    SELECT new com.project_estramipyme_backend.test.dto.ReportDTO('¿Cómo?', (SUM(qo.score) * 100 / 16),
                    CASE
                                WHEN (SUM(qo.score) * 100 / 16) <= 25 THEN 'Aunque existe alguna consistencia en seguir procesos documentados, es necesario reforzar y revisar estos procesos para asegurar que todos los empleados los sigan de manera uniforme. Se pueden realizar sesiones de formación adicionales y auditorías regulares para mantener la consistencia.'
                                WHEN (SUM(qo.score) * 100 / 16) <= 50 THEN 'Aunque los procesos son razonablemente eficientes, siempre hay margen de mejora. Revisa regularmente los procesos y busca oportunidades para optimizar el uso de recursos y tiempo, manteniendo la calidad como prioridad.'
                                WHEN (SUM(qo.score) * 100 / 16) <= 75 THEN 'Con una alta consistencia en los procesos, el enfoque debe estar en perfeccionarlos. Esto podría incluir la adopción de prácticas de mejores prácticas del sector y la evaluación continua de la efectividad de los procesos para mantener un rendimiento óptimo.'
                                WHEN (SUM(qo.score) * 100 / 16) <= 80 THEN 'Con procesos bien establecidos y seguidos consistentemente, enfócate en perfeccionarlos aún más. Documenta los procesos detalladamente y asegúrate de que estén fácilmente accesibles para todos los empleados, lo que facilitará la capacitación y la replicación de buenas prácticas.'
            
                                ELSE 'Cómo: Excelente nivel. Ejecución impecable.'
                            END)
                    FROM TestModel t
                    JOIN AnswerModel ao ON ao.test = t
                    JOIN Question_Option qo ON qo = ao.question_option
                    JOIN Question q ON q = qo.question
                    JOIN FormModel f ON f = q.form
                    WHERE t.id = :testId AND f.id = 6 AND q.id IN(54,55,56,57)
            
            UNION ALL
            
                    SELECT new com.project_estramipyme_backend.test.dto.ReportDTO('¿Qué?', (SUM(qo.score) * 100 / 16),
                    CASE
                                WHEN (SUM(qo.score) * 100 / 16) <= 25 THEN 'Con puntajes bajos en la relación calidad-precio, enfócate en mejorar la calidad de tus productos o servicios sin aumentar significativamente los costos. Considera opciones para optimizar la producción y ofrecer precios más competitivos.'
                                WHEN (SUM(qo.score) * 100 / 16) <= 50 THEN 'Si los productos/servicios cumplen moderadamente con las expectativas de los clientes, evalúa tu propuesta de valor. Identifica áreas donde puedes ofrecer algo único o agregar más valor para que tus clientes perciban un mayor beneficio.'
                                WHEN (SUM(qo.score) * 100 / 16) <= 75 THEN 'Aunque tus productos o servicios son relativamente innovadores, sigue buscando maneras de mejorar. Evalúa nuevas tecnologías, materiales o metodologías que podrían mantener tu oferta a la vanguardia del mercado.'
                                WHEN (SUM(qo.score) * 100 / 16) <= 80 THEN 'Con un alto grado de escalabilidad y sostenibilidad, explora oportunidades para expandir tu modelo de negocio a nuevas geografías o mercados. Considera alianzas estratégicas o franquicias para acelerar este crecimiento.'
                                ELSE 'Cómo: Excelente nivel. Ejecución impecable.'
                            END)
                    FROM TestModel t
                    JOIN AnswerModel ao ON ao.test = t
                    JOIN Question_Option qo ON qo = ao.question_option
                    JOIN Question q ON q = qo.question
                    JOIN FormModel f ON f = q.form
                    WHERE t.id = :testId AND f.id = 6 AND q.id IN(58,59,60,61)
            """)
    List<ReportDTO> getReportCirculo(@Param("testId") Long testId);


}