package oit.is.quizrock.quiz6.mapper;

import oit.is.quizrock.quiz6.model.Quiz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuizMapper {

  // ランダムなクイズを1問取得
  @Select("SELECT * FROM quiz ORDER BY RAND() LIMIT 1")
  Quiz findRandomQuiz();

  // クイズIDで特定のクイズを取得
  @Select("SELECT * FROM quiz WHERE id = #{quizId}")
  Quiz findQuizById(int quizId);
}
