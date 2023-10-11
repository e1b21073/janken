package oit.is.z1732.kaizi.janken.model;

import java.util.Random;

public class Janken {
  String result;
  String randomHand;

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

  public Janken() {
    Random rand = new Random();
    int randNum = rand.nextInt(3);

    if (randNum == 0) {
      randomHand = "Gu";
    } else if (randNum == 1) {
      randomHand = "Choki";
    } else if (randNum == 2) {
      randomHand = "Pa";
    } else {
      randomHand = "Pa";
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

  public String cpuRandomHand() {
    return getRandomHand();
  }

  public String getRandomHand() {
    return randomHand;
  }
}
