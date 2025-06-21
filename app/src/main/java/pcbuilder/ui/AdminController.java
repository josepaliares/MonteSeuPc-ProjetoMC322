package pcbuilder.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pcbuilder.components.*;
import pcbuilder.service.*;
import java.io.IOException;

public class AdminController {

    @FXML private TabPane tabPane;
    
    // --- Serviços compartilhados ---
    private final FonteService       fonteSvc     = new FonteService();
    private final PlacaMaeService    placaMaeSvc  = new PlacaMaeService();
    private final PlacaDeVideoService placaVidSvc = new PlacaDeVideoService();
    private final ProcessadorService procSvc      = new ProcessadorService();
    private final GabineteService    gabSvc       = new GabineteService();
    private final CoolerService      coolerSvc    = new CoolerService();
    private final MemoriaRAMService  memSvc       = new MemoriaRAMService();
    private final SSDNVMEService      ssdSvc       = new SSDNVMEService();

    // --- Fontes ---
    @FXML private TableView<Fonte>        tvFontes;
    @FXML private TableColumn<Fonte,String> colFonteNome;
    @FXML private TableColumn<Fonte,Integer> colFontePreco;
    @FXML private TableColumn<Fonte,String> colFonteDesc;
    @FXML private TableColumn<Fonte,Marca>   colFonteMarca;
    @FXML private TableColumn<Fonte,Integer> colFontePot;

    // --- Placas-Mãe ---
    @FXML private TableView<PlacaMae>        tvPlacasMae;
    @FXML private TableColumn<PlacaMae,String>  colMaeNome;
    @FXML private TableColumn<PlacaMae,Integer> colMaePreco;
    @FXML private TableColumn<PlacaMae,String>  colMaeSocket;
    @FXML private TableColumn<PlacaMae,String>  colMaeChipset;
    @FXML private TableColumn<PlacaMae,Integer> colMaeSlots;

    // --- Placas de Vídeo ---
    @FXML private TableView<PlacaDeVideo>        tvPlacasVideo;
    @FXML private TableColumn<PlacaDeVideo,String>  colVidNome;
    @FXML private TableColumn<PlacaDeVideo,Integer> colVidPreco;
    @FXML private TableColumn<PlacaDeVideo,Integer> colVidMemoria;
    @FXML private TableColumn<PlacaDeVideo,String>  colVidChipset;

    // --- Processadores ---
    @FXML private TableView<Processador>        tvProcessadores;
    @FXML private TableColumn<Processador,String>  colProcNome;
    @FXML private TableColumn<Processador,Integer> colProcPreco;
    @FXML private TableColumn<Processador,String>  colProcSocket;
    @FXML private TableColumn<Processador,Integer> colProcNucleos;
    @FXML private TableColumn<Processador,Integer> colProcThreads;

    // --- Gabinetes ---
    @FXML private TableView<Gabinete>        tvGabinetes;
    @FXML private TableColumn<Gabinete,String>   colGabNome;
    @FXML private TableColumn<Gabinete,Integer>  colGabPreco;
    @FXML private TableColumn<Gabinete,Integer>  colGabAltura;
    @FXML private TableColumn<Gabinete,Integer>  colGabLargura;
    @FXML private TableColumn<Gabinete,Integer>  colGabProfund;

    // --- Coolers ---
    @FXML private TableView<Cooler>        tvCoolers;
    @FXML private TableColumn<Cooler,String>  colCoolNome;
    @FXML private TableColumn<Cooler,Integer> colCoolPreco;
    @FXML private TableColumn<Cooler,String>  colCoolTipo;

    // --- Memórias RAM ---
    @FXML private TableView<MemoriaRAM>        tvMemorias;
    @FXML private TableColumn<MemoriaRAM,String>  colMemNome;
    @FXML private TableColumn<MemoriaRAM,Integer> colMemPreco;
    @FXML private TableColumn<MemoriaRAM,Integer> colMemCapacidade;
    @FXML private TableColumn<MemoriaRAM,Integer> colMemFrequencia;

