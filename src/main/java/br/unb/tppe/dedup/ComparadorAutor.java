package br.unb.tppe.dedup;

import java.util.List;

public class ComparadorAutor {

    private final NormalizadorNome normalizador = new NormalizadorNome();

    public boolean mesmoAutor(String nomeA, String nomeB) {
        List<NormalizadorNome.Parte> a = normalizador.tokens(nomeA);
        List<NormalizadorNome.Parte> b = normalizador.tokens(nomeB);
        if (a.size() != b.size()) {
            return false;
        }
        return casar(a, b, 0, new boolean[b.size()]);
    }

    private boolean casar(List<NormalizadorNome.Parte> a, List<NormalizadorNome.Parte> b,
                          int i, boolean[] usados) {
        if (i == a.size()) {
            return true;
        }
        for (int j = 0; j < b.size(); j++) {
            if (!usados[j] && compativel(a.get(i), b.get(j))) {
                usados[j] = true;
                if (casar(a, b, i + 1, usados)) {
                    return true;
                }
                usados[j] = false;
            }
        }
        return false;
    }

    private boolean compativel(NormalizadorNome.Parte x, NormalizadorNome.Parte y) {
        if (x.getTipo() == NormalizadorNome.Tipo.COMPLETO
                && y.getTipo() == NormalizadorNome.Tipo.COMPLETO) {
            return x.getValor().equals(y.getValor());
        }
        return x.getValor().charAt(0) == y.getValor().charAt(0);
    }
}
