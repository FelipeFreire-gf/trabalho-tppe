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
    
    @Test
    void nomeComCraseNaParticulaEPadronizado() {
        // crase no apostrofo deve virar apóstrofo comum
        assertEquals(List.of("sant'anna"), valores("Sant`anna"));
    }

    @Test
    void nomeComVirgulaTrocaOrdem() {
        // "Souza, Luiz" vira "Luiz Souza" antes de tokenizar
        assertEquals(List.of("luiz", "oliveira", "souza"), valores("Souza, Luiz de Oliveira"));
    }

    @Test
    void nomeComApenasIniciaisRetornaUmaPartePorLetra() {
        // "L. O." deve resultar em duas iniciais
        List<NormalizadorNome.Parte> partes = normalizador.tokens("L. O.");
        assertEquals(2, partes.size());
        assertEquals(NormalizadorNome.Tipo.INICIAL, partes.get(0).getTipo());
        assertEquals(NormalizadorNome.Tipo.INICIAL, partes.get(1).getTipo());
    }

    @ParameterizedTest
    @Tag("parametrized")
    @CsvSource({
            "de, 0",
            "da, 0",
            "do, 0",
            "dos, 0",
            "das, 0"
    })
    void particulasConhecidasSaoIgnoradas(String particula, int esperado) {
        assertEquals(esperado, normalizador.tokens(particula).size());
    }

    @ParameterizedTest
    @Tag("parametrized")
    @CsvSource({
            "Monica,  monica",
            "Mônica,  monica",
            "Gonçalves, goncalves",
            "Luíza, luiza"
    })
    void semAcentosENormalizadoCorretamente(String entrada, String esperado) {
        assertEquals(List.of(esperado.trim()), valores(entrada));
    }
}
