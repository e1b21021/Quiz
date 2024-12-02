package oit.is.quizrock.quiz6.model;

public class User {
  int id;
  String name;
  int point;

  // Thymeleafでフィールドを扱うためにはgetter/setterが必ず必要
  public String getName() { // フィールド名をDBに合わせて変更
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getpoint() {
    return point;
  }

  public void setpoint(int point) {
    this.point = point;
  }
}
