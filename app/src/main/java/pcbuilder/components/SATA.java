package pcbuilder.components;

public class SATA extends Componente {
    private int capacidade; // Capacidade da memória SATA em GB

    /**
     * Construtor da classe SATA .
     * @param nome Nome do SATA.
     * @param preco Preço do SATA em centavos.
     * @param descricao Descrição do SATA.
     * @param imagePath Caminho da imagem do SATA.
     * @param capacidade Capacidade do SATA em GB.
     */
    public SATA(String nome, int preco, String descricao, Marca marca, String imagePath, int capacidade){
        super(nome, preco, descricao, marca, imagePath);
        this.capacidade = capacidade;
    }

    // Métodos getters para acessar os atributos da memória RAM

    /**
     * Retorna a capacidade da memória RAM em GB.
     * @return Capacidade da memória RAM.
     */
    public int getCapacidade() {
        return capacidade;
    }
}