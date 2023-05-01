/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sami;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


 
public class test extends Application {
  @Override
    public void start(Stage primaryStage) {
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("PanierGraph.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
            //  Parent root = FXMLLoader.load(getClass().getResource("/khademni/guiOffre/StatistiqueFXML.fxml"));
            // Parent root = FXMLLoader.load(getClass().getResource("MesFormationFXML.fxml"));
            //   Parent root = FXMLLoader.load(getClass().getResource("/khademni/guiUser/DashboardFXML.fxml"));

            //Parent root = FXMLLoader.load(getClass().getResource("HistoriqueFXML.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("LOB !");
            primaryStage.setScene(scene);
            primaryStage.show();
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
