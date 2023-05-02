package LeagueOfBoost.gui.NewsInterfaces;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import LeagueOfBoost.services.NewsService;
import javafx.scene.Node;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.*;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.ByteString;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 * FXML Controller class
 *
 * @author Rouaa
 */
public class UserNewsListController implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private ComboBox<String> filterDateInput;

    @FXML
    private GridPane newsListContainer;

    @FXML
    private TextField newsSearchInput;

    @FXML
    private ComboBox<String> sortDateInput;

    private String selectedOption = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.setNewsGridPaneList();

        // set combobox values
        sortDateInput.getItems().add("Date");
        sortDateInput.getItems().add("Views");
        sortDateInput.getItems().add("Title");
        // END set combobox values
        // filter by combobox values
        sortDateInput.setOnAction(event -> {
            selectedOption = sortDateInput.getValue();
            News.setSearchValue(null);
            System.out.println("Selected option: " + selectedOption);

            // System.out.println("Selected value: " + categId);
            GridPane newsListContainer = (GridPane) content_area.lookup("#newsListContainer");
            newsListContainer.getChildren().clear();
            this.setNewsGridPaneList();
        });
        // END filter by combobox values

    }

    private void setNewsGridPaneList() {

        NewsService newsService = new NewsService();

        List<News> newsList = new ArrayList<>();

        System.out.println("searchValue" + News.getSearchValue());
        if (News.getSearchValue() == null) {
            if (News.getSearchImageScore() != -1) {
                // search by image
                newsList = newsService.searchNewsByImage(News.getSearchImageEtiquette(),
                        News.getSearchImageScore());
                News.setSearchImageScore(-1);
            } else if (selectedOption != null) {
                if (selectedOption.equals("Date")) {
                    newsList = newsService.sortNewsBy(1);
                    selectedOption = null;
                } else if (selectedOption.equals("Views")) {
                    newsList = newsService.sortNewsBy(2);
                    selectedOption = null;
                } else
                // if (selectedOption.equals("Title"))
                {
                    newsList = newsService.sortNewsBy(3);
                    selectedOption = null;
                }

            } else {
                newsList = newsService.getAllNews();
            }
        } else {
            newsList = newsService.searchNews(News.getSearchValue());
            News.setSearchValue(null);

        }

        // news list ------------------------------------------------
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < newsList.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/LeagueOfBoost/gui/NewsInterfaces/UserNewsCard.fxml"));
                VBox oneNewsCard = fxmlLoader.load();
                UserNewsCardController newsCardController = fxmlLoader.getController();
                newsCardController.setNewsData(newsList.get(i));

                if (column == 3) {
                    column = 0;
                    ++row;
                }
                newsListContainer.add(oneNewsCard, column++, row);
                // GridPane.setMargin(oneNewsCard, new Insets(10));
                GridPane.setMargin(oneNewsCard, new Insets(0, 40, 35, 40));
                // oneNewsCard.setStyle("-fx-effect: dropshadow(three-pass-box,
                // rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchNews(KeyEvent event) throws IOException {
        News.setSearchValue(((TextField) event.getSource()).getText());
        System.out.println("Recherche en cours: " + News.getSearchValue());

        GridPane productsListContainer = (GridPane) content_area.lookup("#newsListContainer");
        productsListContainer.getChildren().clear();
        this.setNewsGridPaneList();
    }

    @FXML
    void SearchByImage(MouseEvent event) {
        System.out.println("annotation.getName()");
        ImageAnnotatorClient client;

        try {
            // Load the credentials file
            GoogleCredentials credentials = GoogleCredentials
                    .fromStream(new FileInputStream("src/LeagueOfBoost/utils/google_cloud_credentials.json"));
           

            // Build the ImageAnnotatorSettings object with the credentials provider
            ImageAnnotatorSettings settings = ImageAnnotatorSettings.newBuilder()
                    .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                    .build();

            // Create the client with the settings
            client = ImageAnnotatorClient.create(settings);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Créer un nouveau FileChooser
        FileChooser fileChooser = new FileChooser();

        // Configurer le FileChooser pour n'afficher que les fichiers d'image
        fileChooser.setTitle("Sélectionnez une image");
        fileChooser.getExtensionFilters()
                .addAll(new ExtensionFilter("Fichiers d'image", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        // Afficher la boîte de dialogue de sélection de fichier et attendre la
        // sélection de l'utilisateur
        File selectedFile = fileChooser.showOpenDialog(null);

        // Vérifier si un fichier a été sélectionné
        if (selectedFile != null) {
            // Charger le fichier et exécuter le code d'annotation

            // Annotate the image
            try {
                // Load the image file
                byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());

                ByteString byteString = ByteString.copyFrom(imageBytes);

                // Create the image object
                com.google.cloud.vision.v1.Image image = com.google.cloud.vision.v1.Image.newBuilder()
                        .setContent(byteString).build();

                // Create the feature object
                Feature feature = Feature.newBuilder().setType(Feature.Type.OBJECT_LOCALIZATION).build();

                // Create the request object
                AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feature).setImage(image)
                        .build();

                // Send the request and get the response
                BatchAnnotateImagesResponse response = client.batchAnnotateImages(ImmutableList.of(request));

                // Extract labels and objects from the response
                List<LocalizedObjectAnnotation> objectAnnotations = response.getResponses(0)
                        .getLocalizedObjectAnnotationsList();
                News.setSearchImageEtiquette(objectAnnotations.get(0).getName());
                News.setSearchImageScore(objectAnnotations.get(0).getScore());
                System.out.println(objectAnnotations.get(0).getName() + " " + objectAnnotations.get(0).getScore());

                Parent fxml;
                try {
                    fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/NewsInterfaces/UserNewsList.fxml"));
                    content_area.getChildren().removeAll();
                    content_area.getChildren().setAll(fxml);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // for (LocalizedObjectAnnotation annotation : objectAnnotations) {
                // System.out.println(annotation.getName() + " " + annotation.getScore());
                // }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Shut down the client
                client.shutdown();
            }
        }
    }

}