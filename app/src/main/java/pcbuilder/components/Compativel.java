package pcbuilder.components;

public class Compativel {
    public boolean compativel(Processador processador, PlacaMae placaMae){
        return processador.getSocket().equals(placaMae.getSocket());
    }
    public boolean compativel(PlacaMae placaMae, Processador processador){
        return compativel(processador,placaMae);
    }
    public boolean compativel(PlacaMae placaMae, MemoriaRAM memoriaRAM){
        return placaMae.getRamType().equals(memoriaRAM.getTipo());
    }
    public boolean compativel(MemoriaRAM memoriaRAM, PlacaMae placaMae){
        return compativel(placaMae, memoriaRAM);
    }
    public boolean compativel(SSDNVME ssdnvme, PlacaMae placaMae){
        return (placaMae.getSSDMaxLength() >= ssdnvme.getLength());
    }
    public boolean compativel(PlacaMae placaMae, SSDNVME ssdnvme){
        return compativel(ssdnvme, placaMae);
    }
}
