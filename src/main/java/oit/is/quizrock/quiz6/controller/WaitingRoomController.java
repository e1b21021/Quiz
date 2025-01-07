package oit.is.quizrock.quiz6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.quizrock.quiz6.mapper.UserMapper;
import oit.is.quizrock.quiz6.model.User;

@Controller
public class WaitingRoomController {

  @Autowired
  UserMapper userMapper;

  /**
   * /waiting-roomを呼び出すときに名前、ジャンル、問題数を受け取り、
   * 適切な待機部屋に遷移します。
   *
   * @param username     フォームから送信された名前
   * @param genre        フォームから送信されたジャンル
   * @param numQuestions フォームから送信された問題数
   * @param model        モデルに追加するデータ
   * @return 遷移先テンプレート名
   */
  @GetMapping("/waiting-room")
  public String showWaitingRoom(
      @RequestParam(name = "username", required = false) String username,
      @RequestParam(name = "genre", required = false) String genre,
      @RequestParam(name = "numQuestions", required = false) Integer numQuestions,
      Model model) {

    if (username != null && !username.isEmpty()) {
      // データベースに名前を保存
      User user = new User();
      user.setName(username);
      userMapper.insertUser(user);
    }

    if (genre != null && numQuestions != null) {
      // 選択されたジャンルと問題数に応じて遷移
      String roomTemplate = numQuestions + "_" + genre + "_waiting-room";
      return roomTemplate; // テンプレート名
    }

    model.addAttribute("message", "ジャンルと問題数を選んでください");
    return "waiting-room";
  }
}
