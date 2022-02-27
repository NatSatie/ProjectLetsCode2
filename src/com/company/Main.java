package com.company;

import com.company.databases.DbManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        DbManager db = new DbManager();
        db.init();
    }
}
