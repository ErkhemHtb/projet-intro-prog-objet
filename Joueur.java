public class Joueur extends Entite {
  private Direction direction;
  private Terrain terrain;
  private int positionX;
  private int positionY;

  public Joueur() {
    // Appeler le constructeur de la classe parente avec la résistance par défaut
    super(1);

    // Initialiser les attributs supplémentaires
    this.positionX = 0;
    this.positionY = 0;
    this.direction = Direction.nord;
  }

  public Joueur(Terrain terrain) {
    // Appeler le constructeur de la classe parente avec la résistance par défaut
    super(1);
  
    // Initialiser les attributs supplémentaires
    this.positionX = 0;
    this.positionY = 0;
    this.direction = Direction.nord;
    this.terrain = terrain;
  }
  

  public Joueur(int positionX, int positionY, Direction direction) {
    // Appeler le constructeur de la classe parente avec la résistance par défaut
    super(1);

    // Initialiser les attributs supplémentaires avec les valeurs passées en argument
    this.positionX = positionX;
    this.positionY = positionY;
    this.direction = direction;
  }

  public Joueur(int resistance) {
    super(resistance);
  }

  public Joueur(Direction direction) {
    super(1); // Appel du constructeur de la classe parente avec une résistance initiale de 1
    this.direction = direction;
  }
  


  public Joueur(int resistance, Direction direction) {
    super(resistance);
    this.direction = direction;
}

  public Joueur(int positionX, int positionY, Direction direction, int resistance) {
    super(resistance);
    this.positionX = positionX;
    this.positionY = positionY;
    this.direction = direction;
}

   // Récupérer la position en X du joueur
   public int getPositionX() {
    return positionX;
  }

  // Récupérer la position en Y du joueur
  public int getPositionY() {
    return positionY;
  }

  // Définir la nouvelle position du joueur
  public void setPosition(int positionX, int positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
  }

  public Direction getDirection() {
    return direction;
  }

  // Définir la nouvelle direction du joueur
  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public void deplacer(Direction dir) {
    // Récupérer la position actuelle du joueur
    int x = this.getPositionX();
    int y = this.getPositionY();
    
    // Modifier la position du joueur en fonction de la direction choisie
    switch (dir) {
        case nord:
            y -= 1;
            break;
        case est:
            x += 1;
            break;
        case sud:
            y += 1;
            break;
        case ouest:
            x -= 1;
            break;
    }
    
    // Vérifier si la nouvelle position est dans les limites du terrain
    if (x >= 0 && x < terrain.getLargeur() && y >= 0 && y < terrain.getHauteur()) {
        // Récupérer la case à la nouvelle position
        Case c = terrain.getCase(x, y);
        
        // Vérifier si la case est traversable
        if (c.estTraversable()) {
            // Déplacer le joueur dans la nouvelle direction
            this.setPosition(x, y);
            this.setDirection(dir);
        }
    }
  }

  public String toString(String background) {
    return "<html><b>H</b></html>";
  }


}