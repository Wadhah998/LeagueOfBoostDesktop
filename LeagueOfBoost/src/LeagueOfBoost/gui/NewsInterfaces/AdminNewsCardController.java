package LeagueOfBoost.gui.NewsInterfaces;

import java.io.IOException;
import java.sql.SQLException;

import LeagueOfBoost.entities.News;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import LeagueOfBoost.services.NewsService;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author Rouaa
 */
public class AdminNewsCardController {

    @FXML
    private Label date;

    @FXML
    private HBox deleteNews;

    @FXML
    private HBox editNews;

    @FXML
    private ImageView img;

    @FXML
    private HBox sendSms;

    @FXML
    private Text stockProduit;

    @FXML
    private Text stockProduit11;

    @FXML
    private Text title;

    @FXML
    private Text views;

    public void setNewsData(News news) {

        Image image = new Image(
                getClass().getResource("/assets/actualiteUploads/" +
                        news.getImg()).toExternalForm());
        img.setImage(image);

        title.setText(news.getTitre());

        date.setText(news.getDate());

        views.setText("" + news.getNb_vues());

        NewsService newsService = new NewsService();

        // deleteNews btn click
        deleteNews.setId(String.valueOf(news.getId()));

        deleteNews.setOnMouseClicked(event -> {
            System.out.println("ID du news à supprimer : " + news.getId());
            try {
                newsService.supprimer(news.getId());

            } catch (SQLException e) {
                e.printStackTrace();
            }
            // supprimer le contenu de la liste et afficher la nouvelle liste(apres
            // supprimer)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LeagueOfBoost/gui/NewsInterfaces/AdminNewsList.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis ce controller
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de AdminNewsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // end
        });
        // END deleteNews btn click

        // editNEws btn click
        editNews.setId(String.valueOf(news.getId()));

        editNews.setOnMouseClicked(event -> {
            System.out.println("ID du news à modifier : " + news.getId());
            News.setNewsId(news.getId());

            News.actionTest = 1; // pour afficher le bouton update

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LeagueOfBoost/gui/NewsInterfaces/AdminAddNews.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis ce controller
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de AddProduct.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        // // END editNews btn click

        // sendSms btn click

        sendSms.setOnMouseClicked(event -> {
            System.out.println("ID du Actualite à envoyer un sms : " + news.getId());

            HBox smsModel = (HBox) ((Node) event.getSource()).getScene().lookup("#smsModel");
            smsModel.setVisible(true);

        });
        // END sendSms btn click

    }

}
