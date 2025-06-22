package pcbuilder.factory;

import org.junit.jupiter.api.Test;

import pcbuilder.components.Marca;
import pcbuilder.components.Processador;
import static org.junit.jupiter.api.Assertions.*;

class ComponenteFactoryTest {

    @Test
    void criarProcessador_deveRetornarProcessadorCorreto() {
        Processador proc = ComponenteFactory.criarProcessador(
                    "Intel Core i7-10700K",
                    160000,
                    "8 núcleos, 16 threads, até 5.1GHz Turbo",
                    Marca.INTEL,
                    "/images/i7-10700k.png",
                    "Comet Lake", // !!! Não existe como atributo do processador
                    "LGA1200",
                    8,
                    16
                );
        assertNotNull(proc);
        assertEquals("Intel Core i7-10700K", proc.getNome());
        assertEquals(160000, proc.getPreco());
        assertEquals("8 núcleos, 16 threads, até 5.1GHz Turbo", proc.getDescricao());
        assertEquals(Marca.INTEL, proc.getMarca());
        assertEquals("/images/i7-10700k.png", proc.getImagePath());
        assertEquals("LGA1200", proc.getSocket());
        assertEquals(8, proc.getNucleos());
        assertEquals(16, proc.getThreads());
    }
}