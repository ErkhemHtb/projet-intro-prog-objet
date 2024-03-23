import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FenetreJeu extends JPanel implements KeyListener {
    private Terrain terrain;
    private int tailleCase = 24;
    private int hauteur, largeur;
    private JFrame frame;
    private Joueur joueur;

    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;
        this.joueur = t.getJoueur();
        

        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(largeur * tailleCase, hauteur * tailleCase));

        JFrame frame = new JFrame("Donjon");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        // On enregistre le KeyListener sur notre JFrame
        frame.addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        // Dessiner toutes les cases du terrain
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                // Récupérer la case à la position (i, j)
                Case c = terrain.getCase(i, j);
    
                // Déterminer la couleur à utiliser en fonction du contenu de la case
                Color color = Color.GRAY;
                if (c instanceof CaseIntraversable) {
                    color = Color.BLACK;
                } else if (c instanceof Sortie) {
                    color = Color.YELLOW;
                } else {
                    if (c.getContenu() != null && c.getContenu() instanceof Personnage) {
                        color = Color.WHITE;
                    } else if (c.getContenu() instanceof Monstre) {
                        color = Color.RED;
                    }
                }
    
                // Dessiner un rectangle de la couleur appropriée à la position (i, j)
                g.setColor(color);
                g.fillRect(j * tailleCase, i * tailleCase, tailleCase*(j+1), tailleCase*(i+1));
            }
        }
    }
    

    public void ecranFinal(int n) {
        frame.remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }

    // Implémentons les méthodes de l'interface KeyListener

    public void keyPressed(KeyEvent e) {
        // Récupérer la touche du clavier qui a été appuyée
        int key = e.getKeyCode();
        
        // Récupérer la direction actuelle du joueur
        Direction dir = joueur.getDirection();
        
        // Modifier la direction du joueur en fonction de la touche appuyée
        if (key == KeyEvent.VK_LEFT) {
            dir = Direction.ouest;
        } else if (key == KeyEvent.VK_RIGHT) {
            dir = Direction.est;
        } else if (key == KeyEvent.VK_UP) {
            dir = Direction.nord;
        } else if (key == KeyEvent.VK_DOWN) {
            dir = Direction.sud;
        } else if (key == KeyEvent.VK_SPACE) {
            // Si la touche "espace" est appuyée, vérifier si le joueur se trouve sur une Sortie
            Case c = terrain.getCase(joueur.getPositionX(), joueur.getPositionY());
            if (c instanceof Sortie) {
                // Si oui, sortir du donjon
                System.exit(0);
            }
        
        // Déplacer le joueur dans la nouvelle direction
        joueur.deplacer(dir);
        }

    }
    
    
    public void keyReleased(KeyEvent e) {
        // Ne pas utiliser
    }
    
    public void keyTyped(KeyEvent e) {
        // Ne pas utiliser
    }

}