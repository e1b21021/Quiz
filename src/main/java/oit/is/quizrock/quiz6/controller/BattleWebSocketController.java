package oit.is.quizrock.quiz6.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import oit.is.quizrock.quiz6.model.Quiz;
import oit.is.quizrock.quiz6.service.QuizService;

@Controller
public class BattleWebSocketController {

  private final QuizService quizService;

  public BattleWebSocketController(QuizService quizService) {
    this.quizService = quizService;
  }

  @MessageMapping("/battle/update")
  @SendTo("/topic/battle")
  public Quiz sendQuizUpdate() {
    return quizService.getRandomQuiz(); // 新しいクイズをランダムに取得して送信
  }

  @MessageMapping("/battle/submit")
  @SendTo("/topic/score")
  public String handleAnswer(String userAnswer) {
    // 回答の処理ロジック（簡易版）
    if (userAnswer.equalsIgnoreCase("正解")) {
      return "対戦相手が正解しました！";
    } else {
      return "対戦相手が不正解です！";
    }
  }
}
