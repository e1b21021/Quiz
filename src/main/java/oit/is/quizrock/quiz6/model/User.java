package oit.is.quizrock.quiz6.model;

public class User {
  int id;
  String name; // DBのカラム名に合わせる

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
}
