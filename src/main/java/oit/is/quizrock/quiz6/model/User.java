package oit.is.quizrock.quiz6.model;

public class User {
  private int id; // ユーザーID
  private String name; // ユーザー名
  private int point; // ポイント

  // ユーザーIDのgetter/setter
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  // ユーザー名のgetter/setter
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // ポイントのgetter/setter
  public int getPoint() {
    return point;
  }

  public void setPoint(int point) {
    this.point = point;
  }
}
