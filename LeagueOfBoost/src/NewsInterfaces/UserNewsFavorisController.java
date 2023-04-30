package NewsInterfaces;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import LeagueOfBoost.entities.News;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import LeagueOfBoost.services.NewsService;

/**
 * FXML Controller class
 *
 * @author Rouaa
 */
public class UserNewsFavorisController implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private GridPane newsListContainer;

    @FXML
    private ScrollPane scrollPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        NewsService newsService = new NewsService();
        List<News> news = new ArrayList<>();

        news = newsService.getNewsFavList(1);
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < news.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/NewsInterfaces/UserNewsCard.fxml"));
                VBox userCard = fxmlLoader.load();
                UserNewsCardController newsCardController = fxmlLoader.getController();
                newsCardController.setNewsData(news.get(i));

                if (column == 3) {
                    column = 0;
                    ++row;
                }
                newsListContainer.add(userCard, column++, row);
                // GridPane.setMargin(userCard, new Insets(10));
                GridPane.setMargin(userCard, new Insets(0, 40, 35, 40));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
