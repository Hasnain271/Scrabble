

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

    public void placeWord(String word, String loc, String orientation) throws NotALocationException, NotAWordException {
        int row = getRow(loc);
        int col = getColumn(loc);

        for (int i = 0; i < word.length(); i++) {
            Tile x = new Tile(word.substring(i, i + 1));
            if (orientation.equals("h")) {
                board[row][col + i] = x;
            } else if (orientation.equals("v")) {
                board[row + i][col] = x;
            }
        }
    }



    public static int getRow(String loc) throws NotALocationException {
        Character x = loc.charAt(0);
        if (loc.substring(0, 1).matches("[A-O]")) {
            return x - 'a' + 32;
        } else {
            throw new NotALocationException();
        }
        
        
    }

    public static int getColumn(String loc) throws NotALocationException {
        int x = Integer.valueOf(loc.substring(1));
        if (x < 15) {
            return x - 1;
        } else {
            throw new NotALocationException();
        }
        
    }

    public boolean isAdj(String word, String loc, String orientation) throws NotALocationException, NotAValidWordPlacement {
        boolean flag = false;

        int row = getRow(loc);
        int col = getColumn(loc);

        for (int i = 0; i < word.length(); i++) {
            if (orientation.equals("v")) {
                if (col < 15) {
                    if (board[row][col + 1].hasLetter()) {
                        flag = true;
                    }
                }
                if (col > 0) {
                    if (board[row][col - 1].hasLetter()) {
                        flag = true;
                    }
                } 
                
            } else if (orientation.equals("h")) {
                if (row < 15) {
                    if (board[row + 1][col].hasLetter()) {
                    flag = true;
                    }   
                }
                if (row > 0) {
                    if (board[row - 1][col].hasLetter()) {
                        flag = true;
                    }
                } 
            
        }
        }

        if (flag) {
            return true;
        } else {
            throw new NotAValidWordPlacement();
        }
    }






    public static void main(String[] args) throws NotALocationException {
        Board b = new Board();

        System.out.println(b.toString());
    }



}

