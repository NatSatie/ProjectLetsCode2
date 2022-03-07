# ProjectLetsCode2

### Enunciado do exercício

Com base nos arquivos `oscar_age_male.csv` e `oscar_age_female.csv`, escreva uma aplicação utilizando o framework Java Streams API para responder as seguintes funcionalidades.

- Quem foi o ator mais jovem a ganhar um Oscar?
- Quem foi a atriz que mais vezes foi premiada?
- Qual atriz entre 20 e 30 anos que mais vezes foi vencedora?
- Quais atores ou atrizes receberam mais de um Oscar? Elabore uma única estrutura contendo atores e atrizes.
- Quando informado o nome de um ator ou atriz, dê um resumo de quantos prêmios ele/ela recebeu e liste ano, idade e nome de cada filme pelo qual foi premiado(a).

## Estrutura do Projeto

Esse projeto tem `Main.java` no qual inicializa todo o sistema.

```
public class Main {
    public static void main(String[] args) {
        DbManager db = new DbManager();
        db.init();
        db.getYoungest(GenderEnum.FEMALE);
        db.getMostPremiere(GenderEnum.FEMALE);
        db.getMostPremiereByAgeGap(GenderEnum.FEMALE, 20,30);
        db.getMoreThanOneOscar();
        db.getActorByName("Michael Douglas");
    }
}
```

Ao observar o código acima, a classe `DbManager` é responsável pelas respostas das perguntas do enunciado.

## db.init()

Ao inicializar o DbManager, ele vai fazer uma leitura das entradas dos arquivos csv. Para isso, temos `FileReader` em `src/main/java/com/company/databases/files/FileReader.java` que segue um padrão dado em Regex.

`DbManager` tem três atributos privados, `DbOscar`, `DbActor`, `DbMovie`, que correspondem ao banco de dados sobre as premiações do Oscar registradros, atores participantes e os filmes premiados.

![](https://raw.githubusercontent.com/NatSatie/ProjectLetsCode2/main/uml.png)

