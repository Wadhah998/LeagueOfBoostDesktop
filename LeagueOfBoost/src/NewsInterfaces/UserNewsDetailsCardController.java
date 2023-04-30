package NewsInterfaces;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import LeagueOfBoost.entities.Comment;
import LeagueOfBoost.entities.News;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Node;

import java.io.File;
import java.io.IOException;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import facebook4j.FacebookException;

import tray.animations.AnimationType;
import tray.notification.NotificationType;

import javafx.util.Duration;
import LeagueOfBoost.services.NewsService;

/**
 * FXML Controller class
 *
 * @author Rouaa
 */
public class UserNewsDetailsCardController implements Initializable {

  @FXML
  private GridPane commentsListContainer;

  @FXML
  private VBox content_area;

  @FXML
  private Text description;

  @FXML
  private ImageView favBtn;

  @FXML
  private ImageView img;

  @FXML
  private Text shortDesc;

  @FXML
  private Text title;

  @FXML
  private Text total_views;

  @FXML
  private Text totalComment;

  private int found = 0;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // set news details
    NewsService newsService = new NewsService();
    News news = new News();
    try {
      news = newsService.getOneNews(News.getNewsId());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    // System.out.println(news);

    title.setText(news.getTitre());

    shortDesc.setText(news.getShort_description());
    description.setText(news.getDescription());

    Image image = new Image(
        getClass().getResource("/assets/actualiteUploads/" +
            news.getImg()).toExternalForm());
    img.setImage(image);

    total_views.setText("" + news.getNb_vues());

    try {
      found = newsService.newsInFavList(news.getId(), 1);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    if (found == 1) {
      Image fullFavImage = new Image("/assets/img/fav.png");
      favBtn.setImage(fullFavImage);
    }

    totalComment.setText("" + newsService.getTotalComment(News.getNewsId()));
    // END - set news details

    // set comments details

    List<Comment> commentList = new ArrayList<>();

    commentList = newsService.getNewsComments(News.getNewsId());

    // Set comments List
    int CommentColumn = 0;
    int CommentRow = 1;
    try {
      for (int i = 0; i < commentList.size(); i++) {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/NewsInterfaces/UserCommentItem.fxml"));
        VBox commentItem = fxmlLoader.load();
        UserCommentItemController userCommentItemController = fxmlLoader.getController();
        userCommentItemController.setCommentData(commentList.get(i));

        if (CommentColumn == 1) {
          CommentColumn = 0;
          ++CommentRow;
        }
        commentsListContainer.add(commentItem, CommentColumn++, CommentRow);
        GridPane.setMargin(commentItem, new Insets(0, 10, 15, 10));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @FXML
  void open_add_new_comment_model(MouseEvent event) {
    HBox addCommentModel = (HBox) ((Node) event.getSource()).getScene().lookup("#addCommentModel");
    addCommentModel.setVisible(true);
  }

  @FXML
  void partageFacebook(MouseEvent event) throws SQLException {
    News news = new News();
    NewsService newsService = new NewsService();
    news = newsService.getOneNews(News.getNewsId());

    String appId = "605544598295425";
    String appSecret = "bb9b574fee9809b487204083d1d98a45";
    String accessTokenString = "EAAImvVatU4EBANhCaSS6Ukg3ZC1JRauWbBvO37e8tSNZBrdR5favtW3ZCcK4V5zsd6aFZBtYLaiftU5ZCoeoNL5mUbsSA1EkOOJbfQx1oVbPBHACOhc6kOSOCUKKDpvfyHLNNeoVHzqIhqZAcjhYgRNiiduom9EILMchSOJbzm0cm1HzfY3M4ZCbTVnjl5M0cgbquyZCZBKQ0okYJaLWbnu1JJII8uYovZBIAZD";

    Facebook facebook = new FacebookFactory().getInstance();
    facebook.setOAuthAppId(appId, appSecret);
    facebook.setOAuthAccessToken(new AccessToken(accessTokenString, null));

    String msg = "A new actualite is available on league"
        + "\n***Article Name: " + news.getTitre()
        + "\n***Article short description " + news.getShort_description()
        + "\n***Article Description: " + news.getDescription()
        + "\n***article date: " + news.getDate();
    try {
      facebook.postStatusMessage(msg);
      System.out.println("partage avec succes");

    } catch (FacebookException e) {
      e.printStackTrace();
      System.out.println("badel token");
    }

  }

  @FXML
  void addToFavoriteList(MouseEvent event) throws SQLException {
    // set product details
    NewsService newsService = new NewsService();
    News news = new News();
    try {
      news = newsService.getOneNews(News.getNewsId());
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      found = newsService.newsInFavList(news.getId(), 1); // 1 c'est pour le user id
    } catch (SQLException e) {
      e.printStackTrace();
    }

    if (found == 0) {
      newsService.addNewsToFavoriteList(News.getNewsId(), 1);

      Image fullFavImage = new Image("/assets/img/fav.png");
      favBtn.setImage(fullFavImage);

    }
    if (found == 1) {

      newsService.removeNewsFromFavoriteList(News.getNewsId(), 1);

      Image FavImage = new Image("/assets/img/addFav.png");
      favBtn.setImage(FavImage);

    }

  }

}
