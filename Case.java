public abstract class Case {
  private final int lig;
  private final int col;
  
  public Case(int lig, int col) {
      this.lig = lig;
      this.col = col;
  }

  public abstract Entite getContenu();

  public abstract void vide();

  public abstract void entre(Entite e);
  
  public int getLig() {
      return lig;
  }
  
  public int getCol() {
      return col;
  }
  
  public abstract boolean estLibre();

  // Déterminer si la case est traversable ou non
  public boolean estTraversable() {
    return this instanceof CaseTraversable;
  }
  
  
}