    // --- SSD NVME ---
    @FXML private TableView<SSDNVME> tvSSDs;
    @FXML private TableColumn<SSDNVME,String>  colSSDNome;
    @FXML private TableColumn<SSDNVME,Integer> colSSDPreco;
    // @FXML private TableColumn<SSDNVME,String>  colSSDDesc;
    @FXML private TableColumn<SSDNVME,Marca>   colSSDMarca;
    @FXML private TableColumn<SSDNVME,Integer> colSSDCapacidade;
    @FXML private TableColumn<SSDNVME,Integer> colSSDLength;

    @FXML
    public void initialize() {
        // CONFIGURAÇÃO DAS COLUNAS E CARREGAMENTO INICIAL
        // Fontes
        colFonteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colFontePreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colFonteDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colFonteMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colFontePot.setCellValueFactory(new PropertyValueFactory<>("potencia"));
        loadFontes();

        // Placas-Mãe
        colMaeNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colMaePreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colMaeSocket.setCellValueFactory(new PropertyValueFactory<>("socket"));
        colMaeChipset.setCellValueFactory(new PropertyValueFactory<>("chipset"));
        colMaeSlots.setCellValueFactory(new PropertyValueFactory<>("ramSlots"));
        loadPlacasMae();

        // Placas de Vídeo
        colVidNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colVidPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colVidMemoria.setCellValueFactory(new PropertyValueFactory<>("memoria"));
        colVidChipset.setCellValueFactory(new PropertyValueFactory<>("chipset"));
        loadPlacasVideo();

        // Processadores
        colProcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colProcPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colProcSocket.setCellValueFactory(new PropertyValueFactory<>("socket"));
        colProcNucleos.setCellValueFactory(new PropertyValueFactory<>("nucleos"));
        colProcThreads.setCellValueFactory(new PropertyValueFactory<>("threads"));
        loadProcessadores();

        // Gabinetes
        colGabNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colGabPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colGabAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
        colGabLargura.setCellValueFactory(new PropertyValueFactory<>("largura"));
        colGabProfund.setCellValueFactory(new PropertyValueFactory<>("profundidade"));
        loadGabinetes();

        // Coolers
        colCoolNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCoolPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colCoolTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        loadCoolers();

        // Memórias RAM
        colMemNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colMemPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colMemCapacidade.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        colMemFrequencia.setCellValueFactory(new PropertyValueFactory<>("frequencia"));
        loadMemorias();

        // SSDs NVME
        colSSDNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colSSDPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        // colSSDDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colSSDMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colSSDCapacidade.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        colSSDLength.setCellValueFactory(new PropertyValueFactory<>("length"));
        loadSSDs();
    }

    // ---- Métodos de recarga ----
    private void loadFontes() {
        tvFontes.setItems(FXCollections.observableArrayList(fonteSvc.buscarTodos()));
    }
    private void loadPlacasMae() {
        tvPlacasMae.setItems(FXCollections.observableArrayList(placaMaeSvc.buscarTodos()));
    }
    private void loadPlacasVideo() {
        tvPlacasVideo.setItems(FXCollections.observableArrayList(placaVidSvc.buscarTodos()));
    }
    private void loadProcessadores() {
        tvProcessadores.setItems(FXCollections.observableArrayList(procSvc.buscarTodos()));
    }
    private void loadGabinetes() {
        tvGabinetes.setItems(FXCollections.observableArrayList(gabSvc.buscarTodos()));
    }
    private void loadCoolers() {
        tvCoolers.setItems(FXCollections.observableArrayList(coolerSvc.buscarTodos()));
    }
    private void loadMemorias() {
        tvMemorias.setItems(FXCollections.observableArrayList(memSvc.buscarTodos()));
    }
    private void loadSSDs() {
        tvSSDs.setItems(FXCollections.observableArrayList(ssdSvc.buscarTodos()));
    }

