package pcbuilder.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlacaMaeTest {

    private Marca marcaTeste; // Definindo uma marca de teste para usar em todos os cenários

    @BeforeEach
    void setUp() {
        marcaTeste = Marca.INTEL;
    }
    
    @Test
    @DisplayName("Deve criar um PlacaMae válida com todos os atributos corretos")
    void deveCriarPlacaMaeValidaComTodosAtributos() {
        // Cenário: Instanciar um objeto PlacaMae válido
        String nome = "PlacaMae Correta";
        int preco = 50000;
        String descricao = "PlacaMae com todos os atributor corretos";
        String imagePath = "/images/PlacaMae_correto.png";
        String socket = "LGA1151";
        String chipset = "Intel Z490";
        int ramSlots = 4;
        int ramSizePerSlot = 16;
        String ramType = "DDR4";
        int ssdMaxLength = 80;

        PlacaMae PlacaMae = new PlacaMae(nome, preco, descricao, marcaTeste, imagePath, socket, chipset, ramSlots, ramSizePerSlot, ramType, ssdMaxLength);

        assertNotNull(PlacaMae, "O objeto PlacaMae não deve ser nulo.");
        assertEquals(nome, PlacaMae.getNome(), "O nome da PlacaMae deve ser o esperado.");
        assertEquals(preco, PlacaMae.getPreco(), "O preço da PlacaMae deve ser o esperado.");
        assertEquals(descricao, PlacaMae.getDescricao(), "A descrição da PlacaMae deve ser a esperada.");
        assertEquals(marcaTeste, PlacaMae.getMarca(), "A marca da PlacaMae deve ser a esperada.");
        assertEquals(imagePath, PlacaMae.getImagePath(), "O caminho da imagem deve ser o esperado.");
        assertEquals(socket, PlacaMae.getSocket(), "O socket da PlacaMae deve ser o esperado.");
        assertEquals(chipset, PlacaMae.getChipset(), "O chipset da PlacaMae deve ser o esperado.");
        assertEquals(ramSlots, PlacaMae.getRamSlots(), "O número de slots de RAM deve ser o esperado.");
        assertEquals(ramSizePerSlot, PlacaMae.getRamSizePerSlot(), "O tamanho do pente de RAM por slot deve ser o esperado.");
        assertEquals(ramType, PlacaMae.getRamType(), "O tipo de RAM deve ser o esperado.");
        assertEquals(ssdMaxLength, PlacaMae.getSSDMaxLength(), "O comprimento máximo do SSD deve ser o esperado.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o socket da PlacaMae for nulo")
    void deveLancarExcecaoSeSocketForNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae sem socket", 30000, "PlacaMae com socket nulo", marcaTeste, "/images/PlacaMae_socket_nulo.png", null, "Intel Z490", 4, 16, "DDR4", 80);
        }, "Deve lançar IllegalArgumentException se o socket for nulo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o socket da PlacaMae for uma string vazia")
    void deveLancarExcecaoSeSocketForVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae socket vazio", 30000, "PlacaMae com socket vazio", marcaTeste, "/images/PlacaMae_socket_vazio.png", "", "Intel Z490", 4, 16, "DDR4", 80);
        }, "Deve lançar IllegalArgumentException se o socket for uma string vazia.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o socket da PlacaMae for uma string com apenas espaços em branco")
    void deveLancarExcecaoSeSocketForEspacosEmBranco() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae socket espaços", 30000, "PlacaMae com socket apenas com espaços", marcaTeste, "/images/PlacaMae_socket_espacos.png", "   ", "Intel Z490", 4, 16, "DDR4", 80);
        }, "Deve lançar IllegalArgumentException se o socket for uma string com apenas espaços em branco.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o chipset da PlacaMae for nulo")
    void deveLancarExcecaoSeChipsetForNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae sem chipset", 30000, "PlacaMae com chipset nulo", marcaTeste, "/images/PlacaMae_chipset_nulo.png", "LGA1151", null, 4, 16, "DDR4", 80);
        }, "Deve lançar IllegalArgumentException se o chipset for nulo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o chipset da PlacaMae for uma string vazia")
    void deveLancarExcecaoSeChipsetForVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae chipset vazio", 30000, "PlacaMae com chipset vazio", marcaTeste, "/images/PlacaMae_chipset_vazio.png", "LGA1151", "", 4, 16, "DDR4", 80);
        }, "Deve lançar IllegalArgumentException se o chipset for uma string vazia.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o chipset da PlacaMae for uma string com apenas espaços em branco")
    void deveLancarExcecaoSeChipsetForEspacosEmBranco() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae chipset espaços", 30000, "PlacaMae com chipset apenas com espaços", marcaTeste, "/images/PlacaMae_chipset_espacos.png", "LGA1151", "   ", 4, 16, "DDR4", 80);
        }, "Deve lançar IllegalArgumentException se o chipset for uma string com apenas espaços em branco.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o número de slots de RAM for zero")
    void deveLancarExcecaoSeRamSlotsForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae sem slots de RAM", 30000, "PlacaMae com 0 slots de RAM", marcaTeste, "/images/PlacaMae_sem_slots_ram.png", "LGA1151", "Intel Z490", 0, 16, "DDR4", 80);
        }, "Deve lançar IllegalArgumentException se o número de slots de RAM for zero.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o número de slots de RAM for negativo")
    void deveLancarExcecaoSeRamSlotsForNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae slots RAM negativos", 30000, "PlacaMae com slots de RAM negativos", marcaTeste, "/images/PlacaMae_slots_ram_negativos.png", "LGA1151", "Intel Z490", -2, 16, "DDR4", 80);
        }, "Deve lançar IllegalArgumentException se o número de slots de RAM for negativo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o tamanho do pente de RAM por slot for zero")
    void deveLancarExcecaoSeRamSizePerSlotForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae sem tamanho de RAM", 30000, "PlacaMae com tamanho de RAM por slot zero", marcaTeste, "/images/PlacaMae_sem_tamanho_ram.png", "LGA1151", "Intel Z490", 4, 0, "DDR4", 80);
        }, "Deve lançar IllegalArgumentException se o tamanho do pente de RAM por slot for zero.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o tamanho do pente de RAM por slot for negativo")
    void deveLancarExcecaoSeRamSizePerSlotForNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae tamanho RAM negativo", 30000, "PlacaMae com tamanho de RAM por slot negativo", marcaTeste, "/images/PlacaMae_tamanho_ram_negativo.png", "LGA1151", "Intel Z490", 4, -8, "DDR4", 80);
        }, "Deve lançar IllegalArgumentException se o tamanho do pente de RAM por slot for negativo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o tipo de RAM for nulo")
    void deveLancarExcecaoSeRamTypeForNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae sem tipo de RAM", 30000, "PlacaMae com tipo de RAM nulo", marcaTeste, "/images/PlacaMae_sem_tipo_ram.png", "LGA1151", "Intel Z490", 4, 16, null, 80);
        }, "Deve lançar IllegalArgumentException se o tipo de RAM for nulo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o tipo de RAM for uma string vazia")
    void deveLancarExcecaoSeRamTypeForVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae tipo RAM vazio", 30000, "PlacaMae com tipo de RAM vazio", marcaTeste, "/images/PlacaMae_tipo_ram_vazio.png", "LGA1151", "Intel Z490", 4, 16, "", 80);
        }, "Deve lançar IllegalArgumentException se o tipo de RAM for uma string vazia.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o tipo de RAM for uma string com apenas espaços em branco")
    void deveLancarExcecaoSeRamTypeForEspacosEmBranco() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae tipo RAM espaços", 30000, "PlacaMae com tipo de RAM apenas com espaços", marcaTeste, "/images/PlacaMae_tipo_ram_espacos.png", "LGA1151", "Intel Z490", 4, 16, "   ", 80);
        }, "Deve lançar IllegalArgumentException se o tipo de RAM for uma string com apenas espaços em branco.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o comprimento máximo do SSD for zero")
    void deveLancarExcecaoSeSSDMaxLengthForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae sem comprimento SSD", 30000, "PlacaMae com comprimento máximo do SSD zero", marcaTeste, "/images/PlacaMae_sem_comprimento_ssd.png", "LGA1151", "Intel Z490", 4, 16, "DDR4", 0);
        }, "Deve lançar IllegalArgumentException se o comprimento máximo do SSD for zero.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o comprimento máximo do SSD for negativo")
    void deveLancarExcecaoSeSSDMaxLengthForNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlacaMae("PlacaMae comprimento SSD negativo", 30000, "PlacaMae com comprimento máximo do SSD negativo", marcaTeste, "/images/PlacaMae_comprimento_ssd_negativo.png", "LGA1151", "Intel Z490", 4, 16, "DDR4", -10);
        }, "Deve lançar IllegalArgumentException se o comprimento máximo do SSD for negativo.");
    }
}
