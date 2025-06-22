package pcbuilder.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pcbuilder.components.MemoriaRAM;
import pcbuilder.components.Marca;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.service.MemoriaRAMService;

public class MemoriaRAMFormController {
    @FXML private TextField       tfNome;
    @FXML private TextField       tfPreco;
    @FXML private TextField       tfDesc;
    @FXML private ChoiceBox<Marca> cbMarca;
    @FXML private TextField       tfCapacidade;
    @FXML private TextField       tfFrequencia;
    @FXML private TextField       tfTipo;

    private MemoriaRAMService service;
    private MemoriaRAM memoria;

    public void setService(MemoriaRAMService svc) {
        this.service = svc;
    }

    @FXML
    public void initialize() {
        cbMarca.getItems().setAll(Marca.values());
    }

    public void setMemoriaRAM(MemoriaRAM m) {
        this.memoria = m;
        if (m != null) {
            tfNome.setText(m.getNome());
            tfPreco.setText(String.valueOf(m.getPreco()));
            tfDesc.setText(m.getDescricao());
            cbMarca.setValue(m.getMarca());
            tfCapacidade.setText(String.valueOf(m.getCapacidade()));
            tfFrequencia.setText(String.valueOf(m.getFrequencia()));
            tfTipo.setText(m.getTipo());
        }
    }

    @FXML
    private void onSave() {
        try {
            String nome      = tfNome.getText().trim();
            int preco        = Integer.parseInt(tfPreco.getText().trim());
            String descricao = tfDesc.getText().trim();
            Marca marca      = cbMarca.getValue();
            int capacidade   = Integer.parseInt(tfCapacidade.getText().trim());
            int frequencia   = Integer.parseInt(tfFrequencia.getText().trim());
            String tipo      = tfTipo.getText().trim();

            if (nome.isEmpty() || marca == null || tipo.isEmpty()) {
                throw new IllegalArgumentException("Preencha nome, marca e tipo.");
            }

            if (memoria != null) {
                service.excluir(memoria.getNome());
            }

            MemoriaRAM m2 = ComponenteFactory.criarMemoriaRAM(
                nome,
                preco,
                descricao,
                marca,
                "",     
                capacidade,
                frequencia,
                tipo
            );

            service.salvarOuAtualizar(m2);
            close();
        } catch (NumberFormatException nfe) {
            new Alert(Alert.AlertType.ERROR,
                "Insira números válidos para preço, capacidade e frequência.")
                .showAndWait();
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
