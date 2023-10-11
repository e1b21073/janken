package oit.is.z1732.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import oit.is.z1732.kaizi.janken.model.Janken;

@Controller
public class JankenController {

  @GetMapping("/index")
  public String showIndex() {
    return "janken";
  }

  /**
   *
   * @param name
   * @param model
   * @return
   */

  @PostMapping("/janken")
  public String showJanken(@RequestParam String name, Model model) {
    model.addAttribute("name", name);
    return "janken";
  }

  @GetMapping("/janken")
  public String showGetJanken() {
    return "janken";
  }

  /**
   *
   * @param playerChoice
   * @param model
   * @return
   */

  @GetMapping("/play2")
  public String play2Janken(@RequestParam String playerChoice, Model model) {
    // プレイヤーの手
    model.addAttribute("playerChoice", playerChoice);

    // CPUの手（グー）
    String computerChoice = "Gu";
    model.addAttribute("computerChoice", computerChoice);

    // 結果
    Janken result = new Janken(playerChoice, computerChoice);
    model.addAttribute("result", result.gameResult());

    return "janken.html";
  }
}
