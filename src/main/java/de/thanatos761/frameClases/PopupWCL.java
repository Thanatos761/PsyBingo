package de.thanatos761.frameClases;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PopupWCL extends WindowAdapter {
    private Frame f;
    public PopupWCL(Frame frame) {
        this.f = frame;
    }

    public void windowClosing(WindowEvent e) {
        f.dispose();
    }
}
