package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NewTimer {

        int utworzMape, wypelnijMape, rozrostZiarna, rozrostZiarnaBezWarunkow, MC, wypelnijMapeMC;
        int[] iteracjaMC;
        int countFile;
        NewTimer(int countFile){
            this.countFile = countFile;
        }

    void wypisz() {
        System.out.println("Wyniki: ");
        System.out.println("utworzMape= " + utworzMape);
        System.out.println("wypelnijMape= " +wypelnijMape);
        System.out.println("rozrostZiarna= " +rozrostZiarna);
        System.out.println("rozrostZiarnaBezWarunkow= " + rozrostZiarnaBezWarunkow);
        System.out.println("wypelnijMapeMC= " + wypelnijMapeMC);
        System.out.println("MC= " + MC);
        for(int i=0;i<iteracjaMC.length;i++){
            System.out.println("MC iteracja " + i + "= " + iteracjaMC[i]);
        }
    }

    void eksportWynikow() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("WynikiCzasu"+countFile+".txt"));

        writer.write("Utworzenie mapy: " + utworzMape);
        writer.newLine();
        writer.write("Wypelnienie mapy: " +wypelnijMape);
        writer.newLine();
        writer.write("Naiwny rozrost ziarna= " +rozrostZiarna);
        writer.newLine();
        writer.write("Naiwny rozrost ziarna (bez uwględnienia funkcji warunkowych)= " + rozrostZiarnaBezWarunkow);
        writer.newLine();
        writer.write("wypelnijMapeMC= " + wypelnijMapeMC);
        writer.newLine();
        writer.write("MC= " + MC);
        writer.newLine();
        for(int i=0;i< iteracjaMC.length;i++){
            writer.write("Iteracja "+i + ": " +  iteracjaMC[i]);
            writer.newLine();
        }
        writer.close();
    }
}
