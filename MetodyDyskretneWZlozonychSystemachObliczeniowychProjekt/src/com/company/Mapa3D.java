package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Mapa3D {
    int w,h,z; // Wymiary powierzchni
    int[][][][] map;
    int lZarodkow; // Liczba zarodków
    int i,j,k, x, y,zz; // Do pętli (i, j) Do losowania pozycji nowych zarodkow (x, y)
    int r,g,b; //Do wyboru koloru ziarna
    int[][] tablicaKolorow;

    Mapa3D(int w, int h, int z){
        this.w = w;
        this.h = h;
        this.z = z;
        map = new int[w][h][z][5]; //[0] - określa czy jest ziarno [1] - kolor r [2] - kolor g [3] - kolor b [4] - energia
        for(i=0;i<w;i++){
            for(j=0;j<h;j++){
                for(k=0;k<z;k++){
                    for(int l=0;l<4;l++){
                        map[i][j][k][l]=0;
                    }
                }
            }
        }
    }

    void wypelnijMape(int lZarodkow){ // Losowe wypełnienie mapy zadaną liczbą ziaren + przypisanie koloru ziarna

        this.lZarodkow = lZarodkow;
        Random rand = new Random();
        for(i=0; i<lZarodkow; i++){
            x = rand.nextInt(w);
            y = rand.nextInt(h);
            zz = rand.nextInt(z);
            if(sprawdzZiarno(x,y,zz)==0){
                map[x][y][zz][0]=1;
                wybierzKolor();
                map[x][y][zz][1]=r;
                map[x][y][zz][2]=g;
                map[x][y][zz][3]=b;
            }else{
                i--;
            }
        }
    }

    void wypelnijMC(int lZarodkow){
        this.lZarodkow=lZarodkow;
        tablicaKolorow = new int[lZarodkow][3];
        wypelnijTablice(lZarodkow);
        Random rand = new Random();
        int rgb;
        for(int i=0;i<w*h*z;i++){
            x = rand.nextInt(w);
            y = rand.nextInt(h);
            zz = rand.nextInt(z);
            if(sprawdzZiarno(x,y,zz)==0){
                map[x][y][zz][0]=1;
                rgb = rand.nextInt(lZarodkow);
                map[x][y][zz][1]=tablicaKolorow[rgb][0];
                map[x][y][zz][2]=tablicaKolorow[rgb][1];
                map[x][y][zz][3]=tablicaKolorow[rgb][2];
            }else{
                i--;
            }
        }
    }

    void wypelnijTablice(int lZarodkow){
        Random rand = new Random();
        for(int i=0;i<lZarodkow;i++){
            r = rand.nextInt(255);
            g = rand.nextInt(255);
            b = rand.nextInt(255);
            if(sprawdzKolor(r,g,b,i)==0){
                tablicaKolorow[i][0]=r;
                tablicaKolorow[i][1]=g;
                tablicaKolorow[i][2]=b;
            }else{
                i--;
            }
        }
    }

    int sprawdzKolor(int r, int g,int b,int lKolor){
        for(int i=0;i<lKolor;i++){
            if(tablicaKolorow[i][0]==r&&tablicaKolorow[i][1]==g&&tablicaKolorow[i][2]==b){
                return 1;
            }
        }
        return 0;
    }

    int sprawdzZiarno(int x, int y, int z){
        if(map[x][y][z][0]==0){
            return 0; // Nie ma zirana na tej pozycji
        }else{
            return 1; // W wylosowanym miejscu jest już ziarno
        }
    }

    void wybierzKolor(){ // Funkcja losuje kolor i sprawdza czy nie został jeszcze wykorzystany
        Random rand = new Random();
        r = rand.nextInt(255);
        g = rand.nextInt(255);
        b = rand.nextInt(255);
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                for(int k=0;k<z;k++){
                    if(map[i][j][k][1]==r && map[i][j][k][2]==g&&map[i][j][k][3]==b){
                        wybierzKolor();
                    }
                }
            }
        }
    }

    void exportIMGFront() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = bufferedImage.createGraphics();

        for(int i=0; i< w;i++){
            for(int j=0;j<h;j++){
                g2d.setColor(new Color(map[i][j][0][1],map[i][j][0][2], map[i][j][0][3]));
                g2d.fillRect(i,j,1,1);
            }
        }
        g2d.dispose();

        File file = new File("mapaFront.bmp");
        ImageIO.write(bufferedImage,"bmp",file);
    }

    void exportIMGBack() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = bufferedImage.createGraphics();

        for(int i=0; i< w;i++){
            for(int j=0;j<h;j++){
                g2d.setColor(new Color(map[i][j][z-1][1],map[i][j][z-1][2], map[i][j][z-1][3]));
                g2d.fillRect(i,j,1,1);
            }
        }
        g2d.dispose();

        File file = new File("mapaBack.bmp");
        ImageIO.write(bufferedImage,"bmp",file);
    }

    void exportIMGLeft() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(z,h,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = bufferedImage.createGraphics();

        for(int i=0; i< h;i++){
            for(int j=0;j<z;j++){
                g2d.setColor(new Color(map[0][i][j][1],map[0][i][j][2], map[0][i][j][3]));
                g2d.fillRect(i,j,1,1);
            }
        }
        g2d.dispose();

        File file = new File("mapaLeft.bmp");
        ImageIO.write(bufferedImage,"bmp",file);
    }

    void exportIMGRight() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(z,h,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = bufferedImage.createGraphics();

        for(int i=0; i< h;i++){
            for(int j=0;j<z;j++){
                g2d.setColor(new Color(map[w-1][i][j][1],map[w-1][i][j][2], map[w-1][i][j][3]));
                g2d.fillRect(i,j,1,1);
            }
        }
        g2d.dispose();

        File file = new File("mapaRight.bmp");
        ImageIO.write(bufferedImage,"bmp",file);
    }

    void exportIMGTop() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(w,z,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = bufferedImage.createGraphics();

        for(int i=0; i< w;i++){
            for(int j=0;j<z;j++){
                g2d.setColor(new Color(map[i][0][j][1],map[i][0][j][2], map[i][0][j][3]));
                g2d.fillRect(i,j,1,1);
            }
        }
        g2d.dispose();

        File file = new File("mapaTop.bmp");
        ImageIO.write(bufferedImage,"bmp",file);
    }

    void exportIMGDown() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(w,z,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = bufferedImage.createGraphics();


        for(int i=0; i< w;i++){
            for(int j=0;j<z;j++){
                g2d.setColor(new Color(map[i][h-1][j][1],map[i][h-1][j][2], map[i][h-1][j][3]));
                g2d.fillRect(i,j,1,1);
            }
        }
        g2d.dispose();

        File file = new File("mapaDown.bmp");
        ImageIO.write(bufferedImage,"bmp",file);
    }


    void exportIMGLayers() throws IOException{
        for(int i=0;i<k;i++){
            exportIMGLayer(i);
        }
    }

    void exportIMGLayers(String additional) throws IOException{
        for(int i=0;i<k;i++){
            exportIMGLayer(i,additional);
        }
    }

    void exportIMGShell() throws IOException {
        exportIMGFront();
        exportIMGBack();
        exportIMGLeft();
        exportIMGRight();
        exportIMGTop();
        exportIMGDown();
    }

    void exportIMGLayer(int k) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = bufferedImage.createGraphics();


        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                g2d.setColor(new Color(map[i][j][k][1], map[i][j][k][2], map[i][j][k][3]));
                g2d.fillRect(i, j, 1, 1);
            }
        }
        g2d.dispose();

        File file = new File("mapaLayer " +k+".bmp");
        ImageIO.write(bufferedImage, "bmp", file);

    }

    void exportIMGLayer(int k, String additional) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = bufferedImage.createGraphics();


            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    g2d.setColor(new Color(map[i][j][k][1], map[i][j][k][2], map[i][j][k][3]));
                    g2d.fillRect(i, j, 1, 1);
                }
            }
            g2d.dispose();

            File file = new File("mapaLayer " + additional +k+".bmp");
            ImageIO.write(bufferedImage, "bmp", file);

    }

}