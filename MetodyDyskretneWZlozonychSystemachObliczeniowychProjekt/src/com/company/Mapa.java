package com.company;

import java.util.Random;

public class Mapa {
    int w,h,z; // Wymiary powierzchni
    int[][][] map;
    int[][] tablicaKolorow;
    int lZarodkow; // Liczba zarodków
    int i,j,k, x, y; // Do pętli (i, j) Do losowania pozycji nowych zarodkow (x, y)
    int r,g,b; //Do wyboru koloru ziarna

    Mapa(int w, int h){
        this.w = w;
        this.h = h;
        map = new int[w][h][5]; //[0] - określa czy jest ziarno [1] - kolor r [2] - kolor g [3] - kolor b [4] - energia
        for(i=0;i<w;i++){
            for(j=0;j<h;j++){
                for(int l=0;l<4;l++){
                        map[i][j][l]=0;
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
            if(sprawdzZiarno(x,y)==0){
                map[x][y][0]=1;
                wybierzKolor();
                map[x][y][1]=r;
                map[x][y][2]=g;
                map[x][y][3]=b;
            }else{
                i--;
            }
        }
    }

    void wypelnijMapeMC(int lZarodkow){
        this.lZarodkow = lZarodkow;
        Random rand = new Random();
        tablicaKolorow(lZarodkow);
        int rgb;
        for(int i=0;i< w*h;i++){
            x = rand.nextInt(w);
            y = rand.nextInt(h);
            if(sprawdzZiarno(x,y)==0){
                map[x][y][0] = 1;
                rgb = rand.nextInt(lZarodkow);
                map[x][y][1] = tablicaKolorow[rgb][0];
                map[x][y][2] = tablicaKolorow[rgb][1];
                map[x][y][3] = tablicaKolorow[rgb][2];
            }
        }
    }

    void tablicaKolorow(int lZarodkow){
        tablicaKolorow = new int[lZarodkow][3];
        Random rand = new Random();

        for(int i=0;i<lZarodkow;i++){
            r = rand.nextInt(255);
            g = rand.nextInt(255);
            b = rand.nextInt(255);
            if(czyWystapil(r,g,b)==1){
                tablicaKolorow[i][0] = r;
                tablicaKolorow[i][1] = g;
                tablicaKolorow[i][2] = b;

            }else{
                i--;
            }
        }
    }

    int czyWystapil(int r, int g, int b){

        for(int i=0;i<tablicaKolorow.length;i++){
            if(tablicaKolorow[i][0] ==r && tablicaKolorow[i][1] == g && tablicaKolorow[i][2] ==b){
                return 0;
            }
        }

        return 1;
    }

    int sprawdzZiarno(int x, int y){
        if(map[x][y][0]==0){
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
                if(map[i][j][1]==r && map[i][j][2]==g&&map[i][j][3]==b){
                    wybierzKolor();
                }
            }
        }

    }

    void wypiszMape(){
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                System.out.print(map[i][j][0]);
            }
            System.out.println();
        }
    }

}
