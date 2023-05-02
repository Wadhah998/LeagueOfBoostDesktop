/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui.user;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import LeagueOfBoost.entities.User;
import LeagueOfBoost.services.ServicePersonne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author daoid
 */
public class LoginController implements Initializable {
    Connection con ;
    @FXML
    private AnchorPane login_form;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login_btn;
    @FXML
    private Label m;
    @FXML
    private AnchorPane signup_form;
    @FXML
    private Label m1;
    @FXML
    private TextField su_email;
    @FXML
    private TextField su_username;
    @FXML
    private PasswordField su_password;
    @FXML
    private Button signup_btn;
    @FXML
    private Hyperlink login_acc;

    ServicePersonne ps = new ServicePersonne();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean ValidationEmail(String email){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9._]+([.][a-zA-Z0-9]+)+");
        Matcher match = pattern.matcher(email);

        if(match.find() && match.group().equals(email))
        {
            return true;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore message");
            alert.setHeaderText(null);
            alert.setContentText("Le format d'email n'est valide");
            alert.showAndWait();

            return false;
        }

    }
    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        String firstnameText = firstname.getText();
        String lastnameText = lastname.getText();
        String usernameText = username.getText();
        String emailText = email.getText();
        String passwordText = password.getText();
        if(       firstnameText.isEmpty()
                | lastnameText.isEmpty()
                | usernameText.isEmpty()
                | passwordText.isEmpty()
                | emailText.isEmpty()

        ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Khademni :: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont obligatoire !!");
            alert.showAndWait();
        }else if(passwordText.length() < 8  ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Khademni :: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Le mot de passe doit etre sup à 8 caractéres ");
            alert.showAndWait();
        }else if(!(firstnameText.matches("[a-zA-Z]+") & lastnameText.matches("[a-zA-Z]+") )){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Khademni :: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Le nom et le prenom doivent contenir que des lettres !!");
            alert.showAndWait();
        }else if(ps.userExist(usernameText)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Travel Me :: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("l'identifiant : "+usernameText+" est deja utilise!!");
            alert.showAndWait();
        }
        else{
            if(ValidationEmail(emailText)){
                User u = new User(firstnameText, lastnameText, usernameText, emailText, passwordText);
                ps.Ajouter(u);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(loader.load()));
                stage.show();
                Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                currentStage.hide();





    }
    
}}}
