package CommandLine;

import java.util.List;
import java.util.Scanner;

public class Game {
    private static int points = 0;
    public static void display(String question, List<String> options) {
        System.out.printf("""
                        Points: %d\s
                        Q: %s
                        A. %s
                        B. %s
                        C. %s
                        D. %s
                        """,
                points, question, options.get(0), options.get(1),
                options.get(2), options.get(3)
        );
    }
    public static void playGame() {
        Scanner input = new Scanner(System.in);
        System.out.print("Number of Quizzes: ");
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            Quiz quiz = new Quiz();
            display(quiz.getQuestion(), quiz.getOptions());
            char playerChoice = input.next().charAt(0);
            quiz.checkCorrect(playerChoice);
            if(quiz.isCorrect()) {
                System.out.println("Correct!");
                points++;
            } else {
                System.out.println("Incorrect!");
            }
        }
        System.out.println("Game Over! Scores: " + points + "/" + n);
    }

}