# PROGRESS — Modernização caelum-stella

Atualizado: 2026-10-02

## Fase atual

Fase 1 (Java 8) + triagem inicial concluídas; aguardando review no upstream.

## Concluído

- **Baseline JDK 11**: `mvn -B clean install` verde — 975 testes, 0 falhas.
- **Baseline JDK 21**: `mvn -B clean test` — 32 classes de teste do `stella-core` falham por
  Mockito 1.8.5 (`ClassImposterizer` usa reflection em `ClassLoader.defineClass`, bloqueada no
  JDK 17+). Demais módulos verdes. Vira tarefa da Fase 4.
- **PR upstream #319** (`fix/316-java8-bytecode`): compila com `release 8` → bytecode 52.
  Verificado: 975 testes no JDK 11 e `javap` major 52 nos jars de `stella-core` e `stella-boleto`.
  Diff: apenas `pom.xml`. Corrige #316.
- **Triagem upstream** (comentários publicados):
  - #291 — corrigido desde 2.2.0 (PR #292).
  - #309 — corrigido desde 2.2.1 (PR #307; pattern aceita 15 e 75–79).
  - #306 — coberto por testes desde 2.1.5 (commits de 2018, range ABBC pós-2025).
  - #313 — completo na 2.2.2 (PRs #305/#315); Java 8 depende do #319.
  - #316 — comentado com o link do PR #319.
- **Review dos PRs abertos**:
  - #318 (IE Goiás): testado — 12 testes verdes, aderente ao SINTEGRA atual — comentado.
  - #317 (template boleto-sem-sacador-avalista): reproduzi #308
    (`105613749501-4-4` no master → `105613749501-4` no PR); jrxml compila com JasperReports 6.1.0;
    115 testes verdes — comentado.

## Fila (próximas tarefas)

1. **#308** Santander DV duplicado — coberto pelo PR #317 (template padrão é o
   `boleto-sem-sacador-avalista`); `boleto-default.jrxml` já usava só `nossoNumeroECodDocumento`.
   Fechar quando o #317 for mergeado.
2. **#293** campo Instrução cortado — tratado no PR #317 (`jr:listContents`); validar e fechar.
3. **#221** Bradesco: DV "P" quando resto 1 (toca `GeradorDeDigitoPadrao`/`Bradesco`).
4. **#286** IE Goiás — coberto pelo PR #318; fechar após merge.
5. **#288** BB `linhaDigitavel` — sem reprodução; pedir dados na issue.
6. **#312** exemplos com APIs deprecated (`examples/boleto-example` fora do reactor).
7. **#184** `CodigoDeBarrasBuilder` público.
8. **#287** validador de PIS.
9. **Fase 4 (fork)**: JUnit 4.13.2; remover jmock/`mockito-all` (destrava JDK 21); plugins mortos;
   JAXB do pom pai para os módulos certos; JasperReports 6.21; JSF 2.3; ADR dos módulos órfãos.

## Branches

- `fix/316-java8-bytecode` → PR #319 (upstream).
- `review/pr-318`, `review/pr-317` → branches locais de teste (não publicar).
- `modernization` → AGENTS.md + este PROGRESS.md (docs internos).

## Links

- Upstream: https://github.com/caelum/caelum-stella
- PR aberto: https://github.com/caelum/caelum-stella/pull/319
- PRs em review: https://github.com/caelum/caelum-stella/pull/318 · https://github.com/caelum/caelum-stella/pull/317
