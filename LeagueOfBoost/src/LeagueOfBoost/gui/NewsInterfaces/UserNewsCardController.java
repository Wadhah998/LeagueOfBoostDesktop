package LeagueOfBoost.gui.NewsInterfaces;

import java.io.IOException;
import java.sql.SQLException;

import LeagueOfBoost.entities.News;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import LeagueOfBoost.services.NewsService;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author Rouaa
 */
public class UserNewsCardController {

  @FXML
  private Text date;

  @FXML
  private Text description;

  @FXML
  private ImageView img;

  @FXML
  private Text nbViews;

  @FXML
  private HBox openNewsDetails;

  @FXML
  private HBox priceWithoutOffer;

  @FXML
  private Text titre;

  public void setNewsData(News news) {

    Image image = new Image(
        getClass().getResource("../../../assets/actualiteUploads/" +
            news.getImg()).toExternalForm());
    img.setImage(image);

    titre.setText(news.getTitre());

    description.setText(news.getShort_description());
    date.setText(news.getDate());
    nbViews.setText("" + news.getNb_vues());

    // Open NEws Details and increment the nb total vues
    openNewsDetails.setOnMouseClicked(event -> {

      // System.out.println("ID du news à afficher les details : " +
      NewsService newsService = new NewsService();
      newsService.incrementNb_vues(news.getNb_vues() + 1, news.getId());
      News.setNewsId(news.getId());

      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/LeagueOfBoost/gui/NewsInterfaces/UserNewsDetails.fxml"));
      try {
        Parent root = loader.load();
        // Accéder à la pane content_area depuis ce controller
        Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

        // Vider la pane et afficher le contenu de AddNEws.fxml
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    // END - Open NEws Details
  }

}
