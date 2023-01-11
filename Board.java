import java.util.HashMap;


public class Board {
    private Tile[][] board;
    private String[][] powerUps;
    
    public Board() {
        this.board = generateTiles();
        this.powerUps = generateSpecialTiles();
    }

    public Tile[][] getBoard() {
        return board;
    }



    public void setBoard(Tile[][] board) {
        this.board = board;
    }



    public String[][] getPowerUps() {
        return powerUps;
    }



    public void setPowerUps(String[][] powerUps) {
        this.powerUps = powerUps;
    }


    // = triple word score
    // * double word score
    // & double letter score
    // $ triple letter score


    private String[][] generateSpecialTiles() {
        String[][] x = new String[15][15];

        String [][] z = generateDoubleLetterScore();
        String [][] o = generateDoubleWordScore();
        String[][] l = generateTripleWordScore();
        String[][] m = generateTripleLetterScore();

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

    private String[][] generateTripleWordScore() {
        String[][] x = new String[15][15]; 

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

    private String[][] generateTripleLetterScore() {
        String[][] x = new String[15][15];

        x[1][5] = "$";
        x[1][9] = "$";
        x[5][5] = "$";
        x[5][9] = "$";

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


    public Tile[][] generateTiles() {
        Tile[][] x = new Tile[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                x[i][j] = new Tile();
            }
        }
        return x;
    }

    public boolean hasPowerUp(int i, int j) {
        if (!powerUps[i][j].equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        String expr = "";
        
        for (int i = 0; i < 15; i++) {
            //expr += String.valueOf(i) + "\t";
            expr += "\n";
            for (int j = 0; j < 15; j++) {
                if (hasPowerUp(i, j) && board[i][j].hasLetter()) {
                    expr += String.format("| %s ", board[i][j].getLetter());
                } else if (hasPowerUp(i, j)) {
                    expr += String.format("| %s ", powerUps[i][j]);
                } else if (board[i][j].hasLetter()) {
                    expr += String.format("| %s ", board[i][j].getLetter());
                } else {
                    expr += String.format("|   ");
                }
            }
            expr += "|";   
        }
        return expr;
    }

    public void placeWord(String word, String loc, String orientation) {
        int row = getRow(loc);
        int col = getColumn(loc);

        word = word.toUpperCase();

        HashMap<String, Integer> o = Tile.generateLetterPoints();

        for (int i = 0; i < word.length(); i++) {
            Tile x = new Tile(word.substring(i, i + 1), o.get(word.substring(i, i + 1).toUpperCase()));
            if (orientation.equals("h") && (col + i) < 15) {
                board[row][col + i] = x;
            } else if (orientation.equals("v") && (row + i) < 15) {
                board[row + i][col] = x;
            }
        }
    }



    public static int getRow(String loc) {
        Character x = loc.charAt(0);
        return x - 'a' + 32;
        
        
        
    }

    public static int getColumn(String loc) {
        int x = Integer.valueOf(loc.substring(1));
        return x - 1;
        
    }


    public boolean validLocation(String loc) throws NotALocationException {
        int x = Integer.valueOf(loc.substring(1));
        if (loc.substring(0, 1).matches("[A-O]") && x < 16) {
            return true;
        } 
        throw new NotALocationException();
    }

    public boolean isAdj(String word, String loc, String orientation) {

        int row = getRow(loc);
        int col = getColumn(loc);


        word = word.toUpperCase();
        
        int z = 0;
        for (int i = 0; i < word.length(); i++) {
            if (orientation.equals("h")) {
                
                if (board[row][col + i].getLetter().equals(word.substring(i, i + 1))) {
                    z++;
                }
            } else if (orientation.equals("v")) {
                if (board[row + i][col].getLetter().equals(word.substring(i, i + 1))) {
                    z++;
                }
            }
        }

        if (z < word.length() && z > 0) {
            return true;
        } else {
            return false;
        }
    }

    // = triple word score
    // * double word score
    // & double letter score
    // $ triple letter score


    public int getTotalWordScore(String word, String loc, String orientation) {
        int x = 0;
        HashMap<String, Integer> t = Tile.generateLetterPoints();
        word = word.toUpperCase();
        String o = getScoreMultiplier(word, loc, orientation);
        for (int i = 0; i < word.length(); i++) {
            int l = t.get(word.substring(i, i + 1));
            if (o.substring(i, i + 1).equals("&")) {
                l *= 2;
            } else if (o.substring(i, i + 1).equals("$")) {
                l *= 3;
            } 
            x += l;
        }

        for (int i = 0; i < o.length(); i++) {
            if (o.substring(i, i + 1).equals("*")) {
                x *= 2;
            } else if (o.substring(i, i + 1).equals("=")) {
                x *= 3;
            }
        }

        return x;
    }


    private String getScoreMultiplier(String word, String loc, String orientation) {

        int row = getRow(loc);
        int col = getColumn(loc);

        String t = "";

        for (int i = 0; i < word.length(); i++) {
            if (orientation.equals("h")) {
                if (powerUps[row][col + i].equals("")) {
                    t += " ";
                } else {
                    t += powerUps[row][col + i]; 
                }
                

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

    public boolean isMiddle(String word, String loc, String orientation) throws NotAValidWordPlacement, NotInMiddleException {
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


    public static void main(String[] args) throws NotALocationException {
        Board b = new Board();
        b.placeWord("hello", "H6", "h");
        System.out.println(b.toString());
        System.out.println(b.validLocation("A3"));
    }
}

    // = triple word score
    // * double word score
    // & double letter score
    // $ triple letter score