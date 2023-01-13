import java.util.HashMap;


public class Board {
    private Tile[][] board;
    private String[][] powerUps;
    
    public Board() {
        this.board = generateTiles();

        // = triple word score
        // * double word score
         // & double letter score
         // $ triple letter score

        this.powerUps = generateSpecialTiles();
    }

    
    /** 
     * Get game board
     * @return Tile[][]
     */
    public Tile[][] getBoard() {
        return board;
    }



    
    /** 
     * Set game board
     * @param board
     */
    public void setBoard(Tile[][] board) {
        this.board = board;
    }



    
    /** 
     * Get power ups on the board
     * @return String[][]
     */
    public String[][] getPowerUps() {
        return powerUps;
    }



    
    /** 
     * Set power ups on the board
     * @param powerUps
     */
    public void setPowerUps(String[][] powerUps) {
        this.powerUps = powerUps;
    }


    
    /** 
     * Generates all power ups on the board
     * @return String[][]
     */
    private String[][] generateSpecialTiles() {
        String[][] x = new String[15][15];

        String [][] z = generateDoubleLetterScore();
        String [][] o = generateDoubleWordScore();
        String[][] l = generateTripleWordScore();
        String[][] m = generateTripleLetterScore();

        // Puts all seperate power ups on the board into one 2D array
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (z[i][j] == "&") {
                    x[i][j] = "&";
                } else if (o[i][j] == "*") {
                    x[i][j] = "*";
                } else if (l[i][j] == "=") {
                    x[i][j] = "=";
                } else if (m[i][j] == "$") {
                    x[i][j] = "$";
                } else {
                    x[i][j] = "";
                }
            }
        }

        return x;
        
    }

    
    /** 
     * Generates all triple word score items on the board represented by the symbol "="
     * @return String[][]
     */
    private String[][] generateTripleWordScore() {
        String[][] x = new String[15][15]; 

        // Hard coded all triple word scores
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if ((i == 0 && j == 0) || (i == 7 && j == 0) || (i == 14 && j == 0) || (i == 0 && j == 7) 
                || (i == 14 && j == 7) || (i == 0 && j == 14) || (i == 7 && j == 14) || (i == 14 && j == 14)) {
                    x[i][j] = "=";
                }
            }
        } 

        return x;
    }
    
    /** 
     * Generates all doouble word scores on the board represented by "*"
     * @return String[][]
     */
    private String[][] generateDoubleWordScore() {
        String[][] x = new String[15][15];

        // Quads start from top left going right

        // First Quad
        for (int i = 1; i < 5; i++) {
            x[i][i] = "*";
        }
        // Second Quad
        for (int i = 1; i < 5; i++) {
            x[i][14 - i] = "*";
        }
        // Third Quad
        for (int i = 10; i < 14; i++) {
            x[i][14 - i] = "*";
        }
        //Fourth Quad
        for (int i = 10; i < 14; i++) {
            x[i][i] = "*";
        }
        
        x[7][7] = "*";

        return x;
    }

     
    /** 
     * Generates all double letter score represented by "&"
     * @return String[][]
     */
    private String[][] generateDoubleLetterScore() {
        String[][] x = new String[15][15];

        // Top pattern hardcoded
        x[0][3] = "&";
        x[0][11] = "&";
        x[2][6] = "&";
        x[2][8] = "&";
        x[3][7] = "&";
        x[6][6] = "&";
        x[6][8] = "&";

        // Makes the rest of the patterns for double letter score
        int i = -1;
        for (String[] t : x) {
            i++;
            int p = -1;
            for (String l : t) {
                p++;
                if (l == null) {
                } else {
                    x[p][i] = "&";
                    x[i][14 - p] = "&";
                }
            }
        }

        return x;
    }

    
    /** 
     * Generates triple letter score represented by "$"
     * @return String[][]
     */
    private String[][] generateTripleLetterScore() {
        String[][] x = new String[15][15];

        // Hardcoded one section
        x[1][5] = "$";
        x[1][9] = "$";
        x[5][5] = "$";
        x[5][9] = "$";


        // This algorithm codes the rest of the triple letter scores
        int i = -1;
        for (String[] t : x) {
            i++;
            int p = -1;
            for (String l : t) {
                p++;
                if (l == null) {
                } else {
                    x[p][i] = "$";
                    x[i][14 - p] = "$";
                }
            }
        }
        return x;
    }


    
    /** 
     * Generates all board tiles
     * @return Tile[][]
     */
    public Tile[][] generateTiles() {
        Tile[][] x = new Tile[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                x[i][j] = new Tile();
            }
        }
        return x;
    }

    
    /** 
     * Checks if a tile has a power up
     * @param i
     * @param j
     * @return boolean
     */
    public boolean hasPowerUp(int i, int j) {
        if (!powerUps[i][j].equals("")) {
            return true;
        } else {
            return false;
        }
    }

    
    /** 
     * Turns the 2D array board into a string
     * @return String
     */
    public String toString() {
        String expr = "";

        
        for (int i = 0; i < 15; i++) {
            expr += "\n";

            int ascii = 65 + i;
            String x = Character.toString((char) ascii);
            expr += x + "\t";
            

             

            for (int j = 0; j < 15; j++) {
                // If theres a power up and a letter on the board tile
                if (hasPowerUp(i, j) && board[i][j].hasLetter()) {
                    expr += String.format("| %s ", board[i][j].getLetter());
                // If there is a power up on the board tile
                } else if (hasPowerUp(i, j)) {
                    expr += String.format("| %s ", powerUps[i][j]);
                // If there is a letter on the board tile
                } else if (board[i][j].hasLetter()) {
                    expr += String.format("| %s ", board[i][j].getLetter());
                // If there is nothing on the board tile
                } else {
                    expr += String.format("|   ");
                }
            }
            expr += "|"; 
            
        
        }

        expr += "\n\n" + "\t" + "  1" + "   2" + "   3" + "   4" + "   5" + "   6" + "   7" +  "   8" + "   9" + "   10" + "  11" + "  12" + "  13" + "  14" + "  15";
        return expr;
    }

    
    /** 
     * Places a word on the board using the word, location and the orientation
     * @param word
     * @param loc
     * @param orientation
     */
    public void placeWord(String word, String loc, String orientation) {


        int row = getRow(loc);
        int col = getColumn(loc);

        word = word.toUpperCase();

        // Gets hashmap of all points of each letter
        HashMap<String, Integer> o = Tile.generateLetterPoints();

        for (int i = 0; i < word.length(); i++) {
            // Generates a new tile of the letter at index i of the word 
            Tile x = new Tile(word.substring(i, i + 1), o.get(word.substring(i, i + 1).toUpperCase()));
            // Places horizontally
            if (orientation.equals("h") && (col + i) < 15) {
                board[row][col + i] = x;
                powerUps[row][col + i] = "";
            // Places vertically
            } else if (orientation.equals("v") && (row + i) < 15) {
                board[row + i][col] = x;
                powerUps[row + i][col] = "";
            }
        }
    }



    
    /** 
     * Gets the row or the first value of a 2D array
     * @param loc
     * @return int
     */
    public static int getRow(String loc) {
        // Gets the letter part of location
        Character x = loc.charAt(0);
        // Converts to an int using ascii values
        return x - 'a' + 32;
    }

    
    /** 
     * Gets the column or the second value of a 2D array
     * @param loc
     * @return int
     */
    public static int getColumn(String loc) {
        // Gets number value of location
        int x = Integer.valueOf(loc.substring(1));
        return x - 1;
    }


    
    /** 
     * Checks if the location in the parameter is a valid location on the board
     * @param loc
     * @return boolean
     * @throws NotALocationException
     */
    public boolean validLocation(String loc) throws NotALocationException {
        int x = Integer.valueOf(loc.substring(1));
        // Using regex the program matches if the first index is a letter between A and O
        // Checks if second index (x) is a value under 16
        if (loc.substring(0, 1).matches("[A-O]") && x < 16) {
            return true;
        } 
        throw new NotALocationException();
    }

    
    /** 
     * Checks if one word is adjacent to another on the board
     * @param word
     * @param loc
     * @param orientation
     * @return boolean
     */
    public boolean isAdj(String word, String loc, String orientation) {

        int row = getRow(loc);
        int col = getColumn(loc);


        word = word.toUpperCase();
        
        int z = 0;
        for (int i = 0; i < word.length(); i++) {
            if (orientation.equals("h")) {
                // Checks if there is at least one letter that is the same horizontally then adds to z value
                if (board[row][col + i].getLetter().equals(word.substring(i, i + 1))) {
                    z++;
                }
                // Checks if there is at least one letter that is the same vertically then adds to z value
            } else if (orientation.equals("v")) {
                if (board[row + i][col].getLetter().equals(word.substring(i, i + 1))) {
                    z++;
                }
            }
        }

        // The word is adjacent if the z value is greater than 0 meaning it has similar letters and if its less than word length
        if (z < word.length() && z > 0) {
            return true;
        } else {
            return false;
        }
    }

    
    /** 
     * Gets total word score of the word with multipliers 
     * @param word
     * @param loc
     * @param orientation
     * @return int
     */
    public int getTotalWordScore(String word, String loc, String orientation) {
        int totalScore = 0;
        HashMap<String, Integer> t = Tile.generateLetterPoints();
        word = word.toUpperCase();
        String o = getScoreMultiplier(word, loc, orientation);

        // Gets score for each individual letter  with multipliers then adds to total
        for (int i = 0; i < word.length(); i++) {
    
            int l = t.get(word.substring(i, i + 1));
            if (o.substring(i, i + 1).equals("&")) {
                l *= 2;
            } else if (o.substring(i, i + 1).equals("$")) {
                l *= 3;
            } 
            totalScore += l;
        }

        // Gets score for the entire word with multipliers
        for (int i = 0; i < o.length(); i++) {
            if (o.substring(i, i + 1).equals("*")) {
                totalScore *= 2;
            } else if (o.substring(i, i + 1).equals("=")) {
                totalScore *= 3;
            }
        }

        return totalScore;
    }


    
    /** 
     * Gets all score multipliers in the words direction and location into a string
     * @param word
     * @param loc
     * @param orientation
     * @return String
     */
    private String getScoreMultiplier(String word, String loc, String orientation) {

        int row = getRow(loc);
        int col = getColumn(loc);

        String t = "";

        for (int i = 0; i < word.length(); i++) {
            // If horizontal it gets all power ups in that direction
            if (orientation.equals("h")) {
                if (powerUps[row][col + i].equals("")) {
                    t += " ";
                } else {
                    t += powerUps[row][col + i]; 
                }
                
            // If vertical it gets all power ups in that direction
            } else if (orientation.equals("v")) {
                if (powerUps[row + i][col].equals("")) {
                    t += " ";
                } else {
                    t += powerUps[row + i][col];
                }
                
                

            }
        }

        return t;
    }

    
    /** 
     * Checks if at least one letter of the word is in the middle of the board
     * @param word
     * @param loc
     * @param orientation
     * @return boolean
     * @throws NotInMiddleException
     */
    public boolean isMiddle(String word, String loc, String orientation) throws NotInMiddleException {
        int row = getRow(loc);
        int col = getColumn(loc);

        for (int i = 0; i < word.length(); i++) {
            
            if (orientation.equals("h")) {

                if (row == 7 && col + i == 7) {
                    return true;
                }

            } if (orientation.equals("v")) {

                if (row + i == 7 && col == 7) {
                    return true;
                }
            }
        }
        throw new NotInMiddleException();

        
    }

    
    /** 
     * Checks if the word placement doesn't go past the limit of the board
     * @param loc
     * @param word
     * @return boolean
     * @throws NotAValidWordPlacement
     */
    public boolean isValidLimit(String loc, String word) throws NotAValidWordPlacement {
        int row = getRow(loc);
        int col = getColumn(loc);


        int colLim = col + word.length();
        int rowLim = row + word.length();

        if (colLim > 14 || rowLim > 14) {
            throw new NotAValidWordPlacement();
        }
        return true;
    }

    
    /** 
     * Checks for letters already on the board for a word
     * @param word
     * @param loc
     * @param orientation
     * @return String
     */
    public String lettersAlreadyOnBoard(String word, String loc, String orientation) {
        int row = getRow(loc);
        int col = getColumn(loc);

        String letters = "";

        for (int i = 0; i < word.length(); i++) {
            if (orientation.equals("h")) {
                if (board[row][col + i].getLetter().equals(word.substring(i, i + 1))) {
                    letters += word.substring(i, i + 1);
                }
            } else if (orientation.equals("v")) {
                if (board[row + i][col].getLetter().equals(word.substring(i, i + 1))) {
                    letters += word.substring(i, i + 1);
                }
            }
        }

        return letters;
    }
    /**
     * Checks for tiles on the board
     * @return boolean
     */
    public boolean isTilesOnBoard() {
        for (int i = 0; i < 15; i++) {
            for (int t = 0; t < 15; t++) {
                if (board[i][t].hasLetter()) {
                    return true;
                }
            }
        }
        return false;
    }

}