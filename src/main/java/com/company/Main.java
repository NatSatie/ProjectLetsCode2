package com.company;

import com.company.classes.GenderEnum;
import com.company.databases.DbManager;

public class Main {
    public static void main(String[] args) {
        DbManager db = new DbManager();
        db.getYoungestPremiere(GenderEnum.MALE);
        // db.getYoungest(GenderEnum.FEMALE);
        db.getMostPremiere(GenderEnum.FEMALE);
        db.getMostPremiereByAgeGap(GenderEnum.FEMALE, 20,30);
        db.getMoreThanOneOscar();
        db.getActorByName("Michael Douglas");
        //db.getActorByName("Adrien Brody");
        // db.getActorByName("Senhor batata");
        // db.getMostPremiere(GenderEnum.OTHER);
    }
}
