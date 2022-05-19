package com.company;

import java.util.Locale;
import java.util.regex.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            /*
            Считываем стихотворение из текстового файла.
             */
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("text.txt")));
            String line;
            StringBuilder readText = new StringBuilder();
            while((line = br.readLine()) != null){
                readText.append(line).append(" ");
            }
            /*
            В полученном тексте верхние регистры преобразовываем в нижние и меняем переходы на следующую строчку на пробелы.
            Создаём символьный массив этого текста.
             */
            String lowerText = readText.toString().toLowerCase(Locale.ROOT);
            char[] characterText = lowerText.toCharArray();

            StringBuilder result = new StringBuilder(); // Переменная, в которую буду помещаться значения, соответствующие номеру строки и номеру символа.

            /*
            Считываем номера строк и номера символов и текстового файла.
             */
            BufferedReader br1 = new BufferedReader(new FileReader("numbers.txt"));
            StringBuilder lineAndCharacterNumbers = new StringBuilder();
            while ((line = br1.readLine()) != null) {
                lineAndCharacterNumbers.append(line);
            }

            /*
            Создаём 2 регулярны выражения.
            Первое регулярное выражения для извлечения цифр из скобочек.
            Второе регулярное выражения для извлечения значения после ";".
             */
            String regularForFindNumbers = "\\d+(\s*);(\s*)\\d+";
            String regularForNumberOfSymbol = ";\\d+";
            Pattern patternForNumbers = Pattern.compile(regularForFindNumbers);
            Pattern patternForSymbol = Pattern.compile(regularForNumberOfSymbol);
            Matcher matcherForFindNumbers = patternForNumbers.matcher(lineAndCharacterNumbers.toString());
            while(matcherForFindNumbers.find()){
                String t = matcherForFindNumbers.group().replaceAll(" ", "");
                Matcher matcherForSymbolNumber = patternForSymbol.matcher(t);
                while(matcherForSymbolNumber.find()){
                    String numberOfSymbol = matcherForSymbolNumber.group().replaceAll(";", "");
                    int index = Integer.parseInt(numberOfSymbol) - 1;
                    result.append(characterText[index]);
                }
            }
            System.out.println(result);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
