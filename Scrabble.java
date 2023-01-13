import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Scrabble {

    private static Scanner input = new Scanner(System.in);



    public Player[] drawForOrder(Player[] players) {
        int[] l = new int[players.length];
        int i = 0;
        for (Player x : players) {
            System.out.println(x.getName() + " draws: " + Player.bag.getBag().get(0));
            Character ch = Player.bag.getBag().get(0).charAt(0);
            l[i] = ch;
            Collections.shuffle(Player.bag.getBag());
            i++;
        }

        
        int closestIndex = 0;
        int diff = 2093423904;

        for (int t = 0; t < l.length; t++) {
            if (l[t] < diff) {
                closestIndex = t;
                diff = l[t];
            }
        }
        Player[] playerCopy = Arrays.copyOf(players, players.length);
        

        for (int z = 0; z < players.length; z++) {
            if (z == closestIndex) {
                players[0] = players[closestIndex];
                players[z] = playerCopy[0];
            }
        }


        return players;

    }

    
    /** 
     * Generates all the players into a player array
     * @param numOfPlayers
     * @return Player[]
     */
    public Player[] generatePlayers(int numOfPlayers) {

        Player[] p = new Player[numOfPlayers];
        input.nextLine();

        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("Enter player name!");
            String name = input.nextLine();
            p[i] = new Player(name);

        }
        return p;
    }


    
    /** 
     * Generates options for amount of players
     * @return int
     */
    public int playerOptions() {

        while (true) {
            try {
                System.out.println("Enter amount of players (2 - 4)!");
                int numOfPlayers = input.nextInt();
                if (numOfPlayers == 2) {
                    return 2;
                } else if (numOfPlayers == 3) {
                    return 3;
                } else if (numOfPlayers == 4) {
                    return 4;
                } else {
                    throw new NotACorrectChoiceException();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }



    
    /** 
     * Generate options for the player
     * @param players
     * @param playerNum
     */
    public void generateOptions(Player[] players, int playerNum) {
        System.out.println("\n" + players[playerNum].getName() + "'s turn");
        System.out.println("\n" + generateScore(players, playerNum));
        System.out.println("\n" + generateRack(players, playerNum) + "\n");
        System.out.println("Enter 1 to play a word\nEnter 2 to swap tiles\nEnter 3 to pass");
    }

    
    /** 
     * Generate score of a player
     * @param players
     * @param playerNum
     * @return String
     */
    public String generateScore(Player[] players, int playerNum) {
        String expr = "Current Score: " + players[playerNum].getScore();

        return expr;
    }

    
    /** 
     * Generate the rack of a player
     * @param players
     * @param playerNum
     * @return String
     */
    public String generateRack(Player[] players, int playerNum) {
        String expr = "Current Rack\n" + players[playerNum].toString();

        return expr;
    }

    
    /** 
     * Play a word with all input validation 
     * @param b
     * @param players
     * @param t
     * @param words
     * @throws NotAWordException
     * @throws NoLettersInRackException
     * @throws NotEnoughTilesInBagException
     * @throws NotInMiddleException
     * @throws NotALocationException
     * @throws NotAValidWordPlacement
     */
    public void playWord(Board b, Player[] players, int t, Words words) throws NotAWordException, NoLettersInRackException, NotEnoughTilesInBagException, NotInMiddleException, NotALocationException, NotAValidWordPlacement {
        String word = "";
        String location = "";
        String orientation = "";



        while (true) {
            System.out.println("Enter your word!");
            word = input.nextLine();
            words.validWord(word);

            System.out.println("Enter starting location! (Ex. A3)");
            location = input.nextLine();
            b.validLocation(location);

            System.out.println("Enter orientation! (h OR v)");
            orientation = input.nextLine();

            
             b.isValidLimit(location, word);
             int rackLen = players[t].lettersUsedOnRack(word, location, orientation, b);
                
            if (!b.isTilesOnBoard()) {
                players[t].hasLettersInRack(word, location, orientation, b);
                b.isMiddle(word, location, orientation);
            } else {
                players[t].hasLettersInRack(word, location, orientation, b);
                b.isAdj(word, location, orientation);
                }

            if ((!b.isTilesOnBoard() && word.length() == 7) || (b.isTilesOnBoard() && rackLen == 7)) {
                players[t].addScore(b.getTotalWordScore(word, location, orientation) + 50);
            } else {
                players[t].addScore(b.getTotalWordScore(word, location, orientation));
            }
            b.placeWord(word, location, orientation);
            players[t].draw();
            break;
        
            }
        }


        
        /** 
         * Swap tiles options and validation
         * @param players
         * @param b
         * @param t
         * @throws DoNotHaveLettersToSwapException
         */
        public void swapTiles(Player[] players, Board b, int t) throws DoNotHaveLettersToSwapException, NotEnoughTilesInBagException  {
            String lettersToSwap = "";
            

            while (true) {
                
                System.out.println("Enter the letters you want to swap! (Ex. ABUI)");
                lettersToSwap = input.nextLine();
                    
                if (Player.bag.getBag().size() > 7) {
                players[t].canSwap(lettersToSwap);
                players[t].swapTiles(lettersToSwap);
                } else {
                    throw new NotEnoughTilesInBagException();
                }
                break;
            
            }
        }



    
    /** 
     * Main game
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scrabble s = new Scrabble();

        Board b = new Board();
        Leaderboard leaderboard = new Leaderboard();
        int numOfPlayers = s.playerOptions(); 
        Player[] players = s.generatePlayers(numOfPlayers);
        Words words = new Words();
        int playerTurn = 0;
        int passCounter = 0;
        int exchangeCounter = 0;

        players = s.drawForOrder(players);

        while (true) {

            while (true) {
                // Determines which player turn it is
                if (playerTurn > numOfPlayers - 1) {
                    playerTurn = 0;
                }

                System.out.println(b.toString());
                s.generateOptions(players, playerTurn);

                try {
                    // Pass and exchange counter count all passes or exchanges required for the game to end
                    String choice = input.nextLine();
                    if (choice.equals("1")) {
                        s.playWord(b, players, playerTurn, words);
                        playerTurn++;
                        passCounter = 0;
                        exchangeCounter = 0;
        
                    } else if (choice.equals("2")) {
                        s.swapTiles(players, b, playerTurn);
                        playerTurn++;
                        exchangeCounter++;
                        passCounter = 0;

                    } else if (choice.equals("3")) {
                        playerTurn++;
                        passCounter++;
                        exchangeCounter = 0;

                    } else {
                        throw new NotACorrectChoiceException();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(e);
                    break;
                }
            }

            // If a player passes twice or exchanges twice the game ends
            if (passCounter > numOfPlayers || exchangeCounter > numOfPlayers) {
                System.out.println(b.toString() + "\n");
                for (int i = 0; i < numOfPlayers; i++) {
                    System.out.println(players[i].getName() + " " + s.generateScore(players, i));
                }
                leaderboard.determineRankings(players);
                leaderboard.writePlayerRankings();
                System.out.println("\n\n" + leaderboard.toString());
                break;
            }
        }
        input.close();
    }
    
}