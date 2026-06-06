package br.unb.tppe.dedup;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NormalizadorNome {

    private static final Set<String> PARTICULAS =
            Set.of("de", "da", "do", "dos", "das", "e", "del", "di");

    public enum Tipo {
        COMPLETO,
        INICIAL
    }

    public static final class Parte {
        private final Tipo tipo;
        private final String valor;

        public Parte(Tipo tipo, String valor) {
            this.tipo = tipo;
            this.valor = valor;
        }

        public Tipo getTipo() {
            return tipo;
        }

        public String getValor() {
            return valor;
        }
    }

    public List<Parte> tokens(String nome) {
        String preparado = reordenarSobrenome(padronizarApostrofos(nome));
        List<Parte> partes = new ArrayList<>();
        for (String bruto : preparado.split("\\s+")) {
            for (String pedaco : separarIniciais(bruto)) {
                String limpo = semAcentos(pedaco).replace(".", "").toLowerCase();
                if (limpo.isEmpty() || PARTICULAS.contains(limpo)) {
                    continue;
                }
                Tipo tipo = limpo.length() == 1 ? Tipo.INICIAL : Tipo.COMPLETO;
                partes.add(new Parte(tipo, limpo));
            }
        }
        return partes;
    }

    public String padronizarApostrofos(String texto) {
        return texto.replace('`', '\'').replace('´', '\'')
                .replace('‘', '\'').replace('’', '\'');
    }

    public String reordenarSobrenome(String nome) {
        int virgula = nome.indexOf(',');
        if (virgula < 0) {
            return umEspaco(nome);
        }
        String esquerda = nome.substring(0, virgula).trim();
        String direita = nome.substring(virgula + 1).trim();
        return umEspaco(direita + " " + esquerda);
    }

    private String semAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
    }

    private List<String> separarIniciais(String token) {
        String nucleo = token.replace(".", "");
        List<String> partes = new ArrayList<>();
        if (nucleo.length() >= 2 && tudoMaiusculo(nucleo)) {
            for (int i = 0; i < nucleo.length(); i++) {
                partes.add(String.valueOf(nucleo.charAt(i)));
            }
        } else {
            partes.add(nucleo);
        }
        return partes;
    }

    private boolean tudoMaiusculo(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (!Character.isLetter(c) || !Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }

    private String umEspaco(String texto) {
        return texto.trim().replaceAll("\\s+", " ");
    }
}
