import java.util.Scanner;

public class Command {
    private DictionaryManagement manager;
    private Scanner input = null;

    public Command(Scanner p) {
        this.input = p;
        this.manager = new DictionaryManagement(this.input);
    }

    public int readUserCommand() {
        int userInput = input.nextInt();
        return userInput;
    }

    public void renderCommandLine() {
        System.out.println("[0] Exit");
        System.out.println("[1] Add");
        System.out.println("[2] Remove");
        System.out.println("[3] Update");
        System.out.println("[4] Display");
        System.out.println("[5] Lookup");
        System.out.println("[6] Search");
        System.out.println("[7] Game");
        System.out.println("[8] Import from file");
        System.out.printf("%s", "Your action: ");
    }

    public void add() {
        manager.insertFromCommandline();
    }

    public void remove() {
        System.out.println("This feature will be updated soon :(");
    }

    public void update() {
        System.out.printf("%s", "Enter your word: ");
        String currWord = input.nextLine();
        System.out.printf("%s", "Enter new expaination: ");
        String newExplain = input.nextLine();
        manager.updateWordExplain(currWord, newExplain);
    }

    public void display() {
        manager.showAllWords();
    }

    public void lookup() {
        System.out.printf("%s", "Enter your word: ");
        String currWord = input.nextLine();
        manager.showFoundWord(currWord);
    }

    public void search() {
        System.out.println("This feature will be updated soon :(");
    }

    public void game() {
        System.out.println("This feature will be updated soon :(");
    }

    public void inputFromFile() {
        manager.insertFromFile();
    }
}
