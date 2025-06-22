package pcbuilder.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemoriaRAMTest {

    private Marca marcaTeste; // Definindo uma marca de teste para usar em todos os cenários

    @BeforeEach
    void setUp() {
        marcaTeste = Marca.INTEL;
    }
    
    @Test
    @DisplayName("Deve criar um MemoriaRAM válida com todos os atributos corretos")
    void deveCriarMemoriaRAMValidaComTodosAtributos() {
        // Cenário: Instanciar um objeto MemoriaRAM válido
        String nome = "MemoriaRAM Correta";
        int preco = 50000;
        String descricao = "MemoriaRAM com todos os atributor corretos";
        String imagePath = "/images/MemoriaRAM_correto.png";
        int capacidade = 8;
        int frequencia = 2300; 
        String tipo = "ddr4";

        MemoriaRAM MemoriaRAM = new MemoriaRAM(nome, preco, descricao, marcaTeste, imagePath, capacidade, frequencia, tipo);

        assertNotNull(MemoriaRAM, "O objeto MemoriaRAM não deve ser nulo.");
        assertEquals(nome, MemoriaRAM.getNome(), "O nome da MemoriaRAM deve ser o esperado.");
        assertEquals(preco, MemoriaRAM.getPreco(), "O preço da MemoriaRAM deve ser o esperado.");
        assertEquals(descricao, MemoriaRAM.getDescricao(), "A descrição da MemoriaRAM deve ser a esperada.");
        assertEquals(marcaTeste, MemoriaRAM.getMarca(), "A marca da MemoriaRAM deve ser a esperada.");
        assertEquals(imagePath, MemoriaRAM.getImagePath(), "O caminho da imagem deve ser o esperado.");
        assertEquals(capacidade, MemoriaRAM.getCapacidade(), "A capacidade da MemoriaRAM deve ser a esperada.");
        assertEquals(frequencia, MemoriaRAM.getFrequencia(), "A frequência da MemoriaRAM deve ser a esperada.");
        assertEquals(tipo, MemoriaRAM.getTipo(), "O tipo da MemoriaRAM deve ser a esperada.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a capacidade do MemoriaRAM for 0")
    void deveLancarExcecaoSeCapacidadeForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MemoriaRAM("MemoriaRAM sem espaço", 30000, "MemoriaRAM com 0gb disponíveis", marcaTeste, "/images/MemoriaRAM_sem_espaco.png", 0, 1000, "ddr4");
        }, "Deve lançar IllegalArgumentException se a capacidade for 0.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a capacidade do MemoriaRAM for negativa")
    void deveLancarExcecaoSeCapacidadeForNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MemoriaRAM("MemoriaRAM espaço negativo", 30000, "MemoriaRAM com menos de 0gb disponíveis", marcaTeste, "/images/MemoriaRAM_espaco_negativo.png", -10, 1000, "ddr4");
        }, "Deve lançar IllegalArgumentException se a capacidade for negativa.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a frequencia do MemoriaRAM for 0")
    void deveLancarExcecaoSeFrquenciaForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MemoriaRAM("MemoriaRAM sem frequencia", 30000, "MemoriaRAM com frequencia 0", marcaTeste, "/images/MemoriaRAM_sem_frequencia.png", 20, 0, "ddr4");
        }, "Deve lançar IllegalArgumentException se a frequencia for 0.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a frequencia do MemoriaRAM for negativa")
    void deveLancarExcecaoSeFrequenciaForNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MemoriaRAM("MemoriaRAM frequencia negativa", 30000, "MemoriaRAM com frequencia negativa", marcaTeste, "/images/MemoriaRAM_freq_negativa.png", 20, -1000, "ddr4");
        }, "Deve lançar IllegalArgumentException se a frequencia for negativa.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o tipo da MemoriaRAM for nulo")
    void deveLancarExcecaoSeTipoForNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MemoriaRAM("MemoriaRAM sem tipo", 30000, "MemoriaRAM sem tipo definido.", marcaTeste, "/images/MemoriaRAM_sem_tipo.png", 1, 1, null);
        }, "Deve lançar IllegalArgumentException se o tipo for nulo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o tipo da MemoriaRAM for uma string vazia")
    void deveLancarExcecaoSeTipoForVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MemoriaRAM("MemoriaRAM sem tipo", 30000, "MemoriaRAM sem tipo definido.", marcaTeste, "/images/MemoriaRAM_sem_tipo.png", 1, 1, "");
        }, "Deve lançar IllegalArgumentException se o tipo for uma string vazia.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o tipo da MemoriaRAM for apenas espaços em branco")
    void deveLancarExcecaoSeTipoForApenasEspaços() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MemoriaRAM("MemoriaRAM sem tipo", 30000, "MemoriaRAM sem tipo definido.", marcaTeste, "/images/MemoriaRAM_sem_tipo.png", 1, 1, "   ");
        }, "Deve lançar IllegalArgumentException se o tipo for apenas espaços em branco.");
    }
}
