package pcbuilder.components;

public class MemoriaRAM extends Componente {
    private int capacidade; // Capacidade da memória RAM em GB
    private int frequencia; // Frequência da memória RAM em MHz
    private String tipo; // Tipo de memória RAM (ex: DDR4, DDR5)

    /**
     * Construtor da classe MemoriaRAM.
     * @param nome Nome da memória RAM.
     * @param preco Preço da memória RAM em centavos.
     * @param descricao Descrição da memória RAM.
     * @param imagePath Caminho da imagem da memória RAM.
     * @param capacidade Capacidade da memória RAM em GB.
     * @param frequencia Frequência da memória RAM em MHz.
     * @param tipo Tipo de memória RAM (ex: DDR4, DDR5).
     */
    public MemoriaRAM(String nome, int preco, String descricao, Marca marca, String imagePath, int capacidade, int frequencia, String tipo) {
        super(nome, preco, descricao, marca, imagePath);
        this.capacidade = capacidade;
        this.frequencia = frequencia;
        this.tipo = tipo;
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
     * Retorna a frequência da memória RAM em MHz.
     * @return Frequência da memória RAM.
     */
    public int getFrequencia() {
        return frequencia;
    }

    /**
     * Retorna o tipo de memória RAM (ex: DDR4, DDR5).
     * @return Tipo de memória RAM.
     */
    public String getTipo() {
        return tipo;
    }
}