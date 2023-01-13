import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Words {
    private ArrayList<String> wordList;

    public Words() throws FileNotFoundException {
        wordList = generateWordList();
    }

    
    /** 
     * Get the word list
     * @return ArrayList<String>
     */
    public ArrayList<String> getWordlist() {
        return wordList;
    }

    
    /** 
     * Checks if the word is a valid word in words.txt
     * @param word
     * @return boolean
     * @throws NotAWordException
     */
    public boolean validWord(String word) throws NotAWordException {
        word = word.toLowerCase();


        if (word.length() > 7) {
            throw new NotAWordException();
        }


        for (String e : wordList) {
            if (e.equals(word)) {
                return true;
            } 
        }
        
        throw new NotAWordException();
    }



    
    /** 
     * Generates the word list 
     * @return ArrayList<String>
     * @throws FileNotFoundException
     */
    private ArrayList<String> generateWordList() throws FileNotFoundException {
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

}
