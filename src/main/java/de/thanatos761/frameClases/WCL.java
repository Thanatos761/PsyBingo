package de.thanatos761.frameClases;

import de.thanatos761.misc.DebugLogger;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WCL extends WindowAdapter {
    private DebugLogger cl;

    public WCL(boolean debug) {
        super();
        cl = new DebugLogger(debug, "[DEBUG]", DebugLogger.DO_FULL);
    }

    public void windowClosing(WindowEvent e) {
        cl.log("Closing Window");;
        System.exit(69);
    }
}