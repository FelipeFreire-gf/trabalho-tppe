package br.unb.tppe.dedup;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Caso 2 - Sobrenome + iniciais")
@SelectClasses(SobrenomeIniciaisTest.class)
class Caso2SobrenomeIniciaisSuite {
}
