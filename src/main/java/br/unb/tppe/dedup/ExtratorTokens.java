package br.unb.tppe.dedup;

import java.util.ArrayList;
import java.util.List;

/**
 * TP2 - Refatoracao aplicada:
 * 
 * [Refact] Extrair Classe, NormalizadorNome::tokens() para ExtratorTokens
 * 
 */
class ExtratorTokens {

    private final NormalizadorNome normalizador;
    private final String nome;
    private final List<NormalizadorNome.Parte> partes = new ArrayList<>();

    ExtratorTokens(NormalizadorNome normalizador, String nome) {
        this.normalizador = normalizador;
        this.nome = nome;
    }

    /**
     * Orquestra a extracao de tokens do nome.
     * Corpo original decomposto por Extrair Classe (TP2) de {@link NormalizadorNome#tokens(String)}.
     */
    List<NormalizadorNome.Parte> extrair() {
        String preparado = normalizador.reordenarSobrenome(normalizador.padronizarApostrofos(nome));
        for (String bruto : preparado.split("\\s+")) {
            processarToken(bruto);
        }
        return partes;
    }

    /** Extraido de {@link NormalizadorNome#tokens(String)} — processa cada parte do nome separada por espaco. */
    private void processarToken(String bruto) {
        for (String pedaco : normalizador.separarIniciais(bruto)) {
            adicionarParte(pedaco);
        }
    }

    /** Extraido de {@link NormalizadorNome#tokens(String)} — limpa e adiciona uma parte na lista de tokens. */
    private void adicionarParte(String pedaco) {
        String limpo = normalizador.semAcentos(pedaco).replace(".", "").toLowerCase();
        if (limpo.isEmpty() || NormalizadorNome.PARTICULAS.contains(limpo)) {
            return;
        }
        NormalizadorNome.Tipo tipo = limpo.length() == 1
                ? NormalizadorNome.Tipo.INICIAL
                : NormalizadorNome.Tipo.COMPLETO;
        partes.add(new NormalizadorNome.Parte(tipo, limpo));
    }
}