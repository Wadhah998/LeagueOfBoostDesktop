/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui.user;

import LeagueOfBoost.entities.User;
import LeagueOfBoost.services.ServicePersonne;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author daoid
 */
public class ListUsersController implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private TextField prixtotal;
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
    @FXML
    private ChoiceBox<String> choiceBox;




    ServicePersonne sp = new ServicePersonne();
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

     // bar de recherche
        prixtotal.textProperty().addListener((observable, oldValue, newValue) -> {
            rechercherUtilisateurs();
        });


        loadUsers();
        // Tri
        ObservableList<TableColumn<?, ?>> sortedColumns = FXCollections.observableArrayList();
        sortedColumns.add(fnclm);

        sortedColumns.sort((c1, c2) -> {
            if (c1 == fnclm) {
                return -1;
            } else if (c2 == fnclm) {
                return 1;
            } else {
                return c1.getText().compareToIgnoreCase(c2.getText());
            }
        });

        table.getSortOrder().clear();
        table.getSortOrder().addAll((TableColumn<User, ?>[]) sortedColumns.toArray(new TableColumn<?, ?>[0]));

        //choiceBox
        ObservableList<String> roles = FXCollections.observableArrayList("All","User", "Booster", "Coach");
        choiceBox.setItems(roles);

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            rechercherUtilisateursRoles();
        });







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
    public void rechercherUtilisateurs() {
        String keyword = prixtotal.getText();
        ObservableList<User> filteredList = FXCollections.observableArrayList();
        ObservableList<TableColumn<User, ?>> columns = table.getColumns();
        for (int i = 0; i <sp.afficherUtilisateurs().size(); i++) {
            User user = sp.afficherUtilisateurs().get(i);
            for (int j = 0; j < columns.size(); j++) {
                TableColumn<User, ?> column = columns.get(j);
                String cellValue = column.getCellData(user).toString();
                if (cellValue.contains(keyword)) {
                    filteredList.add(user);
                    break;
                }
            }
        }
        table.setItems(filteredList);
    }
    private void rechercherUtilisateursRoles() {
        String role = choiceBox.getValue();
        if (role=="User"){
            role="[\"ROLE_USER\"]";
        } else if (role=="Booster") {
            role="[\"ROLE_BOOSTER\"]";

        } else if (role=="Coach") {
            role="[\"ROLE_CHOACH\"]";

        }
        ObservableList<User> filteredList = FXCollections.observableArrayList();
        for (User user : sp.afficherUtilisateurs()) {
            if (role == null || role.equals("All") || user.getRoles().equals(role)) {
                filteredList.add(user);
            }
        }
        table.setItems(filteredList);
    }


    public void SendWarning(ActionEvent event) throws SQLException {
        User u = table.getSelectionModel().getSelectedItem();
        sp.sendMail(u,"warning","LeagueOfBoost warning");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LeagueOfBoost :: Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Warning envoyer !!");
        alert.showAndWait();
    }
}
