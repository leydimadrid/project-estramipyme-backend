package com.project_estramipyme_backend.test.service;


import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.project_estramipyme_backend.test.dto.HeadlineReportDTO;
import com.project_estramipyme_backend.test.dto.ReportREODTO;
import com.project_estramipyme_backend.test.repository.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.io.ByteArrayOutputStream;

import static com.itextpdf.kernel.colors.ColorConstants.BLUE;

@Service
public class ReporteService {
    @Autowired
    private ITestRepository testRepository;


    public byte[] generarReportePDF(Long testId) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // Crear el documento PDF
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Título
            Paragraph titulo = new Paragraph("RESULTADOS TEST ESTRAMIPYME")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(16);
            document.add(titulo);


            List<HeadlineReportDTO> HeadlineReportDTO = testRepository.getHeadlineReportDTO(testId);


//            String texto = "Id test: " + HeadlineReportDTO.get(0).getId() + "\n" +
//                    "Fecha test: " + HeadlineReportDTO.get(0).getDate() + "\n" +
//                    "Nombre: " + HeadlineReportDTO.get(0).getUser() + "    Correo: " + HeadlineReportDTO.get(0).getEmail();
//
//            Paragraph info = new Paragraph(texto);


            Long idTest = HeadlineReportDTO.get(0).getId();
            String dateTest = HeadlineReportDTO.get(0).getDate();
            String name = HeadlineReportDTO.get(0).getName();
            String lastname = HeadlineReportDTO.get(0).getLastname();
            String email = HeadlineReportDTO.get(0).getEmail();

            Paragraph info = new Paragraph()
                    .add(new Text("Id test: ").setBold().setFontSize(14))
                    .add(idTest + "\n")
                    .add(new Text("Fecha: ").setBold().setFontSize(14))
                    .add(dateTest + "\n")
                    .add(new Text("Nombre completo: ").setBold().setFontSize(14))
                    .add(name + " "  + lastname + "\n")
                    .add(new Text("Correo electrónico: ").setBold().setFontSize(14))
                    .add(email);

            info.setMargins(30, 10, 10, 0);
            document.add(info);



            //Tabla REO
            List<ReportREODTO> resultadosReo = testRepository.getReportReoDTO(testId);

            // Tabla 1: Resultado Esquema REO
            document.add(new Paragraph("\nResultado Esquema REO").setBold().setFontSize(18).setTextAlignment(TextAlignment.CENTER));
            Table tablaREO = new Table(UnitValue.createPercentArray(new float[]{4, 2, 4})).useAllAvailableWidth().setBackgroundColor(ColorConstants.BLACK).setFontColor(ColorConstants.WHITE).setTextAlignment(TextAlignment.CENTER);
            tablaREO.addHeaderCell("Sección");
            tablaREO.addHeaderCell("Puntaje");
            tablaREO.addHeaderCell("Resultado");


            // Llenar la tabla con datos del DTO
            for (ReportREODTO ReoDto : resultadosReo) {
                Cell nameCell = new Cell().add(new Paragraph(ReoDto.getName()));
                Cell scoreCell = new Cell().add(new Paragraph(String.valueOf(ReoDto.getScore())));
                Cell resultCell = new Cell().add(new Paragraph(ReoDto.getResult()));
                double score = ReoDto.getScore();
                if (score >= 1 && score <= 2) {
                    // Rojo para puntajes bajos
                    nameCell.setBackgroundColor(ColorConstants.LIGHT_GRAY).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    scoreCell.setBackgroundColor(ColorConstants.RED).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    resultCell.setBackgroundColor(ColorConstants.RED).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                } else if (score == 3) {
                    // Amarillo para puntajes medios
                    nameCell.setBackgroundColor(ColorConstants.LIGHT_GRAY).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    scoreCell.setBackgroundColor(ColorConstants.CYAN).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    resultCell.setBackgroundColor(ColorConstants.CYAN).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                } else if (score >= 4) {
                    // Verde para puntajes altos
                    nameCell.setBackgroundColor(ColorConstants.LIGHT_GRAY).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    scoreCell.setBackgroundColor(ColorConstants.GREEN).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                    resultCell.setBackgroundColor(ColorConstants.GREEN).setFontColor(ColorConstants.BLACK).setVerticalAlignment(VerticalAlignment.MIDDLE);
                }

                tablaREO.addCell(nameCell);
                tablaREO.addCell(scoreCell);
                tablaREO.addCell(resultCell);
//                tablaREO.addCell(ReoDto.getName());
//                tablaREO.addCell(String.valueOf(ReoDto.getScore()));
//                tablaREO.addCell(ReoDto.getResult().toString());
            }

            document.add(tablaREO);

            // Cerrar el documento
            document.close();

            // Retornar el PDF como byte array
            return baos.toByteArray();


        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }
}