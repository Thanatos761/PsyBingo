package de.thanatos761;

import de.thanatos761.frameClases.BingoFrame;

public class Initiation {
    public static void main(String[] args) {
        if(args.length == 0) {
            BingoFrame bf = new BingoFrame("Ole Bingo A0.1.0", 500, 500, true);
        } else {
            BingoFrame bf = new BingoFrame("Ole Bingo A0.1.0", 500, 500, false);
        }
    }
}