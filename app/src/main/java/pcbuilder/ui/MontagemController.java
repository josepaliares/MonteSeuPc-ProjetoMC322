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
    @FXML private ComboBox<SSDNVME> cbSSD;
    @FXML private ListView<Cooler> lvCoolers;
    @FXML private ListView<MemoriaRAM> lvMemorias;
    @FXML private TextArea taResumo;

    private final FonteService        fonteSvc    = new FonteService();
    private final PlacaMaeService     placaMaeSvc = new PlacaMaeService();
    private final PlacaDeVideoService placaVidSvc = new PlacaDeVideoService();
    private final ProcessadorService  procSvc     = new ProcessadorService();
    private final GabineteService     gabSvc      = new GabineteService();
    private final SSDNVMEService      ssdSvc      = new SSDNVMEService();
    private final CoolerService       coolerSvc   = new CoolerService();
    private final MemoriaRAMService   ramSvc      = new MemoriaRAMService();

    private final Compativel compat = new Compativel();

    @FXML
    public void initialize() {  
        cbFonte.setItems(FXCollections.observableArrayList(fonteSvc.buscarTodos()));
        cbPlacaMae.setItems(FXCollections.observableArrayList(placaMaeSvc.buscarTodos()));
        cbPlacaVideo.setItems(FXCollections.observableArrayList(placaVidSvc.buscarTodos()));
        cbProcessador.setItems(FXCollections.observableArrayList(procSvc.buscarTodos()));
        cbGabinete.setItems(FXCollections.observableArrayList(gabSvc.buscarTodos()));
        cbSSD.setItems(FXCollections.observableArrayList(ssdSvc.buscarTodos()));

        lvCoolers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvCoolers.setItems(FXCollections.observableArrayList(coolerSvc.buscarTodos()));
        lvMemorias.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvMemorias.setItems(FXCollections.observableArrayList(ramSvc.buscarTodos()));

        cbFonte.setCellFactory(lv -> new ListCell<Fonte>() {
            @Override
            protected void updateItem(Fonte item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNome() + " (R$ " + String.format("%.2f", item.getPreco() / 100.0) + ")");
                }
            }
        });
        cbFonte.setButtonCell(cbFonte.getCellFactory().call(null));

        cbPlacaMae.setCellFactory(lv -> new ListCell<PlacaMae>() {
            @Override
            protected void updateItem(PlacaMae item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNome() + " (R$ " + String.format("%.2f", item.getPreco() / 100.0) + ")");
                }
            }
        });
        cbPlacaMae.setButtonCell(cbPlacaMae.getCellFactory().call(null));

        cbPlacaVideo.setCellFactory(lv -> new ListCell<PlacaDeVideo>() {
            @Override
            protected void updateItem(PlacaDeVideo item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNome() + " (R$ " + String.format("%.2f", item.getPreco() / 100.0) + ")");
                }
            }
        });
        cbPlacaVideo.setButtonCell(cbPlacaVideo.getCellFactory().call(null));

        cbProcessador.setCellFactory(lv -> new ListCell<Processador>() {
            @Override
            protected void updateItem(Processador item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.getNome() + " (R$ " + String.format("%.2f", item.getPreco() / 100.0) + ")");
                    PlacaMae placaMae = cbPlacaMae.getValue();
                    if (placaMae != null && compat.compativel(item, placaMae)) {
                        setStyle("-fx-background-color: #b7fcb7;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });
        cbProcessador.setButtonCell(cbProcessador.getCellFactory().call(null));

        cbGabinete.setCellFactory(lv -> new ListCell<Gabinete>() {
            @Override
            protected void updateItem(Gabinete item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNome() + " (R$ " + String.format("%.2f", item.getPreco() / 100.0) + ")");
                }
            }
        });
        cbGabinete.setButtonCell(cbGabinete.getCellFactory().call(null));

        cbSSD.setCellFactory(lv -> new ListCell<SSDNVME>() {
            @Override
            protected void updateItem(SSDNVME item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.getNome() + " (R$ " + String.format("%.2f", item.getPreco() / 100.0) + ")");
                    PlacaMae placaMae = cbPlacaMae.getValue();
                    if (placaMae != null && compat.compativel(placaMae, item)) {
                        setStyle("-fx-background-color: #b7fcb7;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });
        cbSSD.setButtonCell(cbSSD.getCellFactory().call(null));

        lvCoolers.setCellFactory(lv -> new ListCell<Cooler>() {
            @Override
            protected void updateItem(Cooler item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNome() + " (R$ " + String.format("%.2f", item.getPreco() / 100.0) + ")");
                }
            }
        });

        lvMemorias.setCellFactory(lv -> new ListCell<MemoriaRAM>() {
            @Override
            protected void updateItem(MemoriaRAM item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.getNome() + " (R$ " + String.format("%.2f", item.getPreco() / 100.0) + ")");
                    PlacaMae placaMae = cbPlacaMae.getValue();
                    if (placaMae != null && compat.compativel(placaMae, item)) {
                        setStyle("-fx-background-color: #b7fcb7;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        cbPlacaMae.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            lvMemorias.refresh();
            cbProcessador.setButtonCell(cbProcessador.getCellFactory().call(null));
            cbSSD.setButtonCell(cbSSD.getCellFactory().call(null));
        });

        cbProcessador.valueProperty().addListener((obs, oldVal, newVal) -> {
            PlacaMae placaMae = cbPlacaMae.getValue();
            if (placaMae != null && newVal != null && !compat.compativel(newVal, placaMae)) {
                alertaIncompatibilidade("Processador", "Atenção: o processador selecionado NÃO é compatível com a placa-mãe!");
            }
        });

        cbSSD.valueProperty().addListener((obs, oldVal, newVal) -> {
            PlacaMae placaMae = cbPlacaMae.getValue();
            if (placaMae != null && newVal != null && !compat.compativel(placaMae, newVal)) {
                alertaIncompatibilidade("SSD NVMe", "Atenção: o SSD NVMe selecionado NÃO é compatível com a placa-mãe!");
            }
        });

        lvMemorias.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            PlacaMae placaMae = cbPlacaMae.getValue();
            if (placaMae != null && newVal != null && !compat.compativel(placaMae, newVal)) {
                alertaIncompatibilidade("Memória RAM", "Atenção: a memória RAM selecionada NÃO é compatível com a placa-mãe!");
            }
        });
    }

    @FXML
    private void onMontar() {
        ComputadorBuilder b = new ComputadorBuilder()
            .adicionarFonte(cbFonte.getValue())
            .adicionarPlacaMae(cbPlacaMae.getValue())
            .adicionarProcessador(cbProcessador.getValue())
            .adicionarGabinete(cbGabinete.getValue())
            .adicionarSSD(cbSSD.getValue());

        if (cbPlacaVideo.getValue() != null)
            b.adicionarPlacaDeVideo(cbPlacaVideo.getValue());

        lvCoolers.getSelectionModel().getSelectedItems()
                 .forEach(b::adicionarCooler);
        lvMemorias.getSelectionModel().getSelectedItems()
                  .forEach(b::adicionarMemoriaRAM);

        Computador pc = b.build();
        showResumo(pc);
    }

    @FXML
    private void onLimpar() {
        cbFonte.getSelectionModel().clearSelection();
        cbPlacaMae.getSelectionModel().clearSelection();
        cbPlacaVideo.getSelectionModel().clearSelection();
        cbProcessador.getSelectionModel().clearSelection();
        cbGabinete.getSelectionModel().clearSelection();
        cbSSD.getSelectionModel().clearSelection();
        lvCoolers.getSelectionModel().clearSelection();
        lvMemorias.getSelectionModel().clearSelection();
        taResumo.clear();
    }

    private void alertaIncompatibilidade(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo + " incompatível");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void showResumo(Computador pc) {
        StringBuilder sb = new StringBuilder()
            .append("→ Fonte: ").append(pc.getFonte().getNome()).append("\n")
            .append("→ Placa-Mãe: ").append(pc.getPlacaMae().getNome()).append("\n");
        if (pc.getPlacaDeVideo() != null)
            sb.append("→ Vídeo: ").append(pc.getPlacaDeVideo().getNome()).append("\n");
        sb.append("→ Processador: ").append(pc.getProcessador().getNome()).append("\n")
          .append("→ Gabinete: ").append(pc.getGabinete().getNome()).append("\n")
          .append("→ SSD: ").append(pc.getSSD() != null ? pc.getSSD().getNome() : "Nenhum").append("\n")
          .append("→ Coolers: ");
        pc.getCoolers().forEach(c -> sb.append(c.getNome()).append(", "));
        sb.append("\n→ RAM: ");
        pc.getMemoriasRAM().forEach(m -> sb.append(m.getNome()).append(", "));
        sb.append(String.format("\n\nPreço total: R$ %.2f", pc.getPrecoTotal() / 100.0));
        taResumo.setText(sb.toString());
    }
}
