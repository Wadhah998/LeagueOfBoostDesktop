/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui.user;

import LeagueOfBoost.entities.User;
import LeagueOfBoost.services.ServicePersonne;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daoid
 */
public class ListUsersController implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> fnclm;
    @FXML
    private TableColumn<User, String> lnclm;
    @FXML
    private TableColumn<User, String> unclm;
    @FXML
    private TableColumn<User, String> mailclm;
    @FXML
    private TableColumn<User, String> roleclm;


    ServicePersonne sp = new ServicePersonne();
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        loadUsers();
    }    
    private void loadUsers() {

        ObservableList<User> listef = sp.afficherUtilisateurs();
        fnclm.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lnclm.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        unclm.setCellValueFactory(new PropertyValueFactory<>("username"));
        mailclm.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleclm.setCellValueFactory(new PropertyValueFactory<>("roles"));

        table.setItems(listef);
    }




    public void supprimer(ActionEvent actionEvent) {
        User u = table.getSelectionModel().getSelectedItem();
        sp.Supprimer(u);
        loadUsers();
    }

    public void ToCoachers(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoachDemands.fxml"));
        Stage stage = new Stage();

        stage.setScene(new Scene(loader.load()));
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }

    public void ToBoosters(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoostersDemands.fxml"));
        Stage stage = new Stage();

        stage.setScene(new Scene(loader.load()));
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }
}
