import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;



public class Leaderboard {
    
    private Player[] rankings = new Player[10];


    public Leaderboard() {
        getPlayerRankings();
    }


    /**
     * Get player rankings from leaderboard.txt
     */
    public void getPlayerRankings() {
        int i = 0;
        while (true) {
            try {
                File file = new File("leaderboard.txt");
                Scanner input = new Scanner(file);
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    if (line.equals("TBD: 0")) {
                        rankings[i] = new Player("TBD", 0);
                    } else {
                        rankings[i] = new Player(line.substring(0, line.indexOf(":")), Integer.valueOf(line.substring(line.indexOf(":") + 2)));
                    } 

                    i++;
                }
                input.close();
                break;
            } catch (Exception e) {
                System.out.println(e);
                
            }
            
        }
    
    }

    
    /** 
     * Determines rankings of current players
     * @param players
     */
    public void determineRankings(Player[] players) {

        for (int i = 0; i < 10; i++) {

            for (int t = 0; t < players.length; t++) {
                if (isOnRanking(players[t]) && rankings[i].getName().equals(players[t].getName())) {
                    if (players[t].getScore() > rankings[i].getScore()) {
                        rankings[i] = players[t];
                    }
                } else if (!isOnRanking(players[t]) && players[t].getScore() > rankings[i].getScore()) {
                    movePlayersDown(i);
                    rankings[i] = players[t];
                    
                }
            }
        }
    }


    
    /** 
     * Moves all players down one rank from the rank given 
     * @param rank
     */
    public void movePlayersDown(int rank) {
        Player[] z = Arrays.copyOf(rankings, rankings.length);
        for (int i = rank; i < 10; i++) {
            if (i < 9) {
                rankings[i + 1] = z[i];
            }
        }
    }


    /**
     * Writes all player rankings into leaderboard.txt
     */
    public void writePlayerRankings() {
        try {
            File file = new File("leaderboard.txt");
            FileWriter writer = new FileWriter(file);

            for (Player x : rankings) {
                writer.write(x.getName() + ": " + x.getScore() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    
    /** 
     * Checks if a player is already on the rankings
     * @param player
     * @return boolean
     */
    public boolean isOnRanking(Player player) {
        for (int i = 0; i < 10; i++) {
            if (rankings[i].getName().equals(player.getName())) {
                return true;
            }
        }
        return false;
    }



    
    /** 
     * Converts into a readable leaderboard
     * @return String
     */
    public String toString() {
        String expr = "";
        for (Player x : rankings) {
            expr += x.getName() + ": " + String.valueOf(x.getScore()) + "\n";
        }

        return expr;
    }
}
