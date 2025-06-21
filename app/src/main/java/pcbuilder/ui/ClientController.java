package pcbuilder.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientController {

    @FXML
    private void onLogout(ActionEvent event) {
        try {
            Parent loginRoot = FXMLLoader.load(
                getClass().getResource("/fxml/LoginView.fxml")
            );

            Stage stage = (Stage)((Node) event.getSource())
                                .getScene()
                                .getWindow();

            stage.getScene().setRoot(loginRoot);
            stage.setTitle("Login");

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,
                      "Erro ao voltar para login.")
                .showAndWait();
        }
    }
}
