import java.util.HashMap;

public class Player {
    private HashMap<String, Integer> score;
    private Bag bag;


    public Player() {
        score = new HashMap<String, Integer>();
        bag = new Bag();
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((score == null) ? 0 : score.hashCode());
        return result;
    }


    


}
