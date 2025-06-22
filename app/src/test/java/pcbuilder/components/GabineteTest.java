package pcbuilder.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GabineteTest {

    private Marca marcaTeste; // Definindo uma marca de teste para usar em todos os cenários

    @BeforeEach
    void setUp() {
        marcaTeste = Marca.INTEL;
    }
    
    @Test
    @DisplayName("Deve criar um Gabinete válido com todos os atributos corretos")
    void deveCriarGabineteValidaComTodosAtributos() {
        // Cenário: Instanciar um objeto Gabinete válido
        String nome = "Gabinete Correto";
        int preco = 50000;
        String descricao = "Gabinete com todos os atributor corretos";
        String imagePath = "/images/Gabinete_correto.png";
        int largura = 20;
        int altura = 50;
        int profundidade = 60;

        Gabinete Gabinete = new Gabinete(nome, preco, descricao, marcaTeste, imagePath, largura, altura, profundidade);

        assertNotNull(Gabinete, "O objeto Gabinete não deve ser nulo.");
        assertEquals(nome, Gabinete.getNome(), "O nome da Gabinete deve ser o esperado.");
        assertEquals(preco, Gabinete.getPreco(), "O preço da Gabinete deve ser o esperado.");
        assertEquals(descricao, Gabinete.getDescricao(), "A descrição da Gabinete deve ser a esperada.");
        assertEquals(marcaTeste, Gabinete.getMarca(), "A marca da Gabinete deve ser a esperada.");
        assertEquals(imagePath, Gabinete.getImagePath(), "O caminho da imagem deve ser o esperado.");
        assertEquals(largura, Gabinete.getLargura(), "A largura da Gabinete deve ser a esperada.");
        assertEquals(altura, Gabinete.getAltura(), "A potência da Gabinete deve ser o esperada.");
        assertEquals(profundidade, Gabinete.getProfundidade(), "A profundidade da Gabinete deve ser a esperada.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a largura do Gabinete for 0")
    void deveLancarExcecaoSeLarguraForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gabinete("Gabinete 2D", 30000, "Gabinete com apenas duas dimensões", marcaTeste, "/images/Gabinete_2d.png", 0, 1000, 500);
        }, "Deve lançar IllegalArgumentException se a largura for 0.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a largura do Gabinete for negativa")
    void deveLancarExcecaoSeLarguraForNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gabinete("Gabinete Irregular", 30000, "Gabinete com largura negativa", marcaTeste, "/images/Gabinete_2d.png", -100, 1000, 500);
        }, "Deve lançar IllegalArgumentException se a largura for negativa.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a altura do Gabinete for 0")
    void deveLancarExcecaoSeAlturaForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gabinete("Gabinete 2D", 30000, "Gabinete com apenas duas dimensões", marcaTeste, "/images/Gabinete_2d.png", 1000, 0, 500);
        }, "Deve lançar IllegalArgumentException se a altura for 0.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a altura do Gabinete for negativa")
    void deveLancarExcecaoSeAlturaForNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gabinete("Gabinete Irregular", 30000, "Gabinete com altura negativa", marcaTeste, "/images/Gabinete_2d.png", 1000, -100, 500);
        }, "Deve lançar IllegalArgumentException se a altura for negativa.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o comprimento do Gabinete for 0")
    void deveLancarExcecaoSeComprimentoForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gabinete("Gabinete 2D", 30000, "Gabinete com apenas duas dimensões", marcaTeste, "/images/Gabinete_2d.png", 100, 1000, 0);
        }, "Deve lançar IllegalArgumentException se o comprimento for 0.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o comprimento do Gabinete for negativo")
    void deveLancarExcecaoSeComprimentoForNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gabinete("Gabinete Irregular", 30000, "Gabinete com comprimento negativo", marcaTeste, "/images/Gabinete_2d.png", 100, 1000, -500);
        }, "Deve lançar IllegalArgumentException se o comprimento for negativa.");
    }
}
