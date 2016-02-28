public class DNA {
  public Vector2d [] genes;

  double maxforce = 0.1;

  public DNA() {
    //generate random DNA for the first generation
    genes = new Vector2d[Population.lifetime];
    for(int i = 0; i < genes.length; i++) {
      double angle = Math.random() * 2 * Math.PI;
      //choose a vector from 360 degrees range in which the object will move
      genes[i] = new Vector2d(Math.cos(angle), Math.sin(angle));
      genes[i].mult(Math.random() * maxforce);
    }
  }

  public DNA(Vector2d[] newgenes) {
    genes = newgenes;
  }

  public DNA crossover(DNA partner) {
    Vector2d[] child = new Vector2d[genes.length];
    //randomly pick a midpoint
    int crossover = (int) (Math.random() * genes.length);
    //take half from one and half from the other based on the midpoint
    for(int i = 0; i < genes.length; i++) {
      if(i > crossover) {
        child[i] = genes[i];
      }
      else {
        child[i] = partner.genes[i];
      }
    }
    //return new children's DNA
    DNA newgenes = new DNA(child);
    return newgenes;
  }

  public void mutate(double m) { //m is mutationRate given to the population object while instantiating
    for(int i = 0; i < genes.length; i++) {
      if(Math.random() < m) {
        double angle = Math.random() * 2 * Math.PI;
        genes[i] = new Vector2d(Math.cos(angle), Math.sin(angle));
        genes[i].mult(Math.random() * maxforce);
      }
    }
  }
}
