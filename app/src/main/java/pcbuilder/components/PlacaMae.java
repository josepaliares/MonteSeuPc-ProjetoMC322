package pcbuilder.components;

public class PlacaMae extends Componente {
    private String socket; // Socket da placa-mãe (ex: LGA1151, AM4)
    private String chipset; // Chipset da placa-mãe (ex: Intel Z490, AMD B550)
    private int ramSlots; // Número de slots de RAM disponíveis na placa-mãe
    private int ramSizePerSlot; // Frequencia do RAM suportado (ex: 4400 MHz).
    private String ramType;
    private int ssdMaxLength; // Comprimento Físico máximo do SSD

    /**
     * Construtor da classe PlacaMae.
     * @param nome Nome da placa-mãe.
     * @param preco Preço da placa-mãe em centavos.
     * @param descricao Descrição da placa-mãe.
     * @param imagePath Caminho da imagem da placa-mãe.
     * @param socket Soquete da placa-mãe (ex: LGA1151, AM4).
     * @param chipset Chipset da placa-mãe (ex: Intel Z490, AMD B550).
     * @param ramSlots Número de slots de RAM disponíveis na placa-mãe.
     * @param ramSizePerSlot Tamanho do pente de RAM suportado por cada slot.
     * @param ramType; Tipo de RAM suportado (Ex: DDR4, DDR5)
     * @param ssdMaxLength; Comprimento Físico máximo do SSD
     */
    public PlacaMae(String nome, int preco, String descricao, Marca marca, String imagePath, String socket, String chipset, int ramSlots, int ramSizePerSlot, String ramType, int ssdMaxLength){
        super(nome, preco, descricao, marca, imagePath);
        this.socket = socket;
        this.chipset = chipset;
        this.ramSlots = ramSlots;
        this.ramSizePerSlot = ramSizePerSlot;
        this.ramType = ramType;
        this.ssdMaxLength = ssdMaxLength;
    }

    // Métodos getters para acessar os atributos da placa-mãe

    /**
     * Retorna o soquete da placa-mãe.
     * @return Soquete da placa-mãe.
     */
    public String getSocket() {
        return socket;
    }

    /**
     * Retorna o chipset da placa-mãe.
     * @return Chipset da placa-mãe.
     */
    public String getChipset() {
        return chipset;
    }

    /**
     * Retorna a quantidade de slots de RAM
     * @return ramSlots
     */
    public int getRamSlots() {
        return ramSlots;
    }

    /**
     * Retorna a capacidade maxima de cada slot de RAM
     * @return ramSizePerSlot
     */
    public int getRamSizePerSlot() {
        return ramSizePerSlot;
    }

    /**
     * Retorna o tipo de RAM suportado pela placa mae
     * @return ramType
     */
    public String getRamType() {
        return ramType;
    }

    /**
     * Retorna o comprimento máximo do SSD
     * @return ssdMaxLength
     */
    public int getSSDMaxLength() {
        return ssdMaxLength;
    }
}