package de.thanatos761.frameClases;

import de.thanatos761.coreFunctions.Validator3;
import de.thanatos761.misc.DebugLogger;
import de.thanatos761.misc.Logger;
import de.thanatos761.objects.Ole;
import de.thanatos761.coreFunctions.FBFValidator;
import de.thanatos761.coreFunctions.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class BAC implements ActionListener {
    private JFrame bf;
    private JFrame alertFrame;
    private JPanel pp;
    private ArrayList<Integer> buttonList = new ArrayList<>();
    private DebugLogger cl;
    private final Logger pog = new Logger();
    private final boolean isDebug = false;
    private boolean isIDE = false;

    public BAC(JFrame bf, JPanel pan) {
        this.bf = bf;
        this.pp = pan;
        cl = new DebugLogger(isDebug, "[DEBUG]",DebugLogger.DO_MEDIUM);
    }

    public BAC(JFrame bingoFrame, JPanel jp, JFrame alert) {
        this.bf = bingoFrame;
        this.pp = jp;
        this.alertFrame = alert;
    }

    public void setIsIDE(boolean x) {
        this.isIDE = x;
    }

    private final String ideAssets = "src/main/java/de/thanatos761/assets/";
    private final String normalAssets = "assets/";
    private String assets;
    private File iconFile;
    private File bingoFile;

    @Override
    public void actionPerformed(ActionEvent e) {
        assets = isIDE ? ideAssets : normalAssets;
        iconFile = new File(assets + "Circle.png");
        bingoFile = new File(assets + "Bingo.png");
        String icoF = iconFile.getAbsolutePath();
        if(e.getSource().getClass() == JButton.class) { //if the source is a JButton it will fire up the response linked to the JButton
            JButton object = (JButton) e.getSource();
            if(object.getName().equalsIgnoreCase("restart")) {
                //bf.dispose();
                //bf = new BingoFrame(bf.getTitle(),bf.getWidth(), bf.getHeight());
                restartBingo(bf,pp);
            } else {
                int width = object.getWidth();
                int height = object.getHeight();

                //get image
                //TODO: add fail check
                ImageIcon ico = new ImageIcon(icoF);
                Image im = ico.getImage();
                Image im2 = im.getScaledInstance(width,height,2);
                ico = new ImageIcon(im2);

                //put icon instead of text + disable button
                object.setText("");
                object.setIcon(ico);
                object.setEnabled(false);
                object.setDisabledIcon(ico);

                // add to arraylist
                int currentButton = Integer.parseInt(object.getName());
                cl.log("Current Button: " + currentButton);
                buttonList.add(currentButton);

                //initiate validator
                FBFValidator validator = new FBFValidator(buttonList, isDebug);
                Validator3 validate = new Validator3(buttonList, isDebug, DebugLogger.DO_MEDIUM);
                if(validate.validate()) {
                    pog.log("Bingo");
                    //pog.log(buttonList);
                    displayBingo(bf,pp);
                }
            }
        } if(e.getSource().getClass() == MenuItem.class) {
            MenuItem item = (MenuItem) e.getSource();
            cl.log(item.getLabel());

            if(item.getLabel().equalsIgnoreCase("restart")) { //restart
                restartBingo(bf, pp);
            }if(item.getLabel().equalsIgnoreCase("about")) {
                cl.log("Launching About");
                AboutFrame af = new AboutFrame("About",300,400);
            }
        }
    }

    public void restartBingo(JFrame f, JPanel p) {
        // remove all buttons from the panel
        p.removeAll();
        // remove all buttons from the list
        buttonList.clear();

        // get an array with utf8 conforming ole proverbs
        String[] ole = new Ole().getOleBingoUTF8();
        // generate a 16 index list with the integers 0 to 16
        ArrayList<Integer> rnd = new RandomGenerator(16, 0, 15).getIntList();
        JButton jb;
        for(int i = 0; i < 16; i ++) {
            jb = new JButton(ole[rnd.get(i)]);
            jb.setName(String.valueOf(Math.addExact(i,1)));
            jb.addActionListener(this);
            p.add(jb);
        }
        f.validate();
    }

    public void displayBingo(JFrame f, JPanel p) {
        p.removeAll();
        buttonList.clear();
        JLabel bingoImg = new JLabel();
        JButton restartB = new JButton("Restart");
        restartB.setName("restart");
        restartB.addActionListener(this::actionPerformed);
        ImageIcon b = new ImageIcon(bingoFile.getAbsolutePath());
        bingoImg.setIcon(b);

        p.add(bingoImg);
        p.add(restartB);
        p.repaint();
        f.repaint();

        f.validate();

    }
}
