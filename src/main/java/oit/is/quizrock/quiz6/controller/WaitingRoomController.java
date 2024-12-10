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
   * /waiting-roomを呼び出すときに名前をデータベースに格納し、
   * 待機所ページを表示します。
   *
   * @param username フォームから送信された名前
   * @param model    モデルに追加するデータ
   * @return テンプレート名
   */
  @GetMapping("/3_history_waiting-room")
  public String showWaitingRoom(@RequestParam(name = "username", required = false) String username, Model model) {
    if (username != null && !username.isEmpty()) {
      // データベースに名前を保存
      User user = new User();
      user.setName(username);
      userMapper.insertUser(user);
    }

    // メッセージをビューに追加
    model.addAttribute("message", "ここは待機所です");
    return "3_history_waiting-room"; // テンプレート名
  }

}
