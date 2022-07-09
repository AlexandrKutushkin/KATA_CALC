package com.kata.kutushkin;

import java.util.ArrayList;
import java.util.Scanner;

public class Calc {
    static int a;
    static int b;
    static char operation;
    static boolean arabian;
    static int answer;

    public static void main(String[] args) throws userInputException, notATaskException, digitCombinationException, romeBelowZeroException {
        ArrayList<String> rome = new ArrayList<>();

        rome.add("I");
        rome.add("II");
        rome.add("III");
        rome.add("IV");
        rome.add("V");
        rome.add("VI");
        rome.add("VII");
        rome.add("VIII");
        rome.add("IX");
        rome.add("X");


        System.out.println("Введите пример(разделяя пробелами числа и оператор)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] array = input.split(" ");

        for (String strForEach : array) {
            if (!strForEach.matches("[IXV0-9/+/*//-]+"))
                throw new userInputException("throws Exception // недопустимые символы");
        }                                           //проверка на введеные символы

                                                                                        //проверка на длину и запись
        if ((array.length != 3) || (!array[1].matches("[+*/-]")) || (!(array[0].matches("[IXV0-9]+") & (array[2].matches("[IXV0-9]+"))))) {
            throw new notATaskException("throws Exception // строка не является математической операцией");
        }


        if ((array[0].matches("[0-9]+") & !array[2].matches("[0-9]+")) |
                (!array[0].matches("[0-9]+") & array[2].matches("[0-9]+"))) {
            throw new digitCombinationException("Вы пытаетесь выполнить действие с числами разных систем счисления");
        }  //проверка комбинации разных СС

        //решаем

        if (array[0].matches("[0-9]+"))
            arabian = true;
            operation = array[1].charAt(0);

        if (arabian) {
            a = Integer.parseInt(array[0]);
            b = Integer.parseInt(array[2]);
            answer = getAnswer(a, b);
            System.out.println(answer);
        }

        else {
            a = rome.indexOf(array[0]) + 1;
            b = rome.indexOf(array[2]) + 1;
            answer = getAnswer(a, b);
            String romeAnswer = null;
            if (answer < 1)
                throw new romeBelowZeroException("Ответ меньше 1 для римского счета недопустим");
//            System.out.println(answer);               //вывод арабскими цифрами

            if (answer == 100)
                System.out.println("C");
            else {

                switch (answer / 10) {
                    case (0) -> romeAnswer = rome.get(--answer);
                    case (1) -> { if(answer == 10) romeAnswer = "X";else romeAnswer = "X" + rome.get((--answer) % 10);}
                    case (2) -> { if(answer == 20) romeAnswer = "XX";else romeAnswer = "XX" + rome.get((--answer) % 10);}
                    case (3) -> { if(answer == 30) romeAnswer = "XXX";else romeAnswer = "XXX" + rome.get((--answer) % 10);}
                    case (4) -> { if(answer == 40) romeAnswer = "XL";else romeAnswer = "XL" + rome.get((--answer) % 10);}
                    case (5) -> { if(answer == 50) romeAnswer = "L";else romeAnswer = "L" + rome.get((--answer) % 10);}
                    case (6) -> { if(answer == 60) romeAnswer = "LX";else romeAnswer = "LX" + rome.get((--answer) % 10);}
                    case (7) -> { if(answer == 70) romeAnswer = "LXX";else romeAnswer = "LXX" + rome.get((--answer) % 10);}
                    case (8) -> { if(answer == 80) romeAnswer = "LXXX";else romeAnswer = "LXXX" + rome.get((--answer) % 10);}
                    case (9) -> { if(answer == 90) romeAnswer = "XC";else romeAnswer = "XC" + rome.get((--answer) % 10);}


                }
                System.out.println(romeAnswer);
            }
        }

    }

    static int getAnswer(int first, int second) {
        switch (operation) {
            case '+' -> answer = a + b;
            case '-' -> answer = a - b;
            case '*' -> answer = a * b;
            case '/' -> answer = a / b;
            default -> System.out.println("Неизвестная ошибка");
        }
    return answer;}

}
class notATaskException extends Exception{
    public notATaskException (String info){
        super(info);
    }
}
class userInputException extends Exception{
    public userInputException (String info){
        super(info);
    }
}
class romeBelowZeroException extends Exception{
    public romeBelowZeroException (String info){
        super(info);
    }
}
class digitCombinationException extends Exception{
    public digitCombinationException (String info){
        super(info);
    }
}