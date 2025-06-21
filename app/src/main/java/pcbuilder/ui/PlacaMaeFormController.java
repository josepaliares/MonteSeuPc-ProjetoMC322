package pcbuilder.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pcbuilder.components.Marca;
import pcbuilder.components.PlacaMae;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.service.PlacaMaeService;

public class PlacaMaeFormController {

    @FXML private TextField tfNome;
    @FXML private TextField tfPreco;
    @FXML private TextField tfDesc;
    @FXML private ChoiceBox<Marca> cbMarca;
    @FXML private TextField tfSocket;
    @FXML private TextField tfChipset;
    @FXML private TextField tfSlots;
    @FXML private TextField tfRamSizePerSlot;
    @FXML private TextField tfRamType;
    @FXML private TextField tfSSDMaxLength;

    private PlacaMaeService service;
    private PlacaMae placaMae;
    private String nomeOriginal = null;

    public void setService(PlacaMaeService svc) {
        this.service = svc;
    }

    @FXML
    public void initialize() {
        cbMarca.getItems().setAll(Marca.values());
    }

    public void setPlacaMae(PlacaMae p) {
        this.placaMae = p;
        if (p != null) {
            nomeOriginal = p.getNome();
            tfNome.setText(p.getNome());
            tfPreco.setText(String.valueOf(p.getPreco()));
            tfDesc.setText(p.getDescricao());
            cbMarca.setValue(p.getMarca());
            tfSocket.setText(p.getSocket());
            tfChipset.setText(p.getChipset());
            tfSlots.setText(String.valueOf(p.getRamSlots()));
            tfRamSizePerSlot.setText(String.valueOf(p.getRamSizePerSlot()));
            tfRamType.setText(p.getRamType());
            tfSSDMaxLength.setText(String.valueOf(p.getSSDMaxLength()));
        }
    }

    @FXML
    private void onSave() {
        try {
            String nome    = tfNome.getText().trim();
            int preco      = Integer.parseInt(tfPreco.getText().trim());
            String desc    = tfDesc.getText().trim();
            Marca marca    = cbMarca.getValue();
            String socket = tfSocket.getText().trim();
            String chipset = tfChipset.getText().trim();
            int slots      = Integer.parseInt(tfSlots.getText().trim());
            int ramSizePerSlot = Integer.parseInt(tfRamSizePerSlot.getText().trim());
            String ramType = tfRamType.getText().trim();
            int ssdMaxLength = Integer.parseInt(tfSSDMaxLength.getText().trim());

            if (nome.isEmpty() || marca == null) {
                throw new IllegalArgumentException("Nome e marca são obrigatórios.");
            }

            if (placaMae != null && nomeOriginal != null) {
                service.excluir(nomeOriginal);
            }

            PlacaMae pm = ComponenteFactory.criarPlacaMae(
                nome, preco, desc, marca, "", socket, chipset, slots, ramSizePerSlot, ramType, ssdMaxLength
            );

            service.salvarOuAtualizar(pm);
            close();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR,
                "Valores numéricos inválidos em preço, slots, tamanho de RAM ou comprimento do SSD.")
                .showAndWait();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR,
                "Erro: " + ex.getMessage())
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
