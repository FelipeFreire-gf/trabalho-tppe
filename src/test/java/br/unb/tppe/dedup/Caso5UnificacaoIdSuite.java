package br.unb.tppe.dedup;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Caso 5 - IDs diferentes para o mesmo autor")
@SelectClasses(UnificacaoIdTest.class)
class Caso5UnificacaoIdSuite {
}
