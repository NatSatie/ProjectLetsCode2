package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void readInput(String filename){
        Pattern linePattern = Pattern.compile("[0-9]+;\\s[0-9]{4};\\s[0-9]+;\\s[\\p{IsLatin}a-zA-Z\\-\\s\\.]+;\\s[\\p{IsLatin}a-zA-Z\\s\\.]+");
        try (Scanner scan = new Scanner(new File(filename))){
            while (scan.hasNext()){
                String in = scan.nextLine();
                Matcher lineMatcher = linePattern.matcher(in);
                if (lineMatcher.find()){
                    String[] info = in.split(";\\s");
                    for ( String t : info){
                        System.out.println(t);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readInput("src/com/company/actresses.csv");
        readInput("src/com/company/actors.csv");
    }
}
