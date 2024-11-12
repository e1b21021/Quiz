package oit.is.quizrock.quiz6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WaitingRoomController {

  @GetMapping("/waiting-room")
  public String showWaitingRoom(Model model) {
    model.addAttribute("message", "ここは待機所です");
    return "waiting-room"; // テンプレート名
  }
}
