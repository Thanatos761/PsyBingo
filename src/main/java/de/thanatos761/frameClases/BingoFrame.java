package de.thanatos761.frameClases;

import de.thanatos761.objects.Ole;
import de.thanatos761.coreFunctions.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BingoFrame extends JFrame {
    private GridLayout gl = new GridLayout(4,4);
    private JPanel pp = new JPanel(gl);
    private WCL wcl = new WCL(false);
    private BAC bac = new BAC(this, pp);

    private ArrayList<Integer> rnd = new RandomGenerator(16, 0, 15).getIntList();
    private String[] ole = new Ole().getOleBingoUTF8();

    private JButton jb;

    private MenuBar mb = new MenuBar();
    private Menu settingMenu = new Menu("Settings");
    private MenuItem restart = new MenuItem("Restart");
    private Menu infoMenu = new Menu("Info");
    private MenuItem about = new MenuItem("About");
    private String assets;
    private final String ideAssets = "src/main/java/de/thanatos761/assets/";
    private final String normalAssets = "/";

    public BingoFrame(String title, int width, int height, boolean isIDE) {
        super(title);
        getDirs();
        bac.setIsIDE(isIDE);

        this.add(pp);
        infoMenu.add(about);
        about.addActionListener(bac);

        restart.addActionListener(bac);
        settingMenu.add(restart);

        for(int i = 0; i < 16; i ++) {
            jb = new JButton(ole[rnd.get(i)]);
            jb.setName(String.valueOf(Math.addExact(i,1)));
            jb.addActionListener(bac);
            pp.add(jb);
        }

        mb.add(settingMenu);
        mb.add(infoMenu);
        this.setMenuBar(mb);

        assets = isIDE ? ideAssets + "ShittyIcon.png" : "ShittyIcon.png";
        File fIcon =new File(assets);

        if(!isIDE) {
            this.setIconImage(new ImageIcon(getClass().getResource("assets/ShittyIcon.png")).getImage());
            //TODO: find a way to use images in jar
        } else {
            this.setIconImage(new ImageIcon(fIcon.getAbsolutePath()).getImage());
        }

        this.setSize(width, height);
        this.addWindowListener(wcl);
        this.setVisible(true);
    }

    public void getDirs() {
        String cwd = System.getProperty("user.dir");

        Set<String> list = Stream.of(new File(cwd).listFiles())
                .filter(file -> file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
        Iterator<String> it = list.iterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}