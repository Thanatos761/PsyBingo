package de.thanatos761.frameClases;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AboutFrame extends JFrame {
    private PopupWCL wcl;
    public AboutFrame(String title, int width, int height) {
        super(title);
        this.setSize(width, height);
        wcl = new PopupWCL(this);

        String header = "<html>" +
                "<h1>About Psy Bingo</h1>" +
                "</html>";
        String body = "<html>" +
                "<p>" +
                "<b>This game was thought off by<br>members of the PSY Clan</b><br><br>" +
                "<B>Developer:</B> Lasse Voigt (Walli/Thanatos761)<br>" +
                "<B>Logic & UI Ideas:</B> Void(emm4.)" +
                "</p>" +
                "</html>";

        JLabel h = new JLabel(header);
        JLabel b = new JLabel(body);

        JPanel pp = new JPanel();

        Border border = pp.getBorder();
        Border margin = new EmptyBorder(10,10,10,10);
        pp.setBorder(new CompoundBorder(border, margin));

        pp.add(h);
        pp.add(b);
        this.add(pp);

        this.addWindowListener(wcl);
        this.setVisible(true);
    }
}
