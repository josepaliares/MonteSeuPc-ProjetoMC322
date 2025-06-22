package pcbuilder.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProcessadorTest {

    private Marca marcaTeste; // Definindo uma marca de teste para usar em todos os cenários

    @BeforeEach
    void setUp() {
        marcaTeste = Marca.INTEL;
    }
    
    @Test
    @DisplayName("Deve criar um Processador válida com todos os atributos corretos")
    void deveCriarProcessadorValidaComTodosAtributos() {
        // Cenário: Instanciar um objeto Processador válido
        String nome = "Processador Correta";
        int preco = 50000;
        String descricao = "Processador com todos os atributor corretos";
        String imagePath = "/images/Processador_correto.png";
        String socket = "LGA1151";
        int nucleos = 1; // Para testar o menor núcleos válido
        int threads = 1; // Para testar o menor threads válido

        Processador Processador = new Processador(nome, preco, descricao, marcaTeste, imagePath, socket, nucleos, threads);

        assertNotNull(Processador, "O objeto Processador não deve ser nulo.");
        assertEquals(nome, Processador.getNome(), "O nome da Processador deve ser o esperado.");
        assertEquals(preco, Processador.getPreco(), "O preço da Processador deve ser o esperado.");
        assertEquals(descricao, Processador.getDescricao(), "A descrição da Processador deve ser a esperada.");
        assertEquals(marcaTeste, Processador.getMarca(), "A marca da Processador deve ser a esperada.");
        assertEquals(imagePath, Processador.getImagePath(), "O caminho da imagem deve ser o esperado.");
        assertEquals(socket, Processador.getSocket(), "O socket da Processador deve ser o esperado.");
        assertEquals(nucleos, Processador.getNucleos(), "O número de núcleos do Processador deve ser o esperado.");
        assertEquals(threads, Processador.getThreads(), "O número de threads do Processador deve ser o esperado.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o socket do Processador for nulo")
    void deveLancarExcecaoSeSocketForNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Processador("Processador sem socket", 30000, "Processador com socket nulo", marcaTeste, "/images/Processador_socket_nulo.png", null, 4, 8);
        }, "Deve lançar IllegalArgumentException se o socket for nulo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o socket for uma string vazia")
    void deveLancarExcecaoSeSocketForVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Processador("Processador socket vazio", 30000, "Processador com socket vazio", marcaTeste, "/images/Processador_socket_vazio.png", "", 4, 8);
        }, "Deve lançar IllegalArgumentException se o socket for uma string vazia.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o socket for uma string com apenas espaços")
    void deveLancarExcecaoSeSocketForEspacos() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Processador("Processador socket espaços", 30000, "Processador com socket apenas com espaços", marcaTeste, "/images/Processador_socket_espacos.png", "   ", 4, 8);
        }, "Deve lançar IllegalArgumentException se o socket for uma string com apenas espaços.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o número de núcleos do Processador for 0")
    void deveLancarExcecaoSeNucleosForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Processador("Processador sem núcleos", 30000, "Processador com 0 núcleos", marcaTeste, "/images/Processador_sem_nucleos.png", "LGA1151", 0, 8);
        }, "Deve lançar IllegalArgumentException se o número de núcleos for 0.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o número de núcleos do Processador for negativo")
    void deveLancarExcecaoSeNucleosForNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Processador("Processador núcleos negativos", 30000, "Processador com núcleos negativos", marcaTeste, "/images/Processador_nucleos_negativos.png", "LGA1151", -1, 8);
        }, "Deve lançar IllegalArgumentException se o número de núcleos for negativo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o número de threads do Processador for 0")
    void deveLancarExcecaoSeThreadsForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Processador("Processador sem threads", 30000, "Processador com 0 threads", marcaTeste, "/images/Processador_sem_threads.png", "LGA1151", 4, 0);
        }, "Deve lançar IllegalArgumentException se o número de threads for 0.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o número de threads do Processador for negativo")
    void deveLancarExcecaoSeThreadsForNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Processador("Processador threads negativos", 30000, "Processador com threads negativos", marcaTeste, "/images/Processador_threads_negativos.png", "LGA1151", 4, -1);
        }, "Deve lançar IllegalArgumentException se o número de threads for negativo.");
    }
}
