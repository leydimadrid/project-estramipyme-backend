package com.project_estramipyme_backend.answer.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.project_estramipyme_backend.answer.DTO.PDFRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import com.project_estramipyme_backend.answer.model.AnswerModel;

@Service
public class PDFService {
    private final AnswerService answerService;

    @Autowired
    public PDFService(AnswerService answerService) {
        this.answerService = answerService;
    }

    public ByteArrayOutputStream generatePDF(long id, PDFRequestDTO pdfRequestDTO) {
        Optional<AnswerModel> responseTest = this.answerService.getAnswerById(id);

        if(responseTest.isEmpty() || responseTest == null) {
            throw new RuntimeException("No se encontró la respuesta con el ID: " + id);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            // Verifica si el Optional contiene un valor
            if (responseTest.isPresent()) {
                AnswerModel answer = responseTest.get();

                document.add(new Paragraph("Resultado encuenta Estramipyme")
                        .setFontSize(18)
                        .setTextAlignment(TextAlignment.CENTER));

                Paragraph paragraph1 = new Paragraph()
                        .add(new Text("Id test: " + answer.getId()))
                        .add(new Text("         Fecha test: " + answer.getTest().getDate()))
                        .setFontSize(12);

                document.add(paragraph1);

                Paragraph paragraph2 = new Paragraph()
                        .add(new Text("Nombre: " + answer.getTest().getUsers().getName() + " " + answer.getTest().getUsers().getLastname()))
                        .add(new Text("         CC: " + answer.getTest().getUsers().getNumberDocument()))
                        .setFontSize(12);

                document.add(paragraph2);

                document.add(new Paragraph(""));

                document.add(new Paragraph("Resultado Esquema REO")
                        .setFontSize(16)
                        .setTextAlignment(TextAlignment.CENTER));

                //--//
                float[] columnWidths = {1, 1, 1}; // Tres columnas, cada una con la misma proporción
                Table table = new Table(columnWidths);

                table.setWidth(UnitValue.createPercentValue(90));
                table.setHorizontalAlignment(HorizontalAlignment.CENTER);

                table.addCell(new Cell().add(new Paragraph("Sección").setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph("Puntaje").setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph("Resultado").setTextAlignment(TextAlignment.CENTER)));

                table.addCell(new Cell().add(new Paragraph("Conocimiento del Cliente").setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(pdfRequestDTO.getConocimiento_Cliente())).setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(pdfRequestDTO.Resultado(pdfRequestDTO.getConocimiento_Cliente()))).setTextAlignment(TextAlignment.CENTER)));

                table.addCell(new Cell().add(new Paragraph("Salud Financiera").setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(pdfRequestDTO.getSalud_Financiera())).setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(pdfRequestDTO.Resultado(pdfRequestDTO.getSalud_Financiera()))).setTextAlignment(TextAlignment.CENTER)));

                table.addCell(new Cell().add(new Paragraph("Conocimiento del Negocio").setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(pdfRequestDTO.getConocimiento_Negocio())).setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(pdfRequestDTO.Resultado(pdfRequestDTO.getConocimiento_Negocio()))).setTextAlignment(TextAlignment.CENTER)));

                table.addCell(new Cell().add(new Paragraph("Coherencia del Modelo de Negocio").setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(pdfRequestDTO.getCoherencia_Modelo_Negocio())).setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(pdfRequestDTO.Resultado(pdfRequestDTO.getCoherencia_Modelo_Negocio()))).setTextAlignment(TextAlignment.CENTER)));

                table.addCell(new Cell().add(new Paragraph("Alineacion Interna").setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(pdfRequestDTO.getAlineacion_Interna())).setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(pdfRequestDTO.Resultado(pdfRequestDTO.getAlineacion_Interna()))).setTextAlignment(TextAlignment.CENTER)));

                document.add(table);

                document.add(new Paragraph(""));

                //_-/
                document.add(new Paragraph("Resultado Circulo Dorado")
                        .setFontSize(16)
                        .setTextAlignment(TextAlignment.CENTER));

                float[] columnWidths2 = {1, 1, 1}; // Tres columnas, cada una con la misma proporción
                Table table1 = new Table(columnWidths2);

                table1.setWidth(UnitValue.createPercentValue(90));
                table1.setHorizontalAlignment(HorizontalAlignment.CENTER);

                table1.addCell(new Cell().add(new Paragraph("Sección").setTextAlignment(TextAlignment.CENTER)));
                table1.addCell(new Cell().add(new Paragraph("Puntaje").setTextAlignment(TextAlignment.CENTER)));
                table1.addCell(new Cell().add(new Paragraph("Resultado").setTextAlignment(TextAlignment.CENTER)));

                table1.addCell(new Cell().add(new Paragraph("Que").setTextAlignment(TextAlignment.CENTER)));
                table1.addCell(new Cell().add(new Paragraph(String.valueOf( pdfRequestDTO.getQue() )).setTextAlignment(TextAlignment.CENTER)));
                table1.addCell(new Cell().add(new Paragraph(String.valueOf(pdfRequestDTO.Resultado(pdfRequestDTO.getQue()))).setTextAlignment(TextAlignment.CENTER)));

                table1.addCell(new Cell().add(new Paragraph("Porque").setTextAlignment(TextAlignment.CENTER)));
                table1.addCell(new Cell().add(new Paragraph(String.valueOf( pdfRequestDTO.getPorque() )).setTextAlignment(TextAlignment.CENTER)));
                table1.addCell(new Cell().add(new Paragraph(String.valueOf(pdfRequestDTO.Resultado(pdfRequestDTO.getPorque()))).setTextAlignment(TextAlignment.CENTER)));

                table1.addCell(new Cell().add(new Paragraph("Para Que").setTextAlignment(TextAlignment.CENTER)));
                table1.addCell(new Cell().add(new Paragraph(String.valueOf( pdfRequestDTO.getPara_Que() )).setTextAlignment(TextAlignment.CENTER)));
                table1.addCell(new Cell().add(new Paragraph(String.valueOf(pdfRequestDTO.Resultado(pdfRequestDTO.getPara_Que()))).setTextAlignment(TextAlignment.CENTER)));

                document.add(table1);

            } else {
                document.add(new Paragraph("No se encontró una respuesta con el ID proporcionado."));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream;
    }
}
