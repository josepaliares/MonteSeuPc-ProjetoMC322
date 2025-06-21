package pcbuilder.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pcbuilder.components.PlacaDeVideo;
import pcbuilder.components.Marca;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.service.PlacaDeVideoService;

public class PlacaDeVideoFormController {
    @FXML private TextField    tfNome;
    @FXML private TextField    tfPreco;
    @FXML private TextField    tfDesc;
    @FXML private ChoiceBox<Marca> cbMarca;
    @FXML private TextField    tfMemoria;
    @FXML private TextField    tfChipset;

    private PlacaDeVideoService service;
    private PlacaDeVideo placa;
    private String nomeOriginal = null; 
    public void setService(PlacaDeVideoService svc) {
        this.service = svc;
    }

    @FXML
    public void initialize() {
        cbMarca.getItems().setAll(Marca.values());
    }

    public void setPlacaVideo(PlacaDeVideo p) {
        this.placa = p;
        if (p != null) {
            nomeOriginal = p.getNome();
            tfNome.setText(p.getNome());
            tfPreco.setText(String.valueOf(p.getPreco()));
            tfDesc.setText(p.getDescricao());
            cbMarca.setValue(p.getMarca());
            tfMemoria.setText(String.valueOf(p.getMemoria()));
            tfChipset.setText(p.getChipset());
        }
    }

    @FXML
    private void onSave() {
        try {
            String nome     = tfNome.getText().trim();
            int preco       = Integer.parseInt(tfPreco.getText().trim());
            String desc     = tfDesc.getText().trim();
            Marca marca     = cbMarca.getValue();
            int memoriaGB   = Integer.parseInt(tfMemoria.getText().trim());
            String chipset  = tfChipset.getText().trim();

            if (nome.isEmpty() || marca == null) {
                throw new IllegalArgumentException("Nome e marca são obrigatórios.");
            }

            if (placa != null && nomeOriginal != null) {
                service.excluir(nomeOriginal);
            }

            PlacaDeVideo pv = ComponenteFactory.criarPlacaDeVideo(
                nome,
                preco,
                desc,
                marca,
                "",         
                memoriaGB,
                chipset
            );

            service.salvarOuAtualizar(pv);
            close();
        } catch (NumberFormatException nfe) {
            new Alert(Alert.AlertType.ERROR,
                "Número inválido em preço ou memória.").showAndWait();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR,
                "Erro: " + ex.getMessage()).showAndWait();
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
