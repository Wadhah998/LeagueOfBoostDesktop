/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui.Team;

import LeagueOfBoost.entities.Team;
import LeagueOfBoost.services.ServiceTeam;
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

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class TeamController implements Initializable {
    Stage Stage1_1;

    @FXML
    private TableView<Team> TeamList;
    @FXML
    private TableColumn<?, ?> game_id;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> player1;
    @FXML
    private TableColumn<?, ?> player2;
    @FXML
    private TableColumn<?, ?> player3;
    @FXML
    private TableColumn<?, ?> player4;
    @FXML
    private TableColumn<?, ?> player5;
    
    Button[] supprimerb = new Button[100];
    ServiceTeam st = new ServiceTeam();

    List<Team> lt = st.afficherTeams();
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
          int index1 = lt.get(index).getGame_id();
            ServiceTeam st = new ServiceTeam();
            
             st.SupprimerById(index1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("suppression affecté!");
            alert.show();
             lt = st.afficherTeams();
             TeamList.getItems().clear();
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

       ObservableList<Team> datalist = FXCollections.observableArrayList(lt);
        System.out.println("hiiiii");
        game_id.setCellValueFactory(new PropertyValueFactory<>("game_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        player1.setCellValueFactory(new PropertyValueFactory<>("player1"));
        player2.setCellValueFactory(new PropertyValueFactory<>("player2"));
        player3.setCellValueFactory(new PropertyValueFactory<>("player3"));
        player4.setCellValueFactory(new PropertyValueFactory<>("player4"));
        player5.setCellValueFactory(new PropertyValueFactory<>("player5"));

        Supprimer.setCellValueFactory(new PropertyValueFactory<>("supprimer"));

        
        TeamList.setItems(datalist);
    }    

    @FXML
    private void AjouterButton(MouseEvent event) {
    }
    
    
    public void afficher_A(List<Team> ltt)
    {
      
      for (int i = 0; i < lt.size(); i++) {
         
        ImageView supprimer = new ImageView(new Image(getClass().getResourceAsStream("/images/not_sending_video_frames_16px.png")));
         supprimerb[i] = new Button("", supprimer);
             lt.get(i).setSupprimer(supprimerb[i]);
           supprimerb[i].setOnAction(this::handleButtonAction);
        }

        
       ObservableList<Team> datalist = FXCollections.observableArrayList(lt);
        

        game_id.setCellValueFactory(new PropertyValueFactory<>("game_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        player1.setCellValueFactory(new PropertyValueFactory<>("player1"));
        player2.setCellValueFactory(new PropertyValueFactory<>("player2"));
        player3.setCellValueFactory(new PropertyValueFactory<>("player3"));
        player4.setCellValueFactory(new PropertyValueFactory<>("player4"));
        player5.setCellValueFactory(new PropertyValueFactory<>("player5"));
        Supprimer.setCellValueFactory(new PropertyValueFactory<>("supprimer"));

        TeamList.setItems(datalist);
    }

    @FXML
    private void quit(MouseEvent event) {
        
        Stage1_1 = (Stage)main.getScene().getWindow();
        System.out.println("you succesfully closed the application");
        Stage1_1.close();
    }
    
}

