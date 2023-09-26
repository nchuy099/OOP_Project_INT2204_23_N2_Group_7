import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

public class DictionaryManagement {

    private Dictionary dictionary;
    private Scanner input = null;

    public DictionaryManagement(Scanner p) {
        this.input = p;
        this.dictionary = new Dictionary();
    }

    public void insertFromCommandline() {
        // So luong tu muon nhap
        System.out.printf("%s", "Enter number of words you want to add: ");
        int wordCount = input.nextInt();
        input.nextLine();

        // Nhap tu tieng anh va nghia tieng viet
        while (wordCount > 0) {
            Word temp = new Word();
            System.out.printf("%s", "Enter word: ");
            temp.setWordTarget(input.nextLine());
            System.out.printf("%s", "Enter explaination: ");
            temp.setWordExplain(input.nextLine());
            dictionary.addNewWord(temp);
            wordCount--;
        }
    }

    public void showAllWords() {
        int wordCount = 1;
        List<Word> temp = dictionary.getWordList();

        System.out.printf("%-4s | %-7s | %s\n", "NO", "English", "Vietnamese");

        for (Word w : temp) {
            System.out.printf("%-4d | %-7s | %s\n", wordCount, w.getWordTarget(), w.getWordExplain());
            wordCount++;
        }
    }

    public void insertFromFile() {
        File wordsFile = new File("dictionaries.txt");
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(wordsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (fileInput.hasNextLine()) {
            String line = input.nextLine();
            String[] parts = line.split("\t");
            Word temp = new Word();
            temp.setWordTarget(parts[0]);
            temp.setWordExplain(parts[1]);
            dictionary.addNewWord(temp);
        }
        fileInput.close();
    }

    public int dictionaryLookup(String s) {
        int right = dictionary.getWordList().size();
        int left = 0;
        int mid = (right + left) / 2;
        while (left <= right) {
            String currWord = dictionary.getWordList().get(mid).getWordTarget();
            if (currWord.compareToIgnoreCase(s) > 0) {
                right = mid - 1;
                mid = (left + right) / 2;
            } else if (currWord.compareToIgnoreCase(s) < 0) {
                left = mid + 1;
                mid = (left + right) / 2;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public void showFoundWord(String s) {
        int wordPosition = dictionaryLookup(s);
        if (wordPosition == -1) {
            System.out.println("101 Error: Word Not Found :(");
            return;
        } else {
            dictionary.getWordList().get(wordPosition).showWord();
        }
    }

    public void removeWord(String s) {
        int wordPosition = dictionaryLookup(s);
        if (wordPosition == -1) {
            System.out.println("404 Error: Word Not Found :(");
            return;
        } else {
            dictionary.getWordList().remove(wordPosition);
            System.out.println("Misson completed !");
        }
    }

    public void updateWordExplain(String currWord, String newExplain) {
        int wordPosition = dictionaryLookup(currWord);
        if (wordPosition == -1) {
            System.out.println("404 Error: Word Not Found :(");
            return;
        } else {
            dictionary.getWordList().get(wordPosition).setWordExplain(newExplain);
            System.out.println("Mission completed !");
        }
    }
}