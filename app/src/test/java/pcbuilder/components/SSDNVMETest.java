package pcbuilder.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SSDNVMETest {

    private Marca marcaTeste; // Definindo uma marca de teste para usar em todos os cenários

    @BeforeEach
    void setUp() {
        marcaTeste = Marca.INTEL;
    }
    
    @Test
    @DisplayName("Deve criar um SSDNVME válido com todos os atributos corretos")
    void deveCriarSSDNVMEValidoComTodosAtributos() {
        // Cenário: Instanciar um objeto SSDNVME válido
        String nome = "SSDNVME Master Hyper 212";
        int preco = 50000;
        String descricao = "SSDNVME a ar com bom desempenho para processadores.";
        String imagePath = "/images/SSDNVME_master_hyper_212.png";
        int capacidade = 500;
        int length = 100;

        SSDNVME SSDNVME = new SSDNVME(nome, preco, descricao, marcaTeste, imagePath, capacidade, length);

        assertNotNull(SSDNVME, "O objeto SSDNVME não deve ser nulo.");
        assertEquals(nome, SSDNVME.getNome(), "O nome do SSDNVME deve ser o esperado.");
        assertEquals(preco, SSDNVME.getPreco(), "O preço do SSDNVME deve ser o esperado.");
        assertEquals(descricao, SSDNVME.getDescricao(), "A descrição do SSDNVME deve ser a esperada.");
        assertEquals(marcaTeste, SSDNVME.getMarca(), "A marca do SSDNVME deve ser a esperada.");
        assertEquals(imagePath, SSDNVME.getImagePath(), "O caminho da imagem deve ser o esperado.");
        assertEquals(capacidade, SSDNVME.getCapacidade(), "A capacidade do SSDNVME deve ser a esperada.");
        assertEquals(length, SSDNVME.getLength(), "O comprimento do SSDNVME deve ser o esperado.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a capacidade do SSDNVME for 0")
    void deveLancarExcecaoSeCapacidadeForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SSDNVME("SSDNVME sem capacidade", 30000, "SSDNVME com capacidade zero", marcaTeste, "/images/SSDNVME_sem_capacidade.png", 0, 100);
        }, "Deve lançar IllegalArgumentException se a capacidade for 0.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a capacidade do SSDNVME for negativa")
    void deveLancarExcecaoSeCapacidadeForNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SSDNVME("SSDNVME com capacidade negativa", 30000, "SSDNVME com capacidade negativa", marcaTeste, "/images/SSDNVME_capacidade_negativa.png", -100, 100);
        }, "Deve lançar IllegalArgumentException se a capacidade for negativa.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o comprimento do SSDNVME for 0")
    void deveLancarExcecaoSeComprimentoForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SSDNVME("SSDNVME sem comprimento", 30000, "SSDNVME com comprimento zero", marcaTeste, "/images/SSDNVME_sem_comprimento.png", 500, 0);
        }, "Deve lançar IllegalArgumentException se o comprimento for 0.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o comprimento do SSDNVME for negativo")
    void deveLancarExcecaoSeComprimentoForNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SSDNVME("SSDNVME com comprimento negativo", 30000, "SSDNVME com comprimento negativo", marcaTeste, "/images/SSDNVME_comprimento_negativo.png", 500, -100);
        }, "Deve lançar IllegalArgumentException se o comprimento for negativo.");
    }
}
