package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MC {

    Mapa map_out;
    int w,h;
    int x,y;
    Random rand;
    int [][] colorTab; //Do zbierania wszystkich kolorów z mapy
    int a,b,c,d;
    int[] sasTab; //Do zbierania ID ziaren z sąsiedztwa
    int max; //Liczba zarodków
    int[][] ETab; //Do zliczania energii w danym ziarnie
    int Rgb, rGb, rgB;

    MC(Mapa map){
        this.w = map.w; this.h = map.h;
        map_out = new Mapa(this.w,this.h);
        //System.out.println(map.lZarodkow);
        ETab = new int[w][h];
        rand = new Random();

        sasTab = new int[8];


    }

    void wypelnij(Mapa map, int lZarodkow){

        int[][] chkTab = new int[w][h];
        int zarodekID;
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                chkTab[i][j]=0;
            }
        }
        for(int i=0;i<w*h;i++){
            x = rand.nextInt(w);
            y = rand.nextInt(h);
            if(chkTab[x][y]==0){
                zarodekID = rand.nextInt(lZarodkow);
                chkTab[x][y]=1;
                map.map[x][y][1] =zarodekID;
                map.map[x][y][2] =zarodekID;
                map.map[x][y][3] =zarodekID;
                //System.out.println(zarodekID);
            }else{
                i--;
            }
        }
    }

    void rozrostMC(Mapa map) throws IOException {
        fillColorTable(map); // Przypisanie ID dla każdego koloru
        int[][] checkTab = new int[w][h]; //Tablica do losowania ziaren 1-gdy wylosowane,0 gdy nie
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                checkTab[i][j]=0;
            }
        }
        //**Losowanie**//
        int x,y,EDiff;
        double p,rp;
        for(int i=0;i<w*h;i++){
            x = rand.nextInt(w);
            y = rand.nextInt(h);
            if(checkTab[x][y]==0){
                //System.out.println(x + " "+y);
                checkTab[x][y]=1;
                /**Obliczenie początkowego E**/
                ObliczE(x,y,map,ETab);

                wylosujE(x,y,map,ETab);

                /**Obliczenie nowej energii**/
                obliczNoweE(x,y,map,ETab);
//                System.out.println("Stara energia = "+map_out.map[x][y][4] + " Nowa energia: "+ ETab[x][y]);
                EDiff = map_out.map[x][y][4]-ETab[x][y];
//                System.out.println("Różnica: "+EDiff);
                /**Jeżeli nowa energia jest większa, pozostawiamy początkową energię**/

                if(EDiff<=0){
                    //System.out.println("x: "+x+ " y:  " + y + " EDiff:  "+EDiff + " R: " + map_out.map[x][y][1]+" G: " +map_out.map[x][y][2] +" B: "+ map_out.map[x][y][3]);
                    //System.out.println("Rgb: " +Rgb + " rGb: " + rGb + " rgB: " + rgB);

                    map_out.map[x][y][1] = Rgb;
                    map_out.map[x][y][2] = rGb;
                    map_out.map[x][y][3] = rgB;
                   //System.out.println("Tutaj"+ " R: " + map_out.map[x][y][1]+" G: " +map_out.map[x][y][2] +" B: "+ map_out.map[x][y][3]);
                    //System.out.println("Tutaj"+ " R: " + map_out.map[x][y][1]+" G: " +map_out.map[x][y][2] +" B: "+ map_out.map[x][y][3]);
                    //ETab[x][y]= map_out.map[x][y][4];
                }
            }else{
                i--;
            }
            //exportIMG(map, x, y);
        }

        /**Kopiowanie danych do map_out**/
        for(int j=0; j< h;j++){
            for(int i=0;i<w;i++){
                map_out.map[i][j][4] = ETab[i][j];
            }
        }

        /**Kopiowanie danych do map**/
        for(int i=0; i< w; i++){
            for(int j=0;j<h;j++){
                map.map[i][j] = map_out.map[i][j];
            }
        }
    }

    void copyData(Mapa map){
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                map_out.map[i][j] = map.map[i][j];
            }
        }
    }

    void fillColorTable(Mapa map) {
        int count = 0;
        this.max = map.lZarodkow;
        colorTab = new int[max][4];//0-ID 1-R 2-G 3-B

        //colorTab = new int[][]
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (compareColor(map.map[i][j][1], map.map[i][j][2], map.map[i][j][3], map) == 0) {
                    //System.out.println(count);
                    colorTab[count][0] = count;
                    colorTab[count][1] = map.map[i][j][1];
                    colorTab[count][2] = map.map[i][j][2];
                    colorTab[count][3] = map.map[i][j][3];
                    count++;
                }
            }

        }
    }

    int compareColor(int r, int g, int b, Mapa map){
        for(int i=0;i<max;i++){
            if(colorTab[i][1]==r && colorTab[i][2]==g && colorTab[i][3]==b){
                return 1;
            }
        }

        return 0;
    }

    //Dla warunków periodycznych
    void ObliczE(int i, int j, Mapa map, int[][] ETab){
        a=j-1;
        b=j+1;
        c=i-1;
        d=i+1;
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
        //**sasTab - zawiera ID koloru sąsiada**//
        sasTab[0] =  ZwrocKolorID(map.map[c][b][1], map.map[c][b][2], map.map[c][b][3]);//LG
        sasTab[1] =  ZwrocKolorID(map.map[i][b][1],map.map[i][b][2],map.map[i][b][3]);//SG
        sasTab[2] =  ZwrocKolorID(map.map[d][b][1],map.map[d][b][2],map.map[d][b][3]);//PG
        sasTab[3] =  ZwrocKolorID(map.map[c][j][1],map.map[c][j][2],map.map[c][j][3]);//LS
        sasTab[4] =  ZwrocKolorID(map.map[d][j][1],map.map[d][j][2],map.map[d][j][3]);//PS
        sasTab[5] =  ZwrocKolorID(map.map[c][a][1],map.map[c][a][2],map.map[c][a][3]);//LD
        sasTab[6] =  ZwrocKolorID(map.map[i][a][1],map.map[i][a][2],map.map[i][a][3]);//SD
        sasTab[7] =  ZwrocKolorID(map.map[d][a][1],map.map[d][a][2],map.map[d][a][3]);//PD

        int E=0;
        int kolor;
        for(int x=0;x<8;x++) {
            kolor = ZwrocKolorID(map.map[i][j][1],map.map[i][j][2],map.map[i][j][3]);
            if (sasTab[x] != kolor) {
                E++;
            }
        }

        map_out.map[i][j][4] = E;
    }

    int ZwrocKolorID(int r, int g, int b){
        for(int i=0;i<max;i++){
            if(colorTab[i][1]==r && colorTab[i][2]==g && colorTab[i][3]==b){
                return colorTab[i][0];
            }
        }
        return Integer.parseInt(null);
    }

    void wylosujE(int i, int j, Mapa map, int[][] ETab){
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

        sasTab[0] =  ZwrocKolorID(map.map[c][b][1],map.map[c][b][2],map.map[c][b][3]);//LG
        sasTab[1] =  ZwrocKolorID(map.map[i][b][1],map.map[i][b][2],map.map[i][b][3]);//SG
        sasTab[2] =  ZwrocKolorID(map.map[d][b][1],map.map[d][b][2],map.map[d][b][3]);//PG
        sasTab[3] =  ZwrocKolorID(map.map[c][j][1],map.map[c][j][2],map.map[c][j][3]);//LS
        sasTab[4] =  ZwrocKolorID(map.map[d][j][1],map.map[d][j][2],map.map[d][j][3]);//PS
        sasTab[5] =  ZwrocKolorID(map.map[c][a][1],map.map[c][a][2],map.map[c][a][3]);//LD
        sasTab[6] =  ZwrocKolorID(map.map[i][a][1],map.map[i][a][2],map.map[i][a][3]);//SD
        sasTab[7] =  ZwrocKolorID(map.map[d][a][1],map.map[d][a][2],map.map[d][a][3]);//PD

        int r = rand.nextInt(8);

        //System.out.println("Z mapy: " + map_out.map[i][j][1] + " " + map_out.map[i][j][2] + " " + map_out.map[i][j][3]);
        //System.out.println("Z sasTab: " + colorTab[sasTab[r]][1] + " " + colorTab[sasTab[r]][2] + " " + colorTab[sasTab[r]][3]);
        Rgb = map_out.map[i][j][1];
        rGb = map_out.map[i][j][2];
        rgB = map_out.map[i][j][3]; // Przechowują kolor w przypadku braku zmiany energii
        map_out.map[i][j][1] = colorTab[sasTab[r]][1];
        map_out.map[i][j][2] = colorTab[sasTab[r]][2];
        map_out.map[i][j][3] = colorTab[sasTab[r]][3];
    }

    void obliczNoweE(int i, int j, Mapa map, int[][] ETab){
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
        //**sasTab - zawiera ID koloru sąsiada**//
        sasTab[0] =  ZwrocKolorID(map.map[c][b][1], map.map[c][b][2], map.map[c][b][3]);//LG
        sasTab[1] =  ZwrocKolorID(map.map[i][b][1],map.map[i][b][2],map.map[i][b][3]);//SG
        sasTab[2] =  ZwrocKolorID(map.map[d][b][1],map.map[d][b][2],map.map[d][b][3]);//PG
        sasTab[3] =  ZwrocKolorID(map.map[c][j][1],map.map[c][j][2],map.map[c][j][3]);//LS
        sasTab[4] =  ZwrocKolorID(map.map[d][j][1],map.map[d][j][2],map.map[d][j][3]);//PS
        sasTab[5] =  ZwrocKolorID(map.map[c][a][1],map.map[c][a][2],map.map[c][a][3]);//LD
        sasTab[6] =  ZwrocKolorID(map.map[i][a][1],map.map[i][a][2],map.map[i][a][3]);//SD
        sasTab[7] =  ZwrocKolorID(map.map[d][a][1],map.map[d][a][2],map.map[d][a][3]);//PD

        int E=0;
        for(int x=0;x<8;x++) {
            if (sasTab[x] != ZwrocKolorID(map_out.map[i][j][1],map_out.map[i][j][2],map_out.map[i][j][3])) {
                E++;
            }

        }

        ETab[i][j] = E;
    }

    void exportIMG(Mapa map,int x, int y) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_BGR );
        Graphics2D g2d = bufferedImage.createGraphics();

        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                g2d.setColor(new Color(map.map[i][j][1],map.map[i][j][2],map.map[i][j][3]));

                g2d.fillRect(i,j,1,1);
            }
        }

        g2d.dispose();

        File file = new File("MC "+x+ " "+y+".bmp");
        ImageIO.write(bufferedImage,"bmp",file);
    }
}
