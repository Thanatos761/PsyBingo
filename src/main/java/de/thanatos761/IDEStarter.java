package de.thanatos761;

import de.thanatos761.frameClases.BingoFrame;

import javax.swing.*;
import java.awt.*;
public class IDEStarter {
    // This is the init file for the IDE, should be obvious, don't you think?
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