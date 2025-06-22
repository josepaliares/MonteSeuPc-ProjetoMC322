package pcbuilder.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pcbuilder.components.Cooler;
import pcbuilder.components.Marca;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.service.CoolerService;

public class CoolerFormController {
    @FXML private TextField       tfNome;
    @FXML private TextField       tfPreco;
    @FXML private TextField       tfDesc;
    @FXML private ChoiceBox<Marca> cbMarca;
    @FXML private TextField       tfTipo;

    private CoolerService service;
    private Cooler cooler;

    public void setService(CoolerService svc) {
        this.service = svc;
    }

    @FXML
    public void initialize() {
        cbMarca.getItems().setAll(Marca.values());
    }

    public void setCooler(Cooler c) {
        this.cooler = c;
        if (c != null) {
            tfNome.setText(c.getNome());
            tfPreco.setText(String.valueOf(c.getPreco()));
            tfDesc.setText(c.getDescricao());
            cbMarca.setValue(c.getMarca());
            tfTipo.setText(c.getTipo());
        }
    }

    @FXML
    private void onSave() {
        try {
            String nome   = tfNome.getText().trim();
            int preco     = Integer.parseInt(tfPreco.getText().trim());
            String desc   = tfDesc.getText().trim();
            Marca marca   = cbMarca.getValue();
            String tipo   = tfTipo.getText().trim();

            if (nome.isEmpty() || marca == null || tipo.isEmpty()) {
                throw new IllegalArgumentException("Nome, marca e tipo são obrigatórios.");
            }

            if (cooler != null) {
                service.excluir(cooler.getNome());
            }

            Cooler c2 = ComponenteFactory.criarCooler(
                nome,
                preco,
                desc,
                marca,
                "", 
                tipo
            );

            service.salvarOuAtualizar(c2);
            close();
        } catch (NumberFormatException nfe) {
            new Alert(Alert.AlertType.ERROR,
                "Preço inválido.").showAndWait();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR,
                "Erro ao salvar: " + ex.getMessage())
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
