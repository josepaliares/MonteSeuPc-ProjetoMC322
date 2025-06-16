package pcbuilder.components;

public class PlacaDeVideo extends Componente {
    private int memoria; // Memória da placa de vídeo em GB
    private String chipset; // Chipset da placa de vídeo (ex: NVIDIA, AMD)

    /**
     * Construtor da classe PlacaDeVideo.
     * @param nome Nome da placa de vídeo.
     * @param preco Preço da placa de vídeo em centavos.
     * @param descricao Descrição da placa de vídeo.
     * @param imagePath Caminho da imagem da placa de vídeo.
     * @param memoria Memória da placa de vídeo em GB.
     * @param chipset Chipset da placa de vídeo (ex: NVIDIA, AMD).
     */
    public PlacaDeVideo(String nome, int preco, String descricao, Marca marca, String imagePath, int memoria, String chipset) {
        super(nome, preco, descricao, marca, imagePath);
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