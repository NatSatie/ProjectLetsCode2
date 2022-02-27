package com.company.databases;

import com.company.classes.Actor;
import com.company.classes.GenderEnum;
import com.company.classes.Movie;
import com.company.classes.Oscar;
import com.company.files.FileReader;

import java.time.LocalDate;
import java.util.List;

public final class DbManager {
    protected DbActor dbActor = new DbActor();
    protected DbOscar dbOscar = new DbOscar();
    protected DbMovie dbMovie = new DbMovie();

    public void init(){
        List<String[]> actressInfo = FileReader.readInput("src/com/company/files/actresses.csv");
        List<String[]> actorsInfo = FileReader.readInput("src/com/company/files/actors.csv");

        actressInfo.stream().forEach(
                e -> processInfo((String[]) e, GenderEnum.FEMALE)
        );
        // FileReader.readInput("src/com/company/files/actors.csv", GenderEnum.MALE);
    }

    private void processInfo(String[] array, GenderEnum gender){
        String[] info = (String[]) array;
        int yearRelease = Integer.parseInt(info[1]);
        int actorsAge = Integer.parseInt(info[2]);
        String actorName = info[3];
        String movieName = info[4];

        this.dbMovie.register(new Movie(movieName, yearRelease));
        this.dbActor.register(new Actor(actorName, yearRelease - actorsAge, gender),
                (Movie) this.dbMovie.getElement(movieName));
        System.out.println(this.dbActor.getDb().size());
        System.out.println(this.dbMovie.getDb().size());
    }
}
