package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader sysReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = sysReader.readLine();
        String fileName2 = sysReader.readLine();
        FileOutputStream outputStream = new FileOutputStream(fileName2);
        BufferedReader fileReader = new BufferedReader(new InputStreamReader( new FileInputStream(fileName1)));
        String line = "";
        List<String> numberList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        while ((line = fileReader.readLine()) != null){
            builder.append(line);
        }
            String[] arr = builder.toString().split(" ");
            for (int j = 0; j < arr.length; j++)
            {
                numberList.add(arr[j]);
            }


        for (int i = 0; i < numberList.size(); i++)
        {
            double d = Double.valueOf(numberList.get(i));
            outputStream.write(round(d).getBytes()) ;
            outputStream.write(32);
        }
        sysReader.close();
        fileReader.close();
        outputStream.close();
    }

    private static String round(double d)
    {
        if(d > 0)
        {
            BigDecimal big = new BigDecimal(d);
            return big.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
        }
        else {
            BigDecimal big = new BigDecimal(d);
            return big.setScale(0, BigDecimal.ROUND_HALF_DOWN).toString();
        }
    }
}
