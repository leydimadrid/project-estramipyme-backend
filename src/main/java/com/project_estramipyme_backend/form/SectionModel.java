package com.project_estramipyme_backend.form;
import java.util.ArrayList;
import java.util.List;

public class SectionModel {
    private String title;
    private List<QuestionModel> questions;

    /*--- constructor ----*/
    public SectionModel() {
        questions = new ArrayList<>();
    }

    public SectionModel(String title){
        this.title = title;
    }

    /*--- getter ---*/
    public String getTitle(){
        return title;
    }

    /*---- Métodos ----*/

    public void addQuestion(QuestionModel question){
        questions.add(question);
    }

    @Override
    public String toString() {
        return "SectionModel{" +
                "title='" + title + '\'' +
                ", questions=" + questions +
                '}';
    }
}
