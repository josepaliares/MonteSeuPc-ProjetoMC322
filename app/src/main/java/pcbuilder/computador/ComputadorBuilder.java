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
    private SSDNVME ssd; // NOVO

    public ComputadorBuilder adicionarFonte(Fonte fonte) {
        this.fonte = fonte;
        return this;
    }

    public ComputadorBuilder adicionarPlacaMae(PlacaMae placaMae) {
        this.placaMae = placaMae;
        return this;
    }

    public ComputadorBuilder adicionarPlacaDeVideo(PlacaDeVideo placaDeVideo) {
        this.placaDeVideo = placaDeVideo;
        return this;
    }

    public ComputadorBuilder adicionarProcessador(Processador processador) {
        this.processador = processador;
        return this;
    }

    public ComputadorBuilder adicionarCooler(Cooler cooler) {
        this.coolers.add(cooler);
        return this;
    }

    public ComputadorBuilder adicionarMemoriaRAM(MemoriaRAM memoria) {
        this.memoriasRAM.add(memoria);
        return this;
    }

    public ComputadorBuilder adicionarGabinete(Gabinete gabinete) {
        this.gabinete = gabinete;
        return this;
    }

    public ComputadorBuilder adicionarSSD(SSDNVME ssd) {
        this.ssd = ssd;
        return this;
    }

    public Computador build() {
        if (fonte == null || placaMae == null || processador == null || gabinete == null) {
            throw new IllegalStateException("Faltam componentes obrigatórios para montar o computador.");
        }
        return new Computador(fonte, placaMae, placaDeVideo, processador, coolers, memoriasRAM, gabinete, ssd);
    }
}
