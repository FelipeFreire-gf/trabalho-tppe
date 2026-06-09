package br.unb.tppe.dedup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
@DisplayName("Testes unitarios do ComparadorAutor")
class ComparadorAutorTest {

    private final ComparadorAutor comparador = new ComparadorAutor();

    @Test
    @DisplayName("Nomes identicos devem ser do mesmo autor")
    void nomesIdenticos() {
        assertTrue(comparador.mesmoAutor("Raphael Gonçalves Viana", "Raphael Gonçalves Viana"));
    }

    @Test
    @DisplayName("Nomes com quantidades de tokens diferentes nao sao o mesmo autor")
    void nomesQuantidadesTokensDiferentes() {
        assertFalse(comparador.mesmoAutor("Raphael Gonçalves", "Raphael Gonçalves Viana"));
    }

    @Test
    @DisplayName("Nomes compativeis com iniciais devem ser o mesmo autor")
    void nomesCompativeisIniciais() {
        assertTrue(comparador.mesmoAutor("A M Seabra", "Ana Mattos Seabra"));
    }

    @Test
    @DisplayName("Nomes incompativeis devem falhar")
    void nomesIncompativeis() {
        assertFalse(comparador.mesmoAutor("Ana Mattos Seabra", "Maria Mattos Seabra"));
    }
    
    @Test
    @DisplayName("Mesmo autor com particulas diferentes deve ser reconhecido, pois particulas sao ignoradas")
    void nomesComParticulasDiferentes() {
        assertTrue(comparador.mesmoAutor("Cassius de Souza", "Cassius Souza"));
    }
    
    @Test
    @DisplayName("Iniciais incompativeis nao devem ser do mesmo autor")
    void nomesIniciaisIncompativeis() {
        assertFalse(comparador.mesmoAutor("A M Seabra", "B M Seabra"));
    }
}
