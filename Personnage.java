import java.util.Random;

public class Personnage extends EntiteMobile {
    public Personnage(Direction direction) {
        super(direction);
    }
    
    @Override
    public String toString(String background) {
        char symbol;
        switch (this.direction) {
            case nord: symbol = '^'; break;
            case sud: symbol = 'v'; break;
            case est: symbol = '>'; break;
            case ouest: symbol = '<'; break;
            default: symbol = ' '; break;
        }
        return Character.toString(background.charAt(0)) + symbol + Character.toString(background.charAt(2));
    }

    public void changerDirection() {
        // On génère aléatoirement une nouvelle direction pour le personnage
        this.direction = Direction.values()[new Random().nextInt(Direction.values().length)];
      }


@Override
public void action(Case courante, Case cible) {
  // Si la case cible est une sortie, le personnage sort du jeu
  if (cible instanceof Sortie) {
    Jeu.nbSauves++;
  }
  
  // Si la case cible contient un obstacle, on décrémente sa résistance
  else if (cible.getContenu() instanceof Obstacle) {
    Entite entite = cible.getContenu();
    entite.decrementerResistance();
    
    // Si l'entite doit disparaître, on la retire du jeu
    if (entite.doitDisparaitre()) {
      cible.vide();
    }
  }
  
  // Si la case cible est libre, on y fait entrer le personnage
  else if (cible.estLibre()) {
    cible.entre(this);
  }
  
  // Si la case cible n'est pas libre, on change la direction du personnage
  else {
    this.changerDirection();
  }
}

      
    
}