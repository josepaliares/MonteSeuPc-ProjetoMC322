package pcbuilder.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pcbuilder.components.SSDNVME;
import pcbuilder.components.Marca;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.service.SSDNVMEService;

public class SSDNVMEFormController {

    @FXML private TextField       tfNome;
    @FXML private TextField       tfPreco;
    @FXML private TextField       tfDesc;
    @FXML private ChoiceBox<Marca> cbMarca;
    @FXML private TextField       tfCapacidade;
    @FXML private TextField       tfLength;

    private SSDNVMEService service;
    private SSDNVME ssd;

    public void setService(SSDNVMEService svc) {
        this.service = svc;
    }

    public void setSSDNVME(SSDNVME s) {
        this.ssd = s;
        if (s != null) {
            tfNome.setText(s.getNome());
            tfPreco.setText(String.valueOf(s.getPreco()));
            tfDesc.setText(s.getDescricao());
            cbMarca.setValue(s.getMarca());
            tfCapacidade.setText(String.valueOf(s.getCapacidade()));
            tfLength.setText(String.valueOf(s.getLength()));
        }
    }

    @FXML
    public void initialize() {
        cbMarca.getItems().setAll(Marca.values());
    }

    @FXML
    private void onSave() {
        try {
            String nome      = tfNome.getText().trim();
            int preco        = Integer.parseInt(tfPreco.getText().trim());
            String desc      = tfDesc.getText().trim();
            Marca marca      = cbMarca.getValue();
            int capacidade   = Integer.parseInt(tfCapacidade.getText().trim());
            int length       = Integer.parseInt(tfLength.getText().trim());

            if (nome.isEmpty() || marca == null) {
                throw new IllegalArgumentException("Nome e marca são obrigatórios.");
            }

            if (ssd != null) {
                service.excluir(ssd.getNome());
            }

            SSDNVME novoSSD = ComponenteFactory.criarSSDNVME(
                nome,
                preco,
                desc,
                marca,
                "", 
                capacidade,
                length
            );

            service.salvarOuAtualizar(novoSSD);
            close();
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, "Preço, capacidade ou comprimento inválidos.")
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
