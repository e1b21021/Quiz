package oit.is.quizrock.quiz6.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import oit.is.quizrock.quiz6.model.User;
import oit.is.quizrock.quiz6.model.UserMapper;

@Controller
@RequestMapping("/quiz")
public class QuizController {

  @Autowired
  private UserMapper userMapper;

  @GetMapping
  public String janken(Principal prin, ModelMap model) {

    model.addAttribute("loginUser", prin.getName());

    // DBからすべてのユーザを取得
    List<User> userList = userMapper.selectAllUsers();
    model.addAttribute("userList", userList);

    return "quiz.html"; // janken.htmlを返す
  }

  @GetMapping("/users")
  public String showUsers(ModelMap model) {
    List<User> userList = userMapper.selectAllUsers();
    model.addAttribute("userList", userList);
    return "quiz.html"; // janken.htmlにてユーザ情報を表示
  }
}
