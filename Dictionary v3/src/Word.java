public class Word {
  private String expression;
  private String meaning;

  public void setExpression(String expression) {
    this.expression = expression;
  }

  public String getExpression() {
    return expression;
  }

  public void setMeaning(String meaning) {
    this.meaning = meaning;
  }

  public String getMeaning() {
    return meaning;
  }

  public void showWord() {
    System.out.printf("%-7s | %s\n", expression, meaning);
  }
}
