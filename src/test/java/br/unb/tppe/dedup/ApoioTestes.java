package br.unb.tppe.dedup;

import java.util.ArrayList;
import java.util.List;

final class ApoioTestes {

    private ApoioTestes() {
    }

    static List<RegistroAutor> executar(Object[][] pares) {
        List<RegistroAutor> registros = new ArrayList<>();
        for (Object[] par : pares) {
            registros.add(new RegistroAutor((Integer) par[0], (String) par[1]));
        }
        return Deduplicador.deduplicar(registros);
    }

    static Object[][] pares(Object... flat) {
        Object[][] resultado = new Object[flat.length / 2][2];
        for (int i = 0; i < flat.length; i += 2) {
            resultado[i / 2][0] = flat[i];
            resultado[i / 2][1] = flat[i + 1];
        }
        return resultado;
    }
}
