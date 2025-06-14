package pcbuilder.computador;

import pcbuilder.components.*;
import java.util.List;
import java.util.ArrayList;

public class Computador {
    private Fonte fonte; // Fonte de alimentação do computador
    private PlacaMae placaMae; // Placa-mãe do computador
    private PlacaDeVideo placaDeVideo; // Placa de vídeo do computador caso tenha
    private Processador processador; // Processador do computador
    private List<Cooler> coolers; // Coolers do computador
    private List<MemoriaRAM> memoriasRAM; // Lista de memórias RAM do computador
    private Gabinete gabinete; // Gabinete do computador
    private int precoTotal; // Preço total do computador em centavos

    /**
     * Calcula o preço total do computador somando os preços de todos os componentes.
     * O preço total é calculado como a soma dos preços da fonte, placa-mãe, placa de vídeo (se houver),
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
    }

    /**
     * Construtor da classe Computador.
     * @param fonte Fonte de alimentação do computador.
     * @param placaMae Placa-mãe do computador.
     * @param placaDeVideo Placa de vídeo do computador (pode ser null se não houver).
     * @param processador Processador do computador.
     * @param coolers Lista de coolers do computador.
     * @param memoriasRAM Lista de memórias RAM do computador.
     * @param gabinete Gabinete do computador.
     */
    public Computador(Fonte fonte, PlacaMae placaMae, PlacaDeVideo placaDeVideo, Processador processador, List<Cooler> coolers, List<MemoriaRAM> memoriasRAM, Gabinete gabinete) {
        this.fonte = fonte;
        this.placaMae = placaMae;
        this.placaDeVideo = placaDeVideo;
        this.processador = processador;
        this.coolers = coolers != null ? coolers : new ArrayList<>();
        this.memoriasRAM = memoriasRAM != null ? memoriasRAM : new ArrayList<>();
        this.gabinete = gabinete;
        calcularPrecoTotal();
    }

    // Constutor para criar um computador sem placa de vídeo
    public Computador(Fonte fonte, PlacaMae placaMae, Processador processador, List<Cooler> coolers, List<MemoriaRAM> memoriasRAM, Gabinete gabinete) {
        this(fonte, placaMae, null, processador, coolers, memoriasRAM, gabinete);
    }

    // Métodos getters para acessar os atributos do computador

    /**
     * Retorna a fonte de alimentação do computador.
     * @return Fonte de alimentação.
     */
    public Fonte getFonte() {
        return fonte;
    }
    /**
     * Retorna a placa-mãe do computador.
     * @return Placa-mãe.
     */
    public PlacaMae getPlacaMae() {
        return placaMae;
    }
    /**
     * Retorna a placa de vídeo do computador.
     * @return Placa de vídeo.
     */
    public PlacaDeVideo getPlacaDeVideo() {
        return placaDeVideo;
    }
    /**
     * Retorna o processador do computador.
     * @return Processador.
     */
    public Processador getProcessador() {
        return processador;
    }
    /**
     * Retorna a lista de coolers do computador.
     * @return Lista de coolers.
     */
    public List<Cooler> getCoolers() {
        return new ArrayList<>(coolers); // Retorna uma cópia da lista de coolers para evitar modificações externas
    }
    /**
     * Retorna a lista de memórias RAM do computador.
     * @return Lista de memórias RAM.
     */
    public List<MemoriaRAM> getMemoriasRAM() {
        return new ArrayList<>(memoriasRAM); // Retorna uma cópia da lista de memórias RAM para evitar modificações externas
    }
    /**
     * Retorna o gabinete do computador.
     * @return Gabinete.
     */
    public Gabinete getGabinete() {
        return gabinete;
    }
    /**
     * Retorna o preço total do computador em centavos.
     * @return Preço total do computador.
     */
    public int getPrecoTotal() {
        return precoTotal;
    }
}