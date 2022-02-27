package com.company.files;

import com.company.classes.GenderEnum;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {
    public static List readInput(String filename) {
        List data = new ArrayList();
        Pattern linePattern = Pattern.compile("[0-9]+;\\s[0-9]{4};\\s[0-9]+;\\s[\\p{IsLatin}a-zA-Z\\-\\s\\.]+;\\s[\\p{IsLatin}a-zA-Z\\s\\.]+");
        try (Scanner scan = new Scanner(new File(filename))){
            while (scan.hasNext()){
                String in = scan.nextLine();
                Matcher lineMatcher = linePattern.matcher(in);
                if (lineMatcher.find()){
                    data.add(in.split(";\\s"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return data;
        }
    }
}
