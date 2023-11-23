package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MC3D {

    Mapa3D map_out;
    int lZarodkow;
    int w,h, z;
    int x,y, zz;
    Random rand;
    int [][] colorTab; //Do zbierania wszystkich kolorów z mapy
    int a,b,c,d,e,f;
    int[] sasTab; //Do zbierania ID ziaren z sąsiedztwa
    int max; //Liczba zarodków
    int[][][] ETab; //Do zliczania energii w danym ziarnie
    int Rgb, rGb, rgB;

    MC3D(Mapa3D map){
        this.w = map.w; this.h = map.h; this.z = map.z;
        map_out = new Mapa3D(this.w,this.h,this.z);
        System.out.println(map.lZarodkow);
        this.lZarodkow = map.lZarodkow;
        System.out.println(this.lZarodkow);
        ETab = new int[w][h][z];
        rand = new Random();
        sasTab = new int[27];
    }

    void copyData(Mapa3D mapa){
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                for(int k=0;k<z;k++){
                    map_out.map[i][j][k] = mapa.map[i][j][k];
                }
                //System.out.println();
            }
        }
    }

    void rozrostMC(Mapa3D map) throws IOException {
        colorTab = new int[map.lZarodkow][3];// Przypisanie ID dla każdego koloru
        for(int i=0;i< map.lZarodkow;i++){
            colorTab[i][0] = map.tablicaKolorow[i][0];
            colorTab[i][1] = map.tablicaKolorow[i][1];
            colorTab[i][2] = map.tablicaKolorow[i][2];
            //System.out.println(map.tablicaKolorow[i][0]);
        }

        int[][][] checkTab = new int[w][h][z]; //Tablica do losowania ziaren 1-gdy wylosowane,0 gdy nie
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                for(int k=0;k<z;k++) {
                    checkTab[i][j][k] = 0;
                }
            }
        }
        //**Losowanie**//
        int x,y,zz,EDiff;
        for(int i=0;i<w*h*z;i++){
            x = rand.nextInt(w);
            y = rand.nextInt(h);
            zz = rand.nextInt(z);
            if(checkTab[x][y][zz]==0){
                checkTab[x][y][zz]=1;
                /**Obliczenie początkowego E**/
                ObliczE(x,y,zz,map,ETab);

                wylosujE(x,y,zz,map,ETab);

                /**Obliczenie nowej energii**/
                obliczNoweE(x,y,zz,map,ETab);
                //System.out.println("Stara energia = "+map_out.map[x][y][zz][4] + " Nowa energia: "+ ETab[x][y][zz]);
                EDiff = map_out.map[x][y][zz][4]-ETab[x][y][zz];
//                System.out.println("Różnica: "+EDiff);
                /**Jeżeli nowa energia jest większa, pozostawiamy początkową energię**/

                if(EDiff<=0){
                    map_out.map[x][y][zz][1] = Rgb;
                    map_out.map[x][y][zz][2] = rGb;
                    map_out.map[x][y][zz][3] = rgB;
                }
            }else{
                i--;
            }
            //exportIMG(map, x, y);
        }

        /**Kopiowanie danych do map_out**/
        for(int j=0; j< h;j++){
            for(int i=0;i<w;i++){
                for(int k=0;k<z;k++) {
                    map_out.map[i][j][k][4] = ETab[i][j][k];
                }
            }
        }

        /**Kopiowanie danych do map**/
        for(int i=0; i< w; i++){
            for(int j=0;j<h;j++){
                for(int k=0;k<z;k++) {
                //    if(map.map[i][j][k][1]!=map_out.map[i][j][k][1])
                //        System.out.print(map.map[i][j][k][1] + " " +map_out.map[i][j][k][1] + " | ");
                    map.map[i][j][k] = map_out.map[i][j][k];
                }
            }
            //System.out.println();
        }
    }

    void rozrostMCVN(Mapa3D map) throws IOException {
        colorTab = new int[map.lZarodkow][3];// Przypisanie ID dla każdego koloru
        for(int i=0;i< map.lZarodkow;i++){
            colorTab[i][0] = map.tablicaKolorow[i][0];
            colorTab[i][1] = map.tablicaKolorow[i][1];
            colorTab[i][2] = map.tablicaKolorow[i][2];
            //System.out.println(map.tablicaKolorow[i][0]);
        }

        int[][][] checkTab = new int[w][h][z]; //Tablica do losowania ziaren 1-gdy wylosowane,0 gdy nie
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                for(int k=0;k<z;k++) {
                    checkTab[i][j][k] = 0;
                }
            }
        }
        //**Losowanie**//
        int x,y,zz,EDiff;
        for(int i=0;i<w*h*z;i++){
            x = rand.nextInt(w);
            y = rand.nextInt(h);
            zz = rand.nextInt(z);
            if(checkTab[x][y][zz]==0){
                checkTab[x][y][zz]=1;
                /**Obliczenie początkowego E**/
                ObliczEVN(x,y,zz,map,ETab);

                wylosujEVN(x,y,zz,map,ETab);

                /**Obliczenie nowej energii**/
                obliczNoweEVN(x,y,zz,map,ETab);
                //System.out.println("Stara energia = "+map_out.map[x][y][zz][4] + " Nowa energia: "+ ETab[x][y][zz]);
                EDiff = map_out.map[x][y][zz][4]-ETab[x][y][zz];
//                System.out.println("Różnica: "+EDiff);
                /**Jeżeli nowa energia jest większa, pozostawiamy początkową energię**/

                if(EDiff<=0){
                    map_out.map[x][y][zz][1] = Rgb;
                    map_out.map[x][y][zz][2] = rGb;
                    map_out.map[x][y][zz][3] = rgB;
                }
            }else{
                i--;
            }
            //exportIMG(map, x, y);
        }

        /**Kopiowanie danych do map_out**/
        for(int j=0; j< h;j++){
            for(int i=0;i<w;i++){
                for(int k=0;k<z;k++) {
                    map_out.map[i][j][k][4] = ETab[i][j][k];
                }
            }
        }

        /**Kopiowanie danych do map**/
        for(int i=0; i< w; i++){
            for(int j=0;j<h;j++){
                for(int k=0;k<z;k++) {
                    //    if(map.map[i][j][k][1]!=map_out.map[i][j][k][1])
                    //        System.out.print(map.map[i][j][k][1] + " " +map_out.map[i][j][k][1] + " | ");
                    map.map[i][j][k] = map_out.map[i][j][k];
                }
            }
            //System.out.println();
        }
    }

    //Dla warunków periodycznych
    void ObliczE(int i, int j, int k, Mapa3D map, int[][][] ETab){
        a=j-1;
        b=j+1;
        c=i-1;
        d=i+1;
        e=k-1;
        f=k+1;
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
        if(k==0){
            e = z-1;
        }
        if(k==z-1){
            f=0;
        }
        //**sasTab - zawiera ID koloru sąsiada**//
        sasTab[0] =  ZwrocKolorID(map.map[c][a][k][1],map.map[c][a][k][2],map.map[c][a][k][3]);//GLS
        sasTab[1] =  ZwrocKolorID(map.map[i][a][k][1],map.map[i][a][k][2],map.map[i][a][k][3]);//GSS
        sasTab[2] =  ZwrocKolorID(map.map[d][a][k][1],map.map[d][a][k][2],map.map[d][a][k][3]);//GPS
        sasTab[3] =  ZwrocKolorID(map.map[c][j][k][1],map.map[c][j][k][2],map.map[c][j][k][3]);//SLS
        sasTab[4] =  ZwrocKolorID(map.map[d][j][k][1],map.map[d][j][k][2],map.map[d][j][k][3]);//SPS
        sasTab[5] =  ZwrocKolorID(map.map[c][b][k][1],map.map[c][b][k][2],map.map[c][b][k][3]);//GLS
        sasTab[6] =  ZwrocKolorID(map.map[i][b][k][1],map.map[i][b][k][2],map.map[i][b][k][3]);//GSS
        sasTab[7] =  ZwrocKolorID(map.map[d][b][k][1],map.map[d][b][k][2],map.map[d][b][k][3]);//GPS
        if(z!=1) {
            sasTab[8] = ZwrocKolorID(map.map[c][a][f][1], map.map[c][a][f][2], map.map[c][a][f][3]);//GoraLewaGora
            sasTab[9] = ZwrocKolorID(map.map[i][a][f][1], map.map[i][a][f][2], map.map[i][a][f][3]);//GoraSrodekGora
            sasTab[10] = ZwrocKolorID(map.map[d][a][f][1], map.map[d][a][f][2], map.map[d][a][f][3]);//GoraPrawaGora
            sasTab[11] = ZwrocKolorID(map.map[c][b][f][1], map.map[c][b][f][2], map.map[c][b][f][3]);//GoraLewaGora
            sasTab[12] = ZwrocKolorID(map.map[i][b][f][1], map.map[i][b][f][2], map.map[i][b][f][3]);//GoraSrodekGora
            sasTab[13] = ZwrocKolorID(map.map[d][b][f][1], map.map[d][b][f][2], map.map[d][b][f][3]);//GoraPrawaGora
            sasTab[14] = ZwrocKolorID(map.map[c][j][f][1], map.map[c][j][f][2], map.map[c][j][f][3]);//SLewaGora
            sasTab[15] = ZwrocKolorID(map.map[i][j][f][1], map.map[i][j][f][2], map.map[i][j][f][3]);//SSrodekGora
            sasTab[16] = ZwrocKolorID(map.map[d][j][f][1], map.map[d][j][f][2], map.map[d][j][f][3]);//SrodekPrawaGora

            if(z!=2) {
                sasTab[17] = ZwrocKolorID(map.map[c][a][e][1], map.map[c][a][e][2], map.map[c][a][e][3]);//GLD
                sasTab[18] = ZwrocKolorID(map.map[i][a][e][1], map.map[i][a][e][2], map.map[i][a][e][3]);//GSD
                sasTab[19] = ZwrocKolorID(map.map[d][a][e][1], map.map[d][a][e][2], map.map[d][a][e][3]);//GPD

                sasTab[20] = ZwrocKolorID(map.map[c][j][e][1], map.map[c][j][e][2], map.map[c][j][e][3]);//SLD
                sasTab[21] = ZwrocKolorID(map.map[i][j][e][1], map.map[i][j][e][2], map.map[i][j][e][3]);//SSD
                sasTab[22] = ZwrocKolorID(map.map[d][j][e][1], map.map[d][j][e][2], map.map[d][j][e][3]);//SPD

                sasTab[23] = ZwrocKolorID(map.map[c][b][e][1], map.map[c][b][e][2], map.map[c][b][e][3]);//GLD
                sasTab[24] = ZwrocKolorID(map.map[i][b][e][1], map.map[i][b][e][2], map.map[i][b][e][3]);//GSD
                sasTab[25] = ZwrocKolorID(map.map[d][b][e][1], map.map[d][b][e][2], map.map[d][b][e][3]);//GPD
            }
        }


        int E=0;
        int kolor;

        if(z==1){
            for (int x = 0; x < 8; x++) {
                kolor = ZwrocKolorID(map.map[i][j][k][1], map.map[i][j][k][2], map.map[i][j][k][3]);
                if (sasTab[x] != kolor) {
                    E++;
                }
            }
        }else if(z==2){
            for (int x = 0; x < 17; x++) {
                kolor = ZwrocKolorID(map.map[i][j][k][1], map.map[i][j][k][2], map.map[i][j][k][3]);
                if (sasTab[x] != kolor) {
                    E++;
                }
            }
        }else{
            for (int x = 0; x < 26; x++) {
                kolor = ZwrocKolorID(map.map[i][j][k][1], map.map[i][j][k][2], map.map[i][j][k][3]);
                if (sasTab[x] != kolor) {
                    E++;
                }
            }
        }


        map_out.map[i][j][k][4] = E;
    }

    void ObliczEVN(int i, int j, int k, Mapa3D map, int[][][] ETab){
        a=j-1;
        b=j+1;
        c=i-1;
        d=i+1;
        e=k-1;
        f=k+1;
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
        if(k==0){
            e = z-1;
        }
        if(k==z-1){
            f=0;
        }
        //**sasTab - zawiera ID koloru sąsiada**//

        sasTab[0] =  ZwrocKolorID(map.map[i][a][k][1],map.map[i][a][k][2],map.map[i][a][k][3]);//GSS
        sasTab[1] =  ZwrocKolorID(map.map[c][j][k][1],map.map[c][j][k][2],map.map[c][j][k][3]);//SLS
        sasTab[2] =  ZwrocKolorID(map.map[d][j][k][1],map.map[d][j][k][2],map.map[d][j][k][3]);//SPS
        sasTab[3] =  ZwrocKolorID(map.map[i][b][k][1],map.map[i][b][k][2],map.map[i][b][k][3]);//GSS
        if(z!=1) {
            sasTab[4] = ZwrocKolorID(map.map[i][j][f][1], map.map[i][j][f][2], map.map[i][j][f][3]);//SSrodekGora
            if(z!=2)
            sasTab[5] = ZwrocKolorID(map.map[i][j][e][1], map.map[i][j][e][2], map.map[i][j][e][3]);//SSD
        }

        int E=0;
        int kolor;

        if(z==1){
            for (int x = 0; x < 4; x++) {
                kolor = ZwrocKolorID(map.map[i][j][k][1], map.map[i][j][k][2], map.map[i][j][k][3]);
                if (sasTab[x] != kolor) {
                    E++;
                }
            }
        }else if(z==2){
            for (int x = 0; x < 5; x++) {
                kolor = ZwrocKolorID(map.map[i][j][k][1], map.map[i][j][k][2], map.map[i][j][k][3]);
                if (sasTab[x] != kolor) {
                    E++;
                }
            }
        }else{
            for (int x = 0; x < 6; x++) {
                kolor = ZwrocKolorID(map.map[i][j][k][1], map.map[i][j][k][2], map.map[i][j][k][3]);
                if (sasTab[x] != kolor) {
                    E++;
                }
            }
        }
        map_out.map[i][j][k][4] = E;
    }

    void wylosujE(int i, int j, int k, Mapa3D map, int[][][] ETab){
        a=j-1;
        b=j+1;
        c=i-1;
        d=i+1;
        e=k-1;
        f=k+1;
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
        if(k==0){
            e = z-1;
        }
        if(k==z-1){
            f=0;
        }

        //**sasTab - zawiera ID koloru sąsiada**//
        sasTab[0] =  ZwrocKolorID(map.map[c][a][k][1],map.map[c][a][k][2],map.map[c][a][k][3]);//GLS
        sasTab[1] =  ZwrocKolorID(map.map[i][a][k][1],map.map[i][a][k][2],map.map[i][a][k][3]);//GSS
        sasTab[2] =  ZwrocKolorID(map.map[d][a][k][1],map.map[d][a][k][2],map.map[d][a][k][3]);//GPS
        sasTab[3] =  ZwrocKolorID(map.map[c][j][k][1],map.map[c][j][k][2],map.map[c][j][k][3]);//SLS
        sasTab[4] =  ZwrocKolorID(map.map[d][j][k][1],map.map[d][j][k][2],map.map[d][j][k][3]);//SPS
        sasTab[5] =  ZwrocKolorID(map.map[c][b][k][1],map.map[c][b][k][2],map.map[c][b][k][3]);//GLS
        sasTab[6] =  ZwrocKolorID(map.map[i][b][k][1],map.map[i][b][k][2],map.map[i][b][k][3]);//GSS
        sasTab[7] =  ZwrocKolorID(map.map[d][b][k][1],map.map[d][b][k][2],map.map[d][b][k][3]);//GPS
        if(z!=1) {
            sasTab[8] = ZwrocKolorID(map.map[c][a][f][1], map.map[c][a][f][2], map.map[c][a][f][3]);//GoraLewaGora
            sasTab[9] = ZwrocKolorID(map.map[i][a][f][1], map.map[i][a][f][2], map.map[i][a][f][3]);//GoraSrodekGora
            sasTab[10] = ZwrocKolorID(map.map[d][a][f][1], map.map[d][a][f][2], map.map[d][a][f][3]);//GoraPrawaGora
            sasTab[11] = ZwrocKolorID(map.map[c][b][f][1], map.map[c][b][f][2], map.map[c][b][f][3]);//GoraLewaGora
            sasTab[12] = ZwrocKolorID(map.map[i][b][f][1], map.map[i][b][f][2], map.map[i][b][f][3]);//GoraSrodekGora
            sasTab[13] = ZwrocKolorID(map.map[d][b][f][1], map.map[d][b][f][2], map.map[d][b][f][3]);//GoraPrawaGora
            sasTab[14] = ZwrocKolorID(map.map[c][j][f][1], map.map[c][j][f][2], map.map[c][j][f][3]);//SLewaGora
            sasTab[15] = ZwrocKolorID(map.map[i][j][f][1], map.map[i][j][f][2], map.map[i][j][f][3]);//SSrodekGora
            sasTab[16] = ZwrocKolorID(map.map[d][j][f][1], map.map[d][j][f][2], map.map[d][j][f][3]);//SrodekPrawaGora

            if(z!=2) {
                sasTab[17] = ZwrocKolorID(map.map[c][a][e][1], map.map[c][a][e][2], map.map[c][a][e][3]);//GLD
                sasTab[18] = ZwrocKolorID(map.map[i][a][e][1], map.map[i][a][e][2], map.map[i][a][e][3]);//GSD
                sasTab[19] = ZwrocKolorID(map.map[d][a][e][1], map.map[d][a][e][2], map.map[d][a][e][3]);//GPD

                sasTab[20] = ZwrocKolorID(map.map[c][j][e][1], map.map[c][j][e][2], map.map[c][j][e][3]);//SLD
                sasTab[21] = ZwrocKolorID(map.map[i][j][e][1], map.map[i][j][e][2], map.map[i][j][e][3]);//SSD
                sasTab[22] = ZwrocKolorID(map.map[d][j][e][1], map.map[d][j][e][2], map.map[d][j][e][3]);//SPD

                sasTab[23] = ZwrocKolorID(map.map[c][b][e][1], map.map[c][b][e][2], map.map[c][b][e][3]);//GLD
                sasTab[24] = ZwrocKolorID(map.map[i][b][e][1], map.map[i][b][e][2], map.map[i][b][e][3]);//GSD
                sasTab[25] = ZwrocKolorID(map.map[d][b][e][1], map.map[d][b][e][2], map.map[d][b][e][3]);//GPD
            }
        }


        int r;

        if(z==1){
            r = rand.nextInt(8);
        }else if(z==2){
            r = rand.nextInt(17);
        }else{
            r = rand.nextInt(26);
        }

        //System.out.println("Z mapy: " + map_out.map[i][j][1] + " " + map_out.map[i][j][2] + " " + map_out.map[i][j][3]);
        //System.out.println("Z sasTab: " + colorTab[sasTab[r]][1] + " " + colorTab[sasTab[r]][2] + " " + colorTab[sasTab[r]][3]);
        Rgb = map_out.map[i][j][k][1];
        rGb = map_out.map[i][j][k][2];
        rgB = map_out.map[i][j][k][3]; // Przechowują kolor w przypadku braku zmiany energii
        map_out.map[i][j][k][1] = colorTab[sasTab[r]][0];
        map_out.map[i][j][k][2] = colorTab[sasTab[r]][1];
        map_out.map[i][j][k][3] = colorTab[sasTab[r]][2];
    }

    void wylosujEVN(int i, int j, int k, Mapa3D map, int[][][] ETab){
        a=j-1;
        b=j+1;
        c=i-1;
        d=i+1;
        e=k-1;
        f=k+1;
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
        if(k==0){
            e = z-1;
        }
        if(k==z-1){
            f=0;
        }

        //**sasTab - zawiera ID koloru sąsiada**//
        sasTab[0] =  ZwrocKolorID(map.map[i][a][k][1],map.map[i][a][k][2],map.map[i][a][k][3]);//GSS
        sasTab[1] =  ZwrocKolorID(map.map[c][j][k][1],map.map[c][j][k][2],map.map[c][j][k][3]);//SLS
        sasTab[2] =  ZwrocKolorID(map.map[d][j][k][1],map.map[d][j][k][2],map.map[d][j][k][3]);//SPS
        sasTab[3] =  ZwrocKolorID(map.map[i][b][k][1],map.map[i][b][k][2],map.map[i][b][k][3]);//GSS
        if(z!=1) {
            sasTab[4] = ZwrocKolorID(map.map[i][j][f][1], map.map[i][j][f][2], map.map[i][j][f][3]);//SSrodekGora
            if(z!=2)
                sasTab[5] = ZwrocKolorID(map.map[i][j][e][1], map.map[i][j][e][2], map.map[i][j][e][3]);//SSD
        }


        int r;

        if(z==1){
            r = rand.nextInt(4);
        }else if(z==2){
            r = rand.nextInt(5);
        }else{
            r = rand.nextInt(6);
        }

        //System.out.println("Z mapy: " + map_out.map[i][j][1] + " " + map_out.map[i][j][2] + " " + map_out.map[i][j][3]);
        //System.out.println("Z sasTab: " + colorTab[sasTab[r]][1] + " " + colorTab[sasTab[r]][2] + " " + colorTab[sasTab[r]][3]);
        Rgb = map_out.map[i][j][k][1];
        rGb = map_out.map[i][j][k][2];
        rgB = map_out.map[i][j][k][3]; // Przechowują kolor w przypadku braku zmiany energii
        map_out.map[i][j][k][1] = colorTab[sasTab[r]][0];
        map_out.map[i][j][k][2] = colorTab[sasTab[r]][1];
        map_out.map[i][j][k][3] = colorTab[sasTab[r]][2];
    }

    void obliczNoweE(int i, int j, int k, Mapa3D map, int[][][] ETab){
        a=j-1;
        b=j+1;
        c=i-1;
        d=i+1;
        e=k-1;
        f=k+1;
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
        if(k==0){
            e = z-1;
        }
        if(k==z-1){
            f=0;
        }
        ///**sasTab - zawiera ID koloru sąsiada**//
        sasTab[0] =  ZwrocKolorID(map.map[c][a][k][1],map.map[c][a][k][2],map.map[c][a][k][3]);//GLS
        sasTab[1] =  ZwrocKolorID(map.map[i][a][k][1],map.map[i][a][k][2],map.map[i][a][k][3]);//GSS
        sasTab[2] =  ZwrocKolorID(map.map[d][a][k][1],map.map[d][a][k][2],map.map[d][a][k][3]);//GPS
        sasTab[3] =  ZwrocKolorID(map.map[c][j][k][1],map.map[c][j][k][2],map.map[c][j][k][3]);//SLS
        sasTab[4] =  ZwrocKolorID(map.map[d][j][k][1],map.map[d][j][k][2],map.map[d][j][k][3]);//SPS
        sasTab[5] =  ZwrocKolorID(map.map[c][b][k][1],map.map[c][b][k][2],map.map[c][b][k][3]);//GLS
        sasTab[6] =  ZwrocKolorID(map.map[i][b][k][1],map.map[i][b][k][2],map.map[i][b][k][3]);//GSS
        sasTab[7] =  ZwrocKolorID(map.map[d][b][k][1],map.map[d][b][k][2],map.map[d][b][k][3]);//GPS
        if(z!=1) {
            sasTab[8] = ZwrocKolorID(map.map[c][a][f][1], map.map[c][a][f][2], map.map[c][a][f][3]);//GoraLewaGora
            sasTab[9] = ZwrocKolorID(map.map[i][a][f][1], map.map[i][a][f][2], map.map[i][a][f][3]);//GoraSrodekGora
            sasTab[10] = ZwrocKolorID(map.map[d][a][f][1], map.map[d][a][f][2], map.map[d][a][f][3]);//GoraPrawaGora
            sasTab[11] = ZwrocKolorID(map.map[c][b][f][1], map.map[c][b][f][2], map.map[c][b][f][3]);//GoraLewaGora
            sasTab[12] = ZwrocKolorID(map.map[i][b][f][1], map.map[i][b][f][2], map.map[i][b][f][3]);//GoraSrodekGora
            sasTab[13] = ZwrocKolorID(map.map[d][b][f][1], map.map[d][b][f][2], map.map[d][b][f][3]);//GoraPrawaGora
            sasTab[14] = ZwrocKolorID(map.map[c][j][f][1], map.map[c][j][f][2], map.map[c][j][f][3]);//SLewaGora
            sasTab[15] = ZwrocKolorID(map.map[i][j][f][1], map.map[i][j][f][2], map.map[i][j][f][3]);//SSrodekGora
            sasTab[16] = ZwrocKolorID(map.map[d][j][f][1], map.map[d][j][f][2], map.map[d][j][f][3]);//SrodekPrawaGora

            if(z!=2) {
                sasTab[17] = ZwrocKolorID(map.map[c][a][e][1], map.map[c][a][e][2], map.map[c][a][e][3]);//GLD
                sasTab[18] = ZwrocKolorID(map.map[i][a][e][1], map.map[i][a][e][2], map.map[i][a][e][3]);//GSD
                sasTab[19] = ZwrocKolorID(map.map[d][a][e][1], map.map[d][a][e][2], map.map[d][a][e][3]);//GPD

                sasTab[20] = ZwrocKolorID(map.map[c][j][e][1], map.map[c][j][e][2], map.map[c][j][e][3]);//SLD
                sasTab[21] = ZwrocKolorID(map.map[i][j][e][1], map.map[i][j][e][2], map.map[i][j][e][3]);//SSD
                sasTab[22] = ZwrocKolorID(map.map[d][j][e][1], map.map[d][j][e][2], map.map[d][j][e][3]);//SPD

                sasTab[23] = ZwrocKolorID(map.map[c][b][e][1], map.map[c][b][e][2], map.map[c][b][e][3]);//GLD
                sasTab[24] = ZwrocKolorID(map.map[i][b][e][1], map.map[i][b][e][2], map.map[i][b][e][3]);//GSD
                sasTab[25] = ZwrocKolorID(map.map[d][b][e][1], map.map[d][b][e][2], map.map[d][b][e][3]);//GPD
            }
        }
        int E=0;

        if(z==1){
            for (int x = 0; x < 8; x++) {
                if (sasTab[x] != ZwrocKolorID(map_out.map[i][j][k][1], map_out.map[i][j][k][2], map_out.map[i][j][k][3])) {
                    E++;
                }

            }
        }else if(z==2){
            for (int x = 0; x < 17; x++) {
                if (sasTab[x] != ZwrocKolorID(map_out.map[i][j][k][1], map_out.map[i][j][k][2], map_out.map[i][j][k][3])) {
                    E++;
                }

            }
        }else{
            for (int x = 0; x < 26; x++) {
                if (sasTab[x] != ZwrocKolorID(map_out.map[i][j][k][1], map_out.map[i][j][k][2], map_out.map[i][j][k][3])) {
                    E++;
                }

            }
        }

        ETab[i][j][k] = E;
    }

    void obliczNoweEVN(int i, int j, int k, Mapa3D map, int[][][] ETab){
        a=j-1;
        b=j+1;
        c=i-1;
        d=i+1;
        e=k-1;
        f=k+1;
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
        if(k==0){
            e = z-1;
        }
        if(k==z-1){
            f=0;
        }
        ///**sasTab - zawiera ID koloru sąsiada**//
        sasTab[0] =  ZwrocKolorID(map.map[i][a][k][1],map.map[i][a][k][2],map.map[i][a][k][3]);//GSS
        sasTab[1] =  ZwrocKolorID(map.map[c][j][k][1],map.map[c][j][k][2],map.map[c][j][k][3]);//SLS
        sasTab[2] =  ZwrocKolorID(map.map[d][j][k][1],map.map[d][j][k][2],map.map[d][j][k][3]);//SPS
        sasTab[3] =  ZwrocKolorID(map.map[i][b][k][1],map.map[i][b][k][2],map.map[i][b][k][3]);//GSS
        if(z!=1) {
            sasTab[4] = ZwrocKolorID(map.map[i][j][f][1], map.map[i][j][f][2], map.map[i][j][f][3]);//SSrodekGora
            if(z!=2)
                sasTab[5] = ZwrocKolorID(map.map[i][j][e][1], map.map[i][j][e][2], map.map[i][j][e][3]);//SSD
        }
        int E=0;

        if(z==1){
            for (int x = 0; x < 4; x++) {
                if (sasTab[x] != ZwrocKolorID(map_out.map[i][j][k][1], map_out.map[i][j][k][2], map_out.map[i][j][k][3])) {
                    E++;
                }

            }
        }else if(z==2){
            for (int x = 0; x < 5; x++) {
                if (sasTab[x] != ZwrocKolorID(map_out.map[i][j][k][1], map_out.map[i][j][k][2], map_out.map[i][j][k][3])) {
                    E++;
                }

            }
        }else{
            for (int x = 0; x < 6; x++) {
                if (sasTab[x] != ZwrocKolorID(map_out.map[i][j][k][1], map_out.map[i][j][k][2], map_out.map[i][j][k][3])) {
                    E++;
                }

            }
        }

        ETab[i][j][k] = E;
    }

    int ZwrocKolorID(int r, int g, int b){
        for(int i=0;i< this.lZarodkow;i++){
            if(colorTab[i][0]==r && colorTab[i][1]==g && colorTab[i][2]==b){
                return i;
            }
        }
        return 0;
    }

    void exportLayers(Mapa3D map, int mcCount) throws IOException {
        for(int i=0;i<z;i++){
            exportLayer(map, mcCount, i);
        }
    }

    void exportLayer(Mapa3D map, int mcCount, int k) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = bufferedImage.createGraphics();


        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                g2d.setColor(new Color(map.map[i][j][k][1], map.map[i][j][k][2], map.map[i][j][k][3]));
                g2d.fillRect(i, j, 1, 1);
            }
        }
        g2d.dispose();

        File file = new File("MC step"+mcCount+"Layer"+k+" .bmp");
        ImageIO.write(bufferedImage, "bmp", file);
    }
}
