package oit.is.quizrock.quiz6.model;

public class Quiz {
  private int id;
  private String cate;
  private String subcate;
  private String quiz;
  private String ans;

  // Getters and setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCate() {
    return cate;
  }

  public void setCate(String cate) {
    this.cate = cate;
  }

  public String getSubcate() {
    return subcate;
  }

  public void setSubcate(String subcate) {
    this.subcate = subcate;
  }

  public String getQuiz() {
    return quiz;
  }

  public void setQuiz(String quiz) {
    this.quiz = quiz;
  }

  public String getAns() {
    return ans;
  }

  public void setAns(String ans) {
    this.ans = ans;
  }
}
