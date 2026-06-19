package br.unb.tppe.dedup;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Responsável pela orquestração da normalização de nomes.
 *
 * TP2 - Refatoracao aplicada:
 *
 * [Refact] Extrair Classe, NormalizadorNome::PreparadorNome
 *
 */
public class NormalizadorNome {

    static final Set<String> PARTICULAS =
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

    /**
     * Extrai as partes (tokens) de um nome.
     * Corpo original decomposto por Extrair Classe (TP2) para {@link ExtratorTokens}.
     */
    public List<Parte> tokens(String nome) {
        return new ExtratorTokens(this, nome).extrair();
    }
    public String padronizarApostrofos(String texto) {
        return PreparadorNome.padronizarApostrofos(texto);
    }

    public String reordenarSobrenome(String nome) {
        return PreparadorNome.reordenarSobrenome(nome);
    }

    String semAcentos(String texto) {
        return PreparadorNome.semAcentos(texto);
    }

    List<String> separarIniciais(String token) {
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
        return PreparadorNome.umEspaco(texto);
    }
}