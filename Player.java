
public class Player {
    private int score;
    private String name;
    private Tile[] rack;
    static Bag bag;


    public Player(String name) {
        this.name = name;
        score = 0;
        Tile[] rack = new Tile[7];
    }



    public void generateRack() {
        for (int i = 0; i < 7; i++) {
            rack[i] = bag.getBag().toArray()[0];
        }
    }

    public void addScore(int score) {
        this.score += score;
    }
    


}
