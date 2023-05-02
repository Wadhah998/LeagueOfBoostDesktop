/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui.user;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;

import LeagueOfBoost.entities.User;
import LeagueOfBoost.services.ServicePersonne;
import LeagueOfBoost.utils.MyDB;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import com.google.api.client.json.*;


import java.io.IOException;
import javafx.application.Platform;


/**
 * FXML Controller class
 *
 * @author daoid
 */
public class InscriptionController implements Initializable {
    public static User userc;

    Connection con;

    @FXML
    private Hyperlink create_acc;

    @FXML
    private Hyperlink login_acc;

    @FXML
    private Button login_btn;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Label m;

    @FXML
    private Label m1;

    @FXML
    private PasswordField password;

    @FXML
    private Button signup_btn;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private TextField su_email;

    @FXML
    private PasswordField su_password;

    @FXML
    private TextField su_username;

    @FXML
    private TextField username;


    @FXML
    private Button loginButton;

    private WebView webView;
    private WebEngine webEngine;


    ServicePersonne ps = new ServicePersonne();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = MyDB.createorgetInstance().getCon();

        // TODO
    }


    @FXML
    public void login(javafx.event.ActionEvent actionEven) throws IOException {

        {
            User u = ps.findUserByLogin(username.getText(), password.getText());
            userc = u;
            if (username.getText().equals("admin") && password.getText().equals("123456")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListUsers.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(loader.load()));
                stage.show();
                Stage currentStage = (Stage) ((Button) actionEven.getSource()).getScene().getWindow();
                currentStage.hide();
            } else if (u != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../SessionC/ajoutSession.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(loader.load()));
                stage.show();
                Stage currentStage = (Stage) ((Button) actionEven.getSource()).getScene().getWindow();
                currentStage.hide();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Travel Me :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Email/Password !!");
                alert.showAndWait();
            }

        }
    }


    public void create(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Stage stage = new Stage();

        stage.setScene(new Scene(loader.load()));
        stage.show();
        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.hide();


    }
    public void loginGoogle(ActionEvent event) {
        // Set up Google API credentials
        String clientId = "282455751245-33mj5tf4od6mcii4eo0u85ieu3etic3d.apps.googleusercontent.com";
        String clientSecret = "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";
        String redirectUri = "urn:ietf:wg:oauth:2.0:oob:auto";
        String scope = "openid email";
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(), JacksonFactory.getDefaultInstance(),
                clientId, clientSecret, Collections.singleton(scope))
                .build();

        // Set up WebView to display Google login page
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        String authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectUri).build();
        webEngine.load(authorizationUrl);
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                String url = webEngine.getLocation();
                if (url.startsWith(redirectUri)) {
                    String code = extractCodeFromUrl(url);
                    if (code != null) {
                        try {
                            // Exchange authorization code for access token
                            GoogleTokenResponse tokenResponse = flow.newTokenRequest(code).setRedirectUri(redirectUri).execute();
                            GoogleCredential credential = new GoogleCredential.Builder()
                                    .setTransport(new NetHttpTransport())
                                    .setJsonFactory(JacksonFactory.getDefaultInstance())
                                    .setClientSecrets(clientId, clientSecret)
                                    .build();
                            credential.setFromTokenResponse(tokenResponse);

                            // Use the credential to access the Google API
                            // ...

                            // Extract user information from the ID token
                            GoogleIdToken idToken = tokenResponse.parseIdToken();
                            GoogleIdToken.Payload payload = idToken.getPayload();
                            String email = null;
                            if (payload.getEmail() != null) {
                                email = payload.getEmail();
                                System.out.println("Email: " + email);
                            }
                            String firstName = (String) payload.get("given_name");
                            String lastName = (String) payload.get("family_name");
                            String username = firstName + lastName;

                            // Save the user's Google account information to the database
                           // User u = new User(firstName, lastName, username, email, "123456789");
                            //ps.Ajouter(u);

                            // Close the login window
                            ((Stage) webView.getScene().getWindow()).close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        // Display the WebView in a new window
        StackPane root = new StackPane();
        root.getChildren().add(webView);
        Scene scene = new Scene(root, 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    private String extractCodeFromUrl(URL url) {
        // Extracts the authorization code from the URL returned by Google
        String[] parts = url.toString().split("\\?");
        if (parts.length > 1) {
            String[] params = parts[1].split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2 && keyValue[0].equals("code")) {
                    return keyValue[1];
                }
            }
        }
        return null;
    }

    private String extractCodeFromUrl(String url) {
        try {
            URL u = new URL(url);
            return extractCodeFromUrl(u);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }



}