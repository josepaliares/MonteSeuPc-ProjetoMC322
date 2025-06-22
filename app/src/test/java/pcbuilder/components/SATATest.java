package pcbuilder.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SATATest {

    private Marca marcaTeste; // Definindo uma marca de teste para usar em todos os cenários

    @BeforeEach
    void setUp() {
        marcaTeste = Marca.INTEL;
    }
    
    @Test
    @DisplayName("Deve criar um SATA válido com todos os atributos corretos")
    void deveCriarSATAValidoComTodosAtributos() {
        // Cenário: Instanciar um objeto SATA válido
        String nome = "SATA Master Hyper 212";
        int preco = 50000;
        String descricao = "SATA a ar com bom desempenho para processadores.";
        String imagePath = "/images/SATA_master_hyper_212.png";
        int capacidade = 500;

        SATA SATA = new SATA(nome, preco, descricao, marcaTeste, imagePath, capacidade);

        assertNotNull(SATA, "O objeto SATA não deve ser nulo.");
        assertEquals(nome, SATA.getNome(), "O nome do SATA deve ser o esperado.");
        assertEquals(preco, SATA.getPreco(), "O preço do SATA deve ser o esperado.");
        assertEquals(descricao, SATA.getDescricao(), "A descrição do SATA deve ser a esperada.");
        assertEquals(marcaTeste, SATA.getMarca(), "A marca do SATA deve ser a esperada.");
        assertEquals(imagePath, SATA.getImagePath(), "O caminho da imagem deve ser o esperado.");
        assertEquals(capacidade, SATA.getCapacidade(), "A capacidade do SATA deve ser a esperada.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a capacidade do SATA for 0")
    void deveLancarExcecaoSeCapacidadeForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SATA("SATA sem capacidade", 30000, "SATA com capacidade zero", marcaTeste, "/images/SATA_sem_capacidade.png", 0);
        }, "Deve lançar IllegalArgumentException se a capacidade for 0.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a capacidade do SATA for negativa")
    void deveLancarExcecaoSeCapacidadeForNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SATA("SATA com capacidade negativa", 30000, "SATA com capacidade negativa", marcaTeste, "/images/SATA_capacidade_negativa.png", -100);
        }, "Deve lançar IllegalArgumentException se a capacidade for negativa.");
    }
}
