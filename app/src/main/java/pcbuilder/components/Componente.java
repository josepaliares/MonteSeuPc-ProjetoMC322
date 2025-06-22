package pcbuilder.components;

/**
 * Clase abstrata que representa un componente genérico.
 */
public abstract class Componente {
    private String nome;
    private int preco; // Preço em centavos
    private String descricao;
    private Marca marca; // Por simplicidade todos os componentes compartilham marcas
    private String imagePath; // Opcional, caso dê tempo, implementar 
    
   /**
    * Construtor padrão da classe Componente.
    * @param nome Nome do componente.
    * @param preco Preço do componente em centavos. 
    * @param descricao Descrição do componente.
    * @param marca Marca do componente
    * @param imagePath Caminho da imagem do componente.
    * @throws IllegalArgumentException Se algum parâmetro obrigatório for nulo ou vazio, ou se o preço for negativo.
    */
    public Componente(String nome, int preco, String descricao, Marca marca, String imagePath) {
        // Validação
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do componente não pode ser nulo ou vazio.");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("O preço do componente deve ser maior que zero.");
        }
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do componente não pode ser nula ou vazia.");
        }
        if (marca == null) {
            throw new IllegalArgumentException("A marca do componente não pode ser nula.");
        }

        // Atribuição se as validações passarem
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.marca = marca;
        this.imagePath = imagePath; // imagePath pode ser nulo
    }

    // Métodos getters para acessar os atributos do componente

    /**
     * Retorna o nome do componente.
     * @return Nome do componente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o preço do componente em centavos.
     * @return Preço do componente.
     */
    public int getPreco() {
        return preco;
    }

    /**
     * Retorna a descrição do componente.
     * @return Descrição do componente.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna a marca do componente.
     * @return Marca do componente.
     */
    public Marca getMarca(){
        return marca;
    }

    /**
     * Retorna o caminho da imagem do componente.
     * @return Caminho da imagem do componente.
     */
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return getNome();
    }
    // Métodos setters para modificar os atributos do componente
}