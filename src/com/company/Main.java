package com.company;

import com.company.databases.DbManager;

public class Main {
    public static void main(String[] args) {
        DbManager db = new DbManager();
        db.init();
    }
}
