package com.project_estramipyme_backend.form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class FormModel {
    //Datos de mi base de datos en postgress para asegurarme que si se conecte
    private final String url = "jdbc:postgresql://localhost:5433/prueba"; // puerto y el nombre de la base de datos de prueba
    private final String user = "postgres";
    private final String password = String.valueOf(1234);

    private List<SectionModel> sections;

    public FormModel (){
        sections = new ArrayList<>();
    } //constructor

    //prueba si está conectado con posgress
    public Connection connect() {
        Connection conn = null;
        try {
            // Establece la conexión
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado a PostgreSQL correctamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /*---- FormController----*/
    //metodo PUT
    @PutMapping(path = "/putForm")
    public FormModel updateFormById(@RequestBody FormModel request, @PathVariable Long id) {
        return this.formService.updateById(request, id);
    }

    //metodo DELETE
    @DeleteMapping(path = "/deleteForm")
    public String deleteFormById(@PathVariable("id") Long id) {
        boolean isOk = this.formService.deleteForm(id);

        if (isOk) {
            return "Form deleted!";
        } else {
            return "Error";
        }
    }

    /*---- aqui termina el formController ---*/

    /*-----formService ------*/

    // Metodo PUT
    public FormModel putFormById(Long id) {
        return formRepository.findById(id).orElse(null);
    }

    //metodo DELETE
    public FormModel deleteForm(FormModel form) {
        return formRepository.delete(form);
    }
}
