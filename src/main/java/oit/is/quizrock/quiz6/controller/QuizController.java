package oit.is.quizrock.quiz6.controller;

import oit.is.quizrock.quiz6.model.Quiz;
import oit.is.quizrock.quiz6.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
