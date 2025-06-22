package pcbuilder.components;

/**
 * Classe que representa um cooler de computador.
 * Extende a classe Componente.
 */
public class Cooler extends Componente {
    private String tipo; // Tipo de cooler (ex: ventilador, watercooler)
    
    /**
     * Construtor da classe Cooler.
     * @param nome Nome do cooler.
     * @param preco Preço do cooler em centavos.
     * @param descricao Descrição do cooler.
     * @param marca Marca do cooler.
     * @param imagePath Caminho da imagem do cooler.
     * @param tipo Tipo de cooler (ex: ventilador, watercooler).
     * @throws IllegalArgumentException Se o tipo for nulo ou vazio.
     */
    public Cooler(String nome, int preco, String descricao, Marca marca, String imagePath, String tipo) {
        super(nome, preco, descricao, marca, imagePath);
        // Validação
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo do coller não pode ser nulo ou vazio.");
        }
        this.tipo = tipo;
    }

    // Métodos getters para acessar os atributos do cooler
    /**
     * Retorna o tipo do cooler.
     * @return Tipo do cooler.
     */
    public String getTipo() {
        return tipo;
    }
}