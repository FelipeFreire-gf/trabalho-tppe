package br.unb.tppe.dedup;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Caso 1 - Diferencas tipograficas")
@SelectClasses(VariacaoTipograficaTest.class)
class Caso1TipograficoSuite {
}
