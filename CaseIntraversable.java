public class CaseIntraversable extends Case {
    public CaseIntraversable(int lig, int col) {
      super(lig, col);
    }
  
    @Override
    public boolean estLibre() {
      return false;
    }
  
    @Override
    public String toString() {
      return "###";
    }

    @Override
    public Entite getContenu() {
      return null;
    }

    @Override
    public void vide() {
    // Ne fait rien, car une case intraversable ne peut pas contenir d'entité
    }

    @Override
    public void entre(Entite e) {
    // Ne fait rien, car une case intraversable ne peut pas contenir d'entité
    }

  }