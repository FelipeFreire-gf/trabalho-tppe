package br.unb.tppe.dedup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Tag("unit")
@DisplayName("Testes unitarios do NormalizadorNome")
class NormalizadorNomeTest {

    private final NormalizadorNome normalizador = new NormalizadorNome();

    private List<String> valores(String nome) {
        List<String> valores = new ArrayList<>();
        for (NormalizadorNome.Parte parte : normalizador.tokens(nome)) {
            valores.add(parte.getValor());
        }
        return valores;
    }

    @Test
    void removeAcentosECaixa() {
        assertEquals(List.of("sergio"), valores("Sérgio"));
    }

    @Test
    void particulaDeEIgnorada() {
        assertEquals(List.of("luiz", "souza"), valores("Luiz de Souza"));
    }

    @ParameterizedTest
    @Tag("parametrized")
    @CsvSource({"Sant`anna, sant'anna", "Sant’anna, sant'anna", "Sant'anna, sant'anna"})
    void apostrofosSaoPadronizados(String entrada, String esperado) {
        assertEquals(List.of(esperado), valores(entrada));
    }

    @ParameterizedTest
    @Tag("parametrized")
    @CsvSource({"AM Seabra, a;m;seabra", "VC Junior, v;c;junior", "SH Guaraldi, s;h;guaraldi"})
    void iniciaisAgrupadasSaoSeparadas(String entrada, String esperadoCsv) {
        assertEquals(List.of(esperadoCsv.split(";")), valores(entrada));
    }

    @Test
    void formatoSobrenomeVirgulaNomeEReordenado() {
        assertEquals(List.of("cassius", "souza"), valores("Souza, Cassius de"));
    }

    @ParameterizedTest
    @Tag("parametrized")
    @CsvSource({"A. M. Seabra", "Seabra A. M.", "AM Seabra", "Ana Mattos Seabra"})
    void todaVariacaoTemTresPartes(String nome) {
        assertEquals(3, normalizador.tokens(nome).size());
    }
}
