public abstract class Entite {
    protected int resistance;
  
    public Entite(int resistance) {
      this.resistance = resistance;
    }

    public int getResistance() {
      return resistance;
    }
  
    public abstract String toString(String background);
  
    public void decrementerResistance() {
      this.resistance--;
    }
  
    public boolean estVivant() {
      return this.resistance > 0;
    }

    public boolean doitDisparaitre() {
      return this.resistance <= 0;
    }
  }
  