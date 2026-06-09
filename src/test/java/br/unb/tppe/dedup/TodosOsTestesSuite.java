package br.unb.tppe.dedup;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Todos os testes - deduplicacao de autores")
@SelectClasses({
        NormalizadorNomeTest.class,
        VariacaoTipograficaTest.class,
        SobrenomeIniciaisTest.class,
        ParticulasAbreviacoesTest.class,
        IniciaisAgrupadasTest.class,
        UnificacaoIdTest.class,
        ExcecaoTest.class,
        RegistroAutorTest.class,
        ComparadorAutorTest.class
})
class TodosOsTestesSuite {
}
