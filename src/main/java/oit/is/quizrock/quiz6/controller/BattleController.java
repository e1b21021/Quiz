package oit.is.quizrock.quiz6.controller;

import oit.is.quizrock.quiz6.model.Quiz;
import oit.is.quizrock.quiz6.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  @GetMapping("/result")
  public String showResult(Model model) {
    // モデルにデータを渡す
    model.addAttribute("resultMessage", "おめでとう！対戦結果です！");
    model.addAttribute("resultType", "success"); // 成功か失敗かを示す（"success" or "failure"）
    return "result";
  }
}
