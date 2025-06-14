package pcbuilder.components;

/**
 * Classe que representa um gabinete de computador.
 * Extende a classe Componente.
 */
public class Gabinete extends Componente {
    private int largura; // Largura do gabinete em centímetros
    private int altura;	 // Altura do gabinete em centímetros
    private int profundidade; // Profundidade do gabinete em centímetros

    /**
     * Construtor da classe Gabinete.
     * @param nome Nome do gabinete.
     * @param preco Preço do gabinete em centavos.
     * @param descricao Descrição do gabinete.
     * @param imagePath Caminho da imagem do gabinete.
     * @param largura Largura do gabinete em centímetros.
     * @param altura Altura do gabinete em centímetros.
     * @param profundidade Profundidade do gabinete em centímetros.
     */
    public Gabinete(String nome, int preco, String descricao, String imagePath, int largura, int altura, int profundidade) {
        super(nome, preco, descricao, imagePath);
        this.largura = largura;
        this.altura = altura;
        this.profundidade = profundidade;
    }

    // Métodos getters para acessar os atributos do gabinete
    /**
     * Retorna a altura do gabinete.
     * @return Formato do gabinete.
     */
    public int getAltura() {
        return altura;
    }
    /**
     * Retorna a largura do gabinete.
     * @return Largura do gabinete.
     */
    public int getLargura() {
        return largura;
    }
    /**
     * Retorna a profundidade do gabinete.
     * @return Profundidade do gabinete.
     */
    public int getProfundidade() {
        return profundidade;
    }
}
