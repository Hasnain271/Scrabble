import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scrabble {

    protected static Scanner input = new Scanner(System.in) ;

    public static Player[] generatePlayers() {
        System.out.println("Enter Player 1 name");
        String name = input.nextLine();
        Player one = new Player(name);
        System.out.println("Enter Player 2 name");
        name = input.nextLine();
        Player two = new Player(name);
        Player[] t = new Player[2];
        t[0] = one;
        t[1] = two;


        return t;
    }



    public static void generateOptions(Player[] players, int playerNum) {
        System.out.println("\n" + players[playerNum].getName() + "'s turn");
        System.out.println("\n" + generateScore(players, playerNum));
        System.out.println("\n" + generateRack(players, playerNum) + "\n");
        System.out.println("Enter 1 to play a word\nEnter 2 to swap tiles\nEnter 3 to pass");
    }

    public static String generateScore(Player[] players, int playerNum) {
        String expr = "Current Score: " + players[playerNum].getScore();

        return expr;
    }

    public static String generateRack(Player[] players, int playerNum) {
        String expr = "Current Rack\n" + players[playerNum].toString();

        return expr;
    }

    public static int determineTurn(int t) {
        if (t % 2 == 0) {
            return 0;
        }
        return 1;
    }

    public static void playWord(Board b, Player[] players, int t, Words words) {
        String word = "";
        String location = "";
        String orientation = "";

        int x = determineTurn(t);


        while (true) {
            try {

                System.out.println("Enter your word!");
                word = input.nextLine();
                words.validWord(word);

                System.out.println("Enter starting location! (Ex. A3)");
                location = input.nextLine();
                b.validLocation(location);

                System.out.println("Enter orientation! (h OR v)");
                orientation = input.nextLine();

            
                b.isValidLimit(location, word);
                
                if (t == 0) {
                    players[x].hasLettersInRack(word);
                    b.isMiddle(word, location, orientation);
                } else {
                    players[x].hasLettersInRack(word);
                    b.isAdj(word, location, orientation);
                }
                b.placeWord(word, location, orientation);
                //players[x].removeLetters(word);
                players[x].draw();
                if (word.length() == 7) {
                    players[x].addScore(b.getTotalWordScore(word, location, orientation) + 50);
                } else {
                    players[x].addScore(b.getTotalWordScore(word, location, orientation));
                }
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        }

        public static void swapTiles() {
            String lettersToSwap = "";

            while (true) {
                try {
                    System.out.println("Enter the letters you want to swap! (Ex. ABUI)");
                    lettersToSwap = input.nextLine();
                    
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }


    public static void main(String[] args) throws FileNotFoundException {


        Board b = new Board();
        Player[] players = generatePlayers();
        Words words = new Words();
        int t = 0;
        System.out.println(b.toString());
        generateOptions(players, t);
        playWord(b, players, t, words);
        generateOptions(players, 0);
        //System.out.println(b.toString());
        input.close();

    }
    
}