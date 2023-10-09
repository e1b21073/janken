package oit.is.z1732.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JankenController {

  @GetMapping("/index")
  public String showIndex() {
    return "janken";
  }

  @PostMapping("/play")
  public String playJanken(@RequestParam("name") String name) {
    return "redirect:/janken?name=" + name;
  }

  @GetMapping("/janken")
  public String showJanken(@RequestParam("name") String name, Model model) {
    model.addAttribute("name", name);
    return "janken";
  }
}
