package pcbuilder.computador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pcbuilder.components.*; 
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ComputadorTest {

    // Componentes que serão reutilizados em vários testes
    private Marca marcaTeste;

    private Processador processador;
    private PlacaMae placaMae;
    private MemoriaRAM memoriaRAM1;
    private MemoriaRAM memoriaRAM2;
    private PlacaDeVideo placaDeVideo;
    private Fonte fonte;
    private Gabinete gabinete;
    private Cooler cooler1;
    private Cooler cooler2;
    private SSDNVME ssd;

    @BeforeEach
    void setUp() {
        marcaTeste = Marca.INTEL;

        processador = new Processador("Intel Core i7-10700K",
                160000,
                "8 núcleos, 16 threads, até 5.1GHz Turbo",
                marcaTeste,
                "/images/i7-10700k.png",
                "LGA1200",
                8,
                16);
        placaMae = new PlacaMae("Asus Prime Z490",
                75000,
                "Placa-mãe ATX LGA1200, ideal para 10ª geração Intel",
                marcaTeste,
                "/images/prime_z490.png",
                "LGA1200",
                "Z490",
                4,
                128,
                "DDR4",
                120);
        memoriaRAM1 = new MemoriaRAM("G.Skill Ripjaws V 8GB",
                30000,
                "Memória DDR4 8GB 2666MHz",
                marcaTeste,
                "/images/ripjaws_v.png",
                8,
                2666,
                "DDR4");
        memoriaRAM2 = new MemoriaRAM("Corsair Vengeance LPX 16GB",
                55000,
                "Memória DDR4 16GB 3200MHz",
                marcaTeste,
                "/images/vengeance_lpx.png",
                16,
                3200,
                "DDR4");
        placaDeVideo = new PlacaDeVideo("Intel Arc A770",
                180000,
                "Placa de vídeo com 16GB GDDR6 e DLSS-like XeSS",
                marcaTeste,
                "/images/arc_a770.png",
                16,
                "Xe-HPG");
        fonte = new Fonte("Corsair CX550",
                30000,
                "Fonte semimodular 550W 80 Plus Bronze",
                marcaTeste,
                "/images/fonte_cx550.png",
                550);
        gabinete = new Gabinete("Cooler Master NR600",
                40000,
                "Gabinete Mid Tower com boa ventilação frontal",
                marcaTeste,
                "/images/nr600.png",
                450,
                210,
                470);
        cooler1 = new Cooler("Cooler Master Hyper 212",
                15000,
                "Cooler a ar com dissipador de calor",
                marcaTeste,
                "/images/hyper_212.png",
                "Air");
        cooler2 = new Cooler("Noctua NH-D15",
                35000,
                "Melhor cooler a ar do mercado",
                marcaTeste,
                "/images/nh_d15.png",
                "Air");
        ssd = new SSDNVME("Kingston NV2 500GB",
                28000,
                "SSD NVMe 500GB Gen4 x4, leitura até 3500MB/s",
                marcaTeste,
                "/images/kingston_nv2.png",
                500,
                80);
    }

    @Test
    @DisplayName("Deve inicializar um Computador com todos os componentes e preço total correto")
    void deveCriarComputadorCompletoComPrecoCorreto() {
        List<Cooler> coolers = Arrays.asList(cooler1, cooler2);
        List<MemoriaRAM> memoriasRAM = Arrays.asList(memoriaRAM1, memoriaRAM2);

        // Preço esperado = soma de todos os componentes
        // 160000(CPU) + 75000(MB) + 30000(RAM1) + 55000(RAM2) + 180000(GPU) + 30000(Fonte) + 40000(Gabinete) + 15000(Cooler1) + 35000(Cooler2) + 28000(SSD)
        int precoEsperado = processador.getPreco() + placaMae.getPreco() + memoriaRAM1.getPreco() + memoriaRAM2.getPreco() +
                            placaDeVideo.getPreco() + fonte.getPreco() + gabinete.getPreco() +
                            cooler1.getPreco() + cooler2.getPreco() + ssd.getPreco();

        // Utiliza o construtor mais completo: public Computador(Fonte fonte, PlacaMae placaMae, PlacaDeVideo placaDeVideo, Processador processador, List<Cooler> coolers, List<MemoriaRAM> memoriasRAM, Gabinete gabinete, SSDNVME ssd)
        Computador computador = new Computador(fonte, placaMae, placaDeVideo, processador,
                                               coolers, memoriasRAM, gabinete, ssd);

        assertNotNull(computador, "O objeto Computador não deve ser nulo.");
        assertEquals(fonte, computador.getFonte(), "A fonte não corresponde.");
        assertEquals(placaMae, computador.getPlacaMae(), "A placa mãe não corresponde.");
        assertEquals(placaDeVideo, computador.getPlacaDeVideo(), "A placa de vídeo não corresponde.");
        assertEquals(processador, computador.getProcessador(), "O processador não corresponde.");
        assertEquals(gabinete, computador.getGabinete(), "O gabinete não corresponde.");
        assertEquals(ssd, computador.getSSD(), "O SSD não corresponde.");

        assertEquals(2, computador.getCoolers().size(), "Deve ter 2 coolers.");
        assertTrue(computador.getCoolers().contains(cooler1), "Deve conter cooler1.");
        assertTrue(computador.getCoolers().contains(cooler2), "Deve conter cooler2.");

        assertEquals(2, computador.getMemoriasRAM().size(), "Deve ter 2 memórias RAM.");
        assertTrue(computador.getMemoriasRAM().contains(memoriaRAM1), "Deve conter memoriaRAM1.");
        assertTrue(computador.getMemoriasRAM().contains(memoriaRAM2), "Deve conter memoriaRAM2.");

        assertEquals(precoEsperado, computador.getPrecoTotal(), "O preço total do computador completo está incorreto.");
    }

    @Test
    @DisplayName("Deve inicializar um Computador sem SSD, com placa de vídeo e preço total correto")
    void deveCriarComputadorSemSSDComPlacaVideoEPrecoCorreto() {
        List<Cooler> coolers = Collections.singletonList(cooler1); // Apenas um cooler
        List<MemoriaRAM> memoriasRAM = Collections.singletonList(memoriaRAM1); // Apenas uma RAM

        // Preço esperado = soma dos essenciais + GPU + 1 Cooler + 1 RAM
        // 160000(CPU) + 75000(MB) + 30000(RAM1) + 180000(GPU) + 30000(Fonte) + 40000(Gabinete) + 15000(Cooler1)
        int precoEsperado = processador.getPreco() + placaMae.getPreco() + memoriaRAM1.getPreco() +
                            placaDeVideo.getPreco() + fonte.getPreco() + gabinete.getPreco() +
                            cooler1.getPreco();

        // Utiliza o construtor: public Computador(Fonte fonte, PlacaMae placaMae, PlacaDeVideo placaDeVideo, Processador processador, List<Cooler> coolers, List<MemoriaRAM> memoriasRAM, Gabinete gabinete)
        Computador computador = new Computador(fonte, placaMae, placaDeVideo, processador,
                                               coolers, memoriasRAM, gabinete); // SSD é implicitamente null

        assertNotNull(computador, "O objeto Computador não deve ser nulo.");
        assertNull(computador.getSSD(), "O SSD deve ser nulo.");
        assertEquals(placaDeVideo, computador.getPlacaDeVideo(), "A placa de vídeo deve estar presente.");
        assertEquals(precoEsperado, computador.getPrecoTotal(), "O preço total sem SSD está incorreto.");
    }

    @Test
    @DisplayName("Deve inicializar um Computador sem placa de vídeo e sem SSD, com preço total correto")
    void deveCriarComputadorSemPlacaVideoSemSSDComPrecoCorreto() {
        List<Cooler> coolers = new ArrayList<>(); // Sem coolers
        List<MemoriaRAM> memoriasRAM = Collections.singletonList(memoriaRAM2); // Apenas uma RAM

        // Preço esperado = soma dos essenciais + 1 RAM
        // 160000(CPU) + 75000(MB) + 55000(RAM2) + 30000(Fonte) + 40000(Gabinete)
        int precoEsperado = processador.getPreco() + placaMae.getPreco() + memoriaRAM2.getPreco() +
                            fonte.getPreco() + gabinete.getPreco();

        // Utiliza o construtor: public Computador(Fonte fonte, PlacaMae placaMae, Processador processador, List<Cooler> coolers, List<MemoriaRAM> memoriasRAM, Gabinete gabinete)
        Computador computador = new Computador(fonte, placaMae, processador,
                                               coolers, memoriasRAM, gabinete); // Placa de vídeo e SSD são implicitamente null

        assertNotNull(computador, "O objeto Computador não deve ser nulo.");
        assertNull(computador.getPlacaDeVideo(), "A placa de vídeo deve ser nula.");
        assertNull(computador.getSSD(), "O SSD deve ser nulo.");
        assertTrue(computador.getCoolers().isEmpty(), "A lista de coolers deve estar vazia.");
        assertEquals(precoEsperado, computador.getPrecoTotal(), "O preço total sem placa de vídeo e SSD está incorreto.");
    }

    @Test
    @DisplayName("Deve calcular o preço total corretamente mesmo com listas vazias de Coolers e RAMs")
    void deveCalcularPrecoTotalComListasVazias() {
        // Apenas componentes essenciais + GPU + SSD
        // 160000(CPU) + 75000(MB) + 180000(GPU) + 30000(Fonte) + 40000(Gabinete) + 28000(SSD)
        int precoEsperado = processador.getPreco() + placaMae.getPreco() + placaDeVideo.getPreco() +
                            fonte.getPreco() + gabinete.getPreco() + ssd.getPreco();

        Computador computador = new Computador(fonte, placaMae, placaDeVideo, processador,
                                               new ArrayList<>(), new ArrayList<>(), // Listas vazias
                                               gabinete, ssd);

        assertNotNull(computador);
        assertTrue(computador.getCoolers().isEmpty(), "A lista de coolers deve estar vazia.");
        assertTrue(computador.getMemoriasRAM().isEmpty(), "A lista de RAMs deve estar vazia.");
        assertEquals(precoEsperado, computador.getPrecoTotal(), "O preço total com listas vazias está incorreto.");
    }

    @Test
    @DisplayName("Getters de listas devem retornar cópias para garantir que a lista interna não seja modificada")
    void gettersDeListasDevemRetornarCopias() {
        List<Cooler> coolersOriginais = new ArrayList<>(Arrays.asList(cooler1));
        List<MemoriaRAM> ramsOriginais = new ArrayList<>(Arrays.asList(memoriaRAM1));

        Computador computador = new Computador(fonte, placaMae, placaDeVideo, processador,
                                               coolersOriginais, ramsOriginais, gabinete, ssd);

        // Obtenha as listas através dos getters
        List<Cooler> coolersRetornados = computador.getCoolers();
        List<MemoriaRAM> ramsRetornadas = computador.getMemoriasRAM();

        // Tente modificar as listas retornadas
        coolersRetornados.add(cooler2); // Adiciona na cópia, não na original
        ramsRetornadas.remove(memoriaRAM1); // Remove da cópia, não da original

        // Verifique se as listas internas do Computador NÃO foram modificadas
        assertEquals(1, computador.getCoolers().size(), "A lista interna de coolers não deve ser modificada externamente.");
        assertTrue(computador.getCoolers().contains(cooler1), "A lista interna de coolers ainda deve conter cooler1.");

        assertEquals(1, computador.getMemoriasRAM().size(), "A lista interna de RAMs não deve ser modificada externamente.");
        assertTrue(computador.getMemoriasRAM().contains(memoriaRAM1), "A lista interna de RAMs ainda deve conter memoriaRAM1.");
    }

    // --- Testes de Cenários Inválidos (NullPointerException para componentes essenciais) ---
    /*
     * Estes testes confirmam que um NullPointerException será lançado se componentes essenciais
     * forem passados como nulos ao construtor de Computador.
     * Importante: essa validação de nulidade de componentes essenciais deve ser tratada
     * primariamente pela classe ComputadorBuilder (ou camada de validação),
     * não refletindo o comportamento "ideal" de validação do construtor de Computador.
     */
    @Test
    @DisplayName("Deve lançar NullPointerException se a Fonte for nula no construtor")
    void deveLancarNullPointerExceptionSeFonteNula() {
        assertThrows(NullPointerException.class, () -> {
            new Computador(null, placaMae, placaDeVideo, processador, new ArrayList<>(), new ArrayList<>(), gabinete, ssd);
        }, "Deve lançar NullPointerException se a fonte for nula.");
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se a PlacaMae for nula no construtor")
    void deveLancarNullPointerExceptionSePlacaMaeNula() {
        assertThrows(NullPointerException.class, () -> {
            new Computador(fonte, null, placaDeVideo, processador, new ArrayList<>(), new ArrayList<>(), gabinete, ssd);
        }, "Deve lançar NullPointerException se a placa mãe for nula.");
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se o Processador for nulo no construtor")
    void deveLancarNullPointerExceptionSeProcessadorNulo() {
        assertThrows(NullPointerException.class, () -> {
            new Computador(fonte, placaMae, placaDeVideo, null, new ArrayList<>(), new ArrayList<>(), gabinete, ssd);
        }, "Deve lançar NullPointerException se o processador for nulo.");
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se o Gabinete for nulo no construtor")
    void deveLancarNullPointerExceptionSeGabineteNulo() {
        assertThrows(NullPointerException.class, () -> {
            new Computador(fonte, placaMae, placaDeVideo, processador, new ArrayList<>(), new ArrayList<>(), null, ssd);
        }, "Deve lançar NullPointerException se o gabinete for nulo.");
    }
}