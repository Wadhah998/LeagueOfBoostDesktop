/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui.ReservationC;

import LeagueOfBoost.entities.ReservationC;
import LeagueOfBoost.services.ServiceReservationC;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class ReservationCController implements Initializable {
    Stage Stage1_1;
    public static ReservationC rb; 

    @FXML
    private TableView<ReservationC> ResList;
    @FXML
    private TableColumn<?, ?> coach_id;
    @FXML
    private TableColumn<?, ?> user_id;
    @FXML
    private TableColumn<?, ?> nbr_heures;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> langue;
    
    Button[] supprimerb = new Button[100];
    ServiceReservationC sg = new ServiceReservationC();

    List<ReservationC> lt = sg.afficherCoaches();
    /**
     * Initializes the controller class.
     */
    int index=101;
    @FXML
    private AnchorPane main;
    @FXML
    private TableColumn<?, ?> Supprimer;
    @FXML
    private TextField txtrech;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Ajouter un écouteur de changement de texte au champ de texte
        txtrech.textProperty().addListener((observable, oldValue, newValue) -> {
        rechercherReservation();
    });
        afficher_A(lt);
    }
   
private void handleButtonAction (ActionEvent event)
    {
       
         for (int i = 0; i < lt.size(); i++) {
            // Button a = supprimerb[i];
            
             if (event.getSource() == supprimerb[i])
             {
                 index=i;
             }
                 
         }
          System.out.println(index);
          int index1 = lt.get(index).getId();
            ServiceReservationC sg = new ServiceReservationC();
            
             sg.supprimerReservation(index1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("suppression affecté!");
            alert.show();
             lt = sg.afficherCoaches();
             ResList.getItems().clear();
             afficher_A(lt);
     }
    
    @FXML
    private void AjouterButton(MouseEvent event) {
    }
    
    
    public void afficher_A(List<ReservationC> ltt)
    {
      
      for (int i = 0; i < lt.size(); i++) {
         
        ImageView supprimer = new ImageView(new Image(getClass().getResourceAsStream("/images/not_sending_video_frames_16px.png")));
         supprimerb[i] = new Button("", supprimer);
             lt.get(i).setSupprimer(supprimerb[i]);
           supprimerb[i].setOnAction(this::handleButtonAction);
        }

        
       ObservableList<ReservationC> datalist = FXCollections.observableArrayList(lt);
        

        coach_id.setCellValueFactory(new PropertyValueFactory<>("coach_id"));
        user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        nbr_heures.setCellValueFactory(new PropertyValueFactory<>("nbr_heures"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        langue.setCellValueFactory(new PropertyValueFactory<>("langue"));
        Supprimer.setCellValueFactory(new PropertyValueFactory<>("supprimer"));

        ResList.setItems(datalist);
    }
    

    @FXML
    private void quit(MouseEvent event) {
        
        Stage1_1 = (Stage)main.getScene().getWindow();
        System.out.println("you succesfully close the application");
        Stage1_1.close();
    }

    @FXML
    private void reserv(ActionEvent event) {
        try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("ajout.fxml"));
          main.getChildren().removeAll() ; 
          main.getChildren().setAll(sv) ;                      
        } catch (IOException ex) {
             Logger.getLogger(ReservationCController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void modif(ActionEvent event) {
         rb = ResList.getSelectionModel().getSelectedItem();
        try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("modifier.fxml"));
          main.getChildren().removeAll() ; 
          main.getChildren().setAll(sv) ;                      
        } catch (IOException ex) {
             Logger.getLogger(ReservationCController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public void rechercherReservation() {
        String keyword = txtrech.getText();
        ObservableList<ReservationC> filteredList = FXCollections.observableArrayList();
        ObservableList<TableColumn<ReservationC, ?>> columns = ResList.getColumns();
        for (int i = 0; i < sg.afficherReservation(3).size(); i++) {
            ReservationC ReservationC = sg.afficherReservation(3).get(i);
            for (int j = 0; j < columns.size(); j++) {
                TableColumn<ReservationC, ?> column = columns.get(j);
                String cellValue = column.getCellData(ReservationC).toString();
                if (cellValue.contains(keyword)) {
                    filteredList.add(ReservationC);
                    break;
                }
            }
        }
        ResList.setItems(filteredList);
    }

    public void tosesion(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/SessionC/SessionCUser.fxml"));
        main.getChildren().removeAll();
        main.getChildren().setAll(fxml);

    }
}