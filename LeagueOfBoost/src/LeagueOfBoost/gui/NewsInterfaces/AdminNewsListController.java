package LeagueOfBoost.gui.NewsInterfaces;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.mail.MessagingException;

import LeagueOfBoost.entities.News;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import LeagueOfBoost.services.NewsService;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import javafx.util.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * FXML Controller class
 *
 * @author Rouaa
 */
public class AdminNewsListController implements Initializable {

  @FXML
  private Pane content_area;

  @FXML
  private ComboBox<String> dateInput;

  @FXML
  private GridPane newsListContainer;

  @FXML
  private TextField newsSearchInput;

  @FXML
  private HBox smsModel;

  @FXML
  private TextField phoneInput;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

    smsModel.setVisible(false);

    // set the news list in the gridpane***************************************
    this.setNewsGridPaneList();

  }

  @FXML
  private void open_addNews(MouseEvent event) throws IOException {
    News.actionTest = 0;
    Parent fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/NewsInterfaces/AdminAddNews.fxml"));
    content_area.getChildren().removeAll();
    content_area.getChildren().setAll(fxml);
    System.out.println("test");

  }

  private void setNewsGridPaneList() {

    News news = new News();
    NewsService newsService = new NewsService();

    List<News> newsList = new ArrayList<>();
    newsList = newsService.getAllNews();
    // Afficher les news dans la console (juste pour tester)

    // System.out.println("Liste des news:");
    // for (News n : newsList) {
    // System.out.println(n);
    // }

    // news list ------------------------------------------------
    int column = 0;
    int row = 1;
    try {
      for (int i = 0; i < newsList.size(); i++) {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/LeagueOfBoost/gui/NewsInterfaces/AdminNewsCard.fxml"));
        HBox newsCard = fxmlLoader.load();
        AdminNewsCardController newsCardController = fxmlLoader.getController();
        newsCardController.setNewsData(newsList.get(i));

        if (column == 1) {
          column = 0;
          ++row;
        }
        newsListContainer.add(newsCard, column++, row);
        // GridPane.setMargin(newsCard, new Insets(10));
        GridPane.setMargin(newsCard, new Insets(0, 10, 25, 10));

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void sendSms(MouseEvent event) {
    // Initialisation de la bibliothèque Twilio avec les informations de votre
    // compte
    String ACCOUNT_SID = "ACa7de4c1ee1041b6c06ab8ad216849813";
    String AUTH_TOKEN = "2630c9d29ebda4a679e821d4e0f1e538";

    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    String recipientNumber = "+216" + phoneInput.getText();
    String message = "\nDear Chiboub Rouaa,\nA new publication has been released.\n We wanted to make sure you are aware of this update,\n as it may be of interest to you.\nPlease feel free to visit our website or application or social media channels to access the latest publication.\n We hope you find it informative and engaging.\nThank you for your continued interest in our content.\nBest regards,\nLeague Of Boost";

    Message twilioMessage = Message.creator(
        new PhoneNumber(recipientNumber),
        new PhoneNumber("+16206477886"),
        message)
        .create();

    System.out.println("SMS envoyé : " + twilioMessage.getSid());

    smsModel.setVisible(false);
    phoneInput.clear();

  }

  @FXML
  void close_smsModel(MouseEvent event) {
    smsModel.setVisible(false);
  }

}
