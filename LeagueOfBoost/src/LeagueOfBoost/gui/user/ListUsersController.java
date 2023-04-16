package LeagueOfBoost.gui.user;

import LeagueOfBoost.entities.User;
import LeagueOfBoost.services.ServicePersonne;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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


public class ListUsersController implements Initializable {
    Stage Stage1_1;

    private TableView<User> UserList;
    @FXML
    private TableColumn<?, ?> firstname;
    @FXML
    private TableColumn<?, ?> lastname;
    @FXML
    private TableColumn<?, ?> username;
    @FXML
    private TableColumn<?, ?> roles;
    
    Button[] supprimerb = new Button[100];
    ServicePersonne sg = new ServicePersonne();

    List<User> lt = sg.afficherUtilisateurs();
    /**
     * Initializes the controller class.
     */
    int index=101;
    private AnchorPane main;
    @FXML
    private TableColumn<?, ?> Supprimer;
    @FXML
    private TableView<?> table;
    
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
            ServicePersonne sg = new ServicePersonne();
            
             sg.SupprimerById(index1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("suppression affecté!");
            alert.show();
             lt = sg.afficherUtilisateurs();
             UserList.getItems().clear();
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

       ObservableList<User> datalist = FXCollections.observableArrayList(lt);
        System.out.println("hiiiii");
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        roles.setCellValueFactory(new PropertyValueFactory<>("roles"));
        Supprimer.setCellValueFactory(new PropertyValueFactory<>("supprimer"));

        
        UserList.setItems(datalist);
    }    

    
    
    public void afficher_A(List<User> ltt)
    {
      
      for (int i = 0; i < lt.size(); i++) {
         
        ImageView supprimer = new ImageView(new Image(getClass().getResourceAsStream("/images/not_sending_video_frames_16px.png")));
         supprimerb[i] = new Button("", supprimer);
             lt.get(i).setSupprimer(supprimerb[i]);
           supprimerb[i].setOnAction(this::handleButtonAction);
        }

        
       ObservableList<User> datalist = FXCollections.observableArrayList(lt);
        

        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        roles.setCellValueFactory(new PropertyValueFactory<>("roles"));
        Supprimer.setCellValueFactory(new PropertyValueFactory<>("supprimer"));

        UserList.setItems(datalist);
    }

    private void quit(MouseEvent event) {
        
        Stage1_1 = (Stage)main.getScene().getWindow();
        System.out.println("you succesfully close the application");
        Stage1_1.close();
    }
    
}