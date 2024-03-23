import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Terrain {

    private int hauteur, largeur;
    private Case[][] carte;
    private Joueur joueur;
    private int resistanceJoueur;

    /* Initialisation d'un terrain à partir de la description donnée par
       un fichier texte. Format du fichier de description :
       - hauteur et largeur sur la première ligne
       - puis dessin du terrain (un caractère == une case) avec le codage
         suivant
         '#' pour un mur
         ' ' (espace) pour une case libre
         'o' pour une sortie
         '@' pour une case libre contenant un obstacle
         '^', 'v', '>', '<' pour une case libre contenant un personnage
         'm', 'w', '»', '«' pour une case libre contenant un monstre
    */
    public Terrain(String file) {
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            // Récupérer la résistance du joueur
            this.resistanceJoueur = sc.nextInt();
            sc.nextLine();
            this.carte = new Case[hauteur][largeur];
            for (int l=0; l<hauteur; l++) {
                String line = sc.nextLine();
                if (line.length() < largeur) {
                    // Extend the line with empty space characters
                    line = String.format("%-" + largeur + "s", line);
                }
                for (int c=0; c<largeur; c++) {
                    Case cc;
                    Character ch = line.charAt(c);
                    switch (ch) {
                        case '#': cc = new CaseIntraversable(l, c); break;
                        case ' ': cc = new CaseLibre(l, c); break;
                        case 'o': cc = new Sortie(l, c); break;
                        case '@': cc = new CaseLibre(l, c, new Obstacle()); break;
                        case '^': case '>': case 'v': case '<':
                            cc = new CaseLibre(l, c, new Personnage(Direction.ofChar(ch)));
                            break;
                        case 'm': case '»': case 'w': case '«':
                            cc = new CaseLibre(l, c, new Monstre(Direction.ofChar(ch)));
                            break;
                        case 'H':
                            cc = new CaseLibre(l, c, new Joueur(Direction.ofChar(ch)));
                            this.joueur = (Joueur) cc.getContenu(); // Initialiser la variable joueur ici
                            break;
                        default:  cc = null; break;
                    }
                    carte[l][c] = cc;
                }
            }
            sc.close();
        }
        catch (IOException e) { e.printStackTrace(); }
    }
    
    
    

    
    public void print() {
      for (int i = 0; i < hauteur; i++) {
          for (int j = 0; j < largeur; j++) {
              // Affiche la case (i, j)
              System.out.print(carte[i][j].toString());
          }
          // Saut de ligne à la fin de chaque ligne
          System.out.println();
      }
  }
      
  public Case getCase(int lig, int col) {
    // Vérifier que les coordonnées sont valides
    if (lig < 0 || lig > hauteur || col < 0 || col > largeur) {
        throw new IllegalArgumentException("Coordonées invalides : (" + lig + ", " + col + ")");
    }

    // Récupérer la case correspondante
    Case c = this.carte[lig][col];

    // Vérifier que la case n'est pas null
    if (c == null) {
        throw new NullPointerException("La case aux coordonées (" + lig + ", " + col + ") est null");
    }
    

    return c;
}



  public int getResistanceJoueur() {
    return resistanceJoueur;
  }

      public int getLargeur() {
        return largeur;
      }
    
      public int getHauteur() {
        return hauteur;
      }
      
      public int getNbPersonnages() {
  int nbPersonnages = 0;

  // Parcourir toutes les cases du terrain
  for (int i = 0; i < this.getHauteur(); i++) {
      for (int j = 0; j < this.getLargeur(); j++) {
          // Vérifier si la position est valide
          if (i >= 0 && i < this.getHauteur() && j >= 0 && j < this.getLargeur()) {
              // Récupérer la case à la position (i, j)
              Case c = this.getCase(i, j);

              // Vérifier si la case est libre ou une sortie
              if (c instanceof CaseLibre || c instanceof Sortie) {
                  // Vérifier si la case contient un personnage
                  if (c.getContenu() instanceof Personnage) {
                      // Incrémenter le compteur de personnages
                      nbPersonnages++;
                  }
              }
          }
      }
  }

  // Renvoyer le nombre de personnages trouvés
  return nbPersonnages;
}

public Joueur getJoueur() {
    return joueur;
}
      
    
  
    
}
