/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package LeagueOfBoost.gui.user;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import LeagueOfBoost.entities.User;
import LeagueOfBoost.services.ServicePersonne;
import LeagueOfBoost.utils.MyDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author daoid
 */
public class InscriptionController implements Initializable {
    public static User userc;

    Connection con ;

    @FXML
    private Hyperlink create_acc;

    @FXML
    private Hyperlink login_acc;

    @FXML
    private Button login_btn;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Label m;

    @FXML
    private Label m1;

    @FXML
    private PasswordField password;

    @FXML
    private Button signup_btn;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private TextField su_email;

    @FXML
    private PasswordField su_password;

    @FXML
    private TextField su_username;

    @FXML
    private TextField username;





    ServicePersonne ps = new ServicePersonne();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = MyDB.createorgetInstance().getCon();
        // TODO
    }










    @FXML
    public void login(javafx.event.ActionEvent actionEven) throws IOException{

       {
            User u = ps.findUserByLogin(username.getText(),password.getText());
            userc = u;
           if(username.getText().equals("admin") && password.getText().equals("123456") ){
               FXMLLoader loader = new FXMLLoader(getClass().getResource("ListUsers.fxml"));
               Stage stage = new Stage();

               stage.setScene(new Scene(loader.load()));
               stage.show();
               Stage currentStage = (Stage) ((Button) actionEven.getSource()).getScene().getWindow();
               currentStage.hide();
           }

            else if(u!=null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(loader.load()));
                stage.show();
                Stage currentStage = (Stage) ((Button) actionEven.getSource()).getScene().getWindow();
                currentStage.hide();

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Travel Me :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Email/Password !!");
                alert.showAndWait();
            }

        }
    }


    public void create(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Stage stage = new Stage();

        stage.setScene(new Scene(loader.load()));
        stage.show();
        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.hide();

    }
}
