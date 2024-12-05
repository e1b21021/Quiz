package oit.is.quizrock.quiz6.mapper;

import oit.is.quizrock.quiz6.model.Quiz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuizMapper {
  @Select("SELECT * FROM quiz ORDER BY RAND() LIMIT 1")
  Quiz findRandomQuiz();

  @Select("SELECT * FROM quiz WHERE id = #{id}")
  Quiz findQuizById(int id);
}
