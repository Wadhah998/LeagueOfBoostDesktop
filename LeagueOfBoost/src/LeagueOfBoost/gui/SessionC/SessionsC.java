/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui.SessionC;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sami
 */
public class SessionsC extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.println("FXML file loaded successfully") ;
            Parent root = FXMLLoader.load(getClass().getResource("SessionCUser.fxml"));
            System.out.println("FXML file loaded successfully") ;
            Scene scene = new Scene(root);
            primaryStage.setTitle("SessionsCoach !");
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("FXML file shown successfully");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}