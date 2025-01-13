package oit.is.quizrock.quiz6.service;

import oit.is.quizrock.quiz6.mapper.QuizMapper;
import oit.is.quizrock.quiz6.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

  @Autowired
  private QuizMapper quizMapper;

  // ランダムなクイズを取得
  public Quiz getRandomQuiz() {
    return quizMapper.findRandomQuiz();
  }

  // クイズIDでクイズを取得
  public Quiz findQuizById(int quizId) {
    return quizMapper.findQuizById(quizId);
  }
}
