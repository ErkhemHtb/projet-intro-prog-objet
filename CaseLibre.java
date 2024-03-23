public class CaseLibre extends CaseTraversable {
    private Entite contenu;
    
    public CaseLibre(int lig, int col) {
        super(lig, col, null);
    }
    
    public CaseLibre(int a ,int b, Entite contenu ){
        super(a,b, null);
        this.contenu = contenu;
      }
    
    @Override
    public boolean estLibre() {
        return contenu == null;
    }
    
    @Override
    public String toString() {
        if (contenu == null) {
            return "   ";
        } else {
            return contenu.toString("   ");
        }
    }
}