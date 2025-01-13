package oit.is.quizrock.quiz6.model;

public class User {
  private int id;
  private String name;
  private int point; // スコア

  // Getter
  public int getPoint() {
    return point;
  }

  // Setter
  public void setPoint(int point) {
    this.point = point;
  }

  // 他のフィールド (id, name) も同様に Getter/Setter を定義
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
