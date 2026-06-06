package br.unb.tppe.dedup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("particles")
@Tag("integration")
@DisplayName("Caso 3 - Particulas 'de' e pontos opcionais")
class ParticulasAbreviacoesTest {

    @Test
    @DisplayName("Luiz de Oliveira de Souza e suas variacoes")
    void luizDeOliveiraDeSouza() {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(
                746937, "Luiz de Oliveira de Souza",
                608296, "Luiz Oliveira Souza",
                549242, "Souza, Luiz de Oliveira",
                549242, "Luiz de O. de Souza",
                31297, "Souza, L. O."));

        for (RegistroAutor r : saida) {
            assertEquals(31297, r.getId());
            assertEquals("Luiz de Oliveira de Souza", r.getNome());
        }
    }

    @ParameterizedTest
    @Tag("parametrized")
    @ValueSource(strings = {
            "Luiz Oliveira Souza",
            "Souza, Luiz de Oliveira",
            "Luiz de O. de Souza",
            "Souza, L. O."
    })
    void particulaDeEPontoSaoOpcionais(String variacao) {
        List<RegistroAutor> saida = ApoioTestes.executar(ApoioTestes.pares(
                100, "Luiz de Oliveira de Souza",
                200, variacao));
        assertEquals(100, saida.get(1).getId());
        assertEquals("Luiz de Oliveira de Souza", saida.get(1).getNome());
    }
}
