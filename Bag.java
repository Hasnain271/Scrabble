import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class Bag {

    private ArrayList<String> bag;

    public Bag() {
        bag = new ArrayList<String>();
        generateBag();
        Collections.shuffle(bag);
    }

    public HashMap<String, Integer> generateNumOfTiles() {
        HashMap<String, Integer> numOfTiles = new HashMap<String, Integer>() {{

            put("A", 9);
            put("B", 2);
            put("C", 2);
            put("D", 4);
            put("E", 12);
            put("F", 2);
            put("G", 3);
            put("H", 2);
            put("I", 9);
            put("J", 1);
            put("K", 1);
            put("L", 4);
            put("M", 2);
            put("N", 6);
            put("O", 8);
            put("P", 2);
            put("Q", 1);
            put("R", 6);
            put("S", 4);
            put("T", 6);
            put("U", 4);
            put("V", 2);
            put("W", 2);
            put("X", 1);
            put("Y", 2);
            put("Z", 1);
            put("", 2);
        }}; 
        
        return numOfTiles;
    }

    public void generateBag() {
        HashMap<String, Integer> x = generateNumOfTiles();
        int l = 0;
        for (Integer t : x.values()) {
            if (l == 0) {
                String o = Character.toString((char) 32);
                bag.add(o);
                bag.add(o);
            } else {
                for (int i = 0; i < t; i++) {
                    String z = Character.toString((char) (64 + l));
                    bag.add(z);
                }
            }
            l++;
        }
    }


    public ArrayList<String> getBag() {
        return bag;
    }

}
