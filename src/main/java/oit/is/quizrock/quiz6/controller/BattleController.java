package oit.is.quizrock.quiz6.controller;

import oit.is.quizrock.quiz6.model.Quiz;
import oit.is.quizrock.quiz6.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

  @PostMapping("/battle/submit")
  @ResponseBody
  public String submitAnswer(
      @RequestParam("userAnswer") String userAnswer,
      @RequestParam("quizId") int quizId,
      @RequestParam("quizAns") String quizAnswer,
      Principal principal) {

    // quizIdとuserAnswerのログ出力
    System.out.println("Received userAnswer: " + userAnswer);
    System.out.println("Received quizId: " + quizId);
    System.out.println("Received quizAns: " + quizAnswer);

    // 答えの一致を判定
    if (userAnswer.equals(quizAnswer)) {
      return "正解！";
    } else {
      return "不正解";
    }
  }

}
