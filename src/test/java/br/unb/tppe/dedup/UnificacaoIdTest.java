package br.unb.tppe.dedup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Tag("id_unification")
@Tag("integration")
@DisplayName("Caso 5 - IDs diferentes para o mesmo autor")
class UnificacaoIdTest {

    @Test
    @DisplayName("Raphael Goncalves Viana -> menor ID 31298")
    void raphaelMenorId() {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(
                31298, "Raphael Goncalves Viana",
                433094, "Raphael Gonçalves Viana",
                549243, "Raphael Gonçalves Viana",
                608297, "Raphael Gonçalves Viana",
                746938, "Raphael Gonçalves Viana"));

        for (RegistroAutor r : saida) {
            assertEquals(31298, r.getId());
            assertEquals("Raphael Gonçalves Viana", r.getNome());
        }
    }

    @ParameterizedTest
    @Tag("parametrized")
    @CsvSource({
            "31298;433094;549243;608297;746938, 31298",
            "5;4;3;2;1, 1",
            "900;100;500, 100"
    })
    void menorIdEscolhido(String idsCsv, int menor) {
        List<Object[]> pares = new ArrayList<>();
        for (String parte : idsCsv.split(";")) {
            pares.add(new Object[]{Integer.parseInt(parte.trim()), "Raphael Gonçalves Viana"});
        }
        List<RegistroAutor> saida = ApoioTestes.executar(pares.toArray(new Object[0][]));

        Set<Integer> ids = new HashSet<>();
        for (RegistroAutor r : saida) {
            ids.add(r.getId());
        }
        assertEquals(Set.of(menor), ids);
    }

    @Test
    @DisplayName("Autores distintos mantem IDs distintos")
    void autoresDistintos() {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(
                50, "Raphael Gonçalves Viana",
                10, "Cassius de Souza"));

        assertEquals(50, saida.get(0).getId());
        assertEquals("Raphael Gonçalves Viana", saida.get(0).getNome());
        assertEquals(10, saida.get(1).getId());
        assertEquals("Cassius de Souza", saida.get(1).getNome());
    }
}
