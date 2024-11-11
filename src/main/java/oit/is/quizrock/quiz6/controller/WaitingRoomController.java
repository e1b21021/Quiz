package oit.is.quizrock.quiz6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WaitingRoomController {

  @GetMapping("/waiting-room")
  public String waitingRoom(Model model) {
    model.addAttribute("message", "Welcome to the waiting room!");
    return "waiting-room"; // テンプレートファイル名
  }
}
