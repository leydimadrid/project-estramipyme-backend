package com.project_estramipyme_backend.answer.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PDFRequestDTO {
    @JsonProperty("Conocimiento_Cliente")
    private Long Conocimiento_Cliente;

    @JsonProperty("Salud_Financiera")
    private Long Salud_Financiera;

    @JsonProperty("Conocimiento_Negocio")
    private Long Conocimiento_Negocio;

    @JsonProperty("Coherencia_Modelo_Negocio")
    private Long Coherencia_Modelo_Negocio;

    @JsonProperty("Alineacion_Interna")
    private Long Alineacion_Interna;

    @JsonProperty("Que")
    private Long Que;

    @JsonProperty("Porque")
    private Long Porque;

    @JsonProperty("Para_Que")
    private Long Para_Que;
    public PDFRequestDTO(){
    }

    public PDFRequestDTO(Long Conocimiento_Cliente, Long Salud_Financiera, Long Conocimiento_Negocio, Long Coherencia_Modelo_Negocio, Long Alineacion_Interna , Long Que, Long Porque, Long Para_Que) {
        this.Conocimiento_Cliente = Conocimiento_Cliente;
        this.Salud_Financiera = Salud_Financiera;
        this.Conocimiento_Negocio = Conocimiento_Negocio;
        this.Alineacion_Interna = Alineacion_Interna;
        this.Coherencia_Modelo_Negocio = Coherencia_Modelo_Negocio;
        this.Que = Que;
        this.Porque = Porque;
        this.Para_Que = Para_Que;
    }

    public Long getConocimiento_Cliente() {
        return Conocimiento_Cliente;
    }
    public Long getSalud_Financiera() {
        return Salud_Financiera;
    }
    public Long getConocimiento_Negocio() {
        return Conocimiento_Negocio;
    }

    public Long getCoherencia_Modelo_Negocio() {
        return Coherencia_Modelo_Negocio;
    }

    public Long getAlineacion_Interna() {
        return Alineacion_Interna;
    }

    public Long getQue() {
        return Que;
    }
    public Long getPorque() {
        return Porque;
    }
    public Long getPara_Que() {
        return Para_Que;
    }

    public String Resultado(Long Puntaje){

        if(Puntaje == 1){
            return "Muy mal";
        } else if (Puntaje == 2) {
            return "Está mal";
        } else if (Puntaje == 3) {
            return "Más o menos";
        } else if (Puntaje == 4) {
            return "Muy bien";
        } else{
            return "Puntaje no valido";
        }
    }
}