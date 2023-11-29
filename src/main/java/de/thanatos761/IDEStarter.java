package de.thanatos761;

import de.thanatos761.frameClases.BingoFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class IDEStarter {
    public static void main(String[] args) {
        String idePath = "src/main/java/de/thanatos761/assets/";
        String circle = "Circle.png";
        String ico = "ShittyIcon.png";
        String bingo = "Bingo.png";

        Image c = new ImageIcon(idePath+circle).getImage();
        Image icon = new ImageIcon(idePath+ico).getImage();
        Image b = new ImageIcon(idePath+bingo).getImage();

        BingoFrame bf = new BingoFrame("IDE Test", 500, 500,icon,c,b);
    }
}