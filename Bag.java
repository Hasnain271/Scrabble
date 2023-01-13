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

    
    /** 
     * The number of each tile in the bag
     * @return HashMap<String, Integer>
     */
    private HashMap<String, Integer> generateNumOfTiles() {
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
            //put(" ", 2);
        }}; 
        
        return numOfTiles;
    }


    /**
     * Generates the bag
     */
    public void generateBag() {
        HashMap<String, Integer> x = generateNumOfTiles();
        int l = 1;
        for (Integer t : x.values()) {
            for (int i = 0; i < t; i++) {
                String z = Character.toString((char) (64 + l));
                bag.add(z);
            }
            l++;
        }
            
    }


    
    /** 
     * Get the bag
     * @return ArrayList<String>
     */
    public ArrayList<String> getBag() {
        return bag;
    }


    
    /** 
     * Checks if bag is empty
     * @return boolean
     */
    public boolean isEmpty() {
        if (bag.size() == 0) {
            return true;
        }
        return false;
    }

}
