abstract public class EntiteMobile extends Entite {
  protected Direction direction;

  public EntiteMobile(Direction direction) {
    super(1);
    this.direction = direction;
  }

  public abstract void action(Case courante, Case cible);

  // Méthode qui permet de décrémenter la résistance de la créature
  public void decrementerResistance() {
    this.resistance--;
  }

  // Méthode qui retourne true si la créature doit être retirée du jeu
  public boolean doitDisparaitre() {
    return this.resistance <= 0;
  }
  public Direction getDirection() {
    return this.direction;
  }
}

