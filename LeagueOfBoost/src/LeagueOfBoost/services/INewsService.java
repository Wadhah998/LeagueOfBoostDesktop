package LeagueOfBoost.services;

import java.sql.SQLException;
import java.util.List;

import LeagueOfBoost.entities.Comment;
import LeagueOfBoost.entities.News;

public interface INewsService {

  public List<News> getAllNews();

  public void ajouter(News news);

  public void supprimer(int id) throws SQLException;

  public News getOneNews(int id) throws SQLException;

  public void updateNews(News news);

  public List<Comment> getNewsComments(int newsId);

  public void supprimerComment(int id) throws SQLException;

  public int getTotalComment(int newsId);

  public void ajouterComment(Comment cmt);

  public void updateComment(Comment cmt);

  public List<News> searchNews(String search);

  public List<News> searchNewsByImage(String etiquette, double score);

  public void incrementNb_vues(int totalVues, int newsId);

  public List<News> sortNewsBy(int test);

  public void addNewsToFavoriteList(int newsId, int userId);

  public void removeNewsFromFavoriteList(int idNews, int userID) throws SQLException;

  public int newsInFavList(int newsID, int userId) throws SQLException;

  public List<News> getNewsFavList(int userId);

}
