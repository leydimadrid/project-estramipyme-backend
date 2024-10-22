public class NavbarSection {
    private String name;
    private String link;

    public NavbarSection (String name, String link){
        this.name = name;
        this.link = link;
    }

    public String getName(){
        return name;
    }

    public String getLink(){
        return link;
    }

    @Override
    public String toString(){
        return "Sección" + name + "->" + link;
    }

}
