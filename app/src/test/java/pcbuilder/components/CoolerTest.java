package pcbuilder.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoolerTest {

    private Marca marcaTeste; // Definindo uma marca de teste para usar em todos os cenários

    @BeforeEach
    void setUp() {
        marcaTeste = Marca.INTEL;
    }
    
    @Test
    @DisplayName("Deve criar um Cooler válido com todos os atributos corretos")
    void deveCriarCoolerValidoComTodosAtributos() {
        // Cenário: Instanciar um objeto Cooler válido
        String nome = "Cooler Master Hyper 212";
        int preco = 50000;
        String descricao = "Cooler a ar com bom desempenho para processadores.";
        String imagePath = "/images/cooler_master_hyper_212.png";
        String tipo = "Ventilador";

        Cooler cooler = new Cooler(nome, preco, descricao, marcaTeste, imagePath, tipo);

        assertNotNull(cooler, "O objeto cooler não deve ser nulo.");
        assertEquals(nome, cooler.getNome(), "O nome do cooler deve ser o esperado.");
        assertEquals(preco, cooler.getPreco(), "O preço do cooler deve ser o esperado.");
        assertEquals(descricao, cooler.getDescricao(), "A descrição do cooler deve ser a esperada.");
        assertEquals(marcaTeste, cooler.getMarca(), "A marca do cooler deve ser a esperada.");
        assertEquals(imagePath, cooler.getImagePath(), "O caminho da imagem deve ser o esperado.");
        assertEquals(tipo, cooler.getTipo(), "O tipo do cooler deve ser o esperado.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o tipo do Cooler for nulo")
    void deveLancarExcecaoSeTipoForNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cooler("Cooler sem tipo", 30000, "Cooler sem tipo definido.", marcaTeste, "/images/cooler_sem_tipo.png", null);
        }, "Deve lançar IllegalArgumentException se o tipo for nulo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o tipo do Cooler for uma string vazia")
    void deveLancarExcecaoSeTipoForVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cooler("Cooler sem tipo", 30000, "Cooler sem tipo definido.", marcaTeste, "/images/cooler_sem_tipo.png", "");
        }, "Deve lançar IllegalArgumentException se o tipo for uma string vazia.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o tipo do Cooler for apenas espaços em branco")
    void deveLancarExcecaoSeTipoForApenasEspaços() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cooler("Cooler sem tipo", 30000, "Cooler sem tipo definido.", marcaTeste, "/images/cooler_sem_tipo.png", "   ");
        }, "Deve lançar IllegalArgumentException se o tipo for apenas espaços em branco.");
    }
}
