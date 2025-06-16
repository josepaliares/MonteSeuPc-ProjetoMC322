package pcbuilder.components;

public class Processador extends Componente {
    private String socket; // Soquete do processador (ex: LGA1151, AM4)
    private int nucleos; // Número de núcleos do processador
    private int threads; // Número de threads do processador

    /**
     * Construtor da classe Processador.
     * @param nome Nome do processador.
     * @param preco Preço do processador em centavos.
     * @param descricao Descrição do processador.
     * @param imagePath Caminho da imagem do processador.
     * @param socket Soquete do processador (ex: LGA1151, AM4).
     * @param nucleos Número de núcleos do processador.
     * @param threads Número de threads do processador.
     */
    public Processador(String nome, int preco, String descricao, Marca marca, String imagePath, String socket, int nucleos, int threads) {
        super(nome, preco, descricao, marca, imagePath);
        this.socket = socket;
        this.nucleos = nucleos;
        this.threads = threads;
    }

    // Métodos getters para acessar os atributos do processador

    /**
     * Retorna o soquete do processador.
     * @return Soquete do processador.
     */
    public String getSocket() {
        return socket;
    }

    /**
     * Retorna o número de núcleos do processador.
     * @return Número de núcleos do processador.
     */
    public int getNucleos() {
        return nucleos;
    }

    /**
     * Retorna o número de threads do processador.
     * @return Número de threads do processador.
     */
    public int getThreads() {
        return threads;
    }
}