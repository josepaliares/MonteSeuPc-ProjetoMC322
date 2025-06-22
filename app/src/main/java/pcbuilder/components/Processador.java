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
     * @param marca Marca do processador (ex: Intel, AMD).
     * @param imagePath Caminho da imagem do processador.
     * @param socket Soquete do processador (ex: LGA1151, AM4).
     * @param nucleos Número de núcleos do processador.
     * @param threads Número de threads do processador.
     * @throws IllegalArgumentException Se algum dos parâmetros for inválido.
     */
    public Processador(String nome, int preco, String descricao, Marca marca, String imagePath, String socket, int nucleos, int threads) {
        super(nome, preco, descricao, marca, imagePath);
        if (socket == null || socket.trim().isEmpty()) {
            throw new IllegalArgumentException("O soquete do processador não pode ser nulo ou vazio.");
        }
        if (nucleos <= 0) {
            throw new IllegalArgumentException("O número de núcleos do processador deve ser maior que zero.");
        }
        if (threads <= 0) {
            throw new IllegalArgumentException("O número de threads do processador deve ser maior que zero.");
        }
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