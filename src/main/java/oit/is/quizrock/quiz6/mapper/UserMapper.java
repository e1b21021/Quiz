package oit.is.quizrock.quiz6.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import oit.is.quizrock.quiz6.model.User;

@Mapper
public interface UserMapper {

  // ユーザーIDでユーザー情報を取得
  @Select("SELECT id, name, point FROM users WHERE id = #{id}")
  User selectById(int id);

  // 名前でユーザー情報を取得 (複数)
  @Select("SELECT id, name, point FROM users WHERE name = #{name}")
  ArrayList<User> selectAllByName(String name);

  // すべてのユーザー情報を取得
  @Select("SELECT id, name, point FROM users")
  ArrayList<User> selectAllUsers();

  // 名前で1件のユーザー情報を取得
  @Select("SELECT id, name, point FROM users WHERE name = #{name} LIMIT 1")
  User selectByName(String name);

  // 新しいユーザーを挿入（ID自動生成）
  @Insert("INSERT INTO users (name) VALUES (#{name})")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUser(User user);

  // ポイントを更新
  @Update("UPDATE users SET point = #{point} WHERE id = #{id}")
  void updateUserPoints(User user);
}
