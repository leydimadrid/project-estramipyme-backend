import java.util.ArrayList;
import java.util.List;

public class Section {
    private String title;
    private List<question> questions;

    /*--- constructor ----*/
    public Section() {
        questions = new ArrayList<>();
    }

    public Section (String title){
        this.title = title;
    }

    /*--- getter ---*/
    public String getTitle(){
        return title;
    }

    /*---- Métodos ----*/

    public void addQuestion(question section){
        questions.add(section);
    }

    @Override
    public String toString(){
        return "Sección" + title + "->" + link;
    }

}
