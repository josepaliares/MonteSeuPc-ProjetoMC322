package pcbuilder.computador;

import pcbuilder.components.*;
import pcbuilder.exceptions.ComponentesEssenciaisFaltandoException;
import pcbuilder.exceptions.ComponentesIncompativeisException; // Importe sua exceção de incompatibilidade
import pcbuilder.compatibility.Compativel; // Importe sua classe Compativel

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que constrói um computador com seus componentes.
 * Utiliza o design pattern Builder para facilitar a construção de objetos
 * complexos passo a passo.
 * Inclui validações de componentes essenciais e compatibilidade antes da
 * construção final.
 */
public class ComputadorBuilder {
    private Fonte fonte;
    private PlacaMae placaMae;
    private PlacaDeVideo placaDeVideo;
    private Processador processador;
    private List<Cooler> coolers = new ArrayList<>();
    private List<MemoriaRAM> memoriasRAM = new ArrayList<>();
    private Gabinete gabinete;
    private SSDNVME ssd;

    // Instância da classe Compativel para verificar compatibilidade
    private Compativel compativelChecker;

    public ComputadorBuilder() {
        this.compativelChecker = new Compativel(); // Instancia o verificador de compatibilidade
    }

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
        if (cooler == null) {
            throw new IllegalArgumentException("Cooler não pode ser nulo.");
        }
        this.coolers.add(cooler);
        return this;
    }

    public ComputadorBuilder adicionarMemoriaRAM(MemoriaRAM memoria) {
        if (memoria == null) {
            throw new IllegalArgumentException("Memória RAM não pode ser nula.");
        }
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

    /**
     * Constrói o objeto Computador após validar todos os componentes.
     * 
     * @return Uma nova instância de Computador.
     * @throws ComponentesEssenciaisFaltandoException Se algum componente
     *                                                obrigatório estiver faltando.
     * @throws ComponentesIncompativeisException      Se houver incompatibilidade
     *                                                entre os componentes.
     */
    public Computador build() throws ComponentesEssenciaisFaltandoException, ComponentesIncompativeisException {
        if (fonte == null) {
            throw new ComponentesEssenciaisFaltandoException("Fonte é um componente essencial e está faltando.");
        }
        if (placaMae == null) {
            throw new ComponentesEssenciaisFaltandoException("Placa Mãe é um componente essencial e está faltando.");
        }
        if (processador == null) {
            throw new ComponentesEssenciaisFaltandoException("Processador é um componente essencial e está faltando.");
        }
        if (gabinete == null) {
            throw new ComponentesEssenciaisFaltandoException("Gabinete é um componente essencial e está faltando.");
        }

        if (!compativelChecker.compativel(processador, placaMae)) {
            throw new ComponentesIncompativeisException("Processador " + processador.getNome() +
                    " e Placa Mãe " + placaMae.getNome() + " são incompatíveis (socket).");
        }

        for (MemoriaRAM ram : memoriasRAM) {
            if (!compativelChecker.compativel(placaMae, ram)) { // Usando a sobrecarga placaMae, memoriaRAM
                throw new ComponentesIncompativeisException("Memória RAM " + ram.getNome() +
                        " é incompatível com a Placa Mãe " + placaMae.getNome() + " (tipo de memória).");
            }
        }

        if (ssd != null && !compativelChecker.compativel(ssd, placaMae)) { // Se SSD existe, checa compatibilidade
            throw new ComponentesIncompativeisException("SSD NVME " + ssd.getNome() +
                    " é incompatível com a Placa Mãe " + placaMae.getNome() + " (tamanho do slot).");
        }

        // Se todas as validações passarem, constrói o objeto Computador
        return new Computador(fonte, placaMae, placaDeVideo, processador, coolers, memoriasRAM, gabinete, ssd);
    }
}