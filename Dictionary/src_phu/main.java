import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Command command = new Command(input);
        System.out.println("Welcome to my application !");
        boolean running = true;
        while (running) {
            int userInput = input.nextInt();
            input.nextLine();

            switch (userInput) {
                case 0:
                    running = false;
                    break;
                case 1:
                    command.add();
                    break;
                case 2:
                    command.remove();
                    break;
                case 3:
                    command.update();
                    break;
                case 4:
                    command.display();
                    break;
                case 5:
                    command.lookup();
                    break;
                case 6:
                    command.search();
                    break;
                case 7:
                    command.game();
                    break;
                case 8:
                    command.inputFromFile();
                    break;
                default:
                    System.out.println("Enter a number from 0 to 8 !");
                    break;
            }
        }
    }
}
