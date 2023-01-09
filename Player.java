

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

    public void draw(int numOfDraw) {
        for (int i = 0; i < numOfDraw; i++) {
            
        }
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

    public boolean hasLettersInRack(String word) {
        String[] l = rack;
        boolean flag = true;
        int t = 0;
        word = word.toUpperCase();
        for (int i = 0; i < word.length(); i++) {
            t = 0;
            if (!flag) {
                return false;
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

        return flag;
    }


    public String getName() {
        return name;
    }
    

    public static void main(String[] args) throws NotAWordException {
        Player t = new Player("azd");
        //System.out.println(bag.getBag().size());
        Player x = new Player("sat");
        //System.out.println(bag.getBag().size());
        System.out.println(t.toString());
        System.out.println(t.hasLettersInRack("EA"));

        
        
        
       
    }


}
