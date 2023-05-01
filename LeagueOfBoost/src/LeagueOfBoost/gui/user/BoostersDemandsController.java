/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui.user;

import LeagueOfBoost.entities.User;
import LeagueOfBoost.services.ServicePersonne;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author daoid
 */
public class BoostersDemandsController implements Initializable {

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

        ObservableList<User> listef = sp.afficherBoostersDemands();
        fnclm.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lnclm.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        unclm.setCellValueFactory(new PropertyValueFactory<>("username"));
        mailclm.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleclm.setCellValueFactory(new PropertyValueFactory<>("roles"));

        table.setItems(listef);
    }






    public void accepter(ActionEvent event) throws SQLException {
        User u = table.getSelectionModel().getSelectedItem();
        sp.changeToBooster(u);
        sp.sendMail(u,"CONGRATULATIONS! "+u.getFirstname() + " " + u.getLastname() +", we have reviwed your form and deemed you worthy of being a Booster","Application Result");
        loadUsers();

    }


}
