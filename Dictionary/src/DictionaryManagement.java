import java.util.*;
public class DictionaryManagement {
  public void insertFromCommandline() {
    Scanner sc = new Scanner(System.in);
    int amountOfWords_looked_up = sc.nextInt();
    for (int i = 0; i < amountOfWords_looked_up; i++) {
      String EngExpression;
      String VietMeaning;
      System.out.print("Enter English word: ");
      EngExpression = sc.next();
      System.out.print("Enter Vietnamese meaning: ");
      VietMeaning = sc.next();
    }
  }

}
