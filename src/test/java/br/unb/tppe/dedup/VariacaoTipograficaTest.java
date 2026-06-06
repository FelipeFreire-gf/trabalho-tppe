package br.unb.tppe.dedup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Tag("typographic")
@Tag("integration")
@DisplayName("Caso 1 - Diferencas tipograficas (acentos, apostrofos, cedilha)")
class VariacaoTipograficaTest {

    @Test
    @DisplayName("Monica/Monica Sant'anna -> forma acentuada e apostrofo padronizado")
    void monicaSantanna() {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(
                31299, "Monica Hirata Sant`anna",
                433095, "Mônica Hirata Sant’anna"));

        for (RegistroAutor r : saida) {
            assertEquals(31299, r.getId());
            assertEquals("Mônica Hirata Sant'anna", r.getNome());
        }
    }

    @Test
    @DisplayName("Sergio/Sergio Guaraldi -> Sergio Henrique Guaraldi acentuado")
    void sergioGuaraldi() {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(
                554799, "Sergio Henrique Guaraldi",
                243350, "Sérgio Henrique Guaraldi"));

        for (RegistroAutor r : saida) {
            assertEquals(243350, r.getId());
            assertEquals("Sérgio Henrique Guaraldi", r.getNome());
        }
    }

    @ParameterizedTest
    @Tag("parametrized")
    @CsvSource({
            "Monica Hirata Sant`anna, Mônica Hirata Sant’anna, Mônica Hirata Sant'anna",
            "Sergio Henrique Guaraldi, Sérgio Henrique Guaraldi, Sérgio Henrique Guaraldi",
            "Raphael Goncalves Viana, Raphael Gonçalves Viana, Raphael Gonçalves Viana"
    })
    void padraoOuroPrefereGrafiaCorreta(String a, String b, String esperado) {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(10, a, 20, b));
        assertEquals(esperado, saida.get(0).getNome());
        assertEquals(esperado, saida.get(1).getNome());
        assertEquals(10, saida.get(0).getId());
        assertEquals(10, saida.get(1).getId());
    }
}
