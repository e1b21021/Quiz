package oit.is.quizrock.quiz6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import oit.is.quizrock.quiz6.mapper.UserMapper;
import oit.is.quizrock.quiz6.model.User;

@Controller
public class WaitingRoomController {

  @Autowired
  UserMapper userMapper;

  /**
   * 待機所画面を表示する
   *
   * @param username     名前
   * @param genre        ジャンル
   * @param numQuestions 問題数
   * @param model        モデル
   * @return 待機所テンプレート
   */
  @GetMapping("/waiting-room")
  public String showWaitingRoom(
      @RequestParam(name = "username", required = false) String username,
      @RequestParam(name = "genre", required = false) String genre,
      @RequestParam(name = "numQuestions", required = false) Integer numQuestions,
      Model model) {

    if (username != null && !username.isEmpty()) {
      User user = new User();
      user.setName(username);
      userMapper.insertUser(user);
    }

    model.addAttribute("genre", genre);
    model.addAttribute("numQuestions", numQuestions);

    return "waiting-room";
  }

  /**
   * プレイヤー数を確認するAPI
   *
   * @return プレイヤーが2人揃ったかどうか
   */
  @GetMapping("/check-players")
  @ResponseBody
  public boolean checkPlayersReady() {
    int userCount = userMapper.selectAllUsers().size();
    return userCount >= 2; // 2人以上で対戦準備完了
  }
}
