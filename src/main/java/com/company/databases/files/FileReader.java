package com.company.databases.files;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {
    public static List readInput(String filename){
        List data = new ArrayList();
        Pattern linePattern = Pattern.compile("[0-9]+;\\s[0-9]{4};\\s[0-9]+;\\s[\\p{IsLatin}a-zA-Z\\-\\s\\.]+;\\s[\\p{IsLatin}a-zA-Z\\s\\.]+");
        BufferedReader reader = null;
        try {
            String in;
            reader = new BufferedReader( new java.io.FileReader(filename));
            while ((in = reader.readLine()) != null){
                Matcher lineMatcher = linePattern.matcher(in);
                if (lineMatcher.find()){
                    data.add(in.split(";\\s"));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null){
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                return data;
            }
        }
    }
}
