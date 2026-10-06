# AGENTS.md — caelum-stella (fork pablovns)

Fork de https://github.com/caelum/caelum-stella. Este repo contém:
- branches `fix/<n>-slug` para **PR no upstream** (correção de issue, diff mínimo);
- branch `modernization` com melhorias internas (docs, deps, CI, refactors) que **nunca** vão em PR upstream.

## Regras de contribuição

- Correção de issue → branch criada de `upstream/master`; um PR isolado por issue no repo original.
- Guardrails antes de cada PR:
  - `git log --oneline upstream/master..HEAD` → só commits daquela issue;
  - `git diff --stat upstream/master...HEAD` → só arquivos relevantes;
  - rebase em `upstream/master` antes de abrir/atualizar;
  - nunca incluir bump de dependência, renomeação, limpeza de plugin, formatação geral ou docs.
- Melhorias/infra/docs → somente na branch `modernization` do fork.
- PRs de terceiros no upstream **não são escopo**: não revisar, não comentar, não aprovar.
- `master` é espelho do upstream (fast-forward apenas):
  `git fetch upstream && git push origin upstream/master:master`.

## Build local

- Toolchain: JDK 11 (`/usr/lib/jvm/temurin-11-jdk-amd64`) + Maven 3.9.16
  (`/home/operador/.m2/wrapper/dists/apache-maven-3.9.16/56ba1f9f/bin/mvn`).
- Build completo: `JAVA_HOME=/usr/lib/jvm/temurin-11-jdk-amd64 mvn -B clean install`.
- Módulo específico: `mvn -B -pl stella-core -am test`.
- O projeto compila com `--release 8` (bytecode Java 8) desde o PR upstream #319.
- No JDK 21 os testes do `stella-core` falham por causa do Mockito 1.8.5 (reflection bloqueada) —
  ver `docs/ai/PROGRESS.md`.

## Estado atual

Ver `docs/ai/PROGRESS.md`.
