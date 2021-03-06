import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MyApplet extends JApplet implements ActionListener {
  static int app_width = 800;
  static int app_height = 640;
  double mutationRate = 0.01;
  int rocketsNum = 1000;
  Population population = new Population(mutationRate, rocketsNum);
  JButton start_button;

  public void init() {
    setSize(app_width, app_height);
    start_button = new JButton("START");
    start_button.addActionListener(this);
    setLayout(new FlowLayout());
    add(population);
    add(start_button);
  }

  public void actionPerformed(ActionEvent e){
    population.draw = !population.draw;
    if(population.draw) {
      population.repaint();
      start_button.setText("PAUSE");
    }
    else {
      start_button.setText("START");
    }
  }
}

/*<applet code=MyApplet.java width=800 height=640></applet>*/
