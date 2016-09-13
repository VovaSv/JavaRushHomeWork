package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.valueOf(reader.readLine());
        int b = Integer.valueOf(reader.readLine());
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        List<Integer> listC = new ArrayList<>();


        if(a == 1 || b == 1)
        {
            System.out.println(1);
        return;
        }


        int numA = a;
        int numB = b;
        for (int i = 2; i <= a; i++)
        {
            while((((numA % i)) == 0 && numA != 1)){
                numA = numA/i;
                listA.add(i);
            }

        }
        for (int i = 2; i <= b; i++)
        {
            while((((numB % i)) == 0 && numB != 1)){
                numB = numB/i;
                listB.add(i);
            }

        }

        for (Integer int1 : listA){
            if(listB.contains(int1)){
                listC.add(int1);
            }
            listB.remove(int1);
        }
        int sum;
        if(listC.size() == 0)
        {
            sum = 1;
        }
        else
        {
            sum = listC.get(0);
            for (int i = 1; i < listC.size(); i++)
            {
                sum = sum * listC.get(i);
            }

        }
        System.out.println(sum);
    }

}
