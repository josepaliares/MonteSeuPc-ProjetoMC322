package pcbuilder.components;

public class SSDNVME extends Componente {
    private int capacidade; // Capacidade da memória em GB
    private int length; // Comprimento físico do SSD em mm.

    /**
     * Construtor da classe SSDNVME .
     * @param nome Nome do SSD.
     * @param preco Preço do SSD em centavos.
     * @param descricao Descrição do SSD.
     * @param marca Marca do SSD.
     * @param imagePath Caminho da imagem do SSD.
     * @param capacidade Capacidade do SSD em GB.
     * @param length Comprimento físico do SSD em mm.
     * @throws IllegalArgumentException Se a capacidade for menor ou igual a zero ou se o comprimento for menor ou igual a zero.
     */
    public SSDNVME(String nome, int preco, String descricao, Marca marca, String imagePath, int capacidade, int length){
        super(nome, preco, descricao, marca, imagePath);
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade do SSD deve ser maior que zero.");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("O comprimento do SSD deve ser maior que zero.");
        }
        this.capacidade = capacidade;
        this.length = length;
    }

    // Métodos getters para acessar os atributos da memória

    /**
     * Retorna a capacidade da memória em GB.
     * @return Capacidade da memória.
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Retorna o comprimento físico do SSD.
     * @return Frequência da memória.
     */
    public int getLength(){
        return length;
    }
}