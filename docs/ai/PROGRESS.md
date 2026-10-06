# PROGRESS — Modernização caelum-stella

Atualizado: 2026-10-06

## Fase atual

Fase 1 (Java 8) + triagem + fixes iniciais — 5 PRs aguardando review no upstream.
Próxima frente: Fase 4 (modernização do fork) enquanto os PRs não são revisados.

## PRs abertos no upstream

- **#319** (`fix/316-java8-bytecode`) — compila com `release 8` → bytecode 52. Corrige #316.
- **#320** (`fix/221-bradesco-dv`) — dígito de auto-conferência do nosso número Bradesco
  (módulo 11 base 7; resto 1 = "P"). Relacionado a #221.
- **#321** (`fix/288-campo-livre-msg`) — mensagem de campo livre inválido do BB passa a mostrar
  o tamanho real. Relacionado a #288.
- **#322** (`fix/184-codigo-barras-publico`) — `CodigoDeBarrasBuilder` e construtor públicos.
  Relacionado a #184.
- **#323** (`fix/312-exemplos-deprecated`) — exemplos migrados para `Beneficiario`/`Pagador`;
  parent do example corrigido e módulo reativado no reactor. Relacionado a #312.

## Concluído

- **Baseline JDK 11**: `mvn -B clean install` verde — 975 testes, 0 falhas.
- **Baseline JDK 21**: `mvn -B clean test` — 32 classes de teste do `stella-core` falham por
  Mockito 1.8.5 (`ClassImposterizer` usa reflection em `ClassLoader.defineClass`, bloqueada no
  JDK 17+). Demais módulos verdes. Vira tarefa da Fase 4.
- **Triagem upstream** (comentários publicados):
  - #291 — corrigido desde 2.2.0 (PR #292).
  - #309 — corrigido desde 2.2.1 (PR #307; pattern aceita 15 e 75–79).
  - #306 — coberto por testes desde 2.1.5 (commits de 2018, range ABBC pós-2025).
  - #313 — completo na 2.2.2 (PRs #305/#315); Java 8 depende do #319.
  - #316 — comentado com o link do PR #319.
  - #287 — validação de PIS já é coberta pelo `NITValidator` — comentado.
- **Review dos PRs de terceiros**:
  - #318 (IE Goiás): testado — 12 testes verdes, aderente ao SINTEGRA atual — comentado.
  - #317 (template boleto-sem-sacador-avalista): reproduzi #308
    (`105613749501-4-4` no master → `105613749501-4` no PR); jrxml compila com JasperReports 6.1.0;
    115 testes verdes — comentado.

## Fila (próximas tarefas)

1. **#293** campo Instrução cortado — tratado no PR #317 (aguardando merge).
2. **#286** IE Goiás — coberto pelo PR #318 (aguardando merge).
3. **#287** PIS — respondido que o `NITValidator` cobre (aguardando retorno).
4. **Fase 4 (fork, próxima frente ativa)**: JUnit 4.13.2; remover jmock/`mockito-all` (destrava
   JDK 21); plugins mortos (cobertura, eclipse, assembly 2.2-beta-2); JAXB do pom pai para os
   módulos certos; JasperReports 6.21; JSF 2.3; ADR dos módulos órfãos
   (`stella-nfe`, `stella-feriado`, `stella-gateway-formas-pagamento`; remover `stella-taglib/js/flex`).

## Branches

- `fix/316-java8-bytecode` → PR #319.
- `fix/221-bradesco-dv` → PR #320.
- `fix/288-campo-livre-msg` → PR #321.
- `fix/184-codigo-barras-publico` → PR #322.
- `fix/312-exemplos-deprecated` → PR #323.
- `review/pr-318`, `review/pr-317` → branches locais de teste (não publicar).
- `modernization` → AGENTS.md + este PROGRESS.md (docs internos).

## Links

- Upstream: https://github.com/caelum/caelum-stella
- PRs: #319 · #320 · #321 · #322 · #323
- Em review: #318 · #317
