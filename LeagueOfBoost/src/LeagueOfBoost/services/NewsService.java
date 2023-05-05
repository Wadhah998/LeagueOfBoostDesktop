package LeagueOfBoost.services;

import LeagueOfBoost.entities.Comment;
import LeagueOfBoost.entities.News;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import LeagueOfBoost.gui.user.InscriptionController;
import LeagueOfBoost.utils.MyDB;

public class NewsService implements INewsService {

  public Connection conx;
  public Statement stm;

  public NewsService() {
    conx =  MyDB.createorgetInstance().getCon();
  }

  @Override
  public List<News> getAllNews() {
    List<News> newsList = new ArrayList<>();
    try {
      String query = "SELECT * FROM Actualite ORDER BY id DESC ";
      PreparedStatement preparedStatement = conx.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();

      // Parcours du résultat de la requête
      while (resultSet.next()) {
        News news = new News();
        news.setId(resultSet.getInt("id"));
        news.setTitre(resultSet.getString("Titre"));
        news.setDescription(resultSet.getString("Description"));
        news.setShort_description(resultSet.getString("short_description"));
        news.setImg(resultSet.getString("img"));
        news.setDate(resultSet.getString("date"));
        news.setNb_vues(resultSet.getInt("nb_vues"));

        newsList.add(news);
      }
      preparedStatement.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return newsList;

  }

  @Override
  public void supprimer(int id) throws SQLException {
    String sql = "DELETE FROM Actualite WHERE id = ?";
    try (PreparedStatement pstmt = conx.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public News getOneNews(int id) throws SQLException {
    String req = "SELECT * FROM `Actualite` where id = ?";
    PreparedStatement ps = conx.prepareStatement(req);
    ps.setInt(1, id);

    ResultSet resultSet = ps.executeQuery();
    News news = new News();

    while (resultSet.next()) {

      news.setId(resultSet.getInt("id"));
      news.setTitre(resultSet.getString("Titre"));
      news.setDescription(resultSet.getString("Description"));
      news.setShort_description(resultSet.getString("short_description"));
      news.setImg(resultSet.getString("img"));
      news.setDate(resultSet.getString("date"));
      news.setNb_vues(resultSet.getInt("nb_vues"));
    }
    ps.close();
    return news;
  }

  public void ajouter(News news) {
    try {
      String req = "INSERT INTO `Actualite`(`Titre`, `description`, `short_description`, `img`, `etiquette`, `score`  ) VALUES (?,?,?,?,?,?)";
      PreparedStatement ps = conx.prepareStatement(req);
      ps.setString(1, news.getTitre());
      ps.setString(2, news.getDescription());
      ps.setString(3, news.getShort_description());
      ps.setString(4, news.getImg());
      ps.setString(5, news.getEtiquette());
      ps.setDouble(6, news.getScore());
      ps.executeUpdate();
      System.out.println("News added successfully");
      ps.close();
    } catch (SQLException e) {
      System.out.println("Une erreur s'est produite lors de la récupération du news : " + e.getMessage());
    }

  }

  public void updateNews(News news) {
    try {
      String req = "UPDATE `Actualite` SET `Titre`=?,`description`=?,`short_description`=?,`img`=? , `etiquette`=?, `score`=? WHERE id=?";
      PreparedStatement ps = conx.prepareStatement(req);
      ps.setString(1, news.getTitre());
      ps.setString(2, news.getDescription());
      ps.setString(3, news.getShort_description());
      ps.setString(4, news.getImg());
      ps.setString(5, news.getEtiquette());
      ps.setDouble(6, news.getScore());
      ps.setInt(7, news.getId());
      ps.executeUpdate();
      System.out.println("News updated successfully");
      ps.close();
    } catch (SQLException e) {
      System.out.println("Une erreur s'est produite lors de la récupération du news : " + e.getMessage());
    }

  }

  public List<Comment> getNewsComments(int newsId) {
    List<Comment> commentList = new ArrayList<>();
    try {
      String query = "SELECT * FROM commentaire where news_id=? ORDER BY id DESC ";
      PreparedStatement preparedStatement = conx.prepareStatement(query);
      preparedStatement.setInt(1, newsId);

      ResultSet resultSet = preparedStatement.executeQuery();

      // Parcours du résultat de la requête
      while (resultSet.next()) {
        Comment comment = new Comment();
        comment.setId(resultSet.getInt("id"));
        comment.setComment(resultSet.getString("comment"));
        comment.setUser_id(resultSet.getInt("user_id"));
        comment.setDate(resultSet.getString("date"));

        commentList.add(comment);
      }
      preparedStatement.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return commentList;

  }

  public void supprimerComment(int id) throws SQLException {
    String sql = "DELETE FROM commentaire WHERE id = ?";
    try (PreparedStatement pstmt = conx.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public int getTotalComment(int newsId) {
    int total = 0;
    try {
      String query = "SELECT count(*) FROM commentaire n WHERE news_id =? ";
      PreparedStatement preparedStatement = conx.prepareStatement(query);
      preparedStatement.setInt(1, newsId);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        total = resultSet.getInt(1);
      }
      preparedStatement.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return total;
  }

  public void ajouterComment(Comment cmt) {
    try {
      String req = "INSERT INTO `commentaire`(`comment`, `user_id`, `news_id` ) VALUES (?,?,?)";
      PreparedStatement ps = conx.prepareStatement(req);
      ps.setString(1, cmt.getComment());
      ps.setInt(2, InscriptionController.userc.getId());
      ps.setInt(3, cmt.getNews_id());
      ps.executeUpdate();
      System.out.println("Comment added successfully");
      ps.close();
    } catch (SQLException e) {
      System.out.println("Une erreur s'est produite lors de la récupération du comment : " + e.getMessage());
    }

  }

  public void updateComment(Comment cmt) {
    try {
      String req = "UPDATE `commentaire` SET `comment`=? WHERE id=?";
      PreparedStatement ps = conx.prepareStatement(req);
      ps.setString(1, cmt.getComment());
      ps.setInt(2, cmt.getId());
      ps.executeUpdate();
      System.out.println("Comment updated successfully");
      ps.close();
    } catch (SQLException e) {
      System.out.println("Une erreur s'est produite lors de la récupération du comment : " + e.getMessage());
    }

  }

  public List<News> searchNews(String search) {
    List<News> newsList = new ArrayList<>();
    try {
      String query = "SELECT * FROM Actualite WHERE Titre LIKE ? OR description LIKE ? OR short_description LIKE ?";
      PreparedStatement preparedStatement = conx.prepareStatement(query);
      preparedStatement.setString(1, "%" + search + "%");
      preparedStatement.setString(2, "%" + search + "%");
      preparedStatement.setString(3, "%" + search + "%");
      ResultSet resultSet = preparedStatement.executeQuery();

      // Parcours du résultat de la requête
      while (resultSet.next()) {
        News news = new News();
        news.setId(resultSet.getInt("id"));
        news.setTitre(resultSet.getString("Titre"));
        news.setDescription(resultSet.getString("Description"));
        news.setShort_description(resultSet.getString("short_description"));
        news.setImg(resultSet.getString("img"));
        news.setDate(resultSet.getString("date"));
        news.setNb_vues(resultSet.getInt("nb_vues"));

        newsList.add(news);
      }
      preparedStatement.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return newsList;
  }

  public List<News> searchNewsByImage(String etiquette, double score) {
    List<News> newsList = new ArrayList<>();
    try {
      String query = "SELECT * FROM Actualite WHERE etiquette=? and score=? ORDER BY id DESC ";
      PreparedStatement preparedStatement = conx.prepareStatement(query);
      preparedStatement.setString(1, etiquette);
      preparedStatement.setDouble(2, score);
      ResultSet resultSet = preparedStatement.executeQuery();

      // Parcours du résultat de la requête
      while (resultSet.next()) {
        News news = new News();
        news.setId(resultSet.getInt("id"));
        news.setTitre(resultSet.getString("Titre"));
        news.setDescription(resultSet.getString("Description"));
        news.setShort_description(resultSet.getString("short_description"));
        news.setImg(resultSet.getString("img"));
        news.setDate(resultSet.getString("date"));
        news.setNb_vues(resultSet.getInt("nb_vues"));

        newsList.add(news);
      }
      preparedStatement.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return newsList;

  }

  public void incrementNb_vues(int totalVues, int newsId) {
    try {
      String req = "UPDATE `Actualite` SET `nb_vues`=? WHERE id=?";
      PreparedStatement ps = conx.prepareStatement(req);
      ps.setInt(1, totalVues);
      ps.setInt(2, newsId);
      ps.executeUpdate();
      System.out.println("actualite updated successfully");
      ps.close();
    } catch (SQLException e) {
      System.out.println("Une erreur s'est produite lors de la récupération du news : " + e.getMessage());
    }

  }

  public List<News> sortNewsBy(int test) {
    List<News> newsList = new ArrayList<>();
    try {
      String query = null;
      if (test == 1) { // sort by date
        query = "SELECT * FROM Actualite ORDER BY date DESC ";
      }

      if (test == 2) {// sort by views
        query = "SELECT * FROM Actualite ORDER BY nb_vues DESC ";
      }

      if (test == 3) { // sort by title
        query = "SELECT * FROM Actualite ORDER BY Titre DESC ";
      }
      PreparedStatement preparedStatement = conx.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();

      // Parcours du résultat de la requête
      while (resultSet.next()) {
        News news = new News();
        news.setId(resultSet.getInt("id"));
        news.setTitre(resultSet.getString("Titre"));
        news.setDescription(resultSet.getString("Description"));
        news.setShort_description(resultSet.getString("short_description"));
        news.setImg(resultSet.getString("img"));
        news.setDate(resultSet.getString("date"));
        news.setNb_vues(resultSet.getInt("nb_vues"));

        newsList.add(news);
      }
      preparedStatement.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return newsList;

  }

  public void addNewsToFavoriteList(int newsId, int userId) {
    try {
      String req = "INSERT INTO `actualite_favoris`(`user_id`, `actualite_id` ) VALUES (?,?)";
      PreparedStatement ps = conx.prepareStatement(req);
      ps.setInt(1, InscriptionController.userc.getId());
      ps.setInt(2, newsId);
      ps.executeUpdate();
      System.out.println("News added to fav list successfully");
      ps.close();
    } catch (SQLException e) {
      System.out.println("Une erreur s'est produite lors de l'ajout' du news au fav list : " + e.getMessage());
    }

  }

  public void removeNewsFromFavoriteList(int idNews, int userID) throws SQLException {
    String sql = "DELETE FROM actualite_favoris WHERE actualite_id = ? and user_id=?";
    try (PreparedStatement pstmt = conx.prepareStatement(sql)) {
      pstmt.setInt(1, idNews);
      pstmt.setInt(2, userID);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public int newsInFavList(int newsID, int userId) throws SQLException {
    String req = "SELECT * FROM `actualite_favoris` where actualite_id = ? and user_id=? ";
    PreparedStatement ps = conx.prepareStatement(req);
    ps.setInt(1, newsID);
    ps.setInt(2, userId);

    ResultSet rs = ps.executeQuery();
    int found = 0;
    if (rs.next()) {
      found = 1;
    }
    ps.close();
    return found;
  }

  public List<News> getNewsFavList(int userId) {
    List<News> newsList = new ArrayList<>();
    try {
      String query = "SELECT * FROM Actualite p JOIN actualite_favoris f ON p.id = f.actualite_id WHERE f.user_id=? ";
      PreparedStatement preparedStatement = conx.prepareStatement(query);
      preparedStatement.setInt(1, userId);
      ResultSet resultSet = preparedStatement.executeQuery();

      // Parcours du résultat de la requête
      while (resultSet.next()) {
        News news = new News();
        news.setId(resultSet.getInt("id"));
        news.setTitre(resultSet.getString("Titre"));
        news.setDescription(resultSet.getString("Description"));
        news.setShort_description(resultSet.getString("short_description"));
        news.setImg(resultSet.getString("img"));
        news.setDate(resultSet.getString("date"));
        news.setNb_vues(resultSet.getInt("nb_vues"));

        newsList.add(news);
      }
      preparedStatement.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return newsList;

  }

}
