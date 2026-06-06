package br.unb.tppe.dedup;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Suite TP1 - Deduplicacao de Autores")
@SelectClasses({
        NormalizadorNomeTest.class,
        VariacaoTipograficaTest.class,
        SobrenomeIniciaisTest.class,
        ParticulasAbreviacoesTest.class,
        IniciaisAgrupadasTest.class,
        UnificacaoIdTest.class,
        ExcecaoTest.class
})
class SuiteTestes {
}
