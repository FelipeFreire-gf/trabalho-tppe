package br.unb.tppe.dedup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
@DisplayName("Testes unitarios do RegistroAutor")
class RegistroAutorTest {

    @Test
    @DisplayName("Deve criar um RegistroAutor corretamente e retornar seus atributos")
    void deveCriarRegistroAutorCorretamente() {
        RegistroAutor registro = new RegistroAutor(1, "Ana de Mattos Seabra");
        assertEquals(1, registro.getId());
        assertEquals("Ana de Mattos Seabra", registro.getNome());
    }

    @Test
    @DisplayName("Deve manter espacos no meio do nome")
    void deveManterEspacosNoNome() {
        RegistroAutor registro = new RegistroAutor(2, "Ana   Mattos");
        assertEquals("Ana   Mattos", registro.getNome());
    }
}
