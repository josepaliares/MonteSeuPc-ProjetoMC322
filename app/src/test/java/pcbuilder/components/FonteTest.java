package pcbuilder.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FonteTest {

    private Marca marcaTeste; // Definindo uma marca de teste para usar em todos os cenários

    @BeforeEach
    void setUp() {
        marcaTeste = Marca.INTEL;
    }
    
    @Test
    @DisplayName("Deve criar uma Fonte válida com todos os atributos corretos")
    void deveCriarFonteValidaComTodosAtributos() {
        // Cenário: Instanciar um objeto Fonte válido
        String nome = "Fonte Correta";
        int preco = 50000;
        String descricao = "Fonte com todos os atributor corretos";
        String imagePath = "/images/Fonte_correta.png";
        int potencia = 1000;

        Fonte Fonte = new Fonte(nome, preco, descricao, marcaTeste, imagePath, potencia);

        assertNotNull(Fonte, "O objeto Fonte não deve ser nulo.");
        assertEquals(nome, Fonte.getNome(), "O nome da Fonte deve ser o esperado.");
        assertEquals(preco, Fonte.getPreco(), "O preço da Fonte deve ser o esperado.");
        assertEquals(descricao, Fonte.getDescricao(), "A descrição da Fonte deve ser a esperada.");
        assertEquals(marcaTeste, Fonte.getMarca(), "A marca da Fonte deve ser a esperada.");
        assertEquals(imagePath, Fonte.getImagePath(), "O caminho da imagem deve ser o esperado.");
        assertEquals(potencia, Fonte.getPotencia(), "A potência da Fonte deve ser o esperada.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o a potência da Fonte for 0")
    void deveLancarExcecaoSePotenciaForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Fonte("Fonte potencia zero", 30000, "Fonte sem potencia", marcaTeste, "/images/Fonte_sem_potencia.png", 0);
        }, "Deve lançar IllegalArgumentException se a potência for 0.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o a potência da Fonte for 0")
    void deveLancarExcecaoSePotenciaForNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Fonte("Fonte potencia negativa", 30000, "Fonte com a potência menor que 0", marcaTeste, "/images/Fonte_potencia_negativa.png", -10);
        }, "Deve lançar IllegalArgumentException se a potência for negativa.");
    }
}
