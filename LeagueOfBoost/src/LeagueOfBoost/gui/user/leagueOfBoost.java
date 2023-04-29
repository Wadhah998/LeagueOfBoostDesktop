/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package LeagueOfBoost.gui.user;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author daoid
 */
public class leagueOfBoost extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("PanierGraph.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("inscription.fxml"));
            //  Parent root = FXMLLoader.load(getClass().getResource("/khademni/guiOffre/StatistiqueFXML.fxml"));
            // Parent root = FXMLLoader.load(getClass().getResource("MesFormationFXML.fxml"));
            //   Parent root = FXMLLoader.load(getClass().getResource("/khademni/guiUser/DashboardFXML.fxml"));

            //Parent root = FXMLLoader.load(getClass().getResource("HistoriqueFXML.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Admin Interface !");
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
