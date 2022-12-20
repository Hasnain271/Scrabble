import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scrabble {
    public static void main(String[] args) throws NotALocationException, FileNotFoundException, NotAWordException {
        Scanner input = new Scanner(System.in);
        Words t = new Words();
        Board b = new Board();
        try {
            while (true) {
                String word = input.nextLine();
                if (t.validWord(word.toLowerCase())) {
                b.placeWord(word.toUpperCase(), "A1", "v");
                break;
                } 
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            if (b.isAdj("hello", "A2", "v") && t.validWord("hello")) {
                b.placeWord("hello".toUpperCase(), "A2", "v");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(b.toString());
        input.close();
    }
    
}