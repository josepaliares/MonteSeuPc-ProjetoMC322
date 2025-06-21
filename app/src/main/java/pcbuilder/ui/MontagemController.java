package pcbuilder.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pcbuilder.computador.Computador;
import pcbuilder.computador.ComputadorBuilder;
import pcbuilder.components.*;
import pcbuilder.service.*;

public class MontagemController {

    @FXML private ComboBox<Fonte> cbFonte;
    @FXML private ComboBox<PlacaMae> cbPlacaMae;
    @FXML private ComboBox<PlacaDeVideo> cbPlacaVideo;
    @FXML private ComboBox<Processador> cbProcessador;
    @FXML private ComboBox<Gabinete> cbGabinete;
    @FXML private ListView<Cooler> lvCoolers;
    @FXML private ListView<MemoriaRAM> lvMemorias;
    @FXML private TextArea taResumo;

    private final FonteService        fonteSvc    = new FonteService();
    private final PlacaMaeService     placaMaeSvc = new PlacaMaeService();
    private final PlacaDeVideoService   placaVidSvc = new PlacaDeVideoService();
    private final ProcessadorService  procSvc     = new ProcessadorService();
    private final GabineteService     gabSvc      = new GabineteService();
    private final CoolerService       coolerSvc   = new CoolerService();
    private final MemoriaRAMService   ramSvc      = new MemoriaRAMService();

    @FXML
    public void initialize() {
        cbFonte.setItems(FXCollections.observableArrayList(fonteSvc.buscarTodos()));
        cbPlacaMae.setItems(FXCollections.observableArrayList(placaMaeSvc.buscarTodos()));
        cbPlacaVideo.setItems(FXCollections.observableArrayList(placaVidSvc.buscarTodos()));
        cbProcessador.setItems(FXCollections.observableArrayList(procSvc.buscarTodos()));
        cbGabinete.setItems(FXCollections.observableArrayList(gabSvc.buscarTodos()));

        lvCoolers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvCoolers.setItems(FXCollections.observableArrayList(coolerSvc.buscarTodos()));
        lvMemorias.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvMemorias.setItems(FXCollections.observableArrayList(ramSvc.buscarTodos()));
    }

    @FXML
    private void onMontar() {
        ComputadorBuilder b = new ComputadorBuilder()
            .adicionarFonte(cbFonte.getValue())
            .adicionarPlacaMae(cbPlacaMae.getValue())
            .adicionarProcessador(cbProcessador.getValue())
            .adicionarGabinete(cbGabinete.getValue());

        if (cbPlacaVideo.getValue() != null)
            b.adicionarPlacaDeVideo(cbPlacaVideo.getValue());

        lvCoolers.getSelectionModel().getSelectedItems()
                 .forEach(b::adicionarCooler);
        lvMemorias.getSelectionModel().getSelectedItems()
                  .forEach(b::adicionarMemoriaRAM);

        Computador pc = b.build();
        showResumo(pc);
    }

    private void showResumo(Computador pc) {
        StringBuilder sb = new StringBuilder()
            .append("→ Fonte: ").append(pc.getFonte().getNome()).append("\n")
            .append("→ Placa-Mãe: ").append(pc.getPlacaMae().getNome()).append("\n");
        if (pc.getPlacaDeVideo() != null)
            sb.append("→ Vídeo: ").append(pc.getPlacaDeVideo().getNome()).append("\n");
        sb.append("→ Processador: ").append(pc.getProcessador().getNome()).append("\n")
          .append("→ Gabinete: ").append(pc.getGabinete().getNome()).append("\n")
          .append("→ Coolers: ");
        pc.getCoolers().forEach(c -> sb.append(c.getNome()).append(", "));
        sb.append("\n→ RAM: ");
        pc.getMemoriasRAM().forEach(m -> sb.append(m.getNome()).append(", "));
        sb.append(String.format("\n\nPreço total: R$ %.2f", pc.getPrecoTotal() / 100.0));
        taResumo.setText(sb.toString());
    }
}
