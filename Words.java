import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Words {
    private ArrayList<String> wordList;

    public Words() throws FileNotFoundException {
        generateWordList();
    }

    public ArrayList<String> getWordlist() {
        return wordList;
    }

    public boolean validWord(String word) {
        for (String e : wordList) {
            if (e.equals(word)) {
                return true;
            } 
        }
        return false;
    }

    public void generateWordList() throws FileNotFoundException { 
        try {
            File file = new File("words.txt");
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                wordList.add(input.nextLine());
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found!");
        }
    }   


    public static void main(String[] args) throws FileNotFoundException {
        Words b = new Words();
        System.out.println(b.getWordlist().size());
    }

}
