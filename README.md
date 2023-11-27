# AJK System - Sistema Acadêmico

Aplicação de um Sistema Acadêmico feito com Java e JavaFX para a disciplina de POO, ministrada pelo professor **Jesiel Viana**.

**Instituto Federal do Piauí - Campus Picos** 

## Começando

Essas instruções fornecerão uma cópia do projeto em execução na sua máquina local para fins de teste. Esse projeto visa demonstrar a aplicabilidade de POO no relacionamento entre entidades do sistema. Outros padrões de projeto como o `MVC` e `DAO` também foram explorados neste sistema.

**Versão do Java**: `17`

O gerenciameto de dependências é feito usando o Maven. Caso ocorra erros ao executar o aplicativo através do `App.java`, tente seguir as instruções abaixo:

### Iniciando o projeto com o `Maven`
Execute o seguinte comando no terminal

```bash
mvn clean install javafx:run
```

***O comando acima deverá ser executado toda vez que uma dependência for adicionada ou existir erros na importação de dependências***

Caso esses problemas persistirem, experimente reiniciar sua `IDE` e tentar novamente. Após os procedimentos acima, seu aplicativo poderá ser iniciado normalmente através do [**`Run`**] fornecido pela sua `IDE`. 

## Instruções para o Banco de Dados
A tenologia usada é o `PostgreSQL`. Qundo for criar sua aplicação, de preferência dê o nome a base de dados de `academic_system` para garantir o perfeito funcionamento. Atente-se para modificar o `user` e o `password` na classe `DatabaseConnection` de acordo com a sua base de dados.

O `SQL` das tabelas está situado na pasta `SQL` na raiz do projeto. Porém, o programa automaticamente irá criá-las para você caso consiga conecta r com a base de dados. 

## Desenvolvedores

- [![Avelar Rodrigues](https://img.shields.io/badge/avelando-GitHub-blueviolet)](https://github.com/avelando)
- [![Jean Carlos](https://img.shields.io/badge/JeanCarlos899-GitHub-blueviolet)](https://github.com/JeanCarlos899)
- [![Karielly de Carvalho](https://img.shields.io/badge/Kariellyy-GitHub-blueviolet)](https://github.com/Kariellyy)

<hr>