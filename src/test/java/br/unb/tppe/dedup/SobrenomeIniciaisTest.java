package br.unb.tppe.dedup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("initials")
@Tag("integration")
@DisplayName("Caso 2 - Sobrenome + iniciais (com ou sem pontos)")
class SobrenomeIniciaisTest {

    @Test
    @DisplayName("Ana de Mattos Seabra e suas formas abreviadas")
    void anaDeMattosSeabra() {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(
                28372, "Ana de Mattos Seabra",
                582585, "A. M. Seabra",
                582585, "Seabra A. M.",
                582585, "AM Seabra",
                582585, "Ana Mattos Seabra"));

        for (RegistroAutor r : saida) {
            assertEquals(28372, r.getId());
            assertEquals("Ana de Mattos Seabra", r.getNome());
        }
    }

    @Test
    @DisplayName("Cassius de Souza e suas formas abreviadas")
    void cassiusDeSouza() {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(
                28371, "Cassius de Souza",
                746936, "Souza C.",
                746936, "C. Souza",
                746936, "Souza, Cassius de",
                746936, "Cassius Souza"));

        for (RegistroAutor r : saida) {
            assertEquals(28371, r.getId());
            assertEquals("Cassius de Souza", r.getNome());
        }
    }

    @ParameterizedTest
    @Tag("parametrized")
    @ValueSource(strings = {"A. M. Seabra", "Seabra A. M.", "AM Seabra", "Ana Mattos Seabra", "Seabra A M"})
    void cadaAbreviacaoEquivaleAoCompleto(String variacao) {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(
                28372, "Ana de Mattos Seabra",
                582585, variacao));
        assertEquals(28372, saida.get(1).getId());
        assertEquals("Ana de Mattos Seabra", saida.get(1).getNome());
    }

    @ParameterizedTest
    @Tag("parametrized")
    @ValueSource(strings = {"Souza C.", "C. Souza", "Souza, Cassius de", "Cassius Souza"})
    void cassiusCadaAbreviacao(String variacao) {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(
                28371, "Cassius de Souza",
                746936, variacao));
        assertEquals(28371, saida.get(1).getId());
        assertEquals("Cassius de Souza", saida.get(1).getNome());
    }
}
