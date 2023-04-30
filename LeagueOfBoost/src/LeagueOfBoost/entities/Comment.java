package LeagueOfBoost.entities;

public class Comment {
  private int id;
  private int user_id;
  private String comment;
  private String date;
  private int news_id;

  private static int cmtID;

  public Comment(int id, int user_id, String comment, String date, int news_id) {
    this.id = id;
    this.user_id = user_id;
    this.comment = comment;
    this.date = date;
    this.news_id = news_id;
  }

  public Comment() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getNews_id() {
    return news_id;
  }

  public void setNews_id(int news_id) {
    this.news_id = news_id;
  }

  @Override
  public String toString() {
    return "Comment [id=" + id + ", user_id=" + user_id + ", comment=" + comment + ", date=" + date + ", news_id="
        + news_id + "]";
  }

  public static int getCmtID() {
    return cmtID;
  }

  public static void setCmtID(int cmtID) {
    Comment.cmtID = cmtID;
  }

}
