package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Frame extends JFrame implements ActionListener {

    JPanel varPanel = new JPanel();
    JLabel lWymx = new JLabel("Wymiar x: ");
    JLabel lWymy = new JLabel("Wymiar y: ");
    JLabel lWymz = new JLabel("Wymiar z: ");
    JLabel lWyp = new JLabel("Warunki brzgowe: ");
    JLabel lLbZar = new JLabel("Max liczba zarodków: ");
    JLabel lSas = new JLabel("Sąsiedztwo: ");
    JLabel literMC = new JLabel("Liczba iteracji MC:");
    JButton bGen = new JButton("Generuj");
    JButton bDodaj = new JButton("Dodaj");

    JTextField tWymx = new JTextField();
    JTextField tWymy = new JTextField();
    JTextField tWymz = new JTextField();
    JComboBox cbWyp = new JComboBox();
    JTextField tLbZar = new JTextField();
    JComboBox cbSas = new JComboBox<>();
    JTextField tIterMC = new JTextField();



    int count=0;

    public Frame(){
        setSize(400,400);
        setTitle("Podaj zmienne");
        add(varPanel);
        setVisible(true);
        varPanel.setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        varPanel.add(lWymx);
        varPanel.add(lWymy);
        varPanel.add(lWyp);
        varPanel.add(lLbZar);
        varPanel.add(lSas);
        varPanel.add(bGen);
        varPanel.add(tWymx);
        varPanel.add(tWymy);
        varPanel.add(cbWyp);
        varPanel.add(tLbZar);
        varPanel.add(cbSas);
        varPanel.add(lWymz);
        varPanel.add(literMC);
        varPanel.add(tWymz);
        varPanel.add(tIterMC);
        varPanel.add(bDodaj);


        lWymx.setBounds(10,10,200,30);
        lWymy.setBounds(10,30,200,30);
        lWymz.setBounds(10,50,200,30);
        lWyp.setBounds(10,70,200,30);
        lLbZar.setBounds(10,90,200,30);
        lSas.setBounds(10,110,200,30);
        literMC.setBounds(10,130,200,30);
        bGen.setBounds(250,240,100,30);
        bDodaj.setBounds(50,240,100,30);

        tWymx.setBounds(160,15,200,20);
        tWymy.setBounds(160,35,200,20);
        tWymz.setBounds(160,55,200,20);
        cbWyp.setBounds(160,75,200,20);
        tLbZar.setBounds(160,95,200,20);
        cbSas.setBounds(160,115,200,20);
        tIterMC.setBounds(160,135,200,20);

        cbWyp.addItem("Pochłaniające");//0
        cbWyp.addItem("Periodyczne");//1

        cbSas.addItem("Moor");//0
        cbSas.addItem("VonNeumann");//1


        bGen.addActionListener(this);
        bDodaj.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();

        if(z==bDodaj){
            if(tWymx.getText().length()!=0 && tWymy.getText().length()!=0 && tLbZar.getText().length()!=0){
                int[] config = new int[7];
                config[0] = Integer.parseInt(tWymx.getText());//int w
                config[1] = Integer.parseInt(tWymy.getText());//int h
                config[2] = Integer.parseInt(tWymz.getText());//int zz
                config[3] = Integer.parseInt(tLbZar.getText());//int max
                config[4] = cbSas.getSelectedIndex();//int cbSasVal
                config[5] = cbWyp.getSelectedIndex();//int cbBCVal
                config[6] = Integer.parseInt(tIterMC.getText());//int MC

                BufferedWriter writer = null;

                try {
                    // Tworzenie BufferedWriter do zapisu danych do pliku txt
                    writer = new BufferedWriter(new FileWriter("D:/Projekty_Java/MetodyDyskretneWZlozonychSystemachObliczeniowychProjekt/src/com/company/config"+count+".txt"));

                    for(int i=0;i<7;i++){
                        writer.write(Integer.toString(config[i]));
                        writer.write("\n");
                    }
                    count++;
                    System.out.println("Dane zostały pomyślnie wyeksportowane do config"+count+".txt.");

                } catch (IOException f) {
                    f.printStackTrace();
                } finally {
                    try {
                        // Zamykanie BufferedWriter
                        if (writer != null) writer.close();
                    } catch (IOException f) {
                        f.printStackTrace();
                    }
                }


            }else{
                System.out.println("Podaj poprawne dane!!!");
            }
        }

        if(z==bGen){
            for(int i=0;i< count;i++){
                try {
                    Panel panel = new Panel(i);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
