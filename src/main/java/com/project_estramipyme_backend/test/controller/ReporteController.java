package com.project_estramipyme_backend.test.controller;

import com.project_estramipyme_backend.test.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Operation(summary = "Get response by ID", description = "Get a Test Report by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Test found"),
            @ApiResponse(responseCode = "404", description = "Test not found")
    })
    @GetMapping("/{testId}")
    public ResponseEntity<byte[]> descargarPDF(@PathVariable("testId") Long testId) {
        try {
            byte[] pdf = reporteService.generarReportePDF(testId);
            String fileName = "reporte_" + testId + ".pdf";
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=error.txt")
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(("Error al generar el PDF: " + e.getMessage()).getBytes());
        }
    }

}