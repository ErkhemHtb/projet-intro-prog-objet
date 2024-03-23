public class CaseTraversable extends Case {
    private Entite contenu;

    public CaseTraversable(int lig, int col, Entite contenu) {
        super(lig, col);
        this.contenu = contenu;
      }
  
    public Entite getContenu() {
      return contenu;
    }
  
    public void vide() {
      this.contenu = null;
    }
  
    public void entre(Entite e) {
      this.contenu = e;
    }

    public boolean estLibre() {
      return this.contenu == null;
    }

  
}

  