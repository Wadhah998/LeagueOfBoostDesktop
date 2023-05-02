package LeagueOfBoost.gui.SessionC;

import LeagueOfBoost.entities.ReservationC;
import LeagueOfBoost.entities.SessionC;
import LeagueOfBoost.gui.ReservationC.ReservationCController;
import static LeagueOfBoost.gui.ReservationC.ReservationCController.rb;
import LeagueOfBoost.services.ServiceSessionC;
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
public class SessionCController implements Initializable {
    Stage Stage1_1;
    public static SessionC sb; 

    @FXML
    private TableView<SessionC> SesList;
    @FXML
    private TableColumn<?, ?> titre;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> user_id;
    
    Button[] supprimerb = new Button[100];
    ServiceSessionC sg = new ServiceSessionC();

    List<SessionC> lt = sg.afficherSessions();
    /**
     * Initializes the controller class.
     */
    int index=101;
    @FXML
    private AnchorPane main;
    @FXML
    private TableColumn<?, ?> Supprimer;
   
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
            ServiceSessionC sg = new ServiceSessionC();
            
             sg.supprimerSessionCoaching(index1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("suppression affecté!");
            alert.show();
             lt = sg.afficherSessions();
             SesList.getItems().clear();
             afficher_A(lt);
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      for (int i = 0; i < lt.size(); i++) {
         
    ImageView supprimer = new ImageView(new Image(getClass().getResourceAsStream("/images/not_sending_video_frames_16px.png")));
         supprimerb[i] = new Button("", supprimer);
             lt.get(i).setSupprimer(supprimerb[i]);
           supprimerb[i].setOnAction(this::handleButtonAction);
        }

        ObservableList<SessionC> datalist = FXCollections.observableArrayList(lt);
        System.out.println("hiiiii");
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));

        Supprimer.setCellValueFactory(new PropertyValueFactory<>("supprimer"));

        
        SesList.setItems(datalist);
    }    

    @FXML
    private void AjouterButton(MouseEvent event) {
    }
    
    
    public void afficher_A(List<SessionC> ltt)
    {
      
      for (int i = 0; i < lt.size(); i++) {
         
        ImageView supprimer = new ImageView(new Image(getClass().getResourceAsStream("/images/not_sending_video_frames_16px.png")));
         supprimerb[i] = new Button("", supprimer);
             lt.get(i).setSupprimer(supprimerb[i]);
           supprimerb[i].setOnAction(this::handleButtonAction);
        }

        
       ObservableList<SessionC> datalist = FXCollections.observableArrayList(lt);
        

        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        Supprimer.setCellValueFactory(new PropertyValueFactory<>("supprimer"));

        SesList.setItems(datalist);
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
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("ajoutsession.fxml"));
          main.getChildren().removeAll() ; 
          main.getChildren().setAll(sv) ;                      
        } catch (IOException ex) {
             Logger.getLogger(SessionCController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void modif(ActionEvent event) {
        sb = SesList.getSelectionModel().getSelectedItem();
        try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("modifiersession.fxml"));
          main.getChildren().removeAll() ; 
          main.getChildren().setAll(sv) ;                      
        } catch (IOException ex) {
             Logger.getLogger(SessionCController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
