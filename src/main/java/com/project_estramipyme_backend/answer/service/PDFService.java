package com.project_estramipyme_backend.answer.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.project_estramipyme_backend.answer.model.AnswerModel;
import org.springframework.stereotype.Service;
import java.io.OutputStream;
import java.util.List;

@Service
public class PDFService {

    public void generatePdf(List<AnswerModel> answers, OutputStream outputStream) {
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Agregar un título al PDF
        document.add(new Paragraph("Listado de Respuestas").setBold().setFontSize(16));

        // Iterar sobre las respuestas y agregar contenido al PDF
        for (AnswerModel answer : answers) {
            String testName = answer.getTest() != null ? answer.getTest().toString() : "N/A";
            String optionText = answer.getQuestion_option() != null ? answer.getQuestion_option().toString() : "N/A";
            document.add(new Paragraph("Respuesta ID: " + answer.getId()));
            document.add(new Paragraph("Test: " + testName));
            document.add(new Paragraph("Opción de Pregunta: " + optionText));
            document.add(new Paragraph("--------------"));
        }

        document.close();
    }
}
