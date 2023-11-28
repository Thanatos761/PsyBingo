package de.thanatos761.coreFunctions;

import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {
    private int size = 0;
    private int min = 0;
    private int max = 0;
    private ArrayList<Integer> list = new ArrayList<Integer>();
    public RandomGenerator(int size, int min, int max) {
        this.size = size;
        this.min = min;
        this.max = max;
        gen();
    }
    private int[] arr = new int[size];
    public void gen() {
        int rand = getRandomInt(min, max);
        list.add(rand);
        for(int i = 1; i < size; i++) {
            rand = getRandomInt(min, max);
            list.add(getRandomNew(rand));
        }
    }

    public int getRandomNew(int a) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) == a) {
                int r = getRandomInt(min, max);
                a = getRandomNew(r);
            }
        }
        //System.out.println("Returning: " + a);
        return a;
    }

    public ArrayList<Integer> getIntList() {
        return list;
    }
    public int[] getArr() {
        return this.arr;
    }
    /**
     * Returns a random integer between the range of min and max
     * @param min bottom of the range
     * @param max top of the range
     * @return random integer in range
     */
    public int getRandomInt(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt((max - min) + 1) + min;
    }
}