    // ---- Fontes ----
    @FXML private void onNewFonte() throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/FonteFormView.fxml")
        );
        Parent form = loader.load();
        FonteFormController ctrl = loader.getController();
        ctrl.setService(fonteSvc); 
        showDialog(form, "Nova Fonte");
        loadFontes();
    }
    @FXML private void onEditFonte() throws IOException {
        Fonte sel = tvFontes.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/FonteFormView.fxml")
        );
        Parent form = loader.load();
        FonteFormController ctrl = loader.getController();
        ctrl.setService(fonteSvc);
        ctrl.setFonte(sel);
        showDialog(form, "Editar Fonte");
        loadFontes();
    }

    @FXML private void onDeleteFonte() {
        Fonte sel = tvFontes.getSelectionModel().getSelectedItem();
        if (sel != null) {
            fonteSvc.excluir(sel.getNome());
            loadFontes();
        }
    }

    // ---- Placas-Mãe ----
    @FXML private void onNewPlacaMae() throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/PlacaMaeFormView.fxml")
        );
        Parent form = loader.load();
        PlacaMaeFormController ctrl = loader.getController();
        ctrl.setService(placaMaeSvc);
        showDialog(form, "Nova Placa-Mãe");
        loadPlacasMae();
    }
    @FXML private void onEditPlacaMae() throws IOException {
        PlacaMae sel = tvPlacasMae.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/PlacaMaeFormView.fxml")
        );
        Parent form = loader.load();
        PlacaMaeFormController ctrl = loader.getController();
        ctrl.setService(placaMaeSvc);
        ctrl.setPlacaMae(sel);
        showDialog(form, "Editar Placa-Mãe");
        loadPlacasMae();
    }
    @FXML private void onDeletePlacaMae() {
        PlacaMae sel = tvPlacasMae.getSelectionModel().getSelectedItem();
        if (sel != null) {
            placaMaeSvc.excluir(sel.getNome());
            loadPlacasMae();
        }
    }

    // ---- Placas de Vídeo ----
    @FXML private void onNewPlacaVideo() throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/PlacaDeVideoFormView.fxml")
        );
        Parent form = loader.load();
        PlacaDeVideoFormController ctrl = loader.getController();
        ctrl.setService(placaVidSvc);
        showDialog(form, "Nova Placa de Vídeo");
        loadPlacasVideo();
    }
    @FXML private void onEditPlacaVideo() throws IOException {
        PlacaDeVideo sel = tvPlacasVideo.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/PlacaDeVideoFormView.fxml")
        );
        Parent form = loader.load();
        PlacaDeVideoFormController ctrl = loader.getController();
        ctrl.setService(placaVidSvc);
        ctrl.setPlacaVideo(sel);
        showDialog(form, "Editar Placa de Vídeo");
        loadPlacasVideo();
    }
    @FXML private void onDeletePlacaVideo() {
        PlacaDeVideo sel = tvPlacasVideo.getSelectionModel().getSelectedItem();
        if (sel != null) {
            placaVidSvc.excluir(sel.getNome());
            loadPlacasVideo();
        }
    }

    // ---- Processadores ----
    @FXML private void onNewProcessador() throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/ProcessadorFormView.fxml")
        );
        Parent form = loader.load();
        ProcessadorFormController ctrl = loader.getController();
        ctrl.setService(procSvc);
        showDialog(form, "Novo Processador");
        loadProcessadores();
    }
    @FXML private void onEditProcessador() throws IOException {
        Processador sel = tvProcessadores.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/ProcessadorFormView.fxml")
        );
        Parent form = loader.load();
        ProcessadorFormController ctrl = loader.getController();
        ctrl.setService(procSvc);
        ctrl.setProcessador(sel);
        showDialog(form, "Editar Processador");
        loadProcessadores();
    }
    @FXML private void onDeleteProcessador() {
        Processador sel = tvProcessadores.getSelectionModel().getSelectedItem();
        if (sel != null) {
            procSvc.excluir(sel.getNome());
            loadProcessadores();
        }
    }

    // ---- Gabinetes ----
    @FXML private void onNewGabinete() throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/GabineteFormView.fxml")
        );
        Parent form = loader.load();
        GabineteFormController ctrl = loader.getController();
        ctrl.setService(gabSvc);
        showDialog(form, "Novo Gabinete");
        loadGabinetes();
    }
    @FXML private void onEditGabinete() throws IOException {
        Gabinete sel = tvGabinetes.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/GabineteFormView.fxml")
        );
        Parent form = loader.load();
        GabineteFormController ctrl = loader.getController();
        ctrl.setService(gabSvc);
        ctrl.setGabinete(sel);
        showDialog(form, "Editar Gabinete");
        loadGabinetes();
    }
    @FXML private void onDeleteGabinete() {
        Gabinete sel = tvGabinetes.getSelectionModel().getSelectedItem();
        if (sel != null) {
            gabSvc.excluir(sel.getNome());
            loadGabinetes();
        }
    }

    // ---- Coolers ----
    @FXML private void onNewCooler() throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/CoolerFormView.fxml")
        );
        Parent form = loader.load();
        CoolerFormController ctrl = loader.getController();
        ctrl.setService(coolerSvc);
        showDialog(form, "Novo Cooler");
        loadCoolers();
    }
    @FXML private void onEditCooler() throws IOException {
        Cooler sel = tvCoolers.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/CoolerFormView.fxml")
        );
        Parent form = loader.load();
        CoolerFormController ctrl = loader.getController();
        ctrl.setService(coolerSvc);
        ctrl.setCooler(sel);
        showDialog(form, "Editar Cooler");
        loadCoolers();
    }
    @FXML private void onDeleteCooler() {
        Cooler sel = tvCoolers.getSelectionModel().getSelectedItem();
        if (sel != null) {
            coolerSvc.excluir(sel.getNome());
            loadCoolers();
        }
    }

    // ---- Memórias RAM ----
    @FXML private void onNewMemoria() throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/MemoriaRAMFormView.fxml")
        );
        Parent form = loader.load();
        MemoriaRAMFormController ctrl = loader.getController();
        ctrl.setService(memSvc);
        showDialog(form, "Nova Memória RAM");
        loadMemorias();
    }
    @FXML private void onEditMemoria() throws IOException {
        MemoriaRAM sel = tvMemorias.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/MemoriaRAMFormView.fxml")
        );
        Parent form = loader.load();
        MemoriaRAMFormController ctrl = loader.getController();
        ctrl.setService(memSvc);
        ctrl.setMemoriaRAM(sel);
        showDialog(form, "Editar Memória RAM");
        loadMemorias();
    }
    @FXML private void onDeleteMemoria() {
        MemoriaRAM sel = tvMemorias.getSelectionModel().getSelectedItem();
        if (sel != null) {
            memSvc.excluir(sel.getNome());
            loadMemorias();
        }
    }

    private void showDialog(Parent form, String title) {
        Stage dlg = new Stage();
        dlg.initOwner(tvFontes.getScene().getWindow());
        dlg.setTitle(title);
        dlg.setScene(new Scene(form));
        dlg.showAndWait();
    }

    @FXML
    private void onLogout() {
        Stage st = (Stage) tvFontes.getScene().getWindow();
        st.close(); 

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ---- SSDs NVME ----
    @FXML private void onNewSSD() throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/SSDNVMEFormView.fxml")
        );
        Parent form = loader.load();
        SSDNVMEFormController ctrl = loader.getController();
        ctrl.setService(ssdSvc);
        showDialog(form, "Novo SSD NVMe");
        loadSSDs();
    }
    @FXML private void onEditSSD() throws IOException {
        SSDNVME sel = tvSSDs.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/SSDNVMEFormView.fxml")
        );
        Parent form = loader.load();
        SSDNVMEFormController ctrl = loader.getController();
        ctrl.setService(ssdSvc);
        ctrl.setSSDNVME(sel);
        showDialog(form, "Editar SSD NVMe");
        loadSSDs();
    }
    @FXML private void onDeleteSSD() {
        SSDNVME sel = tvSSDs.getSelectionModel().getSelectedItem();
        if (sel != null) {
            ssdSvc.excluir(sel.getNome());
            loadSSDs();
        }
    }
    
}
