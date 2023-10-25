package oit.is.z1732.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

//import oit.is.z1732.kaizi.janken.model.Janken;
import oit.is.z1732.kaizi.janken.model.Match;
import oit.is.z1732.kaizi.janken.model.MatchInfo;
import oit.is.z1732.kaizi.janken.model.MatchInfoMapper;
import oit.is.z1732.kaizi.janken.model.MatchMapper;
import oit.is.z1732.kaizi.janken.model.User;
//import oit.is.z1732.kaizi.janken.model.Match;
import oit.is.z1732.kaizi.janken.model.UserMapper;
//import oit.is.z1732.kaizi.janken.model.Entry;

@Controller
public class JankenController {

  // @Autowired
  // private Entry entry;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  @Autowired
  MatchInfoMapper matchInfoMapper;

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

  @GetMapping("/janken")
  @Transactional
  public String startGetJanken(ModelMap model) {
    ArrayList<User> entryUsers = userMapper.selectAllUser();
    model.addAttribute("entryUsers", entryUsers);

    ArrayList<MatchInfo> activeMatches = matchInfoMapper.selectActiveMatch();
    model.addAttribute("activeMatches", activeMatches);

    ArrayList<Match> matchResults = matchMapper.selectAllMatch();
    model.addAttribute("matchResults", matchResults);

    return "janken";
  }

  @GetMapping("/match")
  public String matchJanken(@RequestParam Integer enemyUserId, ModelMap model) {
    User enemyUser = userMapper.selectUserById(enemyUserId);
    model.addAttribute("enemyUser", enemyUser);

    return "match.html";
  }

  /**
   *
   * @param playerChoice
   * @param model
   * @return
   */

  @GetMapping("/fight")
  public String fightJanken(@RequestParam String playerChoice, @RequestParam Integer enemyUserId, Model model,
      Principal prin) {
    String loginUserName = prin.getName();

    User loginUser = userMapper.selectUserByName(loginUserName);
    model.addAttribute("loginUser", loginUser);

    User enemyUser = userMapper.selectUserById(enemyUserId);
    model.addAttribute("enemyUser", enemyUser);

    // プレイヤーの手
    model.addAttribute("playerChoice", playerChoice);

    // CPUの手
    /*
     * Janken cpuChoice = new Janken();
     * String computerChoice = cpuChoice.cpuRandomHand();
     * model.addAttribute("computerChoice", computerChoice);
     */

    // 結果
    /*
     * Janken result = new Janken(playerChoice, computerChoice);
     * model.addAttribute("result", result.gameResult());
     */

    MatchInfo matchInfo = new MatchInfo();
    matchInfo.setUser1(loginUser.getId());
    matchInfo.setUser2(enemyUserId);
    matchInfo.setUser1Hand(playerChoice);
    /* matchInfo.setUser2Hand(computerChoice); */
    matchInfoMapper.insertMatchInfo(matchInfo);
    model.addAttribute("matchInfo", matchInfo);

    return "wait.html";
  }
}
