package AdminDashboard;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import LeagueOfBoost.LeagueOfBoostMain;

/**
 * FXML Controller class
 *
 * @author Rouaa
 */
public class AdminDashboardController implements Initializable {

  @FXML
  private Circle circle;

  @FXML
  private Pane content_area;

  @FXML
  private HBox dashboardBtn;

  @FXML
  private ImageView dashboardIcon;

  @FXML
  private Label dashboardText;

  @FXML
  private HBox navBarLogout;

  @FXML
  private Text navFullname;

  @FXML
  private HBox newsBtn;

  @FXML
  private ImageView newsIcon;

  @FXML
  private Label newsText;

  @FXML
  private HBox sideBarLogout;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

  }

  @FXML
  private void open_dashboard(MouseEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
    LeagueOfBoostMain.stage.getScene().setRoot(root);
  }

  @FXML
  private void open_newsList(MouseEvent event) throws IOException {
    Parent fxml = FXMLLoader.load(getClass().getResource("/NewsInterfaces/AdminNewsList.fxml"));
    content_area.getChildren().removeAll();
    content_area.getChildren().setAll(fxml);

    // set active class
    if (!newsBtn.getStyleClass().contains("activeLink")) {
      newsBtn.getStyleClass().add("activeLink");
      newsText.getStyleClass().add("activeText");

      // Load the image
      Image image = new Image("/assets/img/news-active.png");
      newsIcon.setImage(image);

      if (dashboardBtn.getStyleClass().contains("activeLink")) {
        dashboardBtn.getStyleClass().remove("activeLink");
        dashboardText.getStyleClass().remove("activeText");

        Image dashIcon = new Image("/assets/img/menu.png");
        dashboardIcon.setImage(dashIcon);
      }
    }
  }

}
