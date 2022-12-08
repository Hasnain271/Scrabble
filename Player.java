import java.util.ArrayList;

public class Player {
    private String name;
    private int score;
    private ArrayList<String> tiles;


    public Player(String name, int score, ArrayList<String> tiles) {
        this.name = name;
        this.score  = score;
        this.tiles = new ArrayList<String>(tiles);
    }



}
