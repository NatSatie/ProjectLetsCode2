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
import java.util.stream.Stream;

import static java.lang.System.out;

public final class DbManager {
    private DbActor dbActor = new DbActor();
    private DbOscar dbOscar = new DbOscar();
    private DbMovie dbMovie = new DbMovie();

    public DbManager(){
        List<String[]> actressInfo = FileReader.readInput("src/main/java/com/company/databases/files/actresses.csv");
        List<String[]> actorsInfo = FileReader.readInput("src/main/java/com/company/databases/files/actors.csv");

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

    public void getYoungest(GenderEnum gender){
        out.println("-------------- Buscar " + (gender.equals(GenderEnum.FEMALE) ? "atriz" : "ator") +" mais jovem --------------");
        Comparator<Actor> compareAge = (Actor a1, Actor a2) -> a1.compareTo(a2);
        Collections.sort(this.dbActor.getDb(), compareAge.reversed());

        Stream<Actor> actorsStream = this.dbActor.getDb().stream();

        Optional<Actor> res = actorsStream
            .filter( a -> a.getGender().equals(gender))
            .findFirst();

        res.ifPresent(out::println);
    }

    public void getYoungestPremiere(GenderEnum gender){
        out.println("-------------- Buscar " + (gender.equals(GenderEnum.FEMALE) ? "atriz" : "ator") +" premiade mais jovem --------------");
        this.dbOscar.getDb().sort(new AgeComparator());

        Optional res = this.dbOscar.getDb()
                .stream()
                .filter(o -> ((Oscar) o).getActor().getGender().equals(gender))
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
        Stream<Oscar> oscarStream = this.dbOscar.getDb().stream();

        this.dbOscar.getDb().sort(new AgeWhenGotOscarComparator());

        List oscarList = List.of(oscarStream
                .filter(o -> o.getActor().getGender().equals(gender) && (o.getActorAge() >= minAge) && (o.getActorAge() <= maxAge))
                .toArray());

        oscarList.forEach(out::println);
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
        this.dbActor.register( a, (Movie) this.dbMovie.getElement(movieName));
        this.dbOscar.register( new Oscar(
                (Actor) this.dbActor.getElement(actorName),
                (Movie) this.dbMovie.getElement(movieName),
                actorsAge
        ));
    }
}
