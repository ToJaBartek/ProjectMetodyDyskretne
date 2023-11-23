package com.company;
import javax.swing.*;

public class MyFrame extends JFrame{


    MyFrame(Mapa3D mapa, int config[], String name){

        MyPanel panel = new MyPanel(mapa, config[0], config[1], config[2], name);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(2*config[2]+config[0]+4,2*config[2]+2*config[1]+6);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        panel.repaint();
    }

}
