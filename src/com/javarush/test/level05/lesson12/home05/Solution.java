package com.javarush.test.level05.lesson12.home05;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int sum = 0;
        int num;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
           String in =  reader.readLine();
            if(in.equals("сумма"))
                break;
            num = Integer.parseInt(in);
            sum+=num;
        }
        System.out.println(sum);
    }
}