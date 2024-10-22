import java.util.ArrayList;
import java.util.List;

public class Navbar {
    private List<NavbarSection> sections; //lista de secciones de navegación

    public Navbar(){
        section = new ArrayList<>(); //constructor de la lista de secciones
    }

    /*-- Metodo que permite agregar una nueva seccion al menu--*/
    public void addSection(NavbarSection section){
        sections.add(section);
    }

    /* -- Metodo para mostrar el menu completo ---*/
    public void showMenu() {
        for (NavbarSection section : sections) {
            System.out.println(section);
        }
    }
}
