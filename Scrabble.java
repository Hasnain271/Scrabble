import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scrabble {

    public static Player[] generatePlayers() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Player 1 name");
        String name = input.nextLine();
        Player one = new Player(name);
        System.out.println("Enter Player 2 name");
        name = input.nextLine();
        Player two = new Player(name);
        input.close();
        Player[] t = new Player[2];
        t[0] = one;
        t[1] = two;


        return t;
    }



    public static void generateOptions(Player[] players, int playerNum) {
        System.out.println("\n" + players[playerNum].getName() + "'s turn");
        System.out.println("\nCurrent Rack\n" + players[playerNum].toString() + "\n");
        System.out.println("Enter 1 to play a word\nEnter 2 to swap tiles\nEnter 3 to pass");
    }

    public static int determineTurn(int t) {
        if (t % 2 == 0) {
            return 0;
        }
        return 1;
    }

    public static void playWord(Board b, Player[] players, int t, Words words) throws NotALocationException, NotAValidWordPlacement, NotAWordException {
        Scanner input = new Scanner(System.in);
        String word = "";
        String location = "";
        String orientation = "";

        while (true) {
            System.out.println("Enter your word!");
            word = input.nextLine();
            if (words.validWord(word)) {
                break;
            } else {
                System.out.println("Not a valid word!");
            }
        }

        while (true) {
            try {
                System.out.println("Enter starting location! (Ex. A3)");
                location = input.nextLine();
                Board.getRow(location);
                Board.getColumn(location);
                break;
            } catch (NotALocationException e) {
                System.out.println(e);
            }
        }

        while (true) {
            System.out.println("Enter orientation! (h or v)");
            orientation = input.nextLine();
            if (!orientation.equals("h") || !orientation.equals("v")) {
                System.out.println("Not a valid orientation!");
            } else {
                break;
            }
        }

        if (t == 0) {
            if (b.isMiddle(word, location, orientation)) {
                b.placeWord(word, location, orientation);
            }
        } else {
            if (b.isAdj(word, location, orientation)) {
                b.placeWord(word, location, orientation);
            }
        }


        input.close();

        }




    public static void main(String[] args) throws NotALocationException, FileNotFoundException, NotAWordException {
        Scanner input = new Scanner(System.in);
        Board b = new Board();
        Player[] players = generatePlayers();
        Words words = new Words();
        int t = 0;
        System.out.println(b.toString());
        generateOptions(players, 1);
        
        while (true) {
            try {
                
            } catch (Exception e) {
                // TODO: handle exception
            }

            t++;
        }


    }
    
}