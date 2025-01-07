package oit.is.quizrock.quiz6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.quizrock.quiz6.model.Quiz;
import oit.is.quizrock.quiz6.service.QuizService;

@Controller
public class BattleController {

  @Autowired
  private QuizService quizService;

  @GetMapping("/battle")
  public String showBattle(Model model) {
    model.addAttribute("quiz", quizService.getRandomQuiz());
    return "battle";
  }

  @PostMapping("/battle/submit")
  public String submitAnswer(
      @RequestParam("userAnswer") String userAnswer,
      @RequestParam("quizId") int quizId,
      @RequestParam("quizAns") String quizAnswer,
      Model model) {
    boolean isCorrect = userAnswer.equalsIgnoreCase(quizAnswer);
    model.addAttribute("result", isCorrect ? "正解！" : "不正解");
    return "battle";
  }
}
