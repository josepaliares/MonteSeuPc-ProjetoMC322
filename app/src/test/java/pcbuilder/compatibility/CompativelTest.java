package pcbuilder.compatibility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pcbuilder.components.*;
import static org.junit.jupiter.api.Assertions.*;

class CompativelTest {

    private Compativel compativelChecker;
    private Marca marcaTeste;

    // --- Componentes para cenários de Processador e Placa Mãe ---
    private Processador cpuIntelLGA1200;
    private PlacaMae mbIntelLGA1200DDR4;
    private Processador cpuAMDAM4; // Incompatível com LGA1200
    private PlacaMae mbAMDAM4DDR4;

    // --- Componentes para cenários de Memória RAM e Placa Mãe ---
    private MemoriaRAM ramDDR4;
    private MemoriaRAM ramDDR3; // Incompatível com DDR4 MB
    private PlacaMae mbIntelLGA1200DDR3; // Placa mãe que suporta DDR3

    // --- Componentes para cenários de SSD NVMe e Placa Mãe ---
    private SSDNVME ssdNVMe80mm; // Comprimento de 80mm (padrão)
    private SSDNVME ssdNVMe110mm; // Comprimento de 110mm (maior que o padrão)
    private PlacaMae mbSuporteSSD80mm; // Suporta SSDs de até 80mm
    private PlacaMae mbSuporteSSD110mm; // Suporta SSDs de até 110mm

    @BeforeEach
    void setUp() {
        compativelChecker = new Compativel();
        marcaTeste = Marca.INTEL; // Usando uma marca de exemplo

        // Inicialização de componentes para testes de Processador e Placa Mãe
        cpuIntelLGA1200 = new Processador("Intel i5-10400F", 100000, "CPU", marcaTeste, "path", "LGA1200", 6, 12);
        mbIntelLGA1200DDR4 = new PlacaMae("Asus B460M", 60000, "MB", marcaTeste, "path", "LGA1200", "B460", 4, 64, "DDR4", 80);
        cpuAMDAM4 = new Processador("AMD Ryzen 5 3600", 90000, "CPU", marcaTeste, "path", "AM4", 6, 12);
        mbAMDAM4DDR4 = new PlacaMae("Gigabyte B550M", 70000, "MB", marcaTeste, "path", "AM4", "B550", 4, 128, "DDR4", 80);

        // Inicialização de componentes para testes de Memória RAM e Placa Mãe
        ramDDR4 = new MemoriaRAM("Kingston HyperX 8GB", 30000, "RAM", marcaTeste, "path", 8, 3200, "DDR4");
        ramDDR3 = new MemoriaRAM("Corsair Vengeance 8GB DDR3", 20000, "RAM", marcaTeste, "path", 8, 1600, "DDR3");
        mbIntelLGA1200DDR3 = new PlacaMae("Antiga MB LGA1200", 40000, "MB Antiga", marcaTeste, "path", "LGA1200", "Z370", 2, 32, "DDR3", 80); // Placa mãe que suporta DDR3

        // Inicialização de componentes para testes de SSD NVMe e Placa Mãe
        ssdNVMe80mm = new SSDNVME("Samsung 970 EVO 500GB", 50000, "SSD", marcaTeste, "path", 500, 80);
        ssdNVMe110mm = new SSDNVME("WD Black SN750 1TB", 80000, "SSD", marcaTeste, "path", 1000, 110);
        mbSuporteSSD80mm = new PlacaMae("ASUS Prime H510", 45000, "MB", marcaTeste, "path", "LGA1200", "H510", 2, 64, "DDR4", 80); // max SSD length 80
        mbSuporteSSD110mm = new PlacaMae("MSI MPG Z490", 90000, "MB", marcaTeste, "path", "LGA1200", "Z490", 4, 128, "DDR4", 110); // max SSD length 110
    }


    @Test
    @DisplayName("Deve retornar TRUE quando Processador e Placa Mae têm o mesmo socket")
    void deveSerCompativelQuandoCpuEMbTemMesmoSocket() {
        assertTrue(compativelChecker.compativel(cpuIntelLGA1200, mbIntelLGA1200DDR4),
                   "Processador e Placa Mãe com mesmo socket devem ser compatíveis.");
    }

    @Test
    @DisplayName("Deve retornar FALSE quando Processador e Placa Mae têm sockets diferentes")
    void naoDeveSerCompativelQuandoCpuEMbTemSocketsDiferentes() {
        assertFalse(compativelChecker.compativel(cpuIntelLGA1200, mbAMDAM4DDR4),
                    "Processador Intel e Placa Mãe AMD (sockets diferentes) não devem ser compatíveis.");
        assertFalse(compativelChecker.compativel(cpuAMDAM4, mbIntelLGA1200DDR4),
                    "Processador AMD e Placa Mãe Intel (sockets diferentes) não devem ser compatíveis.");
    }

