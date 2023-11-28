package de.thanatos761.misc;

public class DebugLogger {
    private boolean doPost = false;
    private int loggingDepth = -1;

    private String tag = "";

    public final static int DO_FULL = 0;
    public final static int DO_MEDIUM = 1;
    public final static int DO_LIGHT = 2;
    public final static int DO_SPECIAL = 3;


    public DebugLogger(boolean doPost, String tag, int depth) {
        this.doPost = doPost;
        this.loggingDepth = depth;
        this.tag = tag;
    }

    public void log(Object text, int level) {
        //log("Level: " + level + " is bigger or the same? " + (level>=loggingDepth));
        if(doPost && level >= loggingDepth) {
            System.out.println(tag + " " + text);
        }
    }

    public void log(Object text) {
        if(doPost) {
            System.out.println(tag + " " + text);
        }
    }
}