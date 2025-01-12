package oit.is.quizrock.quiz6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oit.is.quizrock.quiz6.mapper.UserMapper;
import oit.is.quizrock.quiz6.model.User;

@Service
public class UserService {

  @Autowired
  UserMapper userMapper;

  /**
   * 指定したユーザーのスコアを加算するメソッド
   *
   * @param userId ユーザーID
   * @param points 加算するスコア
   */
  public void addScore(int userId, int points) {
    User user = userMapper.selectById(userId);
    if (user != null) {
      int newScore = user.getPoint() + points;
      user.setPoint(newScore);
      userMapper.updateScore(userId, newScore); // データベースを更新
    }
  }

  /**
   * 指定したユーザーのスコアを取得するメソッド
   *
   * @param userId ユーザーID
   * @return ユーザーのスコア
   */
  public int getScore(int userId) {
    User user = userMapper.selectById(userId);
    return user != null ? user.getPoint() : 0; // ユーザーが見つからなければスコア0を返す
  }
}
