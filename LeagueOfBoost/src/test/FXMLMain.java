/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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


 
public class FXMLMain extends Application {
  private double x,y;
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("/LeagueOfBoost/gui/Game/Game.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            /// drag the window 
            root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
        primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
        primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
    });
});
            primaryStage.show();
        } catch (Exception ex) {
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
