package pcbuilder.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pcbuilder.computador.Computador;
import pcbuilder.computador.ComputadorBuilder;
import pcbuilder.components.*;
import pcbuilder.service.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class MontagemController {

    @FXML private ComboBox<Processador> cbProcessador;
    @FXML private ComboBox<PlacaMae> cbPlacaMae;
    @FXML private ListView<MemoriaRAM> lvMemorias;
    @FXML private ComboBox<PlacaDeVideo> cbPlacaVideo;
    @FXML private ComboBox<SSDNVME> cbSSD;
    @FXML private ComboBox<Gabinete> cbGabinete;
    @FXML private ComboBox<Fonte> cbFonte;
    @FXML private ListView<Cooler> lvCoolers;
    @FXML private ListView<String> lvResumo;
    @FXML private Label lbAlertaCompatibilidade;
    @FXML private Label lbPrecoTotal;
    @FXML private Spinner<Integer> spQuantidadeMemoria;
    @FXML private Spinner<Integer> spQuantidadeCooler;

    @FXML private TextArea taDescricao;

    private final FonteService fonteSvc = new FonteService();
    private final PlacaMaeService placaMaeSvc = new PlacaMaeService();
    private final PlacaDeVideoService placaVidSvc = new PlacaDeVideoService();
    private final ProcessadorService procSvc = new ProcessadorService();
    private final GabineteService gabSvc = new GabineteService();
    private final SSDNVMEService ssdSvc = new SSDNVMEService();
    private final CoolerService coolerSvc = new CoolerService();
    private final MemoriaRAMService ramSvc = new MemoriaRAMService();

    private final Compativel compat = new Compativel();
    private ObservableList<String> resumoList = FXCollections.observableArrayList();
    private final Map<MemoriaRAM, Integer> memoriaSelecionada = new LinkedHashMap<>();
    private final Map<Cooler, Integer> coolerSelecionado = new LinkedHashMap<>();

    @FXML
    public void initialize() {
        cbProcessador.setItems(FXCollections.observableArrayList(procSvc.buscarTodos()));
        cbPlacaMae.setItems(FXCollections.observableArrayList(placaMaeSvc.buscarTodos()));
        cbPlacaVideo.setItems(FXCollections.observableArrayList(placaVidSvc.buscarTodos()));
        cbGabinete.setItems(FXCollections.observableArrayList(gabSvc.buscarTodos()));
        cbFonte.setItems(FXCollections.observableArrayList(fonteSvc.buscarTodos()));
        cbSSD.setItems(FXCollections.observableArrayList(ssdSvc.buscarTodos()));
        lvCoolers.setItems(FXCollections.observableArrayList(coolerSvc.buscarTodos()));
        lvMemorias.setItems(FXCollections.observableArrayList(ramSvc.buscarTodos()));

        lvCoolers.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvMemorias.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        if (spQuantidadeMemoria != null) {
            spQuantidadeMemoria.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8, 1));
        }
        if (spQuantidadeCooler != null) {
            spQuantidadeCooler.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8, 1));
        }

        setCellFactories();

        cbProcessador.valueProperty().addListener((obs, oldVal, newVal) -> atualizarDescricao(newVal));
        cbPlacaMae.valueProperty().addListener((obs, oldVal, newVal) -> atualizarDescricao(newVal));
        cbPlacaVideo.valueProperty().addListener((obs, oldVal, newVal) -> atualizarDescricao(newVal));
        cbGabinete.valueProperty().addListener((obs, oldVal, newVal) -> atualizarDescricao(newVal));
        cbFonte.valueProperty().addListener((obs, oldVal, newVal) -> atualizarDescricao(newVal));
        cbSSD.valueProperty().addListener((obs, oldVal, newVal) -> atualizarDescricao(newVal));
        lvCoolers.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> atualizarDescricao(newVal));
        lvMemorias.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> atualizarDescricao(newVal));

        cbProcessador.setOnAction(e -> { onProcessadorSelecionado(); atualizarResumo(); });
        cbPlacaMae.setOnAction(e -> { onPlacaMaeSelecionada(); atualizarResumo(); });
        cbPlacaVideo.setOnAction(e -> atualizarResumo());
        cbSSD.setOnAction(e -> atualizarResumo());
        cbGabinete.setOnAction(e -> atualizarResumo());
        cbFonte.setOnAction(e -> atualizarResumo());

        cbSSD.valueProperty().addListener((obs, oldVal, newVal) -> {
            PlacaMae placaMae = cbPlacaMae.getValue();
            if (placaMae != null && newVal != null && !compat.compativel(placaMae, newVal)) {
                setAlerta("SSD NVMe selecionado NÃO é compatível com a placa-mãe!");
            } else {
                setAlerta("");
            }
        });
        lvMemorias.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            PlacaMae placaMae = cbPlacaMae.getValue();
            if (placaMae != null && newVal != null && !compat.compativel(placaMae, newVal)) {
                setAlerta("Memória RAM selecionada NÃO é compatível com a placa-mãe!");
            } else {
                setAlerta("");
            }
        });

        cbPlacaMae.setDisable(true);
        lvMemorias.setDisable(true);
        cbSSD.setDisable(true);

        lvResumo.setItems(resumoList);

        lbAlertaCompatibilidade.setText("");
        lbPrecoTotal.setText("");
        taDescricao.setText("");
    }

    private void setCellFactories() {
        cbProcessador.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Processador item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome() + " (R$ " + formatarPreco(item.getPreco()) + ")");
            }
        }); cbProcessador.setButtonCell(cbProcessador.getCellFactory().call(null));
        cbPlacaMae.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(PlacaMae item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome() + " (R$ " + formatarPreco(item.getPreco()) + ")");
            }
        }); cbPlacaMae.setButtonCell(cbPlacaMae.getCellFactory().call(null));
        cbPlacaVideo.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(PlacaDeVideo item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome() + " (R$ " + formatarPreco(item.getPreco()) + ")");
            }
        }); cbPlacaVideo.setButtonCell(cbPlacaVideo.getCellFactory().call(null));
        cbGabinete.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Gabinete item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome() + " (R$ " + formatarPreco(item.getPreco()) + ")");
            }
        }); cbGabinete.setButtonCell(cbGabinete.getCellFactory().call(null));
        cbFonte.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Fonte item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome() + " (R$ " + formatarPreco(item.getPreco()) + ")");
            }
        }); cbFonte.setButtonCell(cbFonte.getCellFactory().call(null));
        cbSSD.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(SSDNVME item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome() + " (R$ " + formatarPreco(item.getPreco()) + ")");
            }
        }); cbSSD.setButtonCell(cbSSD.getCellFactory().call(null));
        lvCoolers.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Cooler item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome() + " (R$ " + formatarPreco(item.getPreco()) + ")");
            }
        });
        lvMemorias.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(MemoriaRAM item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome() + " (R$ " + formatarPreco(item.getPreco()) + ")");
            }
        });
    }

    private String formatarPreco(int precoCentavos) {
        return String.format("%.2f", precoCentavos / 100.0);
    }

    private void atualizarDescricao(Object item) {
        if (item == null) {
            taDescricao.setText("");
        } else if (item instanceof Componente) {
            taDescricao.setText(((Componente) item).getDescricao());
        } else {
            taDescricao.setText("");
        }
    }

    @FXML
    private void onProcessadorSelecionado() {
        Processador proc = cbProcessador.getValue();
        if (proc != null) {
            cbPlacaMae.setDisable(false);
            cbPlacaMae.setItems(FXCollections.observableArrayList(
                placaMaeSvc.buscarTodos().stream()
                    .filter(m -> compat.compativel(proc, m))
                    .toList()
            ));
            cbPlacaMae.getSelectionModel().clearSelection();
            lvMemorias.setDisable(true);
            cbSSD.setDisable(true);
            setAlerta("");
            atualizarResumo();
        }
    }

    @FXML
    private void onPlacaMaeSelecionada() {
        PlacaMae placaMae = cbPlacaMae.getValue();
        if (placaMae != null) {
            lvMemorias.setDisable(false);
            cbSSD.setDisable(false);

            lvMemorias.setItems(FXCollections.observableArrayList(
                ramSvc.buscarTodos().stream()
                    .filter(ram -> compat.compativel(placaMae, ram))
                    .toList()
            ));
            lvMemorias.getSelectionModel().clearSelection();

            cbSSD.setItems(FXCollections.observableArrayList(
                ssdSvc.buscarTodos().stream()
                    .filter(ssd -> compat.compativel(placaMae, ssd))
                    .toList()
            ));
            cbSSD.getSelectionModel().clearSelection();

            setAlerta("");
            atualizarResumo();
        }
    }

    @FXML
    private void onAdicionarMemoria() {
        MemoriaRAM ram = lvMemorias.getSelectionModel().getSelectedItem();
        if (ram != null && spQuantidadeMemoria != null) {
            memoriaSelecionada.put(ram, spQuantidadeMemoria.getValue());
            atualizarResumo();
        }
    }

    @FXML
    private void onAdicionarCooler() {
        Cooler cooler = lvCoolers.getSelectionModel().getSelectedItem();
        if (cooler != null && spQuantidadeCooler != null) {
            coolerSelecionado.put(cooler, spQuantidadeCooler.getValue());
            atualizarResumo();
        }
    }

    @FXML
    private void onMontar() {
        try {
            ComputadorBuilder b = new ComputadorBuilder()
                .adicionarFonte(cbFonte.getValue())
                .adicionarPlacaMae(cbPlacaMae.getValue())
                .adicionarProcessador(cbProcessador.getValue())
                .adicionarGabinete(cbGabinete.getValue())
                .adicionarSSD(cbSSD.getValue());

            if (cbPlacaVideo.getValue() != null)
                b.adicionarPlacaDeVideo(cbPlacaVideo.getValue());

            lvCoolers.getSelectionModel().getSelectedItems().forEach(b::adicionarCooler);
            lvMemorias.getSelectionModel().getSelectedItems().forEach(b::adicionarMemoriaRAM);

            Computador pc = b.build();
            atualizarResumo(pc);
            lbPrecoTotal.setText(String.format("Preço total: R$ %.2f", pc.getPrecoTotal() / 100.0));
            setAlertaSucesso("PC montado com sucesso!");
        } catch (Exception ex) {
            setAlertaErro("Complete todas as etapas obrigatórias!");
        }
    }

    private void setAlertaSucesso(String texto) {
        lbAlertaCompatibilidade.setText(texto);
        lbAlertaCompatibilidade.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
    }

    private void setAlertaErro(String texto) {
        lbAlertaCompatibilidade.setText(texto);
        lbAlertaCompatibilidade.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
    }


    @FXML
    private void onLimpar() {
        cbProcessador.getSelectionModel().clearSelection();
        cbPlacaMae.getSelectionModel().clearSelection();
        lvMemorias.getSelectionModel().clearSelection();
        cbPlacaVideo.getSelectionModel().clearSelection();
        cbSSD.getSelectionModel().clearSelection();
        cbGabinete.getSelectionModel().clearSelection();
        cbFonte.getSelectionModel().clearSelection();
        lvCoolers.getSelectionModel().clearSelection();
        resumoList.clear();
        lbPrecoTotal.setText("");
        setAlerta("");
        cbPlacaMae.setDisable(true);
        lvMemorias.setDisable(true);
        cbSSD.setDisable(true);

        memoriaSelecionada.clear();
        coolerSelecionado.clear();
    }

    private void setAlerta(String texto) {
        lbAlertaCompatibilidade.setText(texto);
    }

    private void atualizarResumo() {
        resumoList.clear();
        int total = 0;

        if (cbProcessador.getValue() != null) {
            resumoList.add("Processador: " + cbProcessador.getValue().getNome()
                + " (R$ " + formatarPreco(cbProcessador.getValue().getPreco()) + ")");
            total += cbProcessador.getValue().getPreco();
        }
        if (cbPlacaMae.getValue() != null) {
            resumoList.add("Placa-mãe: " + cbPlacaMae.getValue().getNome()
                + " (R$ " + formatarPreco(cbPlacaMae.getValue().getPreco()) + ")");
            total += cbPlacaMae.getValue().getPreco();
        }
        if (!memoriaSelecionada.isEmpty()) {
            StringBuilder sb = new StringBuilder("Memória RAM: ");
            for (Map.Entry<MemoriaRAM, Integer> entry : memoriaSelecionada.entrySet()) {
                sb.append(entry.getKey().getNome()).append(" x").append(entry.getValue())
                  .append(" (R$ ").append(formatarPreco(entry.getKey().getPreco())).append("), ");
                total += entry.getKey().getPreco() * entry.getValue();
            }
            resumoList.add(sb.substring(0, sb.length() - 2));
        }
        if (cbPlacaVideo.getValue() != null) {
            resumoList.add("Placa de Vídeo: " + cbPlacaVideo.getValue().getNome()
                + " (R$ " + formatarPreco(cbPlacaVideo.getValue().getPreco()) + ")");
            total += cbPlacaVideo.getValue().getPreco();
        }
        if (cbSSD.getValue() != null) {
            resumoList.add("SSD NVMe: " + cbSSD.getValue().getNome()
                + " (R$ " + formatarPreco(cbSSD.getValue().getPreco()) + ")");
            total += cbSSD.getValue().getPreco();
        }
        if (cbGabinete.getValue() != null) {
            resumoList.add("Gabinete: " + cbGabinete.getValue().getNome()
                + " (R$ " + formatarPreco(cbGabinete.getValue().getPreco()) + ")");
            total += cbGabinete.getValue().getPreco();
        }
        if (cbFonte.getValue() != null) {
            resumoList.add("Fonte: " + cbFonte.getValue().getNome()
                + " (R$ " + formatarPreco(cbFonte.getValue().getPreco()) + ")");
            total += cbFonte.getValue().getPreco();
        }
        if (!coolerSelecionado.isEmpty()) {
            StringBuilder sb = new StringBuilder("Coolers: ");
            for (Map.Entry<Cooler, Integer> entry : coolerSelecionado.entrySet()) {
                sb.append(entry.getKey().getNome()).append(" x").append(entry.getValue())
                  .append(" (R$ ").append(formatarPreco(entry.getKey().getPreco())).append("), ");
                total += entry.getKey().getPreco() * entry.getValue();
            }
            resumoList.add(sb.substring(0, sb.length() - 2));
        }

        if (total > 0) {
            lbPrecoTotal.setText("Preço parcial: R$ " + formatarPreco(total));
        } else {
            lbPrecoTotal.setText("");
        }
    }

    private void atualizarResumo(Computador pc) {
        resumoList.clear();
        resumoList.add("→ Fonte: " + pc.getFonte().getNome() + " (R$ " + formatarPreco(pc.getFonte().getPreco()) + ")");
        resumoList.add("→ Placa-Mãe: " + pc.getPlacaMae().getNome() + " (R$ " + formatarPreco(pc.getPlacaMae().getPreco()) + ")");
        if (pc.getPlacaDeVideo() != null)
            resumoList.add("→ Vídeo: " + pc.getPlacaDeVideo().getNome() + " (R$ " + formatarPreco(pc.getPlacaDeVideo().getPreco()) + ")");
        resumoList.add("→ Processador: " + pc.getProcessador().getNome() + " (R$ " + formatarPreco(pc.getProcessador().getPreco()) + ")");
        resumoList.add("→ Gabinete: " + pc.getGabinete().getNome() + " (R$ " + formatarPreco(pc.getGabinete().getPreco()) + ")");
        resumoList.add("→ SSD: " + (pc.getSSD() != null ? pc.getSSD().getNome() + " (R$ " + formatarPreco(pc.getSSD().getPreco()) + ")" : "Nenhum"));
        Map<String, Long> rams = pc.getMemoriasRAM().stream()
                .collect(java.util.stream.Collectors.groupingBy(MemoriaRAM::getNome, java.util.stream.Collectors.counting()));
        Map<String, Long> cools = pc.getCoolers().stream()
                .collect(java.util.stream.Collectors.groupingBy(Cooler::getNome, java.util.stream.Collectors.counting()));
        resumoList.add("→ RAM: " + (rams.isEmpty() ? "Nenhum" :
                rams.entrySet().stream().map(e -> e.getKey() + " x" + e.getValue()).reduce((a,b)->a+", "+b).orElse("")));
        resumoList.add("→ Coolers: " + (cools.isEmpty() ? "Nenhum" :
                cools.entrySet().stream().map(e -> e.getKey() + " x" + e.getValue()).reduce((a,b)->a+", "+b).orElse("")));
    }
}
