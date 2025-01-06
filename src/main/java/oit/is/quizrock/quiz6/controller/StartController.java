package oit.is.quizrock.quiz6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

  // Start画面を返すメソッド
  @GetMapping("/start")
  public String showStartPage(ModelMap model) {
    return "start.html"; // start.htmlを返す
  }

}
