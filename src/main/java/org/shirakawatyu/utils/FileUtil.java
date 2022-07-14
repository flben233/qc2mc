package org.shirakawatyu.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class FileUtil {
    public static HashSet<String> readFileLines(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        HashSet<String> hs = new HashSet<>();
        while(scanner.hasNext()){
            hs.add(scanner.nextLine().trim());
        }
        return hs;
    }

    public static String readFile(File file) throws IOException{
        Scanner scanner = new Scanner(file);
        return scanner.next().trim();
    }
}
