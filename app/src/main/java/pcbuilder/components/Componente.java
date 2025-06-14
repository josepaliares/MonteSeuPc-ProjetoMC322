package pcbuilder.components;

/**
 * Clase abstrata que representa un componente genérico.
 */
public abstract class Componente {
    private String nome;
    private int preco; // Preço em centavos
    private String descricao;
    private Marca marca; // Por simplicidade todos os componentes compartilham marcas
    private String imagePath;
    
   /**
    * Construtor padrão da classe Componente.
    * @param nome Nome do componente.
    * @param preco Preço do componente em centavos. 
    * @param descricao Descrição do componente.
    * @param marca Marca do componente
    * @param imagePath Caminho da imagem do componente.
    */
    public Componente(String nome, int preco, String descricao, Marca marca, String imagePath) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.marca = marca;
        this.imagePath = imagePath;
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

    // Métodos setters para modificar os atributos do componente
}