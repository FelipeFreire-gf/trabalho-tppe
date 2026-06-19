package br.unb.tppe.dedup;

import java.text.Normalizer;

/**
 * Classe extraída de `NormalizadorNome` contendo operações de
 * preparação/normalização textual (padronizar, remover acentos, espaços, etc.).
 *
 * TP2 - Refatoracao aplicada:
 *
 * [Refact] Extrair Classe, NormalizadorNome::PreparadorNome
 *
 */
public class PreparadorNome {

    public static String padronizarApostrofos(String texto) {
        return texto.replace('`', '\'')
                .replace('´', '\'')
                .replace('‘', '\'')
                .replace('’', '\'');
    }

    public static String reordenarSobrenome(String nome) {
        int virgula = nome.indexOf(',');
        if (virgula < 0) {
            return umEspaco(nome);
        }
        String esquerda = nome.substring(0, virgula).trim();
        String direita = nome.substring(virgula + 1).trim();
        return umEspaco(direita + " " + esquerda);
    }

    public static String semAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
    }

    public static String umEspaco(String texto) {
        return texto.trim().replaceAll("\\s+", " ");
    }
}
