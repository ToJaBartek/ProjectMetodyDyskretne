package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RozrostCA {
    Mapa mapaOut;
    int w,h;
    int a,b,c,d;
    int suma;
    int count;
    RozrostCA(Mapa mapaIn){
        this.w = mapaIn.w;
        this.h = mapaIn.h;
        suma = w*h;
        mapaOut = new Mapa(w, h);

        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                mapaOut.map[i][j] = mapaIn.map[i][j];
            }
        }
    }

    //Sąsiedztwo Moore'a
    void RozrostCANM_Per(Mapa map){
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                if(map.map[i][j][0]==0){
                    count=0;
                    a = j - 1;
                    b = j + 1;
                    c = i - 1;
                    d = i + 1;
                    if (i == 0) {
                        c = w - 1;
                    }
                    if (j == 0) {
                        a = h - 1;
                    }
                    if (i == w - 1) {
                        d = 0;
                    }
                    if (j == h - 1) {
                        b = 0;
                    }

                    if (map.map[d][j][0] != 0) {
                        mapaOut.map[i][j] = map.map[d][j];
                    } else if (map.map[c][j][0] != 0) {
                        mapaOut.map[i][j] = map.map[c][j];
                    } else if (map.map[i][b][0] != 0) {
                        mapaOut.map[i][j] = map.map[i][b];
                    } else if (map.map[i][a][0] != 0) {
                        mapaOut.map[i][j] = map.map[i][a];
                    } else if (map.map[d][b][0] != 0) {
                        mapaOut.map[i][j] = map.map[d][b];
                    } else if (map.map[d][a][0] != 0) {
                        mapaOut.map[i][j] = map.map[d][a];
                    } else if (map.map[c][a][0] != 0) {
                        mapaOut.map[i][j] = map.map[c][a];
                    } else if (map.map[c][b][0] != 0) {
                        mapaOut.map[i][j] = map.map[c][b];
                    }
                }
            }
        }
        for(int i=0; i< w;i++){
            for(int j=0;j<h;j++){
                map.map[i][j] = mapaOut.map[i][j];
            }
        }
    }

    //Sąsiedztwo Von Neumanna
    void RozrostCANVN_Per(Mapa map){
        for(int i=0;i< w;i++){
            for(int j=0;j<h;j++){
                if (map.map[i][j][0] == 0) {
                    a = j - 1;
                    b = j + 1;
                    c = i - 1;
                    d = i + 1;
                    if (i == 0) {
                        c = w - 1;
                    }
                    if (j == 0) {
                        a = h - 1;
                    }
                    if (i == w - 1) {
                        d = 0;
                    }
                    if (j == h - 1) {
                        b = 0;
                    }

                    if (map.map[d][j][0] != 0) {
                        mapaOut.map[i][j] = map.map[d][j];
                    } else if (map.map[c][j][0] != 0) {
                        mapaOut.map[i][j] = map.map[c][j];
                    } else if (map.map[i][b][0] != 0) {
                        mapaOut.map[i][j] = map.map[i][b];
                    } else if (map.map[i][a][0] != 0) {
                        mapaOut.map[i][j] = map.map[i][a];
                    }

                }
            }
        }

        for(int i=0; i< w;i++){
            for(int j=0;j<h;j++){
                map.map[i][j] = mapaOut.map[i][j];
            }
        }
    }

    //Sąsiedztwo Moore'a
    void RozrostCANM_Poc(Mapa map){
        for(int i=0;i< w;i++) {
            for (int j = 0; j < h; j++) {
                if (map.map[i][j][0] == 0) {
                    a = j - 1;
                    b = j + 1;
                    c = i - 1;
                    d = i + 1;
                    if (i == 0 && j == 0) {
                        if (map.map[d][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][b];
                        } else if (map.map[d][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][j];
                        } else if (map.map[i][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][b];
                        }
                    } else if (i == 0 && j == h - 1) {
                        if (map.map[i][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][a];
                        } else if (map.map[d][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][a];
                        } else if (map.map[d][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][j];
                        }
                    } else if (i == w - 1 && j == 0) {
                        if (map.map[c][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][j];
                        } else if (map.map[c][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][b];
                        } else if (map.map[i][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][b];
                        }
                    } else if (i == w - 1 && j == h - 1) {
                        if (map.map[i][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][a];
                        } else if (map.map[c][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][a];
                        } else if (map.map[c][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][j];
                        }
                    } else if (i == 0) {
                        if (map.map[i][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][a];
                        } else if (map.map[d][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][a];
                        } else if (map.map[d][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][j];
                        } else if (map.map[d][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][b];
                        } else if (map.map[i][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][b];
                        }
                    } else if (i == w - 1) {
                        if (map.map[i][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][a];
                        } else if (map.map[c][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][a];
                        } else if (map.map[c][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][j];
                        } else if (map.map[c][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][b];
                        } else if (map.map[i][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][b];
                        }
                    } else if (j == 0) {
                        if (map.map[c][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][j];
                        } else if (map.map[d][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][j];
                        } else if (map.map[c][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][b];
                        } else if (map.map[i][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][b];
                        } else if (map.map[d][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][b];
                        }
                    } else if (j == h - 1) {
                        if (map.map[c][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][j];
                        } else if (map.map[d][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][j];
                        } else if (map.map[c][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][a];
                        } else if (map.map[i][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][a];
                        } else if (map.map[d][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][a];
                        }
                    } else {
                        if (map.map[c][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][j];
                        } else if (map.map[d][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][j];
                        } else if (map.map[c][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][a];
                        } else if (map.map[i][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][a];
                        } else if (map.map[d][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][a];
                        } else if (map.map[c][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][b];
                        } else if (map.map[i][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][b];
                        } else if (map.map[d][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][b];
                        }
                    }
                }
            }
        }

        for(int i=0; i< w;i++){
            for(int j=0;j<h;j++){
                map.map[i][j] = mapaOut.map[i][j];
            }
        }
    }

    //Sąsiedztwo VonNeumanna
    void RozrostCANVN_Poc(Mapa map){
        for(int i=0;i< w;i++) {
            for (int j = 0; j < h; j++) {
                if (map.map[i][j][0] == 0) {
                    a = j - 1;
                    b = j + 1;
                    c = i - 1;
                    d = i + 1;

                    if (i == 0 && j == 0) {
                        if (map.map[d][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][j];
                        } else if (map.map[i][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][b];
                        }
                    } else if (i == 0 && j == h - 1) {
                        if (map.map[i][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][a];
                        }else if (map.map[d][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][j];
                        }
                    } else if (i == w - 1 && j == 0) {
                        if (map.map[c][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][j];
                        }else if (map.map[i][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][b];
                        }
                    } else if (i == w - 1 && j == h - 1) {
                        if (map.map[i][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][a];
                        }else if (map.map[c][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][j];
                        }
                    } else if (i == 0) {
                        if (map.map[i][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][a];
                        }else if (map.map[d][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][j];
                        }else if (map.map[i][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][b];
                        }
                    } else if (i == w - 1) {
                        if (map.map[i][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][a];
                        }else if (map.map[c][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][j];
                        }else if (map.map[i][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][b];
                        }
                    } else if (j == 0) {
                        if (map.map[c][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][j];
                        } else if (map.map[d][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][j];
                        }else if (map.map[i][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][b];
                        }
                    } else if (j == h - 1) {
                        if (map.map[c][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][j];
                        } else if (map.map[d][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][j];
                        }else if (map.map[i][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][a];
                        }
                    } else {
                        if (map.map[c][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[c][j];
                        } else if (map.map[d][j][0] != 0) {
                            mapaOut.map[i][j] = map.map[d][j];
                        }else if (map.map[i][a][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][a];
                        }else if (map.map[i][b][0] != 0) {
                            mapaOut.map[i][j] = map.map[i][b];
                        }
                    }
                }
            }
        }

        for(int i=0; i< w;i++){
            for(int j=0;j<h;j++){
                map.map[i][j] = mapaOut.map[i][j];
            }
        }
    }


    int czyWypelnione(Mapa map){
        int count=0;
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                if(map.map[i][j][0]==1){
                    count++;
                }
            }
        }

        if(count==suma)  {
            return 1;
        }else{
            return 0;
        }
    }

    void exportIMG(Mapa map,int count) throws IOException{
        BufferedImage bufferedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = bufferedImage.createGraphics();

        for(int i=0; i< w;i++){
            for(int j=0;j<h;j++){
                g2d.setColor(new Color(map.map[i][j][1],map.map[i][j][2], map.map[i][j][3]));
                g2d.fillRect(i,j,1,1);
            }
        }
        g2d.dispose();

        File file = new File("mapa"+count+".bmp");
        ImageIO.write(bufferedImage,"bmp",file);
    }

}
