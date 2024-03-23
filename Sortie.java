public class Sortie extends CaseTraversable {
    private Entite contenu;
    
    public Sortie(int lig, int col) {
        super(lig, col, null);
    }
    
    public Entite getContenu() {
        return contenu;
    }
    
    public void entre(Entite e) {
        contenu = e;
    }
    
    @Override
    public boolean estLibre() {
        return contenu == null;
    }
    
    @Override
    public String toString() {
        if (contenu == null) {
            return "( )";
        } else {
            return contenu.toString("( )");
        }
    }
}