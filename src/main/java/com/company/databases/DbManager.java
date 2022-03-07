package com.company.databases;

import com.company.classes.*;
import com.company.classes.comparators.AgeWhenGotOscarComparator;
import com.company.classes.comparators.MoreThanOneOscarComparator;
import com.company.classes.comparators.MostOscarsComparator;
import com.company.databases.files.FileReader;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class DbManager {
    private DbActor dbActor = new DbActor();
    private DbOscar dbOscar = new DbOscar();
    private DbMovie dbMovie = new DbMovie();

    public void init(){
        List<String[]> actressInfo = FileReader.readInput("src/main/java/com/company/databases/files/actresses.csv");
        List<String[]> actorsInfo = FileReader.readInput("src/main/java/com/company/databases/files/actors.csv");

        actressInfo.stream().forEach(
                e -> processInfo(
                        Integer.parseInt(e[1]),
                        Integer.parseInt(e[2]),
                        e[3],
                        e[4],
                        GenderEnum.FEMALE
                )
        );

        actorsInfo.stream().forEach(
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
        System.out.println("-------------- Buscar ator premiado ao Oscar mais jovem --------------");
        Comparator<Actor> compareAge = (Actor a1, Actor a2) -> a1.compareTo(a2);
        Collections.sort(this.dbActor.getDb(), compareAge);

        Actor actor = (Actor) this.dbActor.getDb().stream()
            .filter( a -> ((Actor) a).getGender().equals(gender))
            .findFirst()
            .get();

        System.out.println(actor.toString());
    }

    public void getMostPremiere(GenderEnum gender){
        System.out.println("-------------- Atriz mais premiada --------------");
        MostOscarsComparator comparator = new MostOscarsComparator();

        Collections.sort(this.dbActor.getDb(), comparator);

        Actor actor = (Actor) this.dbActor.getDb().stream()
            .filter( a -> ((Actor) a).getGender().equals(gender))
            .reduce((a,b) -> b)
            .get();

        System.out.println(actor.toString());
    }

    public void getMostPremiereByAgeGap(GenderEnum gender, int minAge, int maxAge){
        System.out.println("-------------- Atrizes mais premiadas entre os 20 e 30 anos de idade --------------");
        AgeWhenGotOscarComparator comparator = new AgeWhenGotOscarComparator();

        Collections.sort(this.dbOscar.getDb(), comparator);

        this.dbOscar.getDb().stream()
            .filter( o -> ((Oscar)o).getActor().getGender().equals(gender))
            .filter( o -> (((Oscar)o).getActorAge() >= minAge) && (((Oscar)o).getActorAge() <= maxAge))
            .forEach( o -> {
                    System.out.println((((Oscar)o).toString()));
                    System.out.println((((Oscar)o).getActor().toString()));
                }
        );
    }

    public void getMoreThanOneOscar(){
        System.out.println("-------------- Atores e atrizes que receberam mais de um Oscar --------------");
        MoreThanOneOscarComparator comparator = new MoreThanOneOscarComparator();

        Collections.sort(this.dbActor.getDb(), comparator);

        this.dbActor.getDb().stream()
                .filter( a -> ((Actor)a).getNumberOfOscarsAwarded() > 1)
                .forEach(
                        e -> System.out.println(((Actor)e).toString())
                );
    }

    public void getActorByName(String name){
        System.out.println("-------------- " + name + " -------------- ");

        System.out.println(this.dbActor.getElement(name).toString());
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