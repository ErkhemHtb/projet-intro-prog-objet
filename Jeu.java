public class Jeu {

    Terrain terrain;
    int sortis;
    protected static int nbSauves = 0;
    protected static int score = 0;
    Joueur joueur;

    /* Initialisation d'un jeu avec le terrain initial décrit dans
       le fichier [f] donné en paramètre */
    public Jeu(String f) {
        this.terrain = new Terrain(f);
        this.sortis = 0;
    }

    public Case getCaseDevant(Case c, Direction d) {
      int lig = c.getLig();
      int col = c.getCol();
  
      switch (d) {
          case nord:
              lig--;
              break;
          case sud:
              lig++;
              break;
          case est:
              col++;
              break;
          case ouest:
              col--;
              break;
      }
  
      // Vérifier que la case n'est pas en dehors du terrain
      if (lig >= 0 && lig < this.terrain.getHauteur() && col >= 0 && col < this.terrain.getLargeur()) {
          return this.terrain.getCase(lig, col);
      } else {
          return null;
      }
  }

  public Case getCaseCible(Case courante, Direction dir) {
    int lig = courante.getLig();
    int col = courante.getCol();
  
    switch (dir) {
      case nord:
        lig--;
        break;
      case sud:
        lig++;
        break;
      case est:
        col++;
        break;
      case ouest:
        col--;
        break;
    }
  
    return terrain.getCase(lig, col);
  }
  

  public void tour() {
    // Génère des coordonnées aléatoires sur le terrain.
    int lig = (int) (Math.random() * terrain.getLargeur());
    int col = (int) (Math.random() * terrain.getHauteur());
  
    // Récupère la case correspondante.
    Case courante = terrain.getCase(lig, col);
  
    // Si la case est traversable et contient une créature, fait agir la créature.
    if (courante instanceof CaseTraversable) {
      CaseTraversable ct = (CaseTraversable) courante;
      if (ct.getContenu() != null && ct.getContenu() instanceof EntiteMobile) {
        // Récupère la direction de la créature.
        Direction dir = ((EntiteMobile) ct.getContenu()).getDirection();
  
        // Récupère la case devant la créature.
        Case cible = getCaseCible(courante, dir);
  
        // Si la case devant la créature est traversable, fait agir la créature.
        if (cible instanceof CaseTraversable) {
          ((EntiteMobile) ct.getContenu()).action(courante, cible);
        }
      }
    }

  }
  
  public boolean partieFinie() {
    if (terrain.getCase(joueur.getPositionX(), joueur.getPositionY()) instanceof Sortie) {
      // Le joueur est sur une Sortie, on incrémente le score et on arrête le jeu
      score = nbSauves;
      return true;
    }
    if (terrain.getResistanceJoueur() == 0) {
      // Le joueur est mort, on divise le score par deux et on arrête le jeu
      score = nbSauves / 2;
      return true;
    }

    // Vérifier si le jeu est fini
    if (terrain.getNbPersonnages() == 0 || this.sortis == terrain.getNbPersonnages()) {
      // Arrêter le jeu
      System.out.println("Fin du jeu");
      score = nbSauves;
      return true;
  }
    // La partie n'est pas finie
    return false;
  }




  public static void main(String[] args) {
    // Initialiser un nouveau jeu
    Jeu jeu = new Jeu("laby2.txt");
    
    // Enchaîner les tours de jeu
    while (true) {
        // Faire agir une créature
        jeu.tour();
        
        // Afficher le terrain
        jeu.terrain.print();
    }
}


}