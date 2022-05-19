package com.company;

import java.util.Locale;
import java.util.regex.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        StringBuilder readText = new StringBuilder();
        String line;
        /*
        Считываем стихотворение из текстового файла.
        */
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("text.txt")));
            while ((line = br.readLine()) != null) {
                readText.append(line).append(" ");
            }
        }
        catch (IOException ex){
        System.out.println(ex.getMessage());
        }

        /*
        В полученном тексте верхние регистры преобразовываем в нижние.
        Создаём символьный массив этого текста.
        */
        String lowerText = readText.toString().toLowerCase(Locale.ROOT);
        char[] characterText = lowerText.toCharArray();

        StringBuilder result = new StringBuilder(); // Переменная, в которую буду помещаться значения, соответствующие номеру строки и номеру символа.

        /*
        Считываем номера строк и номера символов и текстового файла.
        */

        StringBuilder lineAndCharacterNumbers = new StringBuilder();
        try {
            BufferedReader br1 = new BufferedReader(new FileReader("numbers.txt"));
            while ((line = br1.readLine()) != null) {
                lineAndCharacterNumbers.append(line);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
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
            
        /*
        Находим номера строк и номера символов.
        Извлекаем второе число, вычитаем из него единицу и заносим значение в переменную index.
        По индексу ищем соответствующую букву из текста.
        К переменной result добавляем найденную букву.
        */
        while(matcherForFindNumbers.find()){
            String t = matcherForFindNumbers.group().replaceAll(" ", "");
            Matcher matcherForSymbolNumber = patternForSymbol.matcher(t);
            while(matcherForSymbolNumber.find()){
                String numberOfSymbol = matcherForSymbolNumber.group().replaceAll(";", "");
                int index = Integer.parseInt(numberOfSymbol) - 1;
                result.append(characterText[index]);
            }
        }

        System.out.println(result); // Выводим полученное слово.
    }
}
