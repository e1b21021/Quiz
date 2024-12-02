package oit.is.quizrock.quiz6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.quizrock.quiz6.model.User;
import oit.is.quizrock.quiz6.model.UserMapper;

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
  @GetMapping("/waitingRoom_numQuestions_settings")
  public String showNumQuestionsSettings() {
    // numQuestions_settings.html を返す
    return "numQuestions_settings";
  }

  @GetMapping("/waiting-room")
  public String showWaitingRoom(@RequestParam(name = "username", required = false) String username, Model model) {
    if (username != null && !username.isEmpty()) {
      // データベースに名前を保存
      User user = new User();
      user.setName(username);
      userMapper.insertUser(user);
    }

    // メッセージをビューに追加
    model.addAttribute("message", "ここは待機所です");
    return "waiting-room"; // テンプレート名
  }

}
