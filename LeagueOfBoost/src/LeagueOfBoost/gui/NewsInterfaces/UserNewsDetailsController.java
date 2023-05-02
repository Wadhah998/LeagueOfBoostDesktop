package LeagueOfBoost.gui.NewsInterfaces;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

import LeagueOfBoost.entities.Comment;
import LeagueOfBoost.entities.News;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
import LeagueOfBoost.services.NewsService;
import javafx.scene.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import facebook4j.FacebookException;

import tray.animations.AnimationType;
import tray.notification.NotificationType;

import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Rouaa
 */
public class UserNewsDetailsController implements Initializable {
  @FXML
  private HBox addCommentModel;

  @FXML
  private TextArea commentInput;

  @FXML
  private Pane content_area;

  @FXML
  private GridPane newsDetailsContainer;

  @FXML
  private HBox submitBtn;

  @FXML
  private HBox updateBtn;

  @FXML
  private HBox updateBtnContainer;

  @FXML
  private HBox badWordDetectedHbox;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    addCommentModel.setVisible(false);
    updateBtnContainer.setVisible(false);
    badWordDetectedHbox.setVisible(false);

    try {

      // Ajouter productContainer à la première ligne de AddproductContainer
      FXMLLoader fxmlLoader1 = new FXMLLoader();
      fxmlLoader1.setLocation(getClass().getResource("/LeagueOfBoost/gui/NewsInterfaces/UserNewsDetailsCard.fxml"));
      VBox productContainer1 = fxmlLoader1.load();
      newsDetailsContainer.add(productContainer1, 0, 1);
      GridPane.setMargin(productContainer1, new Insets(0, 10, 25, 10));
      // productContainer1.setStyle("-fx-effect: dropshadow(three-pass-box,
      // rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @FXML
  void close_commentModel(MouseEvent event) {
    addCommentModel.setVisible(false);
  }

  @FXML
  void addNewComment(MouseEvent event) {
    Comment cmt = new Comment();
    cmt.setNews_id(News.getNewsId());
    cmt.setUser_id(1);
    cmt.setComment(commentInput.getText());

    NewsService ns = new NewsService();

    String comment = commentInput.getText();
    if (containsBadWords(comment)) {
      // Le commentaire contient des mots inappropriés, ne pas l'ajouter à la review
      // Afficher un message à l'utilisateur pour lui demander de modifier son
      // commentaire
      badWordDetectedHbox.setVisible(true);
      return;
    } else {
      cmt.setComment(comment);
      badWordDetectedHbox.setVisible(false);

    }
    ns.ajouterComment(cmt);

    // supprimer le contenu de la liste et afficher la nouvelle liste
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/LeagueOfBoost/gui/NewsInterfaces/UserNewsDetails.fxml"));
    try {
      Parent root = loader.load();
      // Accéder à la pane content_area depuis ce controller
      Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

      // Vider la pane et afficher le contenu de newsDetail
      contentArea.getChildren().clear();
      contentArea.getChildren().add(root);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // end

  }

  public boolean containsBadWords(String comment) {
    try {
      File file = new File("src/LeagueOfBoost/utils/badwords.txt");
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String badWord = scanner.nextLine();
        if (comment.toLowerCase().contains(badWord.toLowerCase())) {
          scanner.close();
          return true;
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return false;
  }

  @FXML
  void updateComment(MouseEvent event) {
    Comment cmt = new Comment();

    cmt.setComment(commentInput.getText());
    cmt.setId(Comment.getCmtID());

    NewsService ns = new NewsService();

    String comment = commentInput.getText();
    if (containsBadWords(comment)) {
      // Le commentaire contient des mots inappropriés, ne pas l'ajouter à la review
      // Afficher un message à l'utilisateur pour lui demander de modifier son
      // commentaire
      badWordDetectedHbox.setVisible(true);
      return;
    } else {
      cmt.setComment(comment);
      badWordDetectedHbox.setVisible(false);

    }
    ns.updateComment(cmt);

    // supprimer le contenu de la liste et afficher la nouvelle liste
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/LeagueOfBoost/gui/NewsInterfaces/UserNewsDetails.fxml"));
    try {
      Parent root = loader.load();
      // Accéder à la pane content_area depuis ce controller
      Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

      // Vider la pane et afficher le contenu de newsDetail
      contentArea.getChildren().clear();
      contentArea.getChildren().add(root);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // end

  }

}
