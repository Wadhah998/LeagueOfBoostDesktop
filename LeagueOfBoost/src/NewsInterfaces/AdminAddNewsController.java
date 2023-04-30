package NewsInterfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Rouaa
 */
public class AdminAddNewsController implements Initializable {
    @FXML
    private GridPane AddNewsContainer;

    @FXML
    private Pane content_area;

    @FXML
    private ScrollPane scrollPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            // Ajouter newsContainerCard à la première ligne de AddnewsContainer
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/NewsInterfaces/AdminAddNewsCard.fxml"));
            VBox newsContainer1 = fxmlLoader1.load();
            AddNewsContainer.add(newsContainer1, 0, 1);
            GridPane.setMargin(newsContainer1, new Insets(0, 10, 25, 10));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
