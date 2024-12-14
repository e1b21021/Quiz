package oit.is.quizrock.quiz6.controller;

import oit.is.quizrock.quiz6.model.Quiz;
import oit.is.quizrock.quiz6.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {
  @Autowired
  private QuizService quizService;

  @GetMapping("/quiz")
  public String showQuiz(Model model) {
    Quiz quiz = quizService.getRandomQuiz();
    model.addAttribute("quiz", quiz);
    return "quiz"; // quiz.html テンプレートを表示
  }

  @GetMapping("/numQuestions_settings")
  public String showNumQuestionsSettings() {
    return "numQuestions_settings"; // numQuestions_settings.html を表示
  }
  @GetMapping("/genre_settings")
  public String showGenreSettings() {
    return "genre_settings"; // genre_settings.htmlを表示
  }
    // POST: フォームのデータを処理
    @PostMapping("/select")
    public String handleFormSubmission(@RequestParam String numQuestions, @RequestParam String genre, Model model) {
        // 選択した値をモデルに追加して、次の画面に渡す
        model.addAttribute("numQuestions", numQuestions);
        model.addAttribute("genre", genre);

        // 仮に waitingroom.html というテンプレートを次の画面とする
        return "waitingroom"; // 待機画面を表示
    }

  @GetMapping("/quiz/result")
  public String showResult(Model model) {
    // モデルにデータを渡す
    model.addAttribute("resultMessage", "おめでとう！正解です！");
    model.addAttribute("resultType", "success"); // 成功か失敗かを示す（"success" or "failure"）
    return "result";
  }

}
