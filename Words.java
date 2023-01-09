import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Words {
    private ArrayList<String> wordList;

    public Words() throws FileNotFoundException {
        wordList = generateWordList();
    }

    public ArrayList<String> getWordlist() {
        return wordList;
    }

    public boolean validWord(String word) {
        word = word.toLowerCase();


        if (word.length() > 7) {
            return false;
        }


        for (String e : wordList) {
            if (e.equals(word)) {
                return true;
            } 
        }
        return false;
    }



    public ArrayList<String> generateWordList() throws FileNotFoundException {
        ArrayList<String> x = new ArrayList<String>();
        try {
            File file = new File("words.txt");
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                x.add(input.nextLine());
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found!");
        }
        return x;
    }  
    
    
    public static void main(String[] args) throws FileNotFoundException {
        Words t = new Words();
        System.out.println(t.getWordlist().size());

    }

}