    @Test
    @DisplayName("Deve retornar TRUE quando a ordem dos parâmetros é invertida (Placa Mae, Processador)")
    void deveSerCompativelComOrdemInvertidaCpuMb() {
        assertTrue(compativelChecker.compativel(mbIntelLGA1200DDR4, cpuIntelLGA1200),
                   "A ordem invertida (MB, CPU) deve retornar o mesmo resultado.");
    }


    @Test
    @DisplayName("Deve retornar TRUE quando RAM e Placa Mae têm o mesmo tipo de memória (DDR4)")
    void deveSerCompativelQuandoRamEMbTemMesmoTipoMemoria() {
        assertTrue(compativelChecker.compativel(mbAMDAM4DDR4, ramDDR4),
                   "RAM DDR4 e Placa Mãe DDR4 devem ser compatíveis.");
    }

    @Test
    @DisplayName("Deve retornar FALSE quando RAM e Placa Mae têm tipos de memória diferentes (DDR3 vs DDR4)")
    void naoDeveSerCompativelQuandoRamEMbTemTiposMemoriaDiferentes() {
        assertFalse(compativelChecker.compativel(mbIntelLGA1200DDR4, ramDDR3),
                    "RAM DDR3 e Placa Mãe DDR4 não devem ser compatíveis.");
        assertFalse(compativelChecker.compativel(mbIntelLGA1200DDR3, ramDDR4),
                    "RAM DDR4 e Placa Mãe DDR3 não devem ser compatíveis.");
    }

    @Test
    @DisplayName("Deve retornar TRUE quando a ordem dos parâmetros é invertida (MemoriaRAM, Placa Mae)")
    void deveSerCompativelComOrdemInvertidaRamMb() {
        assertTrue(compativelChecker.compativel(ramDDR4, mbIntelLGA1200DDR4),
                   "A ordem invertida (RAM, MB) deve retornar o mesmo resultado.");
    }


    @Test
    @DisplayName("Deve retornar TRUE quando SSD NVMe tem comprimento menor ou igual ao da Placa Mae")
    void deveSerCompativelQuandoSsdEMbComprimentoValido() {
        assertTrue(compativelChecker.compativel(ssdNVMe80mm, mbSuporteSSD80mm),
                   "SSD de 80mm deve ser compatível com MB que suporta até 80mm.");
        assertTrue(compativelChecker.compativel(ssdNVMe80mm, mbSuporteSSD110mm),
                   "SSD de 80mm deve ser compatível com MB que suporta até 110mm.");
        assertTrue(compativelChecker.compativel(ssdNVMe110mm, mbSuporteSSD110mm),
                   "SSD de 110mm deve ser compatível com MB que suporta até 110mm.");
    }

    @Test
    @DisplayName("Deve retornar FALSE quando SSD NVMe tem comprimento maior que o da Placa Mae")
    void naoDeveSerCompativelQuandoSsdEMbComprimentoInvalido() {
        assertFalse(compativelChecker.compativel(ssdNVMe110mm, mbSuporteSSD80mm),
                    "SSD de 110mm não deve ser compatível com MB que suporta apenas até 80mm.");
    }

    @Test
    @DisplayName("Deve retornar TRUE quando a ordem dos parâmetros é invertida (Placa Mae, SSD NVMe)")
    void deveSerCompativelComOrdemInvertidaSsdMb() {
        assertTrue(compativelChecker.compativel(mbSuporteSSD80mm, ssdNVMe80mm),
                   "A ordem invertida (MB, SSD) deve retornar o mesmo resultado.");
    }

    // Os métodos compativel não tratam nulls explicitamente com exceções.
    // Eles lançarão NullPointerException se um componente for null e tentarem chamar um método nele.
    // Isso é aceitável, pois a responsabilidade de passar componentes não-nulos é do Builder.

    @Test
    @DisplayName("Deve lançar NullPointerException se Processador for nulo no teste de socket")
    void deveLancarNPEQuandoCpuNulaNoSocketTest() {
        assertThrows(NullPointerException.class, () -> {
            compativelChecker.compativel((Processador) null, mbIntelLGA1200DDR4);
        }, "Deve lançar NullPointerException se o processador for nulo.");
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se PlacaMae for nula no teste de socket")
    void deveLancarNPEQuandoMbNulaNoSocketTest() {
        assertThrows(NullPointerException.class, () -> {
            compativelChecker.compativel(cpuIntelLGA1200, null);
        }, "Deve lançar NullPointerException se a placa mãe for nula.");
    }
}