public class Obstacle extends Entite {
    public Obstacle() {
      super(3);
    }
  
    public Obstacle(int resistance) {
      super(resistance);
    }
  
    @Override
public String toString(String background) {
  if (this.resistance >= 3) {
    return "@@@";
  } else if (this.resistance == 1) {
    return Character.toString(background.charAt(0)) + "@" + Character.toString(background.charAt(2));
  } else if (this.resistance == 2) {
    return Character.toString(background.charAt(0)) + "@@" + Character.toString(background.charAt(2));
  } else {
    return background;
  }
}

  }