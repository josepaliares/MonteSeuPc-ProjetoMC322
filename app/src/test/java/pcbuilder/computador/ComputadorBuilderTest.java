package pcbuilder.computador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pcbuilder.components.*;
import pcbuilder.exceptions.ComponentesEssenciaisFaltandoException;
import pcbuilder.exceptions.ComponentesIncompativeisException;

import static org.junit.jupiter.api.Assertions.*;

class ComputadorBuilderTest {

    private ComputadorBuilder builder;
    private Marca marcaTeste;

    // Componentes para testes de caminho feliz e erros
    private Fonte fonteValida;
    private PlacaMae placaMaeValida;
    private Processador processadorValido;
    private PlacaDeVideo placaDeVideoValida; // Opcional
    private MemoriaRAM memoriaRAMValida1;
    private MemoriaRAM memoriaRAMValida2;
    private Gabinete gabineteValido;
    private Cooler coolerValido1;
    private Cooler coolerValido2;
    private SSDNVME ssdValido; // Opcional

    // Componentes para cenários de incompatibilidade
    private Processador processadorIncompativelSocket; // Ex: Socket diferente da placaMaeValida
    private PlacaMae placaMaeIncompativelRAMTipo; // Ex: Suporta DDR3, mas RAM é DDR4
    private SSDNVME ssdIncompativelTamanho; // Ex: Tamanho maior que o slot da placa mãe

    @BeforeEach
    void setUp() {
        // Reinicializa o builder para cada teste
        builder = new ComputadorBuilder();
        marcaTeste = Marca.INTEL; // Usando uma marca de exemplo

        // Configuração de componentes VÁLIDOS para cenários de sucesso
        fonteValida = new Fonte("Fonte EVGA 600W", 30000, "Fonte 80 Plus", marcaTeste, "/path/fonte.png", 600);
        placaMaeValida = new PlacaMae("ASUS Prime Z590", 70000, "Placa Mae ATX", marcaTeste, "/path/mb.png", "LGA1200", "Z590", 4, 128, "DDR4", 80); // max SSD length 80
        processadorValido = new Processador("Intel Core i7-11700K", 100000, "CPU de 8 nucleos", marcaTeste, "/path/cpu.png", "LGA1200", 8, 16);
        placaDeVideoValida = new PlacaDeVideo("NVIDIA GeForce RTX 3070", 250000, "GPU poderosa", marcaTeste, "/path/gpu.png", 8, "GDDR6");
        memoriaRAMValida1 = new MemoriaRAM("Corsair Vengeance 8GB", 30000, "RAM 8GB", marcaTeste, "/path/ram1.png", 8, 3200, "DDR4");
        memoriaRAMValida2 = new MemoriaRAM("Corsair Vengeance 16GB", 50000, "RAM 16GB", marcaTeste, "/path/ram2.png", 16, 3200, "DDR4");
        gabineteValido = new Gabinete("NZXT H510", 40000, "Gabinete Mid-Tower", marcaTeste, "/path/case.png", 460, 210, 430);
        coolerValido1 = new Cooler("CoolerMaster Hyper 212", 15000, "Cooler a ar", marcaTeste, "/path/cooler1.png", "Air");
        coolerValido2 = new Cooler("NZXT Kraken X63", 45000, "Water cooler", marcaTeste, "/path/cooler2.png", "Liquid");
        ssdValido = new SSDNVME("Samsung 970 EVO Plus 500GB", 60000, "SSD NVMe Gen3", marcaTeste, "/path/ssd.png", 500, 80); // length 80

        // Configuração de componentes INCOMPATÍVEIS (para testes de exceção)
        // Será testado melhor em CompativelTest.java, mas aqui vamos garantir que o builder lance exceções corretamente
        processadorIncompativelSocket = new Processador("AMD Ryzen 5 5600X", 90000, "CPU incompatível", Marca.AMD, "/path/cpu_amd.png", "AM4", 6, 12);
        placaMaeIncompativelRAMTipo = new PlacaMae("Antiga Placa Mãe DDR3", 50000, "Placa Mae antiga", marcaTeste, "/path/mb_old.png", "LGA1200", "Z490", 2, 64, "DDR3", 80); // Suporta DDR3
        ssdIncompativelTamanho = new SSDNVME("SSD NVMe Longo", 70000, "SSD muito longo", marcaTeste, "/path/ssd_long.png", 1000, 110); // Length > 80 (placaMaeValida max length)
    }

    // Testes de construtor válido

    @Test
    @DisplayName("Deve construir um computador completo e válido com todos os componentes")
    void deveConstruirComputadorCompletoValido() throws ComponentesEssenciaisFaltandoException, ComponentesIncompativeisException {

        builder.adicionarFonte(fonteValida)
               .adicionarPlacaMae(placaMaeValida)
               .adicionarProcessador(processadorValido)
               .adicionarPlacaDeVideo(placaDeVideoValida)
               .adicionarMemoriaRAM(memoriaRAMValida1)
               .adicionarMemoriaRAM(memoriaRAMValida2)
               .adicionarGabinete(gabineteValido)
               .adicionarCooler(coolerValido1)
               .adicionarCooler(coolerValido2)
               .adicionarSSD(ssdValido);

        Computador computador = builder.build();

        assertNotNull(computador, "O computador construído não deve ser nulo.");
        assertEquals(fonteValida, computador.getFonte());
        assertEquals(placaMaeValida, computador.getPlacaMae());
        assertEquals(processadorValido, computador.getProcessador());
        assertEquals(placaDeVideoValida, computador.getPlacaDeVideo());
        assertEquals(gabineteValido, computador.getGabinete());
        assertEquals(ssdValido, computador.getSSD());

        assertEquals(2, computador.getCoolers().size(), "Deve ter 2 coolers.");
        assertTrue(computador.getCoolers().contains(coolerValido1));
        assertTrue(computador.getCoolers().contains(coolerValido2));

        assertEquals(2, computador.getMemoriasRAM().size(), "Deve ter 2 memórias RAM.");
        assertTrue(computador.getMemoriasRAM().contains(memoriaRAMValida1));
        assertTrue(computador.getMemoriasRAM().contains(memoriaRAMValida2));

    }

    @Test
    @DisplayName("Deve construir um computador sem componentes opcionais (placa de video e ssd)")
    void deveConstruirComputadorSemOpcionais() throws ComponentesEssenciaisFaltandoException, ComponentesIncompativeisException {
        builder.adicionarFonte(fonteValida)
               .adicionarPlacaMae(placaMaeValida)
               .adicionarProcessador(processadorValido)
               .adicionarMemoriaRAM(memoriaRAMValida1)
               .adicionarGabinete(gabineteValido)
               .adicionarCooler(coolerValido1);

        Computador computador = builder.build();

        assertNotNull(computador, "O computador construído não deve ser nulo.");
        assertNull(computador.getPlacaDeVideo(), "A placa de vídeo deve ser nula.");
        assertNull(computador.getSSD(), "O SSD deve ser nulo.");
    }

    @Test
    @DisplayName("Deve permitir adicionar múltiplos coolers e memórias RAM")
    void devePermitirAdicionarMultiplosCoolersEMemoriasRAM() throws ComponentesEssenciaisFaltandoException, ComponentesIncompativeisException {
        builder.adicionarFonte(fonteValida)
               .adicionarPlacaMae(placaMaeValida)
               .adicionarProcessador(processadorValido)
               .adicionarGabinete(gabineteValido)
               .adicionarCooler(coolerValido1)
               .adicionarCooler(coolerValido2)
               .adicionarMemoriaRAM(memoriaRAMValida1)
               .adicionarMemoriaRAM(memoriaRAMValida2);

        Computador computador = builder.build();

        assertEquals(2, computador.getCoolers().size(), "Deve ter 2 coolers.");
        assertTrue(computador.getCoolers().contains(coolerValido1));
        assertTrue(computador.getCoolers().contains(coolerValido2));

        assertEquals(2, computador.getMemoriasRAM().size(), "Deve ter 2 memórias RAM.");
        assertTrue(computador.getMemoriasRAM().contains(memoriaRAMValida1));
        assertTrue(computador.getMemoriasRAM().contains(memoriaRAMValida2));
    }

    
    // Testes com componentes essenciais faltando

    @Test
    @DisplayName("Deve lançar ComponentesEssenciaisFaltandoException se a Fonte estiver faltando")
    void deveLancarExcecaoQuandoFonteFalta() {
        builder.adicionarPlacaMae(placaMaeValida)
               .adicionarProcessador(processadorValido)
               .adicionarGabinete(gabineteValido);

        assertThrows(ComponentesEssenciaisFaltandoException.class, () -> {
            builder.build();
        }, "Deve lançar ComponentesEssenciaisFaltandoException quando a fonte está faltando.");
    }

