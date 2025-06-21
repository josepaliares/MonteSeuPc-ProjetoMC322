package pcbuilder.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import pcbuilder.service.UsuarioService;
import pcbuilder.usuario.Role;

import java.io.IOException;

public class LoginController {
    @FXML private TextField tfEmail;
    @FXML private PasswordField pfSenha;
    @FXML private Label lblError;

    private final UsuarioService userService = UsuarioService.getInstance();

    @FXML
    private void onLogin() {
        String email = tfEmail.getText().trim();
        String senha = pfSenha.getText();

        userService.login(email, senha).ifPresentOrElse(user -> {
            try {
                String fxml = user.getRole() == Role.ADMIN
                            ? "/fxml/AdminView.fxml"
                            : "/fxml/ClientView.fxml";
                Parent next = FXMLLoader.load(getClass().getResource(fxml));
                Stage stage = (Stage) tfEmail.getScene().getWindow();
                stage.setScene(new Scene(next));
                stage.setTitle(user.getRole() == Role.ADMIN
                              ? "Painel da Loja"
                              : "Montagem de PC");
            } catch (IOException ex) {
                ex.printStackTrace();
                lblError.setText("Erro ao carregar próxima tela.");
            }
        }, () -> lblError.setText("E-mail ou senha inválidos."));
    }
}
