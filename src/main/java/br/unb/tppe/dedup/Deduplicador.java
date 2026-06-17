package br.unb.tppe.dedup;

import java.util.ArrayList;
import java.util.List;

/**
 * Agrupa registros de autores equivalentes e unifica id e nome.
 *
 * TP2 - Refatoracao aplicada:
 * 
 * [Refact] Extrair Metodo, Deduplicador::deduplicar()</li>
 * 
 */
public class Deduplicador {

    private static final NormalizadorNome NORMALIZADOR = new NormalizadorNome();
    private static final ComparadorAutor COMPARADOR = new ComparadorAutor();

    /**
     * Orquestra validacao, agrupamento e construcao dos registros unificados.
     * Corpo original decomposto por Extrair Metodo (TP2).
     */
    public static List<RegistroAutor> deduplicar(List<RegistroAutor> registros) {
        validarRegistros(registros);
        int[] grupo = agruparPorAutor(registros);
        return construirRegistrosUnificados(registros, grupo);
    }

    /** Extraido de {@link #deduplicar(List)} — valida lista e registros nulos. */
    private static void validarRegistros(List<RegistroAutor> registros) {
        if (registros == null) {
            throw new IllegalArgumentException("lista de registros nao pode ser nula");
        }
        for (RegistroAutor r : registros) {
            if (r == null) {
                throw new IllegalArgumentException("registro em formato invalido");
            }
        }
    }

    /** Extraido de {@link #deduplicar(List)} — union-find por equivalencia de nomes. */
    private static int[] agruparPorAutor(List<RegistroAutor> registros) {
        int n = registros.size();
        int[] grupo = new int[n];
        for (int i = 0; i < n; i++) {
            grupo[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (COMPARADOR.mesmoAutor(registros.get(i).getNome(), registros.get(j).getNome())) {
                    unir(grupo, i, j);
                }
            }
        }
        return grupo;
    }

    /** Extraido de {@link #deduplicar(List)} — monta a lista de saida unificada. */
    private static List<RegistroAutor> construirRegistrosUnificados(List<RegistroAutor> registros,
                                                                    int[] grupo) {
        int n = registros.size();
        List<RegistroAutor> saida = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int raizGrupo = raiz(grupo, i);
            saida.add(criarRepresentanteDoGrupo(registros, grupo, raizGrupo));
        }
        return saida;
    }

    /**
     * Extraido de {@link #deduplicar(List)} — escolhe menor id e nome mais completo
     * para um grupo de registros equivalentes.
     */
    private static RegistroAutor criarRepresentanteDoGrupo(List<RegistroAutor> registros,
                                                           int[] grupo,
                                                           int raizGrupo) {
        int menorId = Integer.MAX_VALUE;
        String melhorNome = null;
        for (int k = 0; k < registros.size(); k++) {
            if (raiz(grupo, k) != raizGrupo) {
                continue;
            }
            menorId = Math.min(menorId, registros.get(k).getId());
            String nome = NORMALIZADOR.reordenarSobrenome(registros.get(k).getNome());
            if (melhorNome == null || maisCompleto(nome, melhorNome)) {
                melhorNome = nome;
            }
        }
        return new RegistroAutor(menorId, NORMALIZADOR.padronizarApostrofos(melhorNome));
    }

    private static boolean maisCompleto(String candidato, String atual) {
        int[] c = nota(candidato);
        int[] a = nota(atual);
        for (int i = 0; i < c.length; i++) {
            if (c[i] != a[i]) {
                return c[i] > a[i];
            }
        }
        return false;
    }

    private static int[] nota(String nome) {
        int palavras = 0;
        for (String parte : nome.split("\\s+")) {
            if (parte.replace(".", "").length() > 1) {
                palavras++;
            }
        }
        int letras = 0;
        int acentuadas = 0;
        for (int i = 0; i < nome.length(); i++) {
            char c = nome.charAt(i);
            if (Character.isLetter(c)) {
                letras++;
                if (c > 127) {
                    acentuadas++;
                }
            }
        }
        return new int[]{palavras, letras, acentuadas};
    }

    private static int raiz(int[] grupo, int i) {
        while (grupo[i] != i) {
            i = grupo[i];
        }
        return i;
    }

    private static void unir(int[] grupo, int a, int b) {
        int ra = raiz(grupo, a);
        int rb = raiz(grupo, b);
        if (ra != rb) {
            grupo[Math.max(ra, rb)] = Math.min(ra, rb);
        }
    }
}
