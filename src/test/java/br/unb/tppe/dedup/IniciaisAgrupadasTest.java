package br.unb.tppe.dedup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Tag("grouped")
@Tag("integration")
@DisplayName("Caso 4 - Iniciais agrupadas + sobrenome")
class IniciaisAgrupadasTest {

    @Test
    @DisplayName("Vanilda Cristina Junior e VC Junior")
    void vanildaCristinaJunior() {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(
                763027, "Vanilda Cristina Junior",
                763027, "VC Junior"));

        for (RegistroAutor r : saida) {
            assertEquals(763027, r.getId());
            assertEquals("Vanilda Cristina Junior", r.getNome());
        }
    }

    @Test
    @DisplayName("Sergio Henrique Guaraldi e SH Guaraldi")
    void sergioHenriqueGuaraldi() {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(
                243350, "Sérgio Henrique Guaraldi",
                954057, "SH Guaraldi"));

        for (RegistroAutor r : saida) {
            assertEquals(243350, r.getId());
            assertEquals("Sérgio Henrique Guaraldi", r.getNome());
        }
    }

    @ParameterizedTest
    @Tag("parametrized")
    @CsvSource({
            "Vanilda Cristina Junior, VC Junior",
            "Sérgio Henrique Guaraldi, SH Guaraldi",
            "Ana de Mattos Seabra, AM Seabra"
    })
    void iniciaisAgrupadasEquivalemAoCompleto(String completo, String agrupado) {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(1, completo, 2, agrupado));
        assertEquals(1, saida.get(1).getId());
        assertEquals(completo, saida.get(1).getNome());
    }
}
