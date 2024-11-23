package com.project_estramipyme_backend.test.service;


import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.project_estramipyme_backend.test.dto.HeadlineReportDTO;
import com.project_estramipyme_backend.test.dto.ReportDTO;
import com.project_estramipyme_backend.test.repository.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.io.ByteArrayOutputStream;

@Service
public class ReporteService {
    @Autowired
    private ITestRepository testRepository;


    public byte[] generarReportePDF(Long testId) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, baos);

            document.open();

            Font font = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);

            Paragraph titulo = new Paragraph("Resultados Test Estramipyme", font);
            titulo.setSpacingAfter(25);
            titulo.setAlignment(Element.ALIGN_CENTER);

            document.add(titulo);


            List<HeadlineReportDTO> HeadlineReportDTO = testRepository.getHeadlineReportDTO(testId);


            Long idTest = HeadlineReportDTO.get(0).getId();
            String dateTest = HeadlineReportDTO.get(0).getDate();
            String name = HeadlineReportDTO.get(0).getName();
            String lastname = HeadlineReportDTO.get(0).getLastname();
            String email = HeadlineReportDTO.get(0).getEmail();

            Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            Paragraph info = new Paragraph();
            info.add(new Chunk("Datos del Usuario", boldFont));
            info.add("\n");
            info.add(new Chunk("Test No: ", boldFont));
            info.add(idTest + "\n");
            info.add(new Chunk("Fecha: ", boldFont));
            info.add(dateTest + "\n");
            info.add(new Chunk("Nombre completo: ", boldFont));
            info.add(name + " " + lastname + "\n");
            info.add(new Chunk("Correo electrónico: ", boldFont));
            info.add(email);


            document.add(info);


            BaseColor lightRed = new BaseColor(255, 182, 193);
            BaseColor lightOrange = new BaseColor(255, 200, 140);
            BaseColor lightBlue = new BaseColor(173, 216, 230);
            BaseColor lightGreen = new BaseColor(144, 238, 144);

            //Tabla REO
            List<ReportDTO> resultReo = testRepository.getReportReoDTO(testId);

            // Tabla 1: Resultado Esquema REO
            Paragraph radarTitle = new Paragraph("\nResultados Radar Estratégico", font);
            radarTitle.setSpacingAfter(25);
            radarTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(radarTitle);

            PdfPTable tablaREO = new PdfPTable(new float[]{3, 2, 6});
            tablaREO.setWidthPercentage(100);

            PdfPCell headerCell1 = new PdfPCell(new Phrase("Sección"));
            headerCell1.setPadding(10);
            headerCell1.setBackgroundColor(BaseColor.DARK_GRAY);
            headerCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell1.setPhrase(new Phrase("Sección", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE)));


            PdfPCell headerCell2 = new PdfPCell(new Phrase("Puntaje"));
            headerCell2.setPadding(10);
            headerCell2.setBackgroundColor(BaseColor.DARK_GRAY);
            headerCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell2.setPhrase(new Phrase("Puntaje", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE)));

            PdfPCell headerCell3 = new PdfPCell(new Phrase("Recomendaciones"));
            headerCell3.setPadding(10);
            headerCell3.setBackgroundColor(BaseColor.DARK_GRAY);
            headerCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell3.setPhrase(new Phrase("Recomendaciones", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE)));


            tablaREO.addCell(headerCell1);
            tablaREO.addCell(headerCell2);
            tablaREO.addCell(headerCell3);

            // Llenar la tabla con datos del DTO
            for (ReportDTO ReoDto : resultReo) {
                PdfPCell nameCell = new PdfPCell(new Phrase(ReoDto.getName(), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                nameCell.setPadding(10);
                nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);


                PdfPCell scoreCell = new PdfPCell(new Phrase(String.valueOf(ReoDto.getScore()), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                scoreCell.setPadding(10);
                scoreCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                scoreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);


                PdfPCell resultCell = new PdfPCell(new Phrase(ReoDto.getRecommendation(), new Font(Font.FontFamily.HELVETICA, 12)));
                resultCell.setPadding(15);
                resultCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                resultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                double score = ReoDto.getScore();
                if (score == 1) {
                    nameCell.setBackgroundColor(BaseColor.WHITE);
                    nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    scoreCell.setBackgroundColor(lightRed);
                    scoreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    resultCell.setBackgroundColor(lightRed);
                    resultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                } else if (score == 2) {
                    nameCell.setBackgroundColor(BaseColor.WHITE);
                    nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    scoreCell.setBackgroundColor(lightOrange);
                    scoreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    resultCell.setBackgroundColor(lightOrange);
                    resultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                } else if (score == 3) {
                    nameCell.setBackgroundColor(BaseColor.WHITE);
                    nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    scoreCell.setBackgroundColor(lightBlue);
                    scoreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    resultCell.setBackgroundColor(lightBlue);
                    resultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                } else if (score >= 4) {
                    nameCell.setBackgroundColor(BaseColor.WHITE);
                    nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    scoreCell.setBackgroundColor(lightGreen);
                    scoreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    resultCell.setBackgroundColor(lightGreen);
                    resultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                }

                tablaREO.addCell(nameCell);
                tablaREO.addCell(scoreCell);
                tablaREO.addCell(resultCell);

            }

            document.add(tablaREO);

            List<ReportDTO> resultCirculoDorado = testRepository.getReportCirculo(testId);

            // Tabla 2: Resultado Circulo Dorado
            Paragraph circuloTitle = new Paragraph("\nResultados Círculo Dorado", font);
            circuloTitle.setSpacingAfter(25);
            circuloTitle.setSpacingBefore(100);
            circuloTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(circuloTitle);


            PdfPTable circuloDorado = new PdfPTable(new float[]{3, 2, 6});
            circuloDorado.setWidthPercentage(100);
            circuloDorado.setKeepTogether(true);


            PdfPCell headerCell4 = new PdfPCell(new Phrase("Sección"));
            headerCell4.setPadding(10);
            headerCell4.setBackgroundColor(BaseColor.DARK_GRAY);
            headerCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell4.setPhrase(new Phrase("Sección", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE)));


            PdfPCell headerCell5 = new PdfPCell(new Phrase("Puntaje"));
            headerCell5.setPadding(10);
            headerCell5.setBackgroundColor(BaseColor.DARK_GRAY);
            headerCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell5.setPhrase(new Phrase("Puntaje", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE)));

            PdfPCell headerCell6 = new PdfPCell(new Phrase("Recomendaciones"));
            headerCell6.setPadding(10);
            headerCell6.setBackgroundColor(BaseColor.DARK_GRAY);
            headerCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell6.setPhrase(new Phrase("Recomendaciones", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE)));


            circuloDorado.addCell(headerCell4);
            circuloDorado.addCell(headerCell5);
            circuloDorado.addCell(headerCell6);

            //llenar la tabla con datos del DTO
            for (ReportDTO circuloDto : resultCirculoDorado) {
                PdfPCell nameCell = new PdfPCell(new Phrase(circuloDto.getName(), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                nameCell.setPadding(10);
                nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);


                PdfPCell scoreCell = new PdfPCell(new Phrase(String.valueOf(circuloDto.getScore() + "%"), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                scoreCell.setPadding(10);
                scoreCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                scoreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);


                PdfPCell resultCell = new PdfPCell(new Phrase(circuloDto.getRecommendation(), new Font(Font.FontFamily.HELVETICA, 12)));
                resultCell.setPadding(15);
                resultCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                resultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                double score = circuloDto.getScore();
                if (score >= 1 && score <= 25) {
                    nameCell.setBackgroundColor(BaseColor.WHITE);
                    nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    scoreCell.setBackgroundColor(lightRed);
                    scoreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    resultCell.setBackgroundColor(lightRed);
                    resultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                } else if (score >= 25 && score <= 50) {
                    nameCell.setBackgroundColor(BaseColor.WHITE);
                    nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    scoreCell.setBackgroundColor(lightOrange);
                    scoreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    resultCell.setBackgroundColor(lightOrange);
                    resultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                } else if (score >= 50 && score <= 75) {
                    nameCell.setBackgroundColor(BaseColor.WHITE);
                    nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    scoreCell.setBackgroundColor(lightBlue);
                    scoreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    resultCell.setBackgroundColor(lightBlue);
                    resultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                } else if (score >= 75 && score <= 100) {
                    nameCell.setBackgroundColor(BaseColor.WHITE);
                    nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    scoreCell.setBackgroundColor(lightGreen);
                    scoreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    resultCell.setBackgroundColor(lightGreen);
                    resultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                }

                circuloDorado.addCell(nameCell);
                circuloDorado.addCell(scoreCell);
                circuloDorado.addCell(resultCell);

            }

            document.add(circuloDorado);
            document.close();

            return baos.toByteArray();


        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }

    }


}