package oit.is.quizrock.quiz6.controller;

import oit.is.quizrock.quiz6.model.Quiz;
import oit.is.quizrock.quiz6.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import oit.is.quizrock.quiz6.model.User;
import oit.is.quizrock.quiz6.mapper.UserMapper;

@Controller
public class BattleController {
  @Autowired
  private QuizService quizService;

  @Autowired
  private UserMapper userMapper;

  private int score = 0; // スコアを一時的に管理

  @GetMapping("/battle")
  public String showBattle(Model model) {
    Quiz quiz = quizService.getRandomQuiz(); // ランダムクイズを取得
    model.addAttribute("quiz", quiz); // クイズをモデルに追加
    model.addAttribute("score", score); // 現在のスコアをモデルに追加
    return "battle"; // battle.html テンプレートを表示
  }

  @GetMapping("/battle/next")
  @ResponseBody
  public Quiz getNextQuiz() {
    return quizService.getRandomQuiz(); // 次のランダムクイズを返す
  }

  @PostMapping("/battle/submit")
  @ResponseBody
  public String submitAnswer(
      @RequestParam("userAnswer") String userAnswer,
      @RequestParam("quizId") int quizId,
      @RequestParam("quizAns") String quizAnswer) {

    // 答えの一致を判定
    if (userAnswer.equalsIgnoreCase(quizAnswer)) {
      score += 10; // 正解時にスコアを加算
      return "正解！現在のスコア: " + score;
    } else {
      return "不正解！正解は: " + quizAnswer + " 現在のスコア: " + score;
    }
  }

  @GetMapping("/result")
  public String showResult(Model model) {
    model.addAttribute("score", score); // スコアを結果画面に渡す
    score = 0;
    return "result"; // result.html テンプレートを表示
  }
}
