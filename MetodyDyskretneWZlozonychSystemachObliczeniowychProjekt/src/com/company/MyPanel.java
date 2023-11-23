package com.company;


import javax.swing.*;
import java.awt.*;
public class MyPanel extends JPanel {

    Mapa3D mapa;
    int x,y,z;
    int[][][] tab;
    MyPanel(Mapa3D mapa, int x, int y, int z,String name){
        this.setName(name);
        this.mapa = mapa;
        this.x = x; this.y = y; this.z = z;
        this.setPreferredSize(new Dimension(2*z+x+4,2*z+2*y+10));
        tab = new int[2*z+x+4][2*z+2*y+6][3]; //tablica do wizualizacji każdej ze ścian obiektu

        for(int i=0;i<2*z+x+4;i++){
            for(int j=0;j<2*z+2*y+6;j++){
                tab[i][j][0]=255;
                tab[i][j][1]=255;
                tab[i][j][2]=255;
            }
        }

        //Mapowanie góry obiektu
        for(int i=z+2;i<z+2+x;i++){
            for(int j=0;j<z;j++){
                tab[i][z-1-j][0] = mapa.map[i-(z+2)][0][j][1];
                tab[i][z-1-j][1] = mapa.map[i-(z+2)][0][j][2];
                tab[i][z-1-j][2] = mapa.map[i-(z+2)][0][j][3];
            }
        }

        //Mapowanie dołu obiektu
        for(int i=z+2;i<z+2+x;i++){
            for(int j=z+y+4;j<z+z+y+4;j++){
                tab[i][j][0] = mapa.map[i-(z+2)][y-1][j-(z+y+4)][1];
                tab[i][j][1] = mapa.map[i-(z+2)][y-1][j-(z+y+4)][2];
                tab[i][j][2] = mapa.map[i-(z+2)][y-1][j-(z+y+4)][3];
            }
        }
        //Mapowanie lewej strony obiektu
        for(int i=0;i<z;i++){
            for(int j=z+2;j<z+2+y;j++){
                tab[z-1-i][j][0] = mapa.map[0][j-(z+2)][i][1];
                tab[z-1-i][j][1] = mapa.map[0][j-(z+2)][i][2];
                tab[z-1-i][j][2] = mapa.map[0][j-(z+2)][i][3];
            }
        }

        //Mapowanie prawej strony obiektu
        for(int i=z+4+x;i<z+z+4+x;i++){
            for(int j=z+2;j<z+2+y;j++){
                tab[i][j][0] = mapa.map[x-1][j-(z+2)][i-(z+4+x)][1];
                tab[i][j][1] = mapa.map[x-1][j-(z+2)][i-(z+4+x)][2];
                tab[i][j][2] = mapa.map[x-1][j-(z+2)][i-(z+4+x)][3];
            }
        }
        //Mapowanie przodu
        for(int i=z+2;i<z+2+x;i++){
            for(int j=z+2;j<z+2+y;j++){
                tab[i][j][0] = mapa.map[i-(z+2)][j-(z+2)][0][1];
                tab[i][j][1] = mapa.map[i-(z+2)][j-(z+2)][0][2];
                tab[i][j][2] = mapa.map[i-(z+2)][j-(z+2)][0][3];
            }
        }
        //Mapowanie tyłu
        for(int i=z+2;i<z+2+x;i++){
            for(int j=2*z+6+y;j<2*z+6+2*y;j++){
                int tmp =4*z+12+3*y-1-j;
                tab[i][tmp][0] = mapa.map[i-(z+2)][j-(z+2+y+2+z+2)][z-1][1];
                tab[i][tmp][1] = mapa.map[i-(z+2)][j-(z+2+y+2+z+2)][z-1][2];
                tab[i][tmp][2] = mapa.map[i-(z+2)][j-(z+2+y+2+z+2)][z-1][3];
            }
        }
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(1));

        for(int i=0;i<2*z+x+4;i++){
            for(int j=0;j<2*z+2*y+6;j++){
                g2D.setPaint(new Color(tab[i][j][0],tab[i][j][1],tab[i][j][2]));

                g2D.fillRect(i,j,1,1);
            }
        }
    }
}