    @Test
    @DisplayName("Deve lançar ComponentesEssenciaisFaltandoException se a Placa Mae estiver faltando")
    void deveLancarExcecaoQuandoPlacaMaeFalta() {
        builder.adicionarFonte(fonteValida)
               .adicionarProcessador(processadorValido)
               .adicionarGabinete(gabineteValido);

        assertThrows(ComponentesEssenciaisFaltandoException.class, () -> {
            builder.build();
        }, "Deve lançar ComponentesEssenciaisFaltandoException quando a placa mãe está faltando.");
    }

    @Test
    @DisplayName("Deve lançar ComponentesEssenciaisFaltandoException se o Processador estiver faltando")
    void deveLancarExcecaoQuandoProcessadorFalta() {
        builder.adicionarFonte(fonteValida)
               .adicionarPlacaMae(placaMaeValida)
               .adicionarGabinete(gabineteValido);

        assertThrows(ComponentesEssenciaisFaltandoException.class, () -> {
            builder.build();
        }, "Deve lançar ComponentesEssenciaisFaltandoException quando o processador está faltando.");
    }

    @Test
    @DisplayName("Deve lançar ComponentesEssenciaisFaltandoException se o Gabinete estiver faltando")
    void deveLancarExcecaoQuandoGabineteFalta() {
        builder.adicionarFonte(fonteValida)
               .adicionarPlacaMae(placaMaeValida)
               .adicionarProcessador(processadorValido);

        assertThrows(ComponentesEssenciaisFaltandoException.class, () -> {
            builder.build();
        }, "Deve lançar ComponentesEssenciaisFaltandoException quando o gabinete está faltando.");
    }

    // Testes de incompatibilidade de componentes

    @Test
    @DisplayName("Deve lançar ComponentesIncompativeisException quando Processador e Placa Mae têm sockets incompatíveis")
    void deveLancarExcecaoQuandoCpuMbIncompativeisPorSocket() {
        builder.adicionarFonte(fonteValida)
               .adicionarPlacaMae(placaMaeValida) // LGA1200
               .adicionarProcessador(processadorIncompativelSocket) // AM4
               .adicionarGabinete(gabineteValido);

        assertThrows(ComponentesIncompativeisException.class, () -> {
            builder.build();
        }, "Deve lançar ComponentesIncompativeisException quando CPU e MB têm sockets incompatíveis.");
    }

    @Test
    @DisplayName("Deve lançar ComponentesIncompativeisException quando RAM e Placa Mae têm tipos de memória incompatíveis")
    void deveLancarExcecaoQuandoRamMbIncompativeisPorTipoMemoria() {
        builder.adicionarFonte(fonteValida)
               .adicionarPlacaMae(placaMaeIncompativelRAMTipo) // Suporta DDR3
               .adicionarProcessador(processadorValido)
               .adicionarMemoriaRAM(memoriaRAMValida1) // DDR4
               .adicionarGabinete(gabineteValido);

        assertThrows(ComponentesIncompativeisException.class, () -> {
            builder.build();
        }, "Deve lançar ComponentesIncompativeisException quando RAM e MB têm tipos de memória incompatíveis.");
    }

    @Test
    @DisplayName("Deve lançar ComponentesIncompativeisException quando SSD NVMe e Placa Mae têm tamanhos de slot incompatíveis")
    void deveLancarExcecaoQuandoSsdMbIncompativeisPorTamanho() {
        builder.adicionarFonte(fonteValida)
               .adicionarPlacaMae(placaMaeValida) // Max SSD Length 80
               .adicionarProcessador(processadorValido)
               .adicionarGabinete(gabineteValido)
               .adicionarSSD(ssdIncompativelTamanho); // SSD Length 110

        assertThrows(ComponentesIncompativeisException.class, () -> {
            builder.build();
        }, "Deve lançar ComponentesIncompativeisException quando SSD NVMe é muito grande para o slot da Placa Mãe.");
    }

    // Testes de exceções ao adicionar componentes nulos

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se tentar adicionar Cooler nulo")
    void deveLancarExcecaoAoAdicionarCoolerNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.adicionarCooler(null);
        }, "Deve lançar IllegalArgumentException ao tentar adicionar um cooler nulo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se tentar adicionar MemoriaRAM nula")
    void deveLancarExcecaoAoAdicionarMemoriaRAMNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.adicionarMemoriaRAM(null);
        }, "Deve lançar IllegalArgumentException ao tentar adicionar uma memória RAM nula.");
    }
}