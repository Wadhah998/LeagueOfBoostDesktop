/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui.ReservationB;

import LeagueOfBoost.entities.User;
import LeagueOfBoost.services.ServicePersonne;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author daoid
 */
public class BoosterListeController implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> fnclm;
    @FXML
    private TableColumn<User, String> lnclm;
    @FXML
    private TableColumn<User, String> vclm;
    @FXML
    private TableColumn<User, Integer> prixclm;

    @FXML
    private Button reserver;
    public static User Booster;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCoachs();
    }    
        ServicePersonne uc = new ServicePersonne();

    private void loadCoachs() {

        ObservableList<User> listef = uc.afficherBooster();
        fnclm.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lnclm.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        vclm.setCellValueFactory(new PropertyValueFactory<>("voie"));
        prixclm.setCellValueFactory(new PropertyValueFactory<>("prix"));

        table.setItems(listef);
    }

    public void reserver(ActionEvent event) {
        Booster = table.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rb.fxml"));
            Parent root = loader.load();

            // ModifiersController controleur = loader.getController();

            // controleur.setTextFields(s);

            Scene scene = new Scene(root);
            Stage stage = (Stage) reserver.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getCause().getMessage());
        }
    }}
