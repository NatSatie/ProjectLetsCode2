package com.company.databases;

import com.company.classes.*;
import com.company.classes.comparators.AgeComparator;
import com.company.classes.comparators.AgeWhenGotOscarComparator;
import com.company.classes.comparators.MoreThanOneOscarComparator;
import com.company.classes.comparators.MostOscarsComparator;
import com.company.databases.files.FileReader;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

public final class DbManager {
    private final DbActor dbActor = new DbActor();
    private final DbOscar dbOscar = new DbOscar();
    private final DbMovie dbMovie = new DbMovie();

    public DbManager(){
        List<String[]> actressInfo = FileReader.readInput("src/main/java/com/company/databases/files/atrizes.csv");
        List<String[]> actorsInfo = FileReader.readInput("src/main/java/com/company/databases/files/atores.csv");

        actressInfo.forEach(
                e -> processInfo(
                        Integer.parseInt(e[1]),
                        Integer.parseInt(e[2]),
                        e[3],
                        e[4],
                        GenderEnum.FEMALE
                )
        );

        actorsInfo.forEach(
                e -> processInfo(
                        Integer.parseInt(e[1]),
                        Integer.parseInt(e[2]),
                        e[3],
                        e[4],
                        GenderEnum.MALE
                )
        );
    }

    public void getYoungestPremiere(GenderEnum gender){
        out.println("-------------- Buscar " + (gender.equals(GenderEnum.FEMALE) ? "atriz" : "ator") +" premiade mais jovem --------------");
        this.dbOscar.getDb().sort(new AgeComparator());

        Optional<Oscar> res = this.dbOscar.getDb()
                .stream()
                .filter(o -> o.getActor().getGender().equals(gender))
                .findFirst();

        res.ifPresent(out::println);
    }

    public void getMostPremiere(GenderEnum gender){
        out.println("-------------- "+ (gender.equals(GenderEnum.FEMALE) ? "Atriz" : "Ator") +" mais premiade --------------");
        this.dbActor.getDb().sort(new MostOscarsComparator().reversed());
        Stream<Actor> actorsStream = this.dbActor.getDb().stream();

        Optional<Actor> res = actorsStream
                .filter(a -> a.getGender().equals(gender))
                .findFirst();

        res.ifPresent(out::println);
    }

    public void getMostPremiereByAgeGap(GenderEnum gender, int minAge, int maxAge){
        out.println("-------------- "+ (gender.equals(GenderEnum.FEMALE) ? "Atrizes" : "Atores") +
                " mais premiades entre os "+ minAge+" e "+ maxAge+" anos de idade --------------");
        Stream<Actor> actorStream = this.dbActor.getDb().stream();

        this.dbActor.getDb().sort(new AgeWhenGotOscarComparator().reversed());

        List<Actor> res = actorStream
                .filter(o -> o.getGender().equals(gender) && o.getNumberOfOscarsAwardedByAgeGap(20,30) > 1)
                .collect(Collectors.toList());

        res.forEach(e -> out.println(e.getName()
                + " recebeu " + e.getNumberOfOscarsAwardedByAgeGap(20,30)
                + " Oscars entre os 20 a 30 anos. Veja seu resumo: \r\n" + e.toString()));
    }

    private static boolean oscarsAwarded(Object a) {
        return ((Actor) a).getNumberOfOscarsAwarded() > 1;
    }

    public void getMoreThanOneOscar(){
        out.println("-------------- Atores e atrizes que receberam mais de um Oscar --------------");
        Stream<Actor> actorsStream = this.dbActor.getDb().stream();
        this.dbActor.getDb().sort(new MoreThanOneOscarComparator());

        actorsStream
                .filter(DbManager::oscarsAwarded)
                .forEach(out::println);
    }

    public void getActorByName(String name){
        out.println("-------------- Resumo de " + name + " -------------- ");

        out.println(this.dbActor.getElement(name).toString());
    }

    private void processInfo(int yearRelease, int actorsAge, String actorName, String movieName, GenderEnum gender){
        Movie m = new Movie(movieName, yearRelease);
        Actor a = new Actor(actorName, yearRelease - actorsAge, gender);
        this.dbMovie.register(m);
        this.dbActor.register( a, this.dbMovie.getElement(movieName));
        this.dbOscar.register( new Oscar(
                this.dbActor.getElement(actorName),
                this.dbMovie.getElement(movieName),
                actorsAge
        ));
    }
}
