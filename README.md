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

Cada um desses atributos herdam de `Database`, que tem uma lista de objetos, por exemplo, `DbOscar` tem uma lista de objetos `Oscar` e vice-versa.

### Database

As classes Databases, tem os seguintes métodos principais

- getDb()
- getElement(T o)
- register(T o)
- add(T o) e search(T o) como métodos privados

## Resultado final

```
"C:\Program Files\Java\jdk-11.0.13\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2021.3.2\lib\idea_rt.jar=63470:C:\Program Files\JetBrains\IntelliJ IDEA 2021.3.2\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\Natália\IdeaProjects\ProjectLetsCode2\target\classes;C:\Users\Natália\.m2\repository\org\projectlombok\lombok\1.18.22\lombok-1.18.22.jar com.company.Main
-------------- Buscar atriz premiade ao Oscar mais jovem --------------
Ator: Eddie Redmayne
Ano de nascimento: 1982
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (1) 
The Theory of Everything	 (2015) aos 33 anos

-------------- Atriz mais premiade --------------
Ator: Daniel Day-Lewis
Ano de nascimento: 1958
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (3) 
My Left Foot (1990) aos 32 anos
There Will Be Blood (2008) aos 50 anos
Lincoln (2013) aos 55 anos

-------------- Atrizes mais premiades entre os 20 e 30 anos de idade --------------
OSCAR de 2003 premiou o filme The Pianist e o ator/atriz Adrien Brody aos seus 29 anos 

Ator: Adrien Brody
Ano de nascimento: 1974
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (1) 
The Pianist (2003) aos 29 anos

OSCAR de 1955 premiou o filme On the Waterfront e o ator/atriz Marlon Brando aos seus 30 anos 

Ator: Marlon Brando
Ano de nascimento: 1925
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
On the Waterfront (1955) aos 30 anos
The Godfather (1973) aos 48 anos

OSCAR de 1978 premiou o filme The Goodbye Girl e o ator/atriz Richard Dreyfuss aos seus 30 anos 

Ator: Richard Dreyfuss
Ano de nascimento: 1948
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (1) 
The Goodbye Girl (1978) aos 30 anos

-------------- Atores e atrizes que receberam mais de um Oscar --------------
Ator: Fredric March
Ano de nascimento: 1898
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Dr. Jekyll and Mr. Hyde (1933) aos 35 anos
The Best Years of Our Lives (1947) aos 49 anos

Ator: Spencer Tracy
Ano de nascimento: 1901
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Captains Courageous (1938) aos 37 anos
Boys Town (1939) aos 38 anos

Ator: Gary Cooper
Ano de nascimento: 1902
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Sergeant York (1942) aos 40 anos
High Noon (1953) aos 51 anos

Ator: Bette Davis
Ano de nascimento: 1908
Genero:  Masculino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Dangerous (1935) aos 27 anos
Jezebel (1938) aos 30 anos

Ator: Luise Rainer
Ano de nascimento: 1909
Genero:  Masculino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
The Great Ziegfeld (1936) aos 27 anos
The Good Earth (1937) aos 28 anos

Ator: Vivien Leigh
Ano de nascimento: 1913
Genero:  Masculino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Gone with the Wind (1939) aos 26 anos
A Streetcar Named Desire (1951) aos 38 anos

Ator: Ingrid Bergman
Ano de nascimento: 1915
Genero:  Masculino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Gaslight (1944) aos 29 anos
Anastasia (1956) aos 41 anos

Ator: Olivia de Havilland
Ano de nascimento: 1916
Genero:  Masculino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
To Each His Own (1946) aos 30 anos
The Heiress (1949) aos 33 anos

Ator: Marlon Brando
Ano de nascimento: 1925
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
On the Waterfront (1955) aos 30 anos
The Godfather (1973) aos 48 anos

Ator: Elizabeth Taylor
Ano de nascimento: 1931
Genero:  Masculino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
BUtterfield 8 (1960) aos 29 anos
Who's Afraid of Virginia Woolf? (1966) aos 35 anos

Ator: Glenda Jackson
Ano de nascimento: 1937
Genero:  Masculino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Women in Love (1971) aos 34 anos
A Touch of Class (1974) aos 37 anos

Ator: Jane Fonda
Ano de nascimento: 1938
Genero:  Masculino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Klute (1972) aos 34 anos
Coming Home (1979) aos 41 anos

Ator: Dustin Hoffman
Ano de nascimento: 1938
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Kramer vs. Kramer (1980) aos 42 anos
Rain Man (1989) aos 51 anos

Ator: Jack Nicholson
Ano de nascimento: 1938
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
One Flew Over the Cuckoo's Nest (1976) aos 38 anos
As Good as It Gets (1998) aos 60 anos

Ator: Sally Field
Ano de nascimento: 1947
Genero:  Masculino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Norma Rae (1980) aos 33 anos
Places in the Heart (1985) aos 38 anos

Ator: Meryl Streep
Ano de nascimento: 1950
Genero:  Masculino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Sophie's Choice (1983) aos 33 anos
The Iron Lady (2012) aos 62 anos

Ator: Tom Hanks
Ano de nascimento: 1957
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Philadelphia (1994) aos 37 anos
Forrest Gump (1995) aos 38 anos

Ator: Sean Penn
Ano de nascimento: 1961
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Mystic River (2004) aos 43 anos
Milk (2009) aos 48 anos

Ator: Jodie Foster
Ano de nascimento: 1963
Genero:  Masculino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
The Accused (1989) aos 26 anos
The Silence of the Lambs (1992) aos 29 anos

Ator: Hilary Swank
Ano de nascimento: 1975
Genero:  Masculino
HISTORICO DE FILMES PREMIADOS AO OSCAR (2) 
Boys Don't Cry (2000) aos 25 anos
Million Dollar Baby (2005) aos 30 anos

Ator: Daniel Day-Lewis
Ano de nascimento: 1958
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (3) 
My Left Foot (1990) aos 32 anos
There Will Be Blood (2008) aos 50 anos
Lincoln (2013) aos 55 anos

Ator: Katharine Hepburn
Ano de nascimento: 1907
Genero:  Masculino
HISTORICO DE FILMES PREMIADOS AO OSCAR (4) 
Morning Glory (1933) aos 26 anos
Guess Who's Coming to Dinner (1967) aos 60 anos
The Lion in Winter (1968) aos 61 anos
On Golden Pond (1982) aos 75 anos

-------------- Resumo de Michael Douglas -------------- 
Ator: Michael Douglas
Ano de nascimento: 1945
Genero:  Feminino
HISTORICO DE FILMES PREMIADOS AO OSCAR (1) 
Wall Street (1988) aos 43 anos


Process finished with exit code 0

```