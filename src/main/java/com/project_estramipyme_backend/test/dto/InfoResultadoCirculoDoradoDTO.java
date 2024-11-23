package com.project_estramipyme_backend.test.dto;

public class InfoResultadoCirculoDoradoDTO {
    private String grupo;
    private double total;


    public InfoResultadoCirculoDoradoDTO(String grupo, double total) {
        this.grupo = grupo;
        this.total = total;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
