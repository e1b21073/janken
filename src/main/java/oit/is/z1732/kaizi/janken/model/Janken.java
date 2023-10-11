package oit.is.z1732.kaizi.janken.model;

public class Janken {
  String result;

  public Janken(String playerChoice, String computerChoice) {
    if (playerChoice.equals(computerChoice)) {
      result = "It's a draw!";
    } else if ((playerChoice.equals("Gu") && computerChoice.equals("Choki")) ||
        (playerChoice.equals("Choki") && computerChoice.equals("Pa")) ||
        (playerChoice.equals("Pa") && computerChoice.equals("Gu"))) {
      result = "You win!";
    } else {
      result = "You lose!";
    }
  }

  public String gameResult() {
    return getResult();
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
