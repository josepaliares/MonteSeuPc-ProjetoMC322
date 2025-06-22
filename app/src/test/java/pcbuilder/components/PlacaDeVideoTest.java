package pcbuilder.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlacaDeVideoTest {

    private Marca marcaTeste; // Definindo uma marca de teste para usar em todos os cenários

    @BeforeEach
    void setUp() {
        marcaTeste = Marca.INTEL;
    }
    
    @Test
    @DisplayName("Deve criar um PlacaDeVideo válida com todos os atributos corretos")
    void deveCriarPlacaDeVideoValidaComTodosAtributos() {
        // Cenário: Instanciar um objeto PlacaDeVideo válido
        String nome = "PlacaDeVideo Correta";
        int preco = 50000;
        String descricao = "PlacaDeVideo com todos os atributor corretos";
        String imagePath = "/images/PlacaDeVideo_correto.png";
        int memoria = 8;
        String chipset = "Ampere";

        PlacaDeVideo PlacaDeVideo = new PlacaDeVideo(nome, preco, descricao, marcaTeste, imagePath, memoria, chipset);

        assertNotNull(PlacaDeVideo, "O objeto PlacaDeVideo não deve ser nulo.");
        assertEquals(nome, PlacaDeVideo.getNome(), "O nome da PlacaDeVideo deve ser o esperado.");
        assertEquals(preco, PlacaDeVideo.getPreco(), "O preço da PlacaDeVideo deve ser o esperado.");
        assertEquals(descricao, PlacaDeVideo.getDescricao(), "A descrição da PlacaDeVideo deve ser a esperada.");
        assertEquals(marcaTeste, PlacaDeVideo.getMarca(), "A marca da PlacaDeVideo deve ser a esperada.");
        assertEquals(imagePath, PlacaDeVideo.getImagePath(), "O caminho da imagem deve ser o esperado.");
        assertEquals(memoria, PlacaDeVideo.getMemoria(), "A memória da PlacaDeVideo deve ser a esperada.");
        assertEquals(chipset, PlacaDeVideo.getChipset(), "O chipset da PlacaDeVideo deve ser o esperado.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a memoria do PlacaDeVideo for 0")
    void deveLancarExcecaoSeMemoriaForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaDeVideo("PlacaDeVideo sem memória", 30000, "PlacaDeVideo com 0gb de memória", marcaTeste, "/images/PlacaDeVideo_sem_memoria.png", 0, "Ampere");
        }, "Deve lançar IllegalArgumentException se a memória for 0.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se a memoria do PlacaDeVideo for negativa")
    void deveLancarExcecaoSeMemoriaForNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaDeVideo("PlacaDeVideo memória negativa", 30000, "PlacaDeVideo com menos de 0gb de memória", marcaTeste, "/images/PlacaDeVideo_memoria_negativa.png", -10, "Ampere");
        }, "Deve lançar IllegalArgumentException se a memória for negativa.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o chipset do PlacaDeVideo for nulo")
    void deveLancarExcecaoSeChipsetForNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaDeVideo("PlacaDeVideo sem chipset", 30000, "PlacaDeVideo com chipset nulo", marcaTeste, "/images/PlacaDeVideo_chipset_nulo.png", 8, null);
        }, "Deve lançar IllegalArgumentException se o chipset for nulo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o chipset do PlacaDeVideo for uma string vazia")
    void deveLancarExcecaoSeChipsetForVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaDeVideo("PlacaDeVideo chipset vazio", 30000, "PlacaDeVideo com chipset vazio", marcaTeste, "/images/PlacaDeVideo_chipset_vazio.png", 8, "");
        }, "Deve lançar IllegalArgumentException se o chipset for uma string vazia.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o chipset do PlacaDeVideo for uma string com apenas espaços")
    void deveLancarExcecaoSeChipsetForEspacos() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaDeVideo("PlacaDeVideo chipset espaços", 30000, "PlacaDeVideo com chipset apenas com espaços", marcaTeste, "/images/PlacaDeVideo_chipset_espacos.png", 8, "   ");
        }, "Deve lançar IllegalArgumentException se o chipset for uma string com apenas espaços.");
    }
}
