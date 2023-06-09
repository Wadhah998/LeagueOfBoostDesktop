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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daoid
 */
public class ProfileController implements Initializable {

    public Button btnSignout;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label firstname_id;
    @FXML
    private Label email_id;
    @FXML
    private Label lastname_id;
    @FXML
    private Label username_id;
    @FXML

    private AnchorPane main;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicePersonne uc = new ServicePersonne() ;
        User u = InscriptionController.userc;
        firstname_id.setText(u.getFirstname());
        email_id.setText(u.getEmail());
        lastname_id.setText(u.getLastname());
        username_id.setText((u.getUsername()));




    }    

    @FXML
    private void editprofil_id(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/user/editprofile.fxml"));
        main.getChildren().removeAll();
        main.getChildren().setAll(fxml);
    }


    public void logout(ActionEvent actionEvent) {
        btnSignout.getScene().getWindow().hide();
    }

    public void goForm(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/user/bebooster.fxml"));
       main.getChildren().removeAll();
        main.getChildren().setAll(fxml);
    }

    public void goForm1(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/user/beChoach.fxml"));
        main.getChildren().removeAll();
        main.getChildren().setAll(fxml);

    }
}
