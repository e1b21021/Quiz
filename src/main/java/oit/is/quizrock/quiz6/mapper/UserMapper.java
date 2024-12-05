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

  // idを指定して1つのユーザ情報を取得する
  @Select("SELECT id, name FROM users WHERE id = #{id}")
  User selectById(int id);

  // nameを指定してすべてのユーザ情報を取得する
  @Select("SELECT id, name FROM users WHERE name = #{name}")
  ArrayList<User> selectAllByName(String name);

  // 新しいユーザを挿入する（自動生成されたIDを取得）
  @Insert("INSERT INTO users (name) VALUES (#{name});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUser(User user);

  // すべてのユーザを取得する
  @Select("SELECT * FROM users")
  ArrayList<User> selectAllUsers();

  // 名前を指定して1つのユーザ情報を取得する
  @Select("SELECT id, name FROM users WHERE name = #{name} LIMIT 1")
  User selectByName(String name);

  @Select("SELECT * FROM users WHERE name = #{name}")
  User selectByName2(String name);

  @Update("UPDATE users SET point = #{point} WHERE id = #{id}")
  void updateUser(User user);

}
