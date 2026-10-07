# PROGRESS — Modernização caelum-stella

Atualizado: 2026-10-07

## Fase atual

Fase 4 (modernização do fork) em andamento — item (a) concluído: suíte completa verde no JDK 21.

## Concluído

### Fase 4a — testes modernos (JDK 21 verde)

- `mockito-all 1.8.5` → `mockito-core 5.19.0` (commit `7f3cd730`).
- `junit 4.11` → `4.13.2`; `hamcrest 2.2`; `jmock`/`jmock-legacy` removidos; 9 testes migrados
  para Mockito (commit `96ad6d8c`).
- `stella-hibernate-user-types`: Hibernate `[4.0.1.Final,5.2.0-final)` → `5.6.15.Final`,
  HSQLDB 2.2.8 → 2.7.4, user types migrados para `SharedSessionContractImplementor`,
  `flush()` antes do `commit()` nos testes (commit `3e8219a6`).
- Verificação: `mvn -B clean test` verde no **JDK 11 e no JDK 21** (975 testes) e
  `mvn -B clean install` verde no JDK 11.
- Observação: Mockito 5 exige Java 11+ para rodar os testes; o bytecode da lib continua 52.

### PRs upstream (Fase 1–3)

- Abertos: **#319** (#316, release 8), **#320** (#221, DV Bradesco), **#321** (#288, msg BB),
  **#322** (#184, builder público), **#323** (#312, exemplos + reactor).
- Triagem comentada: #291, #306, #309, #313, #316, #287.
- Baseline JDK 11 original: 975 testes verdes.

## Fila (próximas tarefas)

1. **Fase 4b (próxima)** — higiene de build: remover plugins mortos (cobertura, eclipse 2.8,
   assembly 2.2-beta-2, source 2.1.2, changelog/jxr, `oss-parent:7`); mover JAXB do pom pai para os
   módulos que usam; alinhar `maven-compiler-plugin`.
2. **Fase 4c** — CI do fork: matrix JDK 11/21 + check de bytecode 52.
3. **Fase 4d** — deps de produção: JasperReports 6.1 → 6.21; JSF 2.0.2 → `javax.faces` 2.3.
4. **Fase 4e** — ADR dos módulos órfãos (`nfe`, `feriado`, `gateway`) e remoção dos vazios
   (`taglib`, `js`, `flex`). Nota: `stella-gateway-formas-pagamento` usa jmock e está fora do
   reactor — se for reativado, migrar (jmock não está mais no pom pai).
5. **Upstream** — acompanhar review dos PRs #319–#323; após merge do #319, pedir release 2.2.3.

## Branches

- `modernization` → docs + Fase 4 (commits `7f3cd730`, `3e8219a6`, `96ad6d8c`).
- `fix/316-java8-bytecode`, `fix/221-bradesco-dv`, `fix/288-campo-livre-msg`,
  `fix/184-codigo-barras-publico`, `fix/312-exemplos-deprecated` → PRs upstream #319–#323.

## Links

- Upstream: https://github.com/caelum/caelum-stella
- PRs: #319 · #320 · #321 · #322 · #323
