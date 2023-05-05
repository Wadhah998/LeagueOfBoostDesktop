/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui.ReservationC;

import LeagueOfBoost.entities.Reservationb;
import LeagueOfBoost.entities.User;
import LeagueOfBoost.gui.ReservationB.ReservationBController;
import LeagueOfBoost.services.ServicePersonne;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daoid
 */
public class CoachListeController implements Initializable {

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
    private AnchorPane content_area;

    @FXML
    private Button reserver;
    public static User Coach;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCoachs();
    }    
        ServicePersonne uc = new ServicePersonne();

    private void loadCoachs() {

        ObservableList<User> listef = uc.afficherCoach();
        fnclm.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lnclm.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        vclm.setCellValueFactory(new PropertyValueFactory<>("voie"));
        prixclm.setCellValueFactory(new PropertyValueFactory<>("prix"));

        table.setItems(listef);
    }

    public void reserver(ActionEvent event) throws IOException {
        Coach = table.getSelectionModel().getSelectedItem();
        Parent fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/ReservationC/ajout.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }}
