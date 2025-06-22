package pcbuilder.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe auxiliar concreta para testar a classe abstrata Componente.
 */
class ComponenteConcreto extends Componente {
    public ComponenteConcreto(String nome, int preco, String descricao, Marca marca, String imagePath) {
        super(nome, preco, descricao, marca, imagePath);
    }
}

/**
 * Classe de testes JUnit para a classe abstrata Componente.
 */
class ComponenteTest {

    private Marca marcaTeste; // Definindo uma marca de teste para usar em todos os cenários

    @BeforeEach
    void setUp() {
        marcaTeste = Marca.INTEL;
    }

    @Test
    @DisplayName("Deve criar um Componente válido com todos os atributos corretos")
    void deveCriarComponenteValidoComTodosAtributos() {
        // Cenário: Instanciar um objeto Componente válido
        String nome = "Processador Core i7";
        int preco = 150000;
        String descricao = "Processador de alto desempenho para jogos e produtividade.";
        String imagePath = "/images/core_i7.png";

        Componente componente = new ComponenteConcreto(nome, preco, descricao, marcaTeste, imagePath);

        assertNotNull(componente, "O objeto componente não deve ser nulo.");
        assertEquals(nome, componente.getNome(), "O nome do componente deve ser o esperado.");
        assertEquals(preco, componente.getPreco(), "O preço do componente deve ser o esperado.");
        assertEquals(descricao, componente.getDescricao(), "A descrição do componente deve ser a esperada.");
        assertEquals(marcaTeste, componente.getMarca(), "A marca do componente deve ser a esperada.");
        assertEquals(imagePath, componente.getImagePath(), "O caminho da imagem deve ser o esperado.");
    }

    @Test
    @DisplayName("Deve criar um Componente válido com imagePath nulo (opcional)")
    void deveCriarComponenteValidoComImagePathNulo() {
        // Cenário: Componente sem caminho de imagem (imagePath opcional)
        String nome = "Placa Mãe Básica";
        int preco = 30000;
        String descricao = "Placa mãe simples para uso geral.";
        String imagePath = null; // imagePath é nulo

        Componente componente = new ComponenteConcreto(nome, preco, descricao, marcaTeste, imagePath);

        assertNotNull(componente, "O objeto componente não deve ser nulo.");
        assertEquals(nome, componente.getNome());
        assertNull(componente.getImagePath(), "O caminho da imagem deve ser nulo.");
    }

    @Test
    @DisplayName("Deve criar um Componente quando o preço é o valor mínimo permitido (1)")
    void deveCriarComponenteComPrecoMinimo() {
        // Cenário: Preço no limite inferior permitido
        String nome = "Componente Custo Zero";
        int preco = 1; // Menor preço válido
        String descricao = "Componente com o menor preço possível.";
        Componente componente = new ComponenteConcreto(nome, preco, descricao, marcaTeste, null);
        
        assertNotNull(componente, "O componente não deve ser nulo para preço 1.");
        assertEquals(1, componente.getPreco(), "O preço deve ser 1.");
    }

    // Testes para validação do NOME
    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando o nome é nulo")
    void deveLancarExcecaoQuandoNomeNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ComponenteConcreto(null, 10000, "Descrição", marcaTeste, null);
        }, "Deve lançar IllegalArgumentException se o nome for nulo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando o nome é uma string vazia")
    void deveLancarExcecaoQuandoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ComponenteConcreto("", 10000, "Descrição", marcaTeste, null);
        }, "Deve lançar IllegalArgumentException se o nome for uma string vazia.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando o nome contém apenas espaços em branco")
    void deveLancarExcecaoQuandoNomeApenasEspacos() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ComponenteConcreto("   ", 10000, "Descrição", marcaTeste, null);
        }, "Deve lançar IllegalArgumentException se o nome contiver apenas espaços em branco.");
    }

    // Testes para validação do PREÇO
    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando o preço é zero")
    void deveLancarExcecaoQuandoPrecoZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ComponenteConcreto("Nome", 0, "Descrição", marcaTeste, null);
        }, "Deve lançar IllegalArgumentException se o preço for zero.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando o preço é negativo")
    void deveLancarExcecaoQuandoPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ComponenteConcreto("Nome", -50, "Descrição", marcaTeste, null);
        }, "Deve lançar IllegalArgumentException se o preço for negativo.");
    }

    // Testes para validação da DESCRIÇÃO
    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando a descrição é nula")
    void deveLancarExcecaoQuandoDescricaoNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ComponenteConcreto("Nome", 100, null, marcaTeste, null);
        }, "Deve lançar IllegalArgumentException se a descrição for nula.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando a descrição é uma string vazia")
    void deveLancarExcecaoQuandoDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ComponenteConcreto("Nome", 100, "", marcaTeste, null);
        }, "Deve lançar IllegalArgumentException se a descrição for uma string vazia.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando a descrição contém apenas espaços em branco")
    void deveLancarExcecaoQuandoDescricaoApenasEspacos() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ComponenteConcreto("Nome", 100, "   ", marcaTeste, null);
        }, "Deve lançar IllegalArgumentException se a descrição contiver apenas espaços em branco.");
    }

    // Testes para validação da MARCA
    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando a marca é nula")
    void deveLancarExcecaoQuandoMarcaNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ComponenteConcreto("Nome", 100, "Descrição", null, null);
        }, "Deve lançar IllegalArgumentException se a marca for nula.");
    }

    @Test
    @DisplayName("Deve retornar o nome do componente no método toString()")
    void deveRetornarNomeEmToString() {
        String nomeTeste = "Componente toString";
        Componente componente = new ComponenteConcreto(nomeTeste, 100, "Descrição", marcaTeste, null);
        assertEquals(nomeTeste, componente.toString(), "O método toString() deve retornar o nome do componente.");
    }
}