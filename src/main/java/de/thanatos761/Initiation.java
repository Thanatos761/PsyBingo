package de.thanatos761;

import de.thanatos761.frameClases.BingoFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Initiation {
    // This is the init file for the jar
    public static void main(String[] args) {
        URL cUrl = Initiation.class.getClassLoader().getResource("Circle.png");
        URL iUrl = Initiation.class.getClassLoader().getResource("ShittyIcon.png");
        URL bUrl = Initiation.class.getClassLoader().getResource("Bingo.png");

        Image circle = new ImageIcon(cUrl).getImage();
        Image bingo = new ImageIcon(bUrl).getImage();
        Image ico = new ImageIcon(iUrl).getImage();

        BingoFrame bf = new BingoFrame("Ole Bingo A0.1.0", 500, 500, ico, circle, bingo);
    }
}