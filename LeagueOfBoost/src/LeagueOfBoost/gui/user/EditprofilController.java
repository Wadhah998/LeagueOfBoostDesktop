/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui.user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import LeagueOfBoost.entities.User;
import LeagueOfBoost.services.ServicePersonne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daoid
 */
public class EditprofilController implements Initializable {

    public TextField email_update;
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField firstname_update;
    @FXML
    private TextField lastname_update;
    @FXML
    private TextField username_update;
    @FXML
    private AnchorPane content_area;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicePersonne uc = new ServicePersonne();
        User u = InscriptionController.userc;
        firstname_update.setText(u.getFirstname());
        lastname_update.setText(u.getLastname());
        username_update.setText(u.getUsername());
        email_update.setText(u.getEmail());
    }    

    @FXML
    private void update_button(ActionEvent event) throws IOException {
        ServicePersonne uc = new ServicePersonne();
        User u = InscriptionController.userc;
        u.setFirstname(firstname_update.getText());
        u.setLastname(lastname_update.getText());
        u.setUsername(username_update.getText());
        u.setEmail(email_update.getText());
        uc.Modifier(u);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Profil updated !");
        alert.showAndWait();
        Parent fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/User/Profile.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

    @FXML
    private void cancel_button(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/User/Profile.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }
    
}
