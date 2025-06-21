package pcbuilder.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pcbuilder.components.Fonte;
import pcbuilder.components.Marca;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.service.FonteService;

public class FonteFormController {

    @FXML private TextField       tfNome;
    @FXML private TextField       tfPreco;
    @FXML private TextField       tfDesc;
    @FXML private ChoiceBox<Marca> cbMarca;
    @FXML private TextField       tfPotencia;

    private FonteService service;
    private Fonte        fonte;

    public void setService(FonteService svc) {
        this.service = svc;
    }

    public void setFonte(Fonte f) {
        this.fonte = f;
        if (f != null) {
            tfNome.setText(f.getNome());
            tfPreco.setText(String.valueOf(f.getPreco()));
            tfDesc.setText(f.getDescricao());
            cbMarca.setValue(f.getMarca());
            tfPotencia.setText(String.valueOf(f.getPotencia()));
        }
    }

    @FXML
    public void initialize() {
        cbMarca.getItems().setAll(Marca.values());
    }

    @FXML
    private void onSave() {
        try {
            String nome    = tfNome.getText().trim();
            int preco      = Integer.parseInt(tfPreco.getText().trim());
            String desc    = tfDesc.getText().trim();
            Marca marca    = cbMarca.getValue();
            int potencia   = Integer.parseInt(tfPotencia.getText().trim());

            if (nome.isEmpty() || marca == null) {
                throw new IllegalArgumentException("Nome e marca são obrigatórios.");
            }

            if (fonte != null) {
                service.excluir(fonte.getNome());
            }

            Fonte f2 = ComponenteFactory.criarFonte(
                nome,
                preco,
                desc,
                marca,
                "",
                potencia
            );

            service.salvarOuAtualizar(f2);
            close();
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, "Preço ou potência inválidos.")
                .showAndWait();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Erro: " + ex.getMessage())
                .showAndWait();
        }
    }

    @FXML
    private void onCancel() {
        close();
    }

    private void close() {
        Stage st = (Stage) tfNome.getScene().getWindow();
        st.close();
    }
}
