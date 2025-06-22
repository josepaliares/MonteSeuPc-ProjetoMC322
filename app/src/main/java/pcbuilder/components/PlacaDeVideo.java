package pcbuilder.components;

public class PlacaDeVideo extends Componente {
    private int memoria; // Memória da placa de vídeo em GB
    private String chipset; // Chipset da placa de vídeo (ex: NVIDIA, AMD)

    /**
     * Construtor da classe PlacaDeVideo.
     * @param nome Nome da placa de vídeo.
     * @param preco Preço da placa de vídeo em centavos.
     * @param descricao Descrição da placa de vídeo.
     * @param marca Marca da placa de vídeo.
     * @param imagePath Caminho da imagem da placa de vídeo.
     * @param memoria Memória da placa de vídeo em GB.
     * @param chipset Chipset da placa de vídeo (ex: NVIDIA, AMD).
     * @throws IllegalArgumentException Se a memória for menor ou igual a zero, ou se o chipset for nulo ou vazio.
     */
    public PlacaDeVideo(String nome, int preco, String descricao, Marca marca, String imagePath, int memoria, String chipset) {
        super(nome, preco, descricao, marca, imagePath);
        if (memoria <= 0) {
            throw new IllegalArgumentException("A memória da placa de vídeo deve ser maior que zero.");
        }
        if (chipset == null || chipset.trim().isEmpty()) {
            throw new IllegalArgumentException("O chipset da placa de vídeo não pode ser nulo ou vazio.");
        }
        this.memoria = memoria;
        this.chipset = chipset;
    }

    // Métodos getters para acessar os atributos da placa de vídeo

    /**
     * Retorna a memória da placa de vídeo em GB.
     * @return Memória da placa de vídeo.
     */
    public int getMemoria() {
        return memoria;
    }

    /**
     * Retorna o chipset da placa de vídeo (ex: NVIDIA, AMD).
     * @return Chipset da placa de vídeo.
     */
    public String getChipset() {
        return chipset;
    }

}