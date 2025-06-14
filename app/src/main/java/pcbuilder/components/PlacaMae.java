package pcbuilder.components;

public class PlacaMae extends Componente {
    private String soquete; // Soquete da placa-mãe (ex: LGA1151, AM4)
    private String chipset; // Chipset da placa-mãe (ex: Intel Z490, AMD B550)
    private int ramSlots; // Número de slots de RAM disponíveis na placa-mãe

    /**
     * Construtor da classe PlacaMae.
     * @param nome Nome da placa-mãe.
     * @param preco Preço da placa-mãe em centavos.
     * @param descricao Descrição da placa-mãe.
     * @param imagePath Caminho da imagem da placa-mãe.
     * @param soquete Soquete da placa-mãe (ex: LGA1151, AM4).
     * @param chipset Chipset da placa-mãe (ex: Intel Z490, AMD B550).
     * @param ramSlots Número de slots de RAM disponíveis na placa-mãe.
     */
    public PlacaMae(String nome, int preco, String descricao, String imagePath, String soquete, String chipset, int ramSlots) {
        super(nome, preco, descricao, imagePath);
        this.soquete = soquete;
        this.chipset = chipset;
        this.ramSlots = ramSlots;
    }

    // Métodos getters para acessar os atributos da placa-mãe

    /**
     * Retorna o soquete da placa-mãe.
     * @return Soquete da placa-mãe.
     */
    public String getSoquete() {
        return soquete;
    }

    /**
     * Retorna o chipset da placa-mãe.
     * @return Chipset da placa-mãe.
     */
    public String getChipset() {
        return chipset;
    }

    /**
     * Retorna o número de slots de RAM disponíveis na placa-mãe.
     * @return Número de slots de RAM.
     */
    public int getRamSlots() {
        return ramSlots;
    }
}