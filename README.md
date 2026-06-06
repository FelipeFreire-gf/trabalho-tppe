<h1 align="center">
  <img src="docs/images/logo.png" alt="Logo Deduplicação de Autores" width="140">
  <br>
  Deduplicação de Autores
  <br>
  <sub><sup>Curadoria de dados de autoria científica - padrão-ouro</sup></sub>
</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Disciplina-TPPE%20(FGA0242)-1565C0?style=flat-square" alt="Disciplina"/>
  <img src="https://img.shields.io/badge/Trabalho-TP1%20·%20Etapa%201%20(TDD)-0277BD?style=flat-square" alt="Etapa"/>
  <img src="https://img.shields.io/badge/UnB-FCTE-00838F?style=flat-square" alt="UnB FCTE"/>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-E76F00?style=flat-square&logo=openjdk&logoColor=white" alt="Java 21"/>
  <img src="https://img.shields.io/badge/JUnit-5-25A162?style=flat-square&logo=junit5&logoColor=white" alt="JUnit 5"/>
  <img src="https://img.shields.io/badge/Maven-build-C71A36?style=flat-square&logo=apachemaven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/testes-53%20passando-2E7D32?style=flat-square" alt="Testes"/>
</p>

<p align="center">
  Aplicação em <b>Java</b> que limpa listas de autores científicos: descobre quando
  vários registros são, na verdade, <b>a mesma pessoa</b> escrita de formas diferentes
  e os unifica em um único registro padrão.
</p>

<p align="center">
  <i>Enunciado completo da disciplina em <a href="ENUNCIADO.md">ENUNCIADO.md</a>.</i>
</p>

---

## Equipe

<table align="center">
  <tr>
    <td align="center">
      <a href="https://github.com/kalebmacedo">
        <img src="https://avatars.githubusercontent.com/u/163928510?v=4" width="90" style="border-radius:50%"><br>
        <sub><b>Kaleb de Souza Macedo</b></sub>
      </a><br>
      <sub>231026975</sub>
    </td>
    <td align="center">
      <a href="https://github.com/MateuSansete">
        <img src="https://avatars.githubusercontent.com/u/164573233?v=4" width="90" style="border-radius:50%"><br>
        <sub><b>Mateus Bastos dos Santos</b></sub>
      </a><br>
      <sub>211062240</sub>
    </td>
    <td align="center">
      <a href="https://github.com/leozinlima">
        <img src="https://avatars.githubusercontent.com/u/105813929?v=4" width="90" style="border-radius:50%"><br>
        <sub><b>Leonardo de Melo Lima</b></sub>
      </a><br>
      <sub>222037700</sub>
    </td>
    <td align="center">
      <a href="https://github.com/Bessazs">
        <img src="https://avatars.githubusercontent.com/u/118318004?v=4" width="90" style="border-radius:50%"><br>
        <sub><b>Vitor Pereira Bessa</b></sub>
      </a><br>
      <sub>180132466</sub>
    </td>
    <td align="center">
      <a href="https://github.com/FelipeFreire-gf">
        <img src="https://avatars.githubusercontent.com/u/62055315?v=4" width="90" style="border-radius:50%"><br>
        <sub><b>Felipe das Neves Freire</b></sub>
      </a><br>
      <sub>202046102</sub>
    </td>
  </tr>
</table>

---

## O que é "deduplicar autores"?

A mesma pessoa costuma aparecer escrita de jeitos diferentes em cada base de
dados: com acento ou sem, com o nome completo ou só as iniciais, com o
sobrenome na frente, com IDs diferentes. **Deduplicar** é juntar todas essas
variações em **um único registro correto**.

<table align="center">
  <tr><th>Entra (vários registros)</th><th>Sai (unificado)</th></tr>
  <tr><td>28372 - Ana de Mattos Seabra</td><td>28372 - Ana de Mattos Seabra</td></tr>
  <tr><td>582585 - A. M. Seabra</td><td>28372 - Ana de Mattos Seabra</td></tr>
  <tr><td>582585 - AM Seabra</td><td>28372 - Ana de Mattos Seabra</td></tr>
</table>

Regras da unificação:

- **Nome** fica a forma mais completa e melhor escrita.
- **ID** fica sempre o **menor** entre os registros iguais.

---

## Problemas que o sistema resolve

| Caso | Problema | Exemplo |
|:----:|----------|---------|
| 1 | Acentos, apóstrofos, cedilha | "Monica Sant\`anna" = "Mônica Sant'anna" |
| 2 | Sobrenome + iniciais | "Cassius de Souza" = "Souza C." |
| 3 | Partícula "de" e pontos opcionais | "Luiz Oliveira Souza" = "Luiz de Oliveira de Souza" |
| 4 | Iniciais agrupadas | "VC Junior" = "Vanilda Cristina Junior" |
| 5 | IDs diferentes p/ mesmo autor | vira o menor ID |

---

## Como o TDD foi aplicado

Ciclo **Red → Green**:

1. **Red** - escrevemos primeiro os testes de cada caso e rodamos com classes
   "stub" vazias. Resultado: **tudo falhou**.
2. **Green** - implementamos o mínimo necessário (`NormalizadorNome`,
   `ComparadorAutor`, `Deduplicador`) até **todos passarem**.
3. Sem refatoração formal - isso é tarefa do TP2.

---

## Como rodar os testes

```bash
mvn test
```

Rodar só uma categoria (usamos `@Tag`):

```bash
mvn test -Dgroups=exception      # só testes de exceção
mvn test -Dgroups=parametrized   # só testes parametrizados
```

Resultado esperado: **BUILD SUCCESS**, com 0 falhas.

---

## Testes (o que cobrimos)

Recursos de teste exigidos pela disciplina:

| Recurso | Onde |
|---------|------|
| Testes unitários | `NormalizadorNomeTest` |
| Testes parametrizados | `@ParameterizedTest` em todos os casos |
| Testes de exceção | `ExcecaoTest` |
| Categorias / tags | `@Tag` (`typographic`, `initials`, `exception`...) |
| Suíte de testes | `SuiteTestes` |

Um arquivo de teste por caso do enunciado:

| Caso | Arquivo |
|:----:|---------|
| 1 - Tipográfico | `VariacaoTipograficaTest` |
| 2 - Sobrenome + iniciais | `SobrenomeIniciaisTest` |
| 3 - Partícula "de" | `ParticulasAbreviacoesTest` |
| 4 - Iniciais agrupadas | `IniciaisAgrupadasTest` |
| 5 - Menor ID | `UnificacaoIdTest` |

---

## Classes principais

| Classe | Responsabilidade |
|--------|------------------|
| `RegistroAutor`   | Registro de autoria (`id` + `nome`), com validação |
| `NormalizadorNome`| Normaliza o nome em "peças" comparáveis (sem acento, sem pontuação, partículas opcionais) |
| `ComparadorAutor` | Diz se dois nomes são a mesma pessoa |
| `Deduplicador`    | Agrupa os iguais e gera os registros unificados |

---

## Estrutura

```
src/main/java/br/unb/tppe/dedup/   -> código da solução
src/test/java/br/unb/tppe/dedup/   -> testes JUnit 5 (1 arquivo por caso)
pom.xml                            -> Maven + JUnit 5
```
