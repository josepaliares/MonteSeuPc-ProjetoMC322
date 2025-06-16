package pcbuilder.components;

/**
 * Classe que representa uma fonte de alimentação de computador.
 * Extende a classe Componente.
 */
public class Fonte extends Componente {
    private int potencia; // Potência da fonte em watts

    /**
     * Construtor da classe Fonte.
     * @param nome Nome da fonte.
     * @param preco Preço da fonte em centavos.
     * @param descricao Descrição da fonte.
     * @param imagePath Caminho da imagem da fonte.
     * @param potencia Potência da fonte em watts.
     */
    public Fonte(String nome, int preco, String descricao, Marca marca, String imagePath, int potencia) {
        super(nome, preco, descricao, marca, imagePath);
        this.potencia = potencia;
    }

    // Métodos getters para acessar os atributos da fonte
    /**
     * Retorna a potência da fonte em watts.
     * @return Potência da fonte.
     */
    public int getPotencia() {
        return potencia;
    }
}