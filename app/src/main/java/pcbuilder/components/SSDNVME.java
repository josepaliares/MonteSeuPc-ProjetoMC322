package pcbuilder.components;

public class SSDNVME extends Componente {
    private int capacidade; // Capacidade da memória RAM em GB
    private int length; // Comprimento físico do SSD em mm.

    /**
     * Construtor da classe SSDNVME .
     * @param nome Nome do SSD.
     * @param preco Preço do SSD em centavos.
     * @param descricao Descrição do SSD.
     * @param imagePath Caminho da imagem do SSD.
     * @param capacidade Capacidade do SSD em GB.
     * @param length Comprimento físico do SSD em mm.
     */
    public SSDNVME(String nome, int preco, String descricao, Marca marca, String imagePath, int capacidade, int length) {
        super(nome, preco, descricao, marca, imagePath);
        this.capacidade = capacidade;
        this.length = length;
    }

    // Métodos getters para acessar os atributos da memória RAM

    /**
     * Retorna a capacidade da memória RAM em GB.
     * @return Capacidade da memória RAM.
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Retorna o comprimento físico do SSD.
     * @return Frequência da memória RAM.
     */
    public int getLength(){
        return length;
    }
}