package pcbuilder.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pcbuilder.components.Gabinete;
import pcbuilder.components.Marca;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.service.GabineteService;

public class GabineteFormController {
    @FXML private TextField    tfNome;
    @FXML private TextField    tfPreco;
    @FXML private TextField    tfDesc;
    @FXML private ChoiceBox<Marca> cbMarca;
    @FXML private TextField    tfAltura;
    @FXML private TextField    tfLargura;
    @FXML private TextField    tfProfund;

    private GabineteService service;
    private Gabinete gabinete;

    public void setService(GabineteService svc) {
        this.service = svc;
    }

    @FXML
    public void initialize() {
        cbMarca.getItems().setAll(Marca.values());
    }

    public void setGabinete(Gabinete g) {
        this.gabinete = g;
        if (g != null) {
            tfNome.setText(g.getNome());
            tfPreco.setText(String.valueOf(g.getPreco()));
            tfDesc.setText(g.getDescricao());
            cbMarca.setValue(g.getMarca());
            tfAltura.setText(String.valueOf(g.getAltura()));
            tfLargura.setText(String.valueOf(g.getLargura()));
            tfProfund.setText(String.valueOf(g.getProfundidade()));
        }
    }

   @FXML
    private void onSave() {
        try {
            String nome   = tfNome.getText().trim();
            int preco     = Integer.parseInt(tfPreco.getText().trim());
            String desc   = tfDesc.getText().trim();
            Marca marca   = cbMarca.getValue();
            int altura    = Integer.parseInt(tfAltura.getText().trim());
            int largura   = Integer.parseInt(tfLargura.getText().trim());
            int profund   = Integer.parseInt(tfProfund.getText().trim());

            if (nome.isEmpty() || marca == null) {
                throw new IllegalArgumentException("Nome e marca são obrigatórios.");
            }

            if (gabinete != null) {
                service.excluir(gabinete.getNome());
            }

            Gabinete g2 = ComponenteFactory.criarGabinete(
                nome,
                preco,
                desc,
                marca,
                "",      
                altura,
                largura,
                profund
            );

            service.salvarOuAtualizar(g2);
            close();
        } catch (NumberFormatException nfe) {
            new Alert(Alert.AlertType.ERROR,
                "Insira números válidos para preço e dimensões.")
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
