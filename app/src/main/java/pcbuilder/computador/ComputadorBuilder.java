package pcbuilder.computador;

import pcbuilder.components.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que constrói um computador com seus componentes.
 * Utiliza o design pattern Builder para facilitar a construção de objetos complexos passo a passo.
 */
public class ComputadorBuilder {
    private Fonte fonte;
    private PlacaMae placaMae;
    private PlacaDeVideo placaDeVideo;
    private Processador processador;
    private List<Cooler> coolers = new ArrayList<>();
    private List<MemoriaRAM> memoriasRAM = new ArrayList<>();
    private Gabinete gabinete;

    /**
     * Método para adicionar uma fonte de alimentação ao computador.
     * @param fonte A fonte de alimentação a ser adicionada.
     */
    public ComputadorBuilder adicionarFonte(Fonte fonte) {
        this.fonte = fonte;
        return this;
    }

    /**
     * Método para adicionar uma placa-mãe ao computador.
     * @param placaMae A placa-mãe a ser adicionada.
     */
    public ComputadorBuilder adicionarPlacaMae(PlacaMae placaMae) {
        this.placaMae = placaMae;
        return this;
    }

    /**
     * Método para adicionar uma placa de vídeo ao computador.
     * @param placaDeVideo A placa de vídeo a ser adicionada (pode ser null se não houver).
     */
    public ComputadorBuilder adicionarPlacaDeVideo(PlacaDeVideo placaDeVideo) {
        this.placaDeVideo = placaDeVideo;
        return this;
    }

    /**
     * Método para adicionar um processador ao computador.
     * @param processador O processador a ser adicionado.
     */
    public ComputadorBuilder adicionarProcessador(Processador processador) {
        this.processador = processador;
        return this;
    }

    /**
     * Método para adicionar um cooler ao computador.
     * @param cooler O cooler a ser adicionado.
     */
    public ComputadorBuilder adicionarCooler(Cooler cooler) {
        this.coolers.add(cooler);
        return this;
    }

    /**
     * Método para adicionar uma memória RAM ao computador.
     * @param memoria A memória RAM a ser adicionada.
     */
    public ComputadorBuilder adicionarMemoriaRAM(MemoriaRAM memoria) {
        this.memoriasRAM.add(memoria);
        return this;
    }

    /**
     * Método para adicionar um gabinete ao computador.
     * @param gabinete O gabinete a ser adicionado.
     */
    public ComputadorBuilder adicionarGabinete(Gabinete gabinete) {
        this.gabinete = gabinete;
        return this;
    }

    /**
     * Método para construir o computador com os componentes adicionados.
     * @return Uma instância de Computador com os componentes configurados.
     */
    public Computador build() {
        // Validação básica (pode ser mais sofisticada)
        if (fonte == null || placaMae == null || processador == null || gabinete == null) {
            throw new IllegalStateException("Faltam componentes obrigatórios para montar o computador.");
        }

        return new Computador(fonte, placaMae, placaDeVideo, processador, coolers, memoriasRAM, gabinete);
    }
}
