# Guia de Contribuição

Este guia descreve como devemos fazer commits utilizando o padrão de commits convencionais (Conventional Commits) e como organizar as branches de acordo com as tasks.

## 1. Padrão de Branches por Tasks

Para manter o repositório organizado, utilizamos um padrão de nomenclatura de branches baseado em tasks. Cada task deve ter sua própria branch, nomeada de acordo com o seguinte formato:

tipo/<descrição-resumida>

### Tipos de Branches

- **feat/**: Para desenvolvimento de novas funcionalidades.
- **fix/**: Para correção de bugs.
- **refactor/**: Para melhorias no código que não alteram funcionalidades ou correções de bugs.
- **test/**: Para adição ou alteração de testes.
- **docs/**: Para alterações na documentação.

### Exemplos de Branches

- `feat/criar-pagina-login`: Para uma página de login.
- `fix/corrigir-login-bug`: Para corrigir um bug na tela de login.
- `docs/atualizar-readme`: Para atualizar o README.

## 2. Commits Usando Conventional Commits

Os commits devem seguir o padrão [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/). Esse padrão ajuda a manter o histórico de commits claro e consistente.

### Formato de Commit

O commit deve seguir o formato:

tipo: <descrição do commit>


### Tipos de Commits

- **feat**: Uma nova funcionalidade.
- **fix**: Uma correção de bug.
- **docs**: Mudanças na documentação.
- **style**: Alterações de formatação que não afetam a lógica do código (espaços em branco, vírgulas ausentes, etc.).
- **refactor**: Alteração de código que não corrige um bug ou adiciona uma funcionalidade.
- **perf**: Mudanças que melhoram a performance.
- **test**: Adição ou correção de testes.
- **chore**: Outras mudanças que não modificam o código de produção.

### Exemplos de Commits

- `feat: adicionando input de texto`
- `fix: corrigindo redirecionamento da página de login`
- `docs: arrumando readme`
- `refactor: simplificando o metódo de getUsuario`


## 3. Fluxo de Trabalho

1. **Crie uma branch**: Crie uma nova branch para cada tarefa seguindo o padrão descrito.

git checkout -b feat/criar-pagina-login


2. **Desenvolva na branch**: Faça os commits na branch seguindo o padrão de commits convencionais.

3. **Abra um Pull Request (PR)**: Quando a tarefa estiver completa, abra um PR da branch de para a branch principal (geralmente `main` ou `develop`).

4. **Revisão de Código**: Aguarde a revisão do código. Se necessário, faça os ajustes e novos commits na mesma branch.

5. **Merge**: Após a aprovação, faça o merge da branch. Certifique-se de que não há conflitos.

---

Seguindo este guia, manteremos o código organizado, fácil de revisar e com um histórico de commits claro.
