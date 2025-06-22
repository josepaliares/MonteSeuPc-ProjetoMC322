package pcbuilder.factory;

import pcbuilder.components.*;

/**
 * Classe responsável por criar instâncias dos componentes de computador.
 * Utiliza o design pattern Factory para encapsular a lógica de criação.
 */
public class ComponenteFactory {

    public static PlacaMae criarPlacaMae(String nome, int preco, String descricao, Marca marca, String imagePath, String socket, String chipset, int ramSlots, int ramSizePerSlot, String ramType, int ssdMaxLength){
        return new PlacaMae(nome, preco, descricao, marca, imagePath, socket, chipset, ramSlots, ramSizePerSlot, ramType, ssdMaxLength);
    }

    public static Fonte criarFonte(String nome, int preco, String descricao, Marca marca, String imagePath, int potencia) {
        return new Fonte(nome, preco, descricao, marca, imagePath, potencia);
    }

    public static Gabinete criarGabinete(String nome, int preco, String descricao, Marca marca, String imagePath, int altura, int largura, int profundidade) {
        return new Gabinete(nome, preco, descricao, marca, imagePath, altura, largura, profundidade);
    }

    public static MemoriaRAM criarMemoriaRAM(String nome, int preco, String descricao, Marca marca, String imagePath, int capacidade, int frequencia, String tipo) {
        return new MemoriaRAM(nome, preco, descricao, marca, imagePath, capacidade, frequencia, tipo);
    }

    public static Cooler criarCooler(String nome, int preco, String descricao, Marca marca, String imagePath, String tipo) {
        return new Cooler(nome, preco, descricao, marca, imagePath, tipo);
    }

    public static PlacaDeVideo criarPlacaDeVideo(String nome, int preco, String descricao, Marca marca, String imagePath, int memoria, String chipset) {
        return new PlacaDeVideo(nome, preco, descricao, marca, imagePath, memoria, chipset);
    }

    public static Processador criarProcessador(String nome, int preco, String descricao, Marca marca, String imagePath, String chipset, String socket, int nucleos, int threads) {
        return new Processador(nome, preco, descricao, marca, imagePath, socket, nucleos, threads);
    }

    public static SSDNVME criarSSDNVME(String nome, int preco, String descricao, Marca marca, String imagePath, int capacidade, int length){
        return new SSDNVME(nome, preco, descricao, marca, imagePath, capacidade, length);
    }

    public static SATA criarSATA(String nome, int preco, String descricao, Marca marca, String imagePath, int capacidade){
        return new SATA(nome, preco, descricao, marca, imagePath, capacidade);
    }
}
