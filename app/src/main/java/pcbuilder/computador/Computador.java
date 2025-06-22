package pcbuilder.computador;

import pcbuilder.components.*;
import java.util.List;
import java.util.ArrayList;

public class Computador {
    private Fonte fonte;
    private PlacaMae placaMae;
    private PlacaDeVideo placaDeVideo;
    private Processador processador;
    private List<Cooler> coolers;
    private List<MemoriaRAM> memoriasRAM;
    private Gabinete gabinete;
    private SSDNVME ssd;
    private int precoTotal;

    /**
     * Calcula o preço total do computador somando os preços de todos os componentes.
     */
    private void calcularPrecoTotal() {
        precoTotal = fonte.getPreco() + placaMae.getPreco() + (placaDeVideo != null ? placaDeVideo.getPreco() : 0) + processador.getPreco();
        for (Cooler cooler : coolers) {
            precoTotal += cooler.getPreco();
        }
        for (MemoriaRAM memoria : memoriasRAM) {
            precoTotal += memoria.getPreco();
        }
        precoTotal += gabinete.getPreco();
        if (ssd != null) {
            precoTotal += ssd.getPreco();
        }
    }

    /**
     * Construtor da classe Computador.
     */
    public Computador(Fonte fonte, PlacaMae placaMae, PlacaDeVideo placaDeVideo, Processador processador,
                      List<Cooler> coolers, List<MemoriaRAM> memoriasRAM, Gabinete gabinete) {
        this(fonte, placaMae, placaDeVideo, processador, coolers, memoriasRAM, gabinete, null);
    }

    /**
     * Construtor para criar um computador sem placa de vídeo.
     */
    public Computador(Fonte fonte, PlacaMae placaMae, Processador processador,
                      List<Cooler> coolers, List<MemoriaRAM> memoriasRAM, Gabinete gabinete) {
        this(fonte, placaMae, null, processador, coolers, memoriasRAM, gabinete, null);
    }

    /**
     * Construtor com SSD
     */
    public Computador(Fonte fonte, PlacaMae placaMae, PlacaDeVideo placaDeVideo, Processador processador,
                      List<Cooler> coolers, List<MemoriaRAM> memoriasRAM, Gabinete gabinete, SSDNVME ssd) {
        this.fonte = fonte;
        this.placaMae = placaMae;
        this.placaDeVideo = placaDeVideo;
        this.processador = processador;
        this.coolers = coolers != null ? coolers : new ArrayList<>();
        this.memoriasRAM = memoriasRAM != null ? memoriasRAM : new ArrayList<>();
        this.gabinete = gabinete;
        this.ssd = ssd;
        calcularPrecoTotal();
    }

    // Métodos getters para acessar os atributos do computador

    public Fonte getFonte() { return fonte; }
    public PlacaMae getPlacaMae() { return placaMae; }
    public PlacaDeVideo getPlacaDeVideo() { return placaDeVideo; }
    public Processador getProcessador() { return processador; }
    public List<Cooler> getCoolers() { return new ArrayList<>(coolers); }
    public List<MemoriaRAM> getMemoriasRAM() { return new ArrayList<>(memoriasRAM); }
    public Gabinete getGabinete() { return gabinete; }
    public SSDNVME getSSD() { return ssd; }
    public int getPrecoTotal() { return precoTotal; }
}
