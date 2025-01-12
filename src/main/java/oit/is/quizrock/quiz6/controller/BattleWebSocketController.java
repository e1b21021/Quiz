package oit.is.quizrock.quiz6.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import oit.is.quizrock.quiz6.model.Quiz;
import oit.is.quizrock.quiz6.service.QuizService;
import oit.is.quizrock.quiz6.service.UserService;

@Controller
public class BattleWebSocketController {

  private final QuizService quizService; // QuizServiceのインスタンス
  private final UserService userService; // UserServiceのインスタンス
  private final SimpMessagingTemplate messagingTemplate; // 個別にメッセージを送信するために使用

  // コンストラクタインジェクションでquizServiceとuserServiceを注入
  public BattleWebSocketController(QuizService quizService, UserService userService,
      SimpMessagingTemplate messagingTemplate) {
    this.quizService = quizService;
    this.userService = userService;
    this.messagingTemplate = messagingTemplate;
  }

  // ランダムなクイズを送信するメソッド
  @MessageMapping("/battle/update")
  @SendTo("/topic/battle")
  public Quiz sendQuizUpdate() {
    return quizService.getRandomQuiz(); // quizServiceからランダムなクイズを取得
  }

  // 回答の処理を行い、次の問題を送信
  @MessageMapping("/battle/submit")
  public void handleAnswer(String userAnswer, int userId, String correctAnswer) {
    // 回答が正しいかを確認
    boolean isCorrect = userAnswer.equalsIgnoreCase(correctAnswer);

    // 正解の場合、スコアを加算
    if (isCorrect) {
      userService.addScore(userId, 10); // 正解ならスコアを加算
    }

    // 更新されたスコアを取得
    int updatedScore = userService.getScore(userId);

    // スコアを送信
    messagingTemplate.convertAndSend("/topic/score", updatedScore);

    // 次のクイズを取得
    Quiz nextQuiz = quizService.getRandomQuiz();

    // 次のクイズをユーザーに送信
    messagingTemplate.convertAndSend("/topic/battle/" + userId, nextQuiz);
  }
}
