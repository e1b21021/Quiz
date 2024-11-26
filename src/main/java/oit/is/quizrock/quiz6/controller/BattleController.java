package oit.is.quizrock.quiz6.controller;

import oit.is.quizrock.quiz6.model.Quiz;
import oit.is.quizrock.quiz6.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BattleController {
  @Autowired
  private QuizService quizService;

  @GetMapping("/battle")
  public String showBattle(org.springframework.ui.Model model) {
    Quiz quiz = quizService.getRandomQuiz(); // ランダムクイズを取得
    model.addAttribute("quiz", quiz); // クイズをモデルに追加
    return "battle"; // battle.html テンプレートを表示
  }

  @GetMapping("/battle/next")
  @ResponseBody
  public Quiz getNextQuiz() {
    return quizService.getRandomQuiz(); // 次のランダムクイズを返す
  }
}
