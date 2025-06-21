package pcbuilder.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pcbuilder.components.Processador;
import pcbuilder.components.Marca;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.service.ProcessadorService;

public class ProcessadorFormController {

    @FXML private TextField    tfNome;
    @FXML private TextField    tfPreco;
    @FXML private TextField    tfDesc;
    @FXML private ChoiceBox<Marca> cbMarca;
    @FXML private TextField    tfSocket;
    @FXML private TextField    tfNucleos;
    @FXML private TextField    tfThreads;

    private ProcessadorService service;
    private Processador processador;

    public void setService(ProcessadorService svc) {
        this.service = svc;
    }

    @FXML
    public void initialize() {
        cbMarca.getItems().setAll(Marca.values());
    }

    public void setProcessador(Processador p) {
        this.processador = p;
        if (p != null) {
            tfNome.setText(p.getNome());
            tfPreco.setText(String.valueOf(p.getPreco()));
            tfDesc.setText(p.getDescricao());
            cbMarca.setValue(p.getMarca());
            tfSocket.setText(p.getSocket());
            tfNucleos.setText(String.valueOf(p.getNucleos()));
            tfThreads.setText(String.valueOf(p.getThreads()));
        }
    }

    @FXML
    private void onSave() {
        try {
            String nome      = tfNome.getText().trim();
            int preco        = Integer.parseInt(tfPreco.getText().trim());
            String desc      = tfDesc.getText().trim();
            Marca marca      = cbMarca.getValue();
            String socket    = tfSocket.getText().trim();
            int nucleos      = Integer.parseInt(tfNucleos.getText().trim());
            int threads      = Integer.parseInt(tfThreads.getText().trim());

            if (nome.isEmpty() || marca == null) {
                throw new IllegalArgumentException("Nome e marca são obrigatórios.");
            }

            if (processador != null) {
                service.excluir(processador.getNome());
            }

            Processador pr = ComponenteFactory.criarProcessador(
                nome,
                preco,
                desc,
                marca,
                "",     
                "",     
                socket,
                nucleos,
                threads
            );

            service.salvarOuAtualizar(pr);
            close();
        } catch (NumberFormatException nfe) {
            new Alert(Alert.AlertType.ERROR,
                "Insira valores numéricos válidos para preço, núcleos ou threads.")
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
