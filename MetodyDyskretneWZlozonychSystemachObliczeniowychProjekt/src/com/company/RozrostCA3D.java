package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RozrostCA3D {
    Mapa3D mapaOut;
    int w, h, z;
    int a, b, c, d, e, f;
    int suma;
    int count;

    RozrostCA3D(Mapa3D mapaIn) {
        this.w = mapaIn.w;
        this.h = mapaIn.h;
        this.z = mapaIn.z;
        suma = w * h * z;
        mapaOut = new Mapa3D(w, h, z);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                for (int k = 0; k < z; k++) {
                    mapaOut.map[i][j][k] = mapaIn.map[i][j][k];
                }
            }
        }
    }

    int czyWypelnione(Mapa3D map) {
        int count = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                for (int k = 0; k < z; k++) {
                    if (map.map[i][j][k][0] == 1) {
                        count++;
                    }
                }
            }
        }

        if (count == suma) {
            return 1;
        } else {
            return 0;
        }
    }

    //Sąsiedztwo Moore'a
    void RozrostCANM_Per(Mapa3D map) {

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                for (int k = 0; k < z; k++) {
                    if (map.map[i][j][k][0] == 0) {
                        count = 0;
                        a = j - 1;
                        b = j + 1;
                        c = i - 1;
                        d = i + 1;
                        e = k - 1;
                        f = k + 1;
                        if (i == 0) {
                            c = w - 1;
                        }
                        if (j == 0) {
                            a = h - 1;
                        }
                        if (k == 0) {
                            e = z - 1;
                        }
                        if (i == w - 1) {
                            d = 0;
                        }
                        if (j == h - 1) {
                            b = 0;
                        }
                        if (k == z - 1) {
                            f = 0;
                        }

                        if (map.map[i][j][f][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[i][j][f];
                        } else if (map.map[i][b][f][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[i][b][f];
                        } else if (map.map[i][a][f][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[i][a][f];
                        } else if (map.map[c][j][f][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[c][j][f];
                        } else if (map.map[d][j][f][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[d][j][f];
                        } else if (map.map[c][b][f][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[c][b][f];
                        } else if (map.map[d][b][f][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[d][b][f];
                        } else if (map.map[c][a][f][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[c][a][f];
                        } else if (map.map[d][a][f][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[d][a][f];
                        } else if (map.map[i][b][k][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[i][b][k];
                        } else if (map.map[i][a][k][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[i][a][k];
                        } else if (map.map[c][j][k][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[c][j][k];
                        } else if (map.map[d][j][k][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[d][j][k];
                        } else if (map.map[c][b][k][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[c][b][k];
                        } else if (map.map[d][b][k][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[d][b][k];
                        } else if (map.map[c][a][k][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[c][a][k];
                        } else if (map.map[d][a][k][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[d][a][k];
                        } else if (map.map[i][j][e][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[i][j][e];
                        } else if (map.map[i][b][e][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[i][b][e];
                        } else if (map.map[i][a][e][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[i][a][e];
                        } else if (map.map[c][j][e][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[c][j][e];
                        } else if (map.map[d][j][e][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[d][j][e];
                        } else if (map.map[c][b][e][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[c][b][e];
                        } else if (map.map[d][b][e][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[d][b][e];
                        } else if (map.map[c][a][e][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[c][a][e];
                        } else if (map.map[d][a][e][0] != 0) {
                            mapaOut.map[i][j][k] = map.map[d][a][e];
                        }
                    }
                }
            }
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                for (int k = 0; k < z; k++) {
                    map.map[i][j][k] = mapaOut.map[i][j][k];
                }
            }
        }
    }

    //Sąsiedztwo Von Neumanna
    void RozrostCANVN_Per(Mapa3D map) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                for(int k=0;k<z;k++){
                    if(map.map[i][j][k][0]==0){
                        a = j - 1;
                        b = j + 1;
                        c = i - 1;
                        d = i + 1;
                        e = k - 1;
                        f = k + 1;
                        if (i == 0) {
                            c = w - 1;
                        }
                        if (j == 0) {
                            a = h - 1;
                        }
                        if(k==0){
                            e = z-1;
                        }
                        if (i == w - 1) {
                            d = 0;
                        }
                        if (j == h - 1) {
                            b = 0;
                        }

                        if (k == z - 1) {
                            f = 0;
                        }
                        if(map.map[i][j][f][0]!=0){
                            mapaOut.map[i][j][k] = map.map[i][j][f];
                        }else if(map.map[i][j][e][0]!=0){
                            mapaOut.map[i][j][k] = map.map[i][j][e];
                        }else if(map.map[i][a][k][0]!=0){
                            mapaOut.map[i][j][k] = map.map[i][a][k];
                        }else if(map.map[i][b][k][0]!=0){
                            mapaOut.map[i][j][k] = map.map[i][b][k];
                        }else if(map.map[c][j][k][0]!=0){
                            mapaOut.map[i][j][k] = map.map[c][j][k];
                        }else if(map.map[d][j][k][0]!=0){
                            mapaOut.map[i][j][k] = map.map[d][j][k];
                        }

                    }
                }
                /*

                    if (map.map[d][j][0] != 0) {
                        mapaOut.map[i][j] = map.map[d][j];
                    } else if (map.map[c][j][0] != 0) {
                        mapaOut.map[i][j] = map.map[c][j];
                    } else if (map.map[i][b][0] != 0) {
                        mapaOut.map[i][j] = map.map[i][b];
                    } else if (map.map[i][a][0] != 0) {
                        mapaOut.map[i][j] = map.map[i][a];
                    }
*/
                }
            }


        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                for(int k=0;k<z;k++) {
                    map.map[i][j][k] = mapaOut.map[i][j][k];
                }
            }
        }
    }

    //Sąsiedztwo Moore'a
    void RozrostCANM_Poc(Mapa3D map){
        for(int i=0;i< w;i++) {
            for (int j = 0; j < h; j++) {
                for(int k=0; k< z;k++) {
                    if (map.map[i][j][k][0] == 0) {
                        a = j - 1;
                        b = j + 1;
                        c = i - 1;
                        d = i + 1;
                        if(z!=1){
                            e = k - 1;
                            f = k + 1;
                        }else{
                            e = 0;
                            f = 0;
                        }
                        //System.out.println(i + " " + j +"  "+k);
                        if (i != 0 && j != 0 && k != 0 && i != w - 1 && j != h - 1 && k != z - 1) {
                            if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            } else if (map.map[i][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][f];
                            } else if (map.map[i][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][f];
                            } else if (map.map[c][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][f];
                            } else if (map.map[d][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][f];
                            } else if (map.map[c][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][f];
                            } else if (map.map[d][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][f];
                            } else if (map.map[c][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][f];
                            } else if (map.map[d][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][f];
                            } else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            } else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            } else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            } else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            } else if (map.map[c][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][k];
                            } else if (map.map[d][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][k];
                            } else if (map.map[c][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][k];
                            } else if (map.map[d][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][k];
                            } else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            } else if (map.map[i][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][e];
                            } else if (map.map[i][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][e];
                            } else if (map.map[c][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][e];
                            } else if (map.map[d][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            } else if (map.map[c][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][e];
                            } else if (map.map[d][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][e];
                            } else if (map.map[c][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][e];
                            } else if (map.map[d][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][e];
                            }
                        } else if (i == 0 && j == 0 && k == 0) {
                            if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            } else if (map.map[i][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][f];
                            } else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            } else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            } else if (map.map[d][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][f];
                            } else if (map.map[d][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][f];
                            } else if (map.map[d][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][k];
                            }
                        } else if (i == 0 && j == 0 & k == z - 1) {
                            if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            } else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            } else if (map.map[d][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][k];
                            } else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            } else if (map.map[d][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            } else if (map.map[d][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][e];
                            } else if (map.map[i][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][e];
                            }
                        } else if (i == w - 1 && j == 0 && k == z - 1) {
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            } else if (map.map[c][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][k];
                            } else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            } else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            } else if (map.map[c][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][e];
                            } else if (map.map[c][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][e];
                            } else if (map.map[i][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][e];
                            }
                        } else if (i == w - 1 && j == 0 && k == 0) {
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            } else if (map.map[c][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][k];
                            } else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            } else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            } else if (map.map[c][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][f];
                            } else if (map.map[c][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][f];
                            } else if (map.map[i][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][f];
                            }
                        } else if (i == 0 && j == h - 1 && k == 0) {
                            if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            } else if (map.map[d][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][k];
                            } else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            } else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            } else if (map.map[i][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][f];
                            } else if (map.map[d][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][f];
                            } else if (map.map[d][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][f];
                            }
                        } else if (i == 0 && j == h - 1 && k == z - 1) {
                            if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            } else if (map.map[d][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][k];
                            } else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            } else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            } else if (map.map[i][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][e];
                            } else if (map.map[d][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][e];
                            } else if (map.map[d][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            }
                        } else if (i == w - 1 && j == h - 1 && k == 0) {
                            if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            } else if (map.map[c][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][k];
                            } else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            } else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            } else if (map.map[i][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][f];
                            } else if (map.map[c][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][f];
                            } else if (map.map[c][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][f];
                            }
                        } else if (i == w - 1 && j == h - 1 && k == z - 1) {
                            if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            } else if (map.map[c][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][k];
                            } else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            } else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            } else if (map.map[i][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][e];
                            } else if (map.map[c][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][e];
                            } else if (map.map[c][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][e];
                            }
                        }else if(i==0&&j==0){
                            if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][e];
                            }else if (map.map[d][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][e];
                            }else if (map.map[d][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[d][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            }else if (map.map[d][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][e];
                            }else if (map.map[i][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][e];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[d][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }
                        }else if(i==0&&j==h-1){
                            if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][e];
                            }else if (map.map[d][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][e];
                            }else if (map.map[d][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[d][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            }else if (map.map[d][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][e];
                            }else if (map.map[i][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][e];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[d][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }
                        }else if(i==w-1&&j==0){
                            if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][e];
                            }else if (map.map[c][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][e];
                            }else if (map.map[c][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][e];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][f];
                            }else if (map.map[c][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][f];
                            }else if (map.map[c][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][f];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[c][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][k];
                            }else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }
                        }else if(i==w-1&&j==h-1){
                            if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][e];
                            }else if (map.map[c][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][e];
                            }else if (map.map[c][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][e];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][f];
                            }else if (map.map[c][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][f];
                            }else if (map.map[c][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][f];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[c][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][k];
                            }else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }
                        }else if(i==0&&k==0){
                            if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[i][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][f];
                            }else if (map.map[d][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][f];
                            }else if (map.map[d][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][k];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][f];
                            }else if (map.map[d][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][f];
                            }else if (map.map[d][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][k];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[d][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][f];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }
                        }else if(i==0&&k==z-1){
                            if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[i][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][e];
                            }else if (map.map[d][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][e];
                            }else if (map.map[d][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][k];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][e];
                            }else if (map.map[d][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][e];
                            }else if (map.map[d][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][k];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[d][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }
                        }else if(i==w-1&&k==0){
                            if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[i][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][f];
                            }else if (map.map[c][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][f];
                            }else if (map.map[c][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][k];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][f];
                            }else if (map.map[c][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][f];
                            }else if (map.map[c][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][k];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[c][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][f];
                            }else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }
                        }else if(i==w-1&&k==z-1){
                            if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[i][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][e];
                            }else if (map.map[c][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][e];
                            }else if (map.map[c][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][k];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][e];
                            }else if (map.map[c][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][e];
                            }else if (map.map[c][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][k];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[c][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][e];
                            }else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }
                        }else if(j==0 &&k==0){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[c][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][f];
                            }else if (map.map[c][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][f];
                            }else if (map.map[c][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[d][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][f];
                            }else if (map.map[d][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][f];
                            }else if (map.map[d][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][k];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][f];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }
                        }else if(j==0&&k==z-1){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[c][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][e];
                            }else if (map.map[c][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][e];
                            }else if (map.map[c][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[d][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            }else if (map.map[d][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][e];
                            }else if (map.map[d][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][k];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][e];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }
                        }else if(j==h-1 && k==0){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[c][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][f];
                            }else if (map.map[c][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][f];
                            }else if (map.map[c][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[d][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][f];
                            }else if (map.map[d][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][f];
                            }else if (map.map[d][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][k];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][f];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }
                        }else if(j==h-1 &&k==z-1){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[c][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][e];
                            }else if (map.map[c][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][e];
                            }else if (map.map[c][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[d][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            }else if (map.map[d][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][e];
                            }else if (map.map[d][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][k];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][e];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }
                        }else if(i==0){
                            if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][f];
                            }else if (map.map[i][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][f];
                            }else if (map.map[d][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][f];
                            }else if (map.map[d][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][f];
                            }else if (map.map[d][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][f];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][e];
                            }else if (map.map[i][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][e];
                            }else if (map.map[d][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            }else if (map.map[d][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][e];
                            }else if (map.map[d][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][e];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[d][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][k];
                            }else if (map.map[d][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][k];
                            }
                        }else if(i==w-1){
                            if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][f];
                            }else if (map.map[i][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][f];
                            }else if (map.map[c][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][f];
                            }else if (map.map[c][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][f];
                            }else if (map.map[c][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][f];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][e];
                            }else if (map.map[i][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][e];
                            }else if (map.map[c][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][e];
                            }else if (map.map[c][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][e];
                            }else if (map.map[c][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][e];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[c][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][k];
                            }else if (map.map[c][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][k];
                            }
                        }else if(k==0){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[c][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][k];
                            }else if (map.map[c][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][k];
                            }else if (map.map[c][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][f];
                            }else if (map.map[c][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][f];
                            }else if (map.map[c][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][f];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[d][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][k];
                            }else if (map.map[d][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][k];
                            }else if (map.map[d][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][f];
                            }else if (map.map[d][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][f];
                            }else if (map.map[d][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][f];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][f];
                            }else if (map.map[i][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][f];
                            }
                        }else if(k==z-1){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[c][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][k];
                            }else if (map.map[c][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][k];
                            }else if (map.map[c][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][e];
                            }else if (map.map[c][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][e];
                            }else if (map.map[c][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][e];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[d][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][k];
                            }else if (map.map[d][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][k];
                            }else if (map.map[d][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            }else if (map.map[d][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][e];
                            }else if (map.map[d][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][e];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][e];
                            }else if (map.map[i][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][e];
                            }
                        }else if(j==0){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[c][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][e];
                            }else if (map.map[c][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][f];
                            }else if (map.map[c][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][f];
                            }else if (map.map[c][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][k];
                            }else if (map.map[c][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][b][e];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[d][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            }else if (map.map[d][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][f];
                            }else if (map.map[d][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][f];
                            }else if (map.map[d][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][k];
                            }else if (map.map[d][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][b][e];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][b][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][f];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[i][b][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][e];
                            }
                        }else if(j==h-1){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[c][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][e];
                            }else if (map.map[c][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][f];
                            }else if (map.map[c][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][f];
                            }else if (map.map[c][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][k];
                            }else if (map.map[c][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][a][e];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[d][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][e];
                            }else if (map.map[d][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][f];
                            }else if (map.map[d][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][f];
                            }else if (map.map[d][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][k];
                            }else if (map.map[d][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][a][e];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][a][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][f];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][a][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][e];
                            }
                        }
                    }
                }
            }
        }

        for(int i=0; i< w;i++){
            for(int j=0;j<h;j++){
                for(int k=0;k<z;k++){
                    map.map[i][j][k] = mapaOut.map[i][j][k];
                }
            }
        }
    }

    //Sąsiedztwo VonNeumanna
    void RozrostCANVN_Poc(Mapa3D map){
        for(int i=0;i< w;i++) {
            for (int j = 0; j < h; j++) {
                for(int k=0; k< z;k++) {
                    if (map.map[i][j][k][0] == 0) {
                        a = j - 1;
                        b = j + 1;
                        c = i - 1;
                        d = i + 1;
                        if(z!=1){
                            e = k - 1;
                            f = k + 1;
                        }else{
                            e = 0;
                            f = 0;
                        }

                        //System.out.println(i + " " + j +"  "+k);
                        if (i != 0 && j != 0 && k != 0 && i != w - 1 && j != h - 1 && k != z - 1) {
                            if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            } else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            } else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            } else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            } else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }
                        } else if (i == 0 && j == 0 && k == 0) {
                            if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            } else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            } else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }
                        } else if (i == 0 && j == 0 & k == z - 1) {
                            if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            } else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            } else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }
                        } else if (i == w - 1 && j == 0 && k == z - 1) {
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            } else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            } else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }
                        } else if (i == w - 1 && j == 0 && k == 0) {
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            } else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            } else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }
                        } else if (i == 0 && j == h - 1 && k == 0) {
                            if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            } else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            } else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }
                        } else if (i == 0 && j == h - 1 && k == z - 1) {
                            if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            } else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            } else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }
                        } else if (i == w - 1 && j == h - 1 && k == 0) {
                            if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            } else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            } else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }
                        } else if (i == w - 1 && j == h - 1 && k == z - 1) {
                            if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            } else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            } else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }
                        }else if(i==0&&j==0){
                            if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }
                        }else if(i==0&&j==h-1){
                            if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }
                        }else if(i==w-1&&j==0){
                            if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }
                        }else if(i==w-1&&j==h-1){
                            if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }
                        }else if(i==0&&k==0){
                            if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }
                        }else if(i==0&&k==z-1){
                            if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }
                        }else if(i==w-1&&k==0){
                            if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }
                        }else if(i==w-1&&k==z-1){
                            if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }
                        }else if(j==0 &&k==0){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }
                        }else if(j==0&&k==z-1){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }
                        }else if(j==h-1 && k==0){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }
                        }else if(j==h-1 &&k==z-1){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }
                        }else if(i==0){
                            if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }
                        }else if(i==w-1){
                            if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }
                        }else if(k==0){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }
                        }else if(k==z-1){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }else if (map.map[i][b][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][b][k];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }
                        }else if(j==0){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }
                        }else if(j==h-1){
                            if (map.map[c][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[c][j][k];
                            }else if (map.map[d][j][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[d][j][k];
                            }else if (map.map[i][j][e][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][e];
                            }else if (map.map[i][j][f][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][j][f];
                            }else if (map.map[i][a][k][0] != 0) {
                                mapaOut.map[i][j][k] = map.map[i][a][k];
                            }
                        }
                    }
                }
            }
        }

        for(int i=0; i< w;i++){
            for(int j=0;j<h;j++){
                for(int k=0;k<z;k++){
                    map.map[i][j][k] = mapaOut.map[i][j][k];
                }
            }
        }
    }

    void exportIMGLayers(Mapa3D map, int step) throws IOException {
        for(int i=0;i< map.k;i++){
            exportIMGLayer(map, step,i);
        }
    }

    void exportIMGLayer(Mapa3D map, int step, int k) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = bufferedImage.createGraphics();


        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                g2d.setColor(new Color(map.map[i][j][k][1], map.map[i][j][k][2], map.map[i][j][k][3]));
                g2d.fillRect(i, j, 1, 1);
            }
        }
        g2d.dispose();

        File file = new File("mapaLayer "+step+  " " +k +  ".bmp");
        ImageIO.write(bufferedImage, "bmp", file);
    }
}