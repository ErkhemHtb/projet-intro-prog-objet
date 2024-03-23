import java.util.Random;

public class Monstre extends EntiteMobile {
    public Monstre(Direction direction) {
      super(direction);
    }
  
    public String toString(String background) {
      char symbol;
      switch (this.direction) {
          case nord: symbol = 'm'; break;
          case sud: symbol = 'w'; break;
          case est: symbol = '»'; break;
          case ouest: symbol = '«'; break;
          default: symbol = '?'; break;
      }
      return Character.toString(background.charAt(0)) + symbol + Character.toString(background.charAt(2));
  }

  public void changerDirection() {
    // On génère aléatoirement une nouvelle direction pour le monstre
    this.direction = Direction.values()[new Random().nextInt(Direction.values().length)];
  }

    @Override
public void action(Case courante, Case cible) {
  // Si la case cible contient un obstacle ou un personnage, on décrémente sa résistance
  if (cible.getContenu() instanceof Obstacle || cible.getContenu() instanceof Personnage) {
    Entite entite = cible.getContenu();
    entite.decrementerResistance();
    
    // Si l'entite doit disparaître, on la retire du jeu
    if (entite.doitDisparaitre()) {
      cible.vide();
    }
  }
  
  // Si la case cible est libre, on y fait entrer la créature
  else if (cible.estLibre()) {
    cible.entre(this);
  }
  
  // Si la case cible n'est pas libre, on change la direction de la créature
  else {
    this.changerDirection();
  }
}

}
