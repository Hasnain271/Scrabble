import java.util.Collections;

public class Player {
    private int score;
    private String name;
    private String[] rack;
    static Bag bag = new Bag();


    public Player(String name) {
        this.name = name;
        score = 0;
        rack = new String[7];
        generateRack();
    }



    public void generateRack() {
        for (int i = 0; i < 7; i++) {
            rack[i] = bag.getBag().get(0);
            bag.getBag().remove(0);
        }
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void draw() {
        for (int i = 0; i < 7; i++) {
            if (rack[i].equals("")) {
                rack[i] = bag.getBag().get(0);
                bag.getBag().remove(0);
            }
        }
    }

    public void removeLetters(String word) {
        for (int i = 0; i < word.length(); i++) {
            for (int t = 0; i < 7; t++) {
                if (rack[t].equals(word.substring(i, i + 1))) {
                    rack[t] = "";
                }
            }
        }
    }

    public void swapTiles(String tilesToSwap) {
        tilesToSwap = tilesToSwap.toUpperCase();
        for (int i = 0; i < tilesToSwap.length(); i++) {
            for (int t = 0; t < 7; t++) {
                if (rack[t].equals(tilesToSwap.substring(i, i + 1))) {
                    bag.getBag().add(rack[t]);
                    Collections.shuffle(bag.getBag());
                    rack[t] = bag.getBag().get(0);

                }
            }
        }
    }

    public boolean canSwap(String tilesToSwap) throws DoNotHaveLettersToSwapException {
        boolean flag = true;
        int t = 0;
        tilesToSwap = tilesToSwap.toUpperCase();
        for (int i = 0; i < tilesToSwap.length(); i++) {
            if (!flag) {
                throw new DoNotHaveLettersToSwapException();
            }
            while (t < 7) {
                if (!rack[t].equals(tilesToSwap.substring(i, i + 1))) {
                    flag = false;
                } else {
                    flag = true;
                    break;
                }
                t++;
            }
        }

        return flag;
    }

    public String toString() {
        String expr = "";
        for (int i = 0; i < 7; i++) {
            if (rack[i].equals(" ")) {
                expr += "_ ";
            } else {
                expr += rack[i] + " ";
            }
        }
        return expr;
    }

    public boolean hasLettersInRack(String word) throws NoLettersInRackException {
        String[] l = rack;
        boolean flag = true;
        int t = 0;
        word = word.toUpperCase();
        for (int i = 0; i < word.length(); i++) {
            t = 0;
            if (!flag) {
                throw new NoLettersInRackException();
            }
            while (t < 7) {
                if (!l[t].equals(word.substring(i, i + 1))) {
                    flag = false;
                } else if (l[t].equals(word.substring(i, i + 1))) {
                    flag = true;
                    l[t] = "";
                    break;
                }

                t++;
            }

        }
        if (!flag) {
            throw new NoLettersInRackException();
        }
        return flag;
    }




    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    

    public static void main(String[] args) throws NotAWordException, NoLettersInRackException {
        Player t = new Player("azd");
        //System.out.println(bag.getBag().size());
        Player x = new Player("sat");
        //System.out.println(bag.getBag().size());
        System.out.println(t.toString());
        System.out.println(t.hasLettersInRack("PI"));

        
        
        
       
    }


}
