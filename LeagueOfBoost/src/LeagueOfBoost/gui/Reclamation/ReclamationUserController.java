/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui.Reclamation;

import LeagueOfBoost.entities.Reclamation;
import static LeagueOfBoost.gui.Reclamation.ReclamationController.varstat;

import LeagueOfBoost.gui.ReservationC.ReservationCController;
import LeagueOfBoost.services.ServiceReclamation;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.prefs.Preferences;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class ReclamationUserController implements Initializable {

    @FXML
    private TableColumn<Reclamation, Integer> idclm;
    @FXML
    private TableColumn<Reclamation, Integer> userclm;
    @FXML
    private TableColumn<Reclamation, Boolean> etatclm;
    @FXML
    private TableColumn<Reclamation, Date> dateclm;
    @FXML
    private TableColumn<Reclamation, String> themeclm;
    @FXML
    private TableColumn<Reclamation, String> objclm;
    @FXML
    private TableColumn<Reclamation, String> txtclm;
    @FXML
    private TextField prixtotal;
    @FXML
    private TextField id;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAfficher;
    @FXML
    private Button btnmodifier;
    @FXML
    private TableView<Reclamation> table;

    @FXML
    private AnchorPane main;


    @FXML
    private Button AjouterR;


    private static boolean isNotificationDisplayed = false; // initialiser à false lors du lancement de l'application




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prixtotal.textProperty().addListener((observable, oldValue, newValue) -> {
            rechercherReclamation();
        });


        loadReclamationsuser();


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
    private void loadReclamationsuser() {
        ObservableList<Reclamation> listef = sr.afficherReclamationbyuser();
        table.setItems(listef);
        idclm.setCellValueFactory(new PropertyValueFactory<>("id"));
        //userclm.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        etatclm.setCellValueFactory(new PropertyValueFactory<>("etat"));
        dateclm.setCellValueFactory(new PropertyValueFactory<>("date"));
        themeclm.setCellValueFactory(new PropertyValueFactory<>("theme"));
        objclm.setCellValueFactory(new PropertyValueFactory<>("object"));
        txtclm.setCellValueFactory(new PropertyValueFactory<>("text"));


        boolean hasTreatedReclamation = listef.stream().anyMatch(r -> r.isEtat());

        if (hasTreatedReclamation && !isNotificationDisplayed) {
            // Afficher la notification
            String message = "Une réclamation a été traitée.";
            notifyUser(message);

            // Marquer la notification comme affichée
            isNotificationDisplayed = true;
        }




        table.setItems(listef);
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        Reclamation r1 = table.getSelectionModel().getSelectedItem();
        sr.Supprimer(r1);
        loadReclamationsuser();
    }

    @FXML
    private void ModifierRuser(ActionEvent event) {
        Reclamation r = table.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRuser.fxml"));
            Parent sv = loader.load();
            ModifierRuserController controleur = loader.getController();
            controleur.setTextFields(r);
            main.getChildren().removeAll();
            main.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(ReservationCController.class.getName()).log(Level.SEVERE, null, ex);
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

                main.getChildren().removeAll();
                main.getChildren().setAll(root);
            } catch (IOException e) {
                System.out.println(e.getCause().getMessage());
            }
        }
    }

    public void rechercherReclamation() {
        String keyword = prixtotal.getText();
        ObservableList<Reclamation> filteredList = FXCollections.observableArrayList();
        ObservableList<TableColumn<Reclamation, ?>> columns = table.getColumns();
        for (int i = 0; i <sr.afficherReclamationbyuser().size(); i++) {
            Reclamation Rec = sr.afficherReclamationbyuser().get(i);
            for (int j = 0; j < columns.size(); j++) {
                TableColumn<Reclamation, ?> column = columns.get(j);
                String cellValue = column.getCellData(Rec).toString();
                if (cellValue.contains(keyword)) {
                    filteredList.add(Rec);
                    break;
                }
            }
        }
        table.setItems(filteredList);
    }

    private void notifyUser(String message) {

        if (SystemTray.isSupported() ) {
            try {
                // Initialize SystemTray
                SystemTray tray = SystemTray.getSystemTray();

                // Create an icon for the notification
                Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                TrayIcon trayIcon = new TrayIcon(image, "Notification");

                // Add the icon to the SystemTray
                tray.add(trayIcon);

                // Display the notification
                trayIcon.displayMessage("Notification", message, TrayIcon.MessageType.INFO);

                // Set the flag to indicate that the notification has been shown

            } catch (AWTException e) {
                System.err.println("Error initializing SystemTray: " + e);
            }
        } else {
            System.out.println("SystemTray is not supported ");
        }
    }



    @FXML
    private void AjouterR(ActionEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/Reclamation/ajouterR.fxml"));
        main.getChildren().removeAll();
        main.getChildren().setAll(fxml);
    }





}