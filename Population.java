import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Population extends JPanel implements MouseListener {
  boolean draw;
  static int size = 600;

  double mutationRate;
  Rocket[] population;
  ArrayList<Rocket> matingPool;
  int generations, cycleNum;
  static int lifetime = size;

  static Vector2d target;

  public Population(double m, int num) {
    draw = false;
    target = new Vector2d(size/2, 24);

    mutationRate = m;
    population = new Rocket[num];
    matingPool = new ArrayList<Rocket>();
    generations = 0;

    for(int i = 0; i < population.length; i++) {
      Vector2d location = new Vector2d(size / 2, size - 20);
      population[i] = new Rocket(location, new DNA());
    }

    setBorder(BorderFactory.createLineBorder(Color.black));
    setBackground(Color.WHITE);
    addMouseListener(this);
  }

  public void mousePressed(MouseEvent e) {
    System.out.println("clicked");
    target.set(e.getX(), e.getY());
  }
  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  public void mouseDragged(MouseEvent e) {}

  public Dimension getPreferredSize() {
    return new Dimension(size, size);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawString("Generation: " + getGenerations(), 10, 18);
    g.drawString("Cycles to next gen: " + (lifetime - cycleNum), 10, 36);
    g.setColor(Color.RED);
    g.fillRect((int)target.x, (int)target.y, 12, 12);
    g.setColor(Color.BLACK);
    for(int i = 0; i < population.length; i++) {
      g.fillRect((int)population[i].location.x, (int)population[i].location.y, 5, 5);
    }
    if(draw) {
      if(cycleNum < lifetime) {
        live();
        cycleNum++;
      }
      else {
        cycleNum = 0;
        fitness();
        selection();
        reproduction();
      }
    }
    try {
      Thread.sleep(10);
    } catch(InterruptedException t) {

    }
    repaint();
  }

  public void live() {
    for(int i = 0; i < population.length; i++) {
      population[i].run();
    }
  }

  public void fitness() {
    for(int i = 0; i < population.length; i++) {
      population[i].fitness();
    }
  }

  public void selection() {
    matingPool.clear();

    double maxFitness = getMaxFitness();

    for(int i = 0; i < population.length; i++) {
      double fitnessNormal = Vector2d.map(population[i].getFitness(), 0, maxFitness, 0, 1);
      int n = (int) (fitnessNormal * 100);
      for(int j = 0; j < n; j++) {
        matingPool.add(population[i]);
      }
    }
  }

  public void reproduction() {
    for(int i = 0; i < population.length; i++) {
      int m = (int) (Math.random() * matingPool.size());
      int d = (int) (Math.random() * matingPool.size());

      Rocket mom = matingPool.get(m);
      Rocket dad = matingPool.get(d);

      DNA momgenes = mom.getDNA();
      DNA dadgenes = dad.getDNA();

      DNA child =  momgenes.crossover(dadgenes);

      child.mutate(mutationRate);

      Vector2d location = new Vector2d(size / 2, size - 20);
      population[i] = new Rocket(location, child);
    }
    generations++;
  }

  public int getGenerations() {
    return generations;
  }

  public double getMaxFitness() {
    double record = 0;
    for(int i = 0; i < population.length; i++) {
      if(population[i].getFitness() > record) {
        record = population[i].getFitness();
      }
    }
    return record;
  }
}
