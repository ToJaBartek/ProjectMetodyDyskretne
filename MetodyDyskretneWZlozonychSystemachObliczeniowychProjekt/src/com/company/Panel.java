package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Panel {

    Panel(int countFile) throws IOException {

        System.out.println("/***********************START***************************/");
        NewTimer timer = new NewTimer(countFile);
        Instant start, end, start1, end1;
        Duration elapsedTime;


        BufferedReader in = new BufferedReader(new FileReader("D:\\Projekty_Java\\MetodyDyskretneWZlozonychSystemachObliczeniowychProjekt\\src\\com\\company\\config"+countFile+".txt"));

        int[] config = new int[7]; // 0 - w 1- h 2 - z 3 - lZarodkow 4 - sasiedztwo 5 - BC 6- liczba kroków MC
        String line;
        int line_count=0;
        while((line = in.readLine())!=null){
            config[line_count] = Integer.parseInt(line);
            line_count++;
        }
        in.close();

        int count=0;
        start = Instant.now();
        Mapa3D mapa = new Mapa3D(config[0],config[1],config[2]);
        end = Instant.now();
        elapsedTime = Duration.between(start, end);
        timer.utworzMape = ((int) elapsedTime.toMillis());

        /****Wypełnienie mapy****/
        start = Instant.now();
        mapa.wypelnijMape(config[3]);
        end = Instant.now();
        elapsedTime = Duration.between(start,end);
        timer.wypelnijMape = ((int) elapsedTime.toMillis());
        //mapa.exportIMGShell();
        //mapa.exportIMGLayers();

        /***Naiwny rozrost ziaren****/
        start = Instant.now();
        RozrostCA3D rozrostCA = new RozrostCA3D(mapa);
        if(config[4]==1 && config[5]==1) {
            start1 = Instant.now();
            while (rozrostCA.czyWypelnione(mapa) == 0) {
                //rozrostCA.exportIMGLayers(mapa,count);
                rozrostCA.RozrostCANVN_Per(mapa);
                count++;
            }
            //rozrostCA.exportIMGLayers(mapa, count);
            end1 = Instant.now();
        }else if(config[4]==0 && config[5]==1){
            start1 = Instant.now();
            while (rozrostCA.czyWypelnione(mapa) == 0) {
                //rozrostCA.exportIMGLayers(mapa,count);
                rozrostCA.RozrostCANM_Per(mapa);
                count++;
            }
            //rozrostCA.exportIMGLayers(mapa, count);
            end1 = Instant.now();
        }
        else if(config[4]==1 && config[5]==0){ // Dla z > 1
            start1 = Instant.now();
            while (rozrostCA.czyWypelnione(mapa) == 0) {
                //if (count % 4 == 0) {
                //    rozrostCA.exportIMG(mapa, count);
                //}
                rozrostCA.RozrostCANVN_Poc(mapa);
                count++;
            }
            //rozrostCA.exportIMG(mapa, count);
            end1 = Instant.now();
        }else if(config[4]==0 && config[5]==0){ // Dla z > 1
            start1 = Instant.now();
            while (rozrostCA.czyWypelnione(mapa) == 0) {
                //rozrostCA.exportIMGLayers(mapa,count);
                rozrostCA.RozrostCANM_Poc(mapa);
                count++;
            }
            //rozrostCA.exportIMGLayers(mapa, count);
            end1 = Instant.now();
        }else{
            start1 = Instant.now();
            System.out.println("Error");
            end1 = Instant.now();
        }
        end = Instant.now();
        //elapsedTime = Duration.between(start1, end1);
        timer.rozrostZiarnaBezWarunkow = ((int) elapsedTime.toMillis());
        elapsedTime = Duration.between(start,end);
        timer.rozrostZiarna = ((int) elapsedTime.toMillis());
        //mapa.exportIMGShell();
        //mapa.exportIMGLayers();

        MyFrame frameCA = new MyFrame(mapa, config, "CA");


        /*******************************Monte Carlo******************************/

        int countMC =0;
        Mapa3D mapaMC  = new Mapa3D(config[0], config[1], config[2]);
        start = Instant.now();
        mapaMC.wypelnijMC(config[3]);
        end = Instant.now();
        elapsedTime = Duration.between(start,end);
        timer.wypelnijMapeMC = ((int) elapsedTime.toMillis());
        timer.iteracjaMC = new int[config[6]];
        //mapaMC.exportIMGLayers("MC");
        MC3D mc = new MC3D(mapaMC);
        start = Instant.now();
        while(countMC<config[6]) {
            start1 = Instant.now();
            mc.copyData(mapaMC);
            if(config[4]==0) {
                mc.rozrostMC(mapaMC);
            }else if(config[4]==1){
                mc.rozrostMCVN(mapaMC);
            }
            //mc.exportLayers(mapaMC,countMC);
            end1 = Instant.now();
            elapsedTime = Duration.between(start1,end1);
            timer.iteracjaMC[countMC] = ((int) elapsedTime.toMillis());
            countMC++;
        }

        end = Instant.now();
        elapsedTime = Duration.between(start,end);
        timer.MC = ((int) elapsedTime.toMillis());

        timer.wypisz();
        timer.eksportWynikow();

        MyFrame frameMC = new MyFrame(mapaMC, config,"MC");
    }
}
