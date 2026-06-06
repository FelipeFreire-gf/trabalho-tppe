package br.unb.tppe.dedup;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("exception")
@DisplayName("Tratamento de excecoes na criacao e na deduplicacao")
class ExcecaoTest {

    @Test
    void idNuloLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new RegistroAutor(null, "Ana de Mattos Seabra"));
    }

    @ParameterizedTest
    @Tag("parametrized")
    @ValueSource(ints = {-1, -100})
    void idInvalidoLancaExcecao(int id) {
        assertThrows(IllegalArgumentException.class,
                () -> new RegistroAutor(id, "Ana de Mattos Seabra"));
    }

    @Test
    void nomeNuloLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new RegistroAutor(1, null));
    }

    @ParameterizedTest
    @Tag("parametrized")
    @ValueSource(strings = {"", "   "})
    void nomeVazioLancaExcecao(String nome) {
        assertThrows(IllegalArgumentException.class,
                () -> new RegistroAutor(1, nome));
    }

    @Test
    void listaDeRegistrosNulaLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> Deduplicador.deduplicar(null));
    }

    @Test
    void registroEmFormatoInvalidoLancaExcecao() {
        List<RegistroAutor> registros = new ArrayList<>();
        registros.add(new RegistroAutor(1, "Ana de Mattos Seabra"));
        registros.add(null);
        assertThrows(IllegalArgumentException.class,
                () -> Deduplicador.deduplicar(registros));
    }

    @Test
    void listaVaziaRetornaListaVazia() {
        List<RegistroAutor> saida = Deduplicador.deduplicar(new ArrayList<>());
        assertTrue(saida.isEmpty());
    }
}
