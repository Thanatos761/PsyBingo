package de.thanatos761.coreFunctions;

import de.thanatos761.misc.DebugLogger;

import java.util.ArrayList;

public class Validator3 {
    private ArrayList<Integer> buttonsPressed = new ArrayList<>();
    private DebugLogger cl;

    private final int HORIZONTAL = 0;
    private final int VERTICAL = 1;
    private final int DIAGONAL_TL = 2;
    private final int DIAGONAL_TR = 3;

    public Validator3(ArrayList<Integer> list, boolean doDebug, int depth) {
        this.buttonsPressed = list;
        this.cl = new DebugLogger(doDebug, "[DEBUG]", depth);
    }

    public Validator3(boolean doDebug, int depth) {
        this.cl = new DebugLogger(doDebug, "[DEBUG]", depth);
    }

    public boolean init(ArrayList<Integer> list) {
        this.buttonsPressed = list;

        return validate();
    }

    public boolean validate() {
        boolean horizontalBingo = false;
        boolean verticalBingo = false;
        boolean diagonalBingoTL = false;
        boolean diagonalBingoTR = false;

        //if more than 11 buttons are pressed = automatic bingo, its impossible to have 12 buttons or more pressed, without producing a bingo in 4x4 Bingo
        if(buttonsPressed.size() > 11) {
            return true;
        }

        //if 3 buttons or less are pressed => automatic false...I hopefully dont have to explain that
        if(buttonsPressed.size() <= 3) {
            return false;
        }

        //now we can start validatin *rubs hands and licks lips* https://imgur.com/a/nWBc7nM

        // we wanna check if there is a horizonta, vertical, diagonal1 or 2 bingo possible
        for(int i = 0; i < buttonsPressed.size(); i++) {
            int currentNumber = buttonsPressed.get(i);

            cl.log("\nRound: " + i + "\n",cl.DO_LIGHT);

            horizontalBingo = isBingo(currentNumber, 0, HORIZONTAL);
            verticalBingo = isBingo(currentNumber, 0, VERTICAL);
            diagonalBingoTL = isBingo(currentNumber, 0, DIAGONAL_TL);
            diagonalBingoTR = isBingo(currentNumber, 0, DIAGONAL_TR);

            if(horizontalBingo || verticalBingo || diagonalBingoTL|| diagonalBingoTR) {
                return horizontalBingo || verticalBingo || diagonalBingoTL|| diagonalBingoTR;
            }
        }

        return horizontalBingo || verticalBingo || diagonalBingoTL|| diagonalBingoTR;
    }

    private boolean isValid(int num) {
        if(num > 16) {
            return false;
        }
        return true;
    }

    // start looking for the next 3 numbers with recursion and a counter
    // there will be the current number passed on (aka the starting point) and the current counter number (aka 1 at start, since it already found a possibility)

    //once the counter reaches 3 it will check if the bingo is valid, if not it will go up only one more(if possible) and then break

    /* I will NOT write a description/comments for every one of the 4 booleans, if you wanna know what does what, use your noggin or read up in "isHBingo"
     * *walks away mumbling why so many people are so big f..n idiots*
     */

    private boolean isBingo(int currentNumber, int counter, int style) {
        int nextOne = -1;
        switch(style) {
            case 0:
                nextOne = Math.addExact(currentNumber, 1);
                nextOne = isValid(nextOne) ? nextOne : currentNumber;
                break;
            case 1:
                nextOne = Math.addExact(currentNumber, 4);
                nextOne = isValid(nextOne) ? nextOne : currentNumber;
                break;
            case 2:
                nextOne = Math.addExact(currentNumber, 5);
                nextOne = isValid(nextOne) ? nextOne : currentNumber;
                break;
            case 3:
                nextOne = Math.addExact(currentNumber, 3);
                nextOne = isValid(nextOne) ? nextOne : currentNumber;
                break;
        }
        cl.log("validator: " + getStyleName(style),cl.DO_MEDIUM);
        cl.log("CurrentNum|NextNum|Counter: " + currentNumber + "|" + nextOne + "|" + counter,cl.DO_LIGHT);

        // following: severall catch clauses
        // this *should* catch a never ending loop, if your input is for example 16 and the counter is at 2 or below you cant go higher and it should be catched
        if(nextOne == currentNumber && counter <= 2) {
            cl.log("Loop break",cl.DO_FULL);
            return false;
        }

        // if your counter goes higher than 3 that means there wasnt a bingo but the algorithm is still trying its best to find one, but it sadly never will...before we go through this shit for roughly at worst 7 times, we cut it short and do it only 4 times
        if(counter > 3) {
            return false;
        }

        // if counter is 3 and the currentNumber divided by 4 gives us no rest, we will have hit gold, we have hit a bingo
        if(counter == 3 && styleValidator(style, currentNumber)) {
            cl.log("Style|number: " + style + "|" + currentNumber, cl.DO_LIGHT);
            cl.log("Bingo found in " + getStyleName(style), cl.DO_LIGHT);
            //System.out.println(getStyleName(style));
            return true;
        }

        // now, the algorithm lol
        if(buttonsPressed.contains(nextOne)) {
            cl.log("Recurse", cl.DO_LIGHT);
            return isBingo(nextOne, counter + 1, style);
        }

        // if nothin hit, its probably not a bingo
        return false;
    }

    private boolean styleValidator(int style, int num) {
        if(style == HORIZONTAL && (num % 4 == 0)) {
            cl.log("fired for horizontal",cl.DO_LIGHT);
            return true;
        }
        if(style == VERTICAL && (num > 12)) {
            cl.log("fired for vertical",cl.DO_LIGHT);
            return true;
        }
        if(style == DIAGONAL_TL && (num == 16)) {
            cl.log("fired for diagonal tl",cl.DO_LIGHT);
            return true;
        }
        if(style == DIAGONAL_TR && (num == 13)) {
            cl.log("fired for diagonal tr",cl.DO_LIGHT);
            return true;
        }
        /*
        switch (style) {
            case 0:
                if(num % 4 == 0) {
                    cl.log("fired for horizontal",cl.DO_LIGHT);
                    return true;
                }
            case 1:
                if(num > 12) {
                    cl.log("fired for vertical",cl.DO_LIGHT);
                    return true;
                }
            case 2:
                if(num == 16) {
                    cl.log("fired for diagonal tl",cl.DO_LIGHT);
                    return true;
                }
            case 3:
                if(num == 13) {
                    cl.log("fired for diagonal tr",cl.DO_LIGHT);
                    return true;
                }
        }*/
        return false;
    }

    private String getStyleName(int style) {
        switch (style) {
            case 0:
                return "horizontal";
            case 1:
                return "vertical";
            case 2:
                return "diagonal TL";
            case 3:
                return "diagonal TR";
        }
        return "none";
    }
}