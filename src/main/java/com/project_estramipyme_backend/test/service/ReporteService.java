package com.project_estramipyme_backend.test.service;


import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.project_estramipyme_backend.test.dto.HeadlineReportDTO;
import com.project_estramipyme_backend.test.dto.ReportDTO;
import com.project_estramipyme_backend.test.repository.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.io.ByteArrayOutputStream;

@Service
public class ReporteService {
    @Autowired
    private ITestRepository testRepository;


    public byte[] generarReportePDF(Long testId) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);


            Paragraph titulo = new Paragraph("Resultados Test Estramipyme")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(16);
            document.add(titulo);


            List<HeadlineReportDTO> HeadlineReportDTO = testRepository.getHeadlineReportDTO(testId);


            Long idTest = HeadlineReportDTO.get(0).getId();
            String dateTest = HeadlineReportDTO.get(0).getDate();
            String name = HeadlineReportDTO.get(0).getName();
            String lastname = HeadlineReportDTO.get(0).getLastname();
            String email = HeadlineReportDTO.get(0).getEmail();


            Paragraph info = new Paragraph()
                    .add(new Text("Datos del Usuario").setBold().setFontSize(12))
                    .add("\n")
                    .add(new Text("Test No: ").setBold().setFontSize(12))
                    .add(idTest + "\n")
                    .add(new Text("Fecha: ").setBold().setFontSize(12))
                    .add(dateTest + "\n")
                    .add(new Text("Nombre completo: ").setBold().setFontSize(12))
                    .add(name + " " + lastname + "\n")
                    .add(new Text("Correo electrónico: ").setBold().setFontSize(12))
                    .add(email);


            info.setMargins(30, 10, 10, 0);
            document.add(info);


            DeviceRgb lightRed = new DeviceRgb(255, 182, 193);
            DeviceRgb lightOrange = new DeviceRgb(255, 200, 140);
            DeviceRgb lightBlue = new DeviceRgb(173, 216, 230);
            DeviceRgb lightGreen = new DeviceRgb(144, 238, 144);

            //Tabla REO
            List<ReportDTO> resultReo = testRepository.getReportReoDTO(testId);

            // Tabla 1: Resultado Esquema REO
            document.add(new Paragraph("\nResultados Radar Estratégico").setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER).setPadding(15));
            Table tablaREO = new Table(UnitValue.createPercentArray(new float[]{3, 1, 6})).useAllAvailableWidth().setBackgroundColor(ColorConstants.DARK_GRAY).setFontColor(ColorConstants.WHITE).setTextAlignment(TextAlignment.CENTER);
            Cell headerCell1 = new Cell().add(new Paragraph("Sección").setPadding(8));
            Cell headerCell2 = new Cell().add(new Paragraph("Puntaje").setPadding(8));
            Cell headerCell3 = new Cell().add(new Paragraph("Recomendaciones").setPadding(8));

            tablaREO.addHeaderCell(headerCell1);
            tablaREO.addHeaderCell(headerCell2);
            tablaREO.addHeaderCell(headerCell3);

            // Llenar la tabla con datos del DTO
            for (ReportDTO ReoDto : resultReo) {
                Cell nameCell = new Cell().add(new Paragraph(ReoDto.getName()).setFontSize(12).setBold().setPadding(10).setTextAlignment(TextAlignment.CENTER));
                Cell scoreCell = new Cell().add(new Paragraph(String.valueOf(ReoDto.getScore())).setFontSize(12).setBold());
                Cell resultCell = new Cell().add(new Paragraph(ReoDto.getRecommendation()).setFontSize(12).setPadding(15).setTextAlignment(TextAlignment.LEFT));
                double score = ReoDto.getScore();
                if (score == 1) {
                    nameCell.setBackgroundColor(ColorConstants.WHITE).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    scoreCell.setBackgroundColor(lightRed).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    resultCell.setBackgroundColor(lightRed).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                }else if (score == 2) {
                    nameCell.setBackgroundColor(ColorConstants.WHITE).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    scoreCell.setBackgroundColor(lightOrange).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    resultCell.setBackgroundColor(lightOrange).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                }else if (score == 3) {
                    nameCell.setBackgroundColor(ColorConstants.WHITE).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    scoreCell.setBackgroundColor(lightBlue).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    resultCell.setBackgroundColor(lightBlue).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                } else if (score >= 4) {
                    nameCell.setBackgroundColor(ColorConstants.WHITE).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    scoreCell.setBackgroundColor(lightGreen).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    resultCell.setBackgroundColor(lightGreen).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                }

                tablaREO.addCell(nameCell);
                tablaREO.addCell(scoreCell);
                tablaREO.addCell(resultCell);
            }

            document.add(tablaREO);


            List<ReportDTO> resultCirculoDorado = testRepository.getReportCirculo(testId);

            // Tabla 1: Resultado Circulo Dorado
            document.add(new Paragraph("\nResultados Círculo Dorado").setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER).setPadding(15));
            Table circuloDorado = new Table(UnitValue.createPercentArray(new float[]{3, 1, 6})).useAllAvailableWidth().setBackgroundColor(ColorConstants.DARK_GRAY).setFontColor(ColorConstants.WHITE).setTextAlignment(TextAlignment.CENTER);

            Cell headerCell4 = new Cell().add(new Paragraph("Sección").setPadding(8));
            Cell headerCell5 = new Cell().add(new Paragraph("Puntaje").setPadding(8));
            Cell headerCell6 = new Cell().add(new Paragraph("Recomendaciones").setPadding(8));

            circuloDorado.addHeaderCell(headerCell4);
            circuloDorado.addHeaderCell(headerCell5);
            circuloDorado.addHeaderCell(headerCell6);


            // Llenar la tabla con datos del DTO
            for (ReportDTO ReoDto : resultCirculoDorado) {
                Cell nameCell = new Cell().add(new Paragraph(ReoDto.getName()).setFontSize(12).setBold().setPadding(10).setTextAlignment(TextAlignment.CENTER));
                Cell scoreCell = new Cell().add(new Paragraph(String.valueOf(ReoDto.getScore() + "%")).setFontSize(12).setBold());
                Cell resultCell = new Cell().add(new Paragraph(ReoDto.getRecommendation()).setFontSize(12).setPadding(15).setTextAlignment(TextAlignment.LEFT));
                double score = ReoDto.getScore();
                if (score >= 1 && score <= 25) {
                    nameCell.setBackgroundColor(ColorConstants.WHITE).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    scoreCell.setBackgroundColor(lightRed).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    resultCell.setBackgroundColor(lightRed).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                } else if (score >= 25 && score <= 50) {
                    nameCell.setBackgroundColor(ColorConstants.WHITE).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    scoreCell.setBackgroundColor(lightOrange).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    resultCell.setBackgroundColor(lightOrange).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                }
                else if (score >= 50 && score <= 75) {
                    nameCell.setBackgroundColor(ColorConstants.WHITE).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    scoreCell.setBackgroundColor(lightBlue).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    resultCell.setBackgroundColor(lightBlue).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                } else if (score >= 75 && score <= 100) {
                    nameCell.setBackgroundColor(ColorConstants.WHITE).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    scoreCell.setBackgroundColor(lightGreen).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    resultCell.setBackgroundColor(lightGreen).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
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