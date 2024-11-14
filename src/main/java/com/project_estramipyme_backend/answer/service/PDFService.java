package com.project_estramipyme_backend.answer.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.font.constants.StandardFonts;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import com.project_estramipyme_backend.answer.model.AnswerModel;

@Service
public class PDFService {

    public ByteArrayOutputStream generatePDF(Optional<AnswerModel> answers) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            document.add(new Paragraph("Respuestas del Formulario")
                    .setFontSize(18));

            answers.ifPresent(answer -> {
                document.add(new Paragraph("Pregunta: " + answer.getQuestion_option()));
                document.add(new Paragraph("Respuesta: " + answer.getTest()));
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream;
    }
}
