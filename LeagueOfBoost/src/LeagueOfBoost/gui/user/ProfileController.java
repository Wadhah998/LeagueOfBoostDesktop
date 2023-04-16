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


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicePersonne uc = new ServicePersonne() ;
        User u = uc.getByID(InscriptionController.userc);
        firstname_id.setText(u.getFirstname());
        email_id.setText(u.getEmail());
        lastname_id.setText(u.getLastname());
        username_id.setText((u.getUsername()));




    }    

    @FXML
    private void editprofil_id(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editprofile.fxml"));
        Stage stage = new Stage();

        stage.setScene(new Scene(loader.load()));
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }


    public void logout(ActionEvent actionEvent) {
        btnSignout.getScene().getWindow().hide();
    }
}
