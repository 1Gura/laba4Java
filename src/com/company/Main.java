package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    //(https?:\/\/)([^\s]{1,}|)*
    public static void main(String[] args) {
        Pattern patternLink = Pattern.compile("<a.*?href ?= ?['\\\"]([^'\\\"]*)['\\\"].*?>(.*?)<\\/a>", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Pattern pattern = Pattern.compile("(https?:\\/\\/)?([\\w-]{1,}\\.[\\w-]{1,})[^\\s]*$", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher;
        Matcher matcher1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к HTML файлу: ");
        String filePath = scanner.nextLine();
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(filePath));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.html"));
        ) {
            String input = "";
            StringBuilder result = new StringBuilder();
            while ((input = bufferedReader.readLine()) != null) {
                matcher = patternLink.matcher(input);
                matcher1 = pattern.matcher(input);

                if (matcher.find()) {
                    result.append(matcher.group(1) + "\t" + matcher.group(2) + "\n");
                } else if (matcher1.find()) {
                    result.append(matcher1.group(1) + matcher1.group(2) + "\n");
                }
            }
            System.out.println(result);
            bufferedWriter.write(result.toString());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
