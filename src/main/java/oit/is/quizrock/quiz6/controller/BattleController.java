package oit.is.quizrock.quiz6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.quizrock.quiz6.service.QuizService;
import oit.is.quizrock.quiz6.service.UserService;

@Controller
public class BattleController {

  @Autowired
  private QuizService quizService;

  @Autowired
  private UserService userService;

  @Autowired
  private SimpMessagingTemplate messagingTemplate; // MessagingTemplateを追加

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
      @RequestParam("userId") int userId, // ユーザーIDを受け取る
      Model model) {

    boolean isCorrect = userAnswer.equalsIgnoreCase(quizAnswer);

    // 正解の場合にスコアを更新
    if (isCorrect) {
      userService.addScore(userId, 10); // 例: 正解でスコア+10
    }

    // 更新されたスコアを送信
    int opponentScore = userService.getScore(userId); // ここで現在のスコアを取得
    messagingTemplate.convertAndSend("/topic/score", opponentScore); // スコアを送信

    model.addAttribute("result", isCorrect ? "正解！" : "不正解");
    return "battle";
  }
}
