public class DNA {
  public Vector2d [] genes;

  double maxforce = 0.1;

  public DNA() {
    genes = new Vector2d[Population.lifetime];
    for(int i = 0; i < genes.length; i++) {
      double angle = Math.random() * 2 * Math.PI;
      genes[i] = new Vector2d(Math.cos(angle), Math.sin(angle));
      // genes[i] = new Vector2d(Math.random() * 2 - 1, Math.random() * 2 - 1);
      genes[i].mult(Math.random() * maxforce);
    }
  }

  public DNA(Vector2d[] newgenes) {
    genes = newgenes;
  }

  public DNA crossover(DNA partner) {
    Vector2d[] child = new Vector2d[genes.length];
    //Pick a midpoint
    int crossover = (int) (Math.random() * genes.length);
    //Take half from one and half from the other
    for(int i = 0; i < genes.length; i++) {
      if(i > crossover) {
        child[i] = genes[i];
      }
      else {
        child[i] = partner.genes[i];
      }
    }
    DNA newgenes = new DNA(child);
    return newgenes;
  }

  public void mutate(double m) {
    for(int i = 0; i < genes.length; i++) {
      if(Math.random() < m) {
        double angle = Math.random() * 2 * Math.PI;
        genes[i] = new Vector2d(Math.cos(angle), Math.sin(angle));
        genes[i].mult(Math.random() * maxforce);
      }
    }
  }
}
