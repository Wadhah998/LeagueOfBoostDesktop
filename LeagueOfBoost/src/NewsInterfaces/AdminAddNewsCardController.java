package NewsInterfaces;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import LeagueOfBoost.entities.News;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import LeagueOfBoost.services.INewsService;
import LeagueOfBoost.services.NewsService;
import javafx.scene.Node;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.*;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.ByteString;
import java.io.FileInputStream;
import java.nio.file.Files;

/**
 * FXML Controller class
 *
 * @author Rouaa
 */
public class AdminAddNewsCardController implements Initializable {

    @FXML
    private Button add_new_newsBtn;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private TextArea descriptionInput;

    @FXML
    private Text descriptionInputError;

    @FXML
    private HBox descriptionInputErrorHbox;

    @FXML
    private ImageView imageInput;

    @FXML
    private Text photoInputError;

    @FXML
    private HBox photoInputErrorHbox;

    @FXML
    private Text productName;

    @FXML
    private TextField shortDescInput;

    @FXML
    private Text shortDescInputError;

    @FXML
    private HBox shortDescInputErrorHbox;

    @FXML
    private TextField titleInput;

    @FXML
    private Text titleInputError;

    @FXML
    private HBox titleInputErrorHbox;

    @FXML
    private Button update_newsBtn;

    private File selectedImageFile;
    private String imageName = null;
    private int titleTest = 0;
    private int descriptionTest = 0;
    private int shortDescTest = 0;
    private int photoTest = 0;

    private String etiquette = null;

    private double score;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("news action test " + News.actionTest);
        // errorsField
        titleInputErrorHbox.setVisible(false);
        descriptionInputErrorHbox.setVisible(false);
        shortDescInputErrorHbox.setVisible(false);
        photoInputErrorHbox.setVisible(false);

        if (News.actionTest == 0) { // add news
            update_newsBtn.setVisible(false);

        } else { // update news
            add_new_newsBtn.setVisible(false);

            // Instancier le service de news
            NewsService newsService = new NewsService();
            News n = new News();

            try {
                n = newsService.getOneNews(News.getNewsId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            titleInput.setText(n.getTitre());
            descriptionInput.setText(n.getDescription());
            shortDescInput.setText(n.getShort_description());

            Image image = new Image(getClass().getResource("/assets/actualiteUploads/" +
                    n.getImg()).toExternalForm());
            imageInput.setImage(image);
            imageName = n.getImg();

            titleTest = 1;
            descriptionTest = 1;
            shortDescTest = 1;

        }

    }

    @FXML
    void add_new_news(MouseEvent event) throws SQLException {

        News news = new News();

        if (titleInput.getText().isEmpty()) {
            titleTest = 0;
            titleInputErrorHbox.setVisible(true);
        } else {
            if (titleTest == 1) {
                news.setTitre(titleInput.getText());
            }
        }

        if (descriptionInput.getText().isEmpty()) {
            descriptionTest = 0;
            descriptionInputErrorHbox.setVisible(true);
        } else {
            if (descriptionTest == 1) {
                news.setDescription(descriptionInput.getText());
            }
        }

        if (shortDescInput.getText().isEmpty()) {
            shortDescTest = 0;
            shortDescInputErrorHbox.setVisible(true);
        } else {
            if (shortDescTest == 1) {
                news.setShort_description(shortDescInput.getText());
            }
        }

        if (imageName == null) {
            photoTest = 0;
            photoInputErrorHbox.setVisible(true);
        } else {
            photoTest = 1;
            news.setImg(imageName);
            news.setEtiquette(etiquette);
            news.setScore(score);
        }

        if (titleTest == 1 && descriptionTest == 1 && shortDescTest == 1 && photoTest == 1) {
            // Instancier le service de news
            INewsService newsService = new NewsService();
            try {
                newsService.ajouter(news);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewsInterfaces/AdminNewsList.fxml"));

                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void ajouter_image(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "assets", "actualiteUploads",
                    imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            // pour le controle de saisie
            photoTest = 1;
            photoInputErrorHbox.setVisible(false);

            // *************************get image object and score
            // *************************************/
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

            // Annotate the image
            try {
                // Load the image file
                byte[] imageBytes = Files.readAllBytes(selectedImageFile.toPath());

                ByteString byteString = ByteString.copyFrom(imageBytes);

                // Create the image object
                com.google.cloud.vision.v1.Image image2 = com.google.cloud.vision.v1.Image.newBuilder()
                        .setContent(byteString).build();

                // Create the feature object
                Feature feature = Feature.newBuilder().setType(Feature.Type.OBJECT_LOCALIZATION).build();

                // Create the request object
                AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feature).setImage(image2)
                        .build();

                // Send the request and get the response
                BatchAnnotateImagesResponse response = client.batchAnnotateImages(ImmutableList.of(request));

                // Extract labels and objects from the response
                List<LocalizedObjectAnnotation> objectAnnotations = response.getResponses(0)
                        .getLocalizedObjectAnnotationsList();
                etiquette = objectAnnotations.get(0).getName();
                score = objectAnnotations.get(0).getScore();
                System.out.println(objectAnnotations.get(0).getName() + " " + objectAnnotations.get(0).getScore());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Shut down the client
                client.shutdown();
            }
        }

    }

    @FXML
    void titleTypedInput(KeyEvent event) {
        String titleText = ((TextField) event.getSource()).getText();
        if (!titleText.isEmpty()) {
            titleInputErrorHbox.setVisible(false);
            titleTest = 1;
        }
    }

    @FXML
    void descriptionTypedInput(KeyEvent event) {
        String descriptionText = ((TextArea) event.getSource()).getText();
        if (!descriptionText.isEmpty()) {
            descriptionInputErrorHbox.setVisible(false);
            descriptionTest = 1;
        }
    }

    @FXML
    void shortDescTypedInput(KeyEvent event) {
        String shortDescText = ((TextField) event.getSource()).getText();
        if (!shortDescText.isEmpty()) {
            shortDescInputErrorHbox.setVisible(false);
            shortDescTest = 1;
        }
    }

    @FXML
    void update_news(MouseEvent event) throws SQLException {

        News news = new News();

        news.setId(News.getNewsId());
        if (titleInput.getText().isEmpty()) {
            titleTest = 0;
            titleInputErrorHbox.setVisible(true);
        } else {
            if (titleTest == 1) {
                news.setTitre(titleInput.getText());
            }
        }

        if (descriptionInput.getText().isEmpty()) {
            descriptionTest = 0;
            descriptionInputErrorHbox.setVisible(true);
        } else {
            if (descriptionTest == 1) {
                news.setDescription(descriptionInput.getText());
            }
        }

        if (shortDescInput.getText().isEmpty()) {
            shortDescTest = 0;
            shortDescInputErrorHbox.setVisible(true);
        } else {
            if (shortDescTest == 1) {
                news.setShort_description(shortDescInput.getText());
            }
        }

        if (imageName == null) {
            photoTest = 0;
            photoInputErrorHbox.setVisible(true);
        } else {
            photoTest = 1;
            news.setImg(imageName);
            news.setEtiquette(etiquette);
            news.setScore(score);
        }

        if (titleTest == 1 && descriptionTest == 1 && shortDescTest == 1 && photoTest == 1) {
            // Instancier le service de news
            INewsService newsService = new NewsService();
            try {
                newsService.updateNews(news);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewsInterfaces/AdminNewsList.fxml"));

                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
