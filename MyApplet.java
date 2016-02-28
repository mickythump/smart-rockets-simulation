import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyApplet extends JApplet implements ActionListener {
  static int app_width = 800;
  static int app_height = 640;
  double mutationRate = 0.01;
  int rocketsNum = 50;
  Population population = new Population(mutationRate, rocketsNum);

  public void init() {
    setSize(app_width, app_height);
    JButton start_button = new JButton("START");
    start_button.addActionListener(this);
    setLayout(new FlowLayout());
    add(population);
    add(start_button);
  }

  public void actionPerformed(ActionEvent e){
    population.draw = !population.draw;
    if(population.draw) {
      population.repaint();
    }
  }
}

/*<applet code=MyApplet.java width=800 height=640></applet>*/
