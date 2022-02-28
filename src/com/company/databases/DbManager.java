package com.company.databases;

import com.company.classes.*;
import com.company.classes.comparators.AgeWhenGotOscarComparator;
import com.company.classes.comparators.MoreThanOneOscarComparator;
import com.company.classes.comparators.MostOscarsComparator;
import com.company.files.FileReader;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class DbManager {
    protected DbActor dbActor = new DbActor();
    protected DbOscar dbOscar = new DbOscar();
    protected DbMovie dbMovie = new DbMovie();

    public void init(){
        List<String[]> actressInfo = FileReader.readInput("src/com/company/files/actresses.csv");
        List<String[]> actorsInfo = FileReader.readInput("src/com/company/files/actors.csv");
        List<String[]> sampleTest = FileReader.readInput("src/com/company/files/actors-reduced.csv");

        actressInfo.stream().forEach(
                e -> processInfo((String[]) e, GenderEnum.FEMALE)
        );

        actorsInfo.stream().forEach(
                e -> processInfo((String[]) e, GenderEnum.MALE)
        );
    }

    public void getYoungestActor(){
        System.out.println("-------------- Buscar ator premiado ao Oscar mais jovem --------------");
        Comparator<Actor> compareAge = (Actor a1, Actor a2) -> a1.compareTo(a2);
        Collections.sort(this.dbActor.getDb(), compareAge);

        Actor actor = (Actor) this.dbActor.getDb().stream()
            .filter( a -> ((Actor) a).getGender() == GenderEnum.MALE)
            .findFirst()
            .get();

        System.out.println(actor.toString());
    }

    public void getActressMostPremiere(){
        System.out.println("-------------- Atriz mais premiada --------------");
        MostOscarsComparator comparator = new MostOscarsComparator();

        Collections.sort(this.dbActor.getDb(), comparator);

        Actor actor = (Actor) this.dbActor.getDb().stream()
            .filter( a -> ((Actor) a).getGender() == GenderEnum.FEMALE)
            .reduce((a,b) -> b)
            .get();

        System.out.println(actor.toString());
    }

    public void getMostPremiereByAgeGap(int minAge, int maxAge){
        System.out.println("-------------- Atrizes mais premiadas entre os 20 e 30 anos de idade --------------");
        AgeWhenGotOscarComparator comparator = new AgeWhenGotOscarComparator();

        Collections.sort(this.dbOscar.getDb(), comparator);


        this.dbOscar.getDb().stream()
            .filter( o -> ((Oscar)o).getActor().getGender() == GenderEnum.FEMALE)
            .filter( o -> (((Oscar)o).getActorAge() >= minAge) && (((Oscar)o).getActorAge() <= maxAge))
            .forEach( o -> {
                    System.out.println((((Oscar)o).toString()));
                    System.out.println((((Oscar)o).getActor().toString()));
                }
        );
    }

    public void getMoreThanOneOscar(){
        System.out.println("-------------- Atores e atrizes que receberam mais de um Oscar -------------- /r/n");
        MoreThanOneOscarComparator comparator = new MoreThanOneOscarComparator();

        Collections.sort(this.dbActor.getDb(), comparator);

        this.dbActor.getDb().stream()
                .filter( a -> ((Actor)a).getNumberOfOscarsAwarded() > 1)
                .forEach(
                        e -> System.out.println(((Actor)e).toString())
                );
    }

    private void processInfo(String[] array, GenderEnum gender){
        String[] info = (String[]) array;
        int yearRelease = Integer.parseInt(info[1]);
        int actorsAge = Integer.parseInt(info[2]);
        String actorName = info[3];
        String movieName = info[4];

        Movie m = new Movie(movieName, yearRelease);
        Actor a = new Actor(actorName, yearRelease - actorsAge, gender);
        this.dbMovie.register(m);
        this.dbActor.register( a, (Movie) this.dbMovie.getElement(m.getName()));
        this.dbOscar.register( new Oscar(
                (Actor) this.dbActor.getElement(a.getName()),
                (Movie) this.dbMovie.getElement(m.getName()),
                actorsAge
        ));
    }
}
