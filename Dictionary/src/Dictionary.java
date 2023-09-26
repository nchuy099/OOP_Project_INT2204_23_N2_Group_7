public class Dictionary {
  private int wordAmount = 100000;
  private Word[] words = new Word[wordAmount];

  public void setWordAmount(int wordAmount) {
    this.wordAmount = wordAmount;
  }

  public int getWordAmount() {
    return wordAmount;
  }


}
