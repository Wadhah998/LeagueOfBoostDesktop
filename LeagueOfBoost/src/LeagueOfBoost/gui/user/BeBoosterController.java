/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui.user;

import java.net.URL;
import java.util.ResourceBundle;

import LeagueOfBoost.entities.User;
import LeagueOfBoost.services.ServicePersonne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author daoid
 */
public class BeBoosterController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private TextField Lien_Opgg;

    @FXML
    private TextField Voie;

    @FXML
    private Button data;

    @FXML
    private TextField description;

    @FXML
    private Button home;

    @FXML
    private AnchorPane navbar;

    @FXML
    private TextField prix;

    @FXML
    private Button record;

    @FXML
    private Button student;

    public void BeBoosteer(ActionEvent actionEvent) {
        ServicePersonne uc = new ServicePersonne();
        User u = InscriptionController.userc;
        u.setPrix(Integer.parseInt(prix.getText()));
        u.setLien_opgg(Lien_Opgg.getText());
        u.setVoie(Voie.getText());
        u.setDescription(description.getText());
        uc.BeBooster(u);
    }
}
