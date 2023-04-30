package NewsInterfaces;

import java.io.IOException;
import java.sql.SQLException;

import LeagueOfBoost.entities.Comment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import LeagueOfBoost.services.NewsService;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rouaa
 */
public class UserCommentItemController {

    @FXML
    private Text comment;

    @FXML
    private Text date;

    @FXML
    private HBox deleteComment;

    @FXML
    private HBox editComment;

    @FXML
    private Text userName;

    public void setCommentData(Comment cmt) {

        comment.setText(cmt.getComment());
        date.setText(cmt.getDate());

        // deleteComment btn click
        deleteComment.setId(String.valueOf(cmt.getId()));
        NewsService newsService = new NewsService();

        deleteComment.setOnMouseClicked(event -> {
            System.out.println("ID du comment à supprimer : " + cmt.getId());
            try {
                newsService.supprimerComment(cmt.getId());

            } catch (SQLException e) {
                e.printStackTrace();
            }
            // supprimer le contenu de la liste et afficher la nouvelle liste
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/NewsInterfaces/UserNewsDetails.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis ce controller
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de newsDetail
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // end
        });
        // END deleteNews btn click

        // editComment btn click
        editComment.setId(String.valueOf(cmt.getId()));

        editComment.setOnMouseClicked(event -> {
            Comment.setCmtID(cmt.getId());
            System.out.println("ID du comment à modifier : " + cmt.getId());

            TextArea commentInput = (TextArea) ((Node) event.getSource()).getScene().lookup("#commentInput");
            commentInput.setText(cmt.getComment());

            HBox updateBtnContainer = (HBox) ((Node) event.getSource()).getScene().lookup("#updateBtnContainer");
            updateBtnContainer.setVisible(true);

            HBox submitBtn = (HBox) ((Node) event.getSource()).getScene().lookup("#submitBtn");
            submitBtn.setVisible(false);

            HBox addCommentModel = (HBox) ((Node) event.getSource()).getScene().lookup("#addCommentModel");
            addCommentModel.setVisible(true);

            // addReviewsModel.setVisible(true);

        });
        // END editComment btn click

    }

}
