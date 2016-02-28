public class Rocket {
  Vector2d location;
  Vector2d velocity;
  Vector2d acceleration;
  Vector2d target;

  double fitness;
  DNA dna;

  int genesNum = 0;

  boolean hitTarget = false;

  public Rocket(Vector2d loc, DNA dna_) {
    velocity = new Vector2d();
    acceleration = new Vector2d();
    location = loc.get();
    target = Population.target.get();
    dna = dna_;
  }

  public void applyForce(Vector2d force) {
    acceleration.add(force);
  }

  public void update() {
    velocity.add(acceleration);
    location.add(velocity);
    acceleration.mult(0);
  }

  public void fitness() {
    //calculate distance from rocket to target. the lower the distance, the higher the fitness
    double dist = Vector2d.dist(location.x, location.y, target.x, target.y);
    fitness = 1 / dist;
  }

  public void run() {
    checkTarget();
    if(!hitTarget) {
      applyForce(dna.genes[genesNum]); //applyForce written in this piece of DNA
      genesNum = (genesNum + 1) % dna.genes.length;
      update();
    }
  }

  public void checkTarget() {
    double dist = Vector2d.dist(location.x, location.y, target.x, target.y);
    if(dist < 12) {
      hitTarget = true;
    }
  }

  public double getFitness() {
    return fitness;
  }

  public DNA getDNA() {
    return dna;
  }
}
