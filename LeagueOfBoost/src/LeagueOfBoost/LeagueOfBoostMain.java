package LeagueOfBoost;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LeagueOfBoostMain extends Application {

    public static Stage stage = null;

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {

            root = FXMLLoader.load(getClass().getResource(  "/UserDashboard/UserDashboard.fxml"));
           // root = FXMLLoader.load(getClass().getResource("/AdminDashboard/AdminDashboard.fxml"));
            Scene scene = new Scene(root);

            primaryStage.getIcons().add(new Image("/assets/img/logo.png"));
            primaryStage.setTitle("League Of Boost");
            primaryStage.setScene(scene);
            LeagueOfBoostMain.stage = primaryStage;
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        launch(args);
    }
}