/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui;

import LeagueOfBoost.entities.Reclamation;
import LeagueOfBoost.services.ServiceReclamation;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class ReclamationController implements Initializable {

    public static int varstat ;
    
    @FXML
    private TableColumn<Reclamation, Integer> idclm;
    @FXML
    private TableColumn<Reclamation, Integer> userclm;
    @FXML
    private TableColumn<Reclamation, Boolean> etatclm;
    @FXML
    private TableColumn<Reclamation, Integer> dateclm;
    @FXML
    private TableColumn<Reclamation, String> themeclm;
    @FXML
    private TableColumn<Reclamation, String> objclm;
    @FXML
    private TableColumn<Reclamation, String> txtclm;
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnAfficher;
    @FXML
    private TextField id;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadReclamations();
        table.setOnMouseClicked(event -> {
        if (event.getClickCount() == 1) {
            Reclamation selectedReclamation = table.getSelectionModel().getSelectedItem();
            if (selectedReclamation != null) {
                btnAfficher.setDisable(false);
            } else {
                btnAfficher.setDisable(true);
            }
        }
    });
    
    btnAfficher.setDisable(true);
    }    
    ServiceReclamation sr = new ServiceReclamation();
    
    private void loadReclamations() {
        ObservableList<Reclamation> listef = sr.afficherReclamation();
        idclm.setCellValueFactory(new PropertyValueFactory<>("id"));
        userclm.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        etatclm.setCellValueFactory(new PropertyValueFactory<>("etat"));
        dateclm.setCellValueFactory(new PropertyValueFactory<>("date"));
        themeclm.setCellValueFactory(new PropertyValueFactory<>("theme"));
        objclm.setCellValueFactory(new PropertyValueFactory<>("object"));
        txtclm.setCellValueFactory(new PropertyValueFactory<>("text"));

        table.setItems(listef);
    
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        Reclamation r1 = table.getSelectionModel().getSelectedItem();
        sr.Supprimer(r1);
        loadReclamations();
    }

    @FXML
    private void Modifier(ActionEvent event) {
        Reclamation r = table.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierR.fxml"));
            Parent root = loader.load();
            
            ModifierRController controleur = loader.getController();
            
            controleur.setTextFields(r);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnmodifier.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            loadReclamations();
        } catch (IOException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
    
    
    
    @FXML
public void Afficher() {
    Reclamation selectedReclamation = table.getSelectionModel().getSelectedItem();
     varstat = selectedReclamation.getId() ; 
    if (selectedReclamation != null) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherR.fxml"));
            Parent root = loader.load();
            
            AfficherRController controller = loader.getController();
            controller.setSelectedReclamation(selectedReclamation);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
}
    
    
}
