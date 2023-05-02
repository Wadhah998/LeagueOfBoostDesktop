package UserDashboard;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import LeagueOfBoost.LeagueOfBoostMain;

/**
 * FXML Controller class
 *
 * @author Rouaa
 */
public class UserDashboardController implements Initializable {

    @FXML
    private HBox actualiteBtn;

    @FXML
    private Circle circle;

    @FXML
    private Pane content_area;

    @FXML
    private HBox dashboardBtn;

    @FXML
    private ImageView dashboardIcon;

    @FXML
    private HBox favBtn;

    @FXML
    private ImageView favIcon;

    @FXML
    private HBox navBarLogout;

    @FXML
    private Text navFullname;

    @FXML
    private ImageView newsIcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image img = new Image("assets/img/rouaa.jpg");
        circle.setFill(new ImagePattern(img));

    }

    @FXML
    private void open_dashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        LeagueOfBoostMain.stage.getScene().setRoot(root);
    }

    @FXML
    void open_actualiteList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/NewsInterfaces/UserNewsList.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

        // set active class
        if (!actualiteBtn.getStyleClass().contains("activeLink")) {
            actualiteBtn.getStyleClass().add("activeLink");

            // Load the image
            Image image = new Image("/assets/img/news-active.png");
            newsIcon.setImage(image);

            if (dashboardBtn.getStyleClass().contains("activeLink")) {
                dashboardBtn.getStyleClass().remove("activeLink");

                Image dashIcon = new Image("/assets/img/menu.png");
                dashboardIcon.setImage(dashIcon);
            } else if (favBtn.getStyleClass().contains("activeLink")) {
                favBtn.getStyleClass().remove("activeLink");

                Image favImg = new Image("/assets/img/fav.png");
                favIcon.setImage(favImg);
            }

        }
    }

    @FXML
    void open_favList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../LeagueOfBoost/gui/NewsInterfaces/UserNewsFavoris.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

        // set active class
        if (!favBtn.getStyleClass().contains("activeLink")) {
            favBtn.getStyleClass().add("activeLink");

            // Load the image
            Image image = new Image("/assets/img/fav-active.png");
            favIcon.setImage(image);

            if (dashboardBtn.getStyleClass().contains("activeLink")) {
                dashboardBtn.getStyleClass().remove("activeLink");

                Image dashIcon = new Image("/assets/img/menu.png");
                dashboardIcon.setImage(dashIcon);
            } else if (actualiteBtn.getStyleClass().contains("activeLink")) {
                actualiteBtn.getStyleClass().remove("activeLink");

                Image newsImg = new Image("/assets/img/news.png");
                newsIcon.setImage(newsImg);
            }

        }

    }

}
