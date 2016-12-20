package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        String mode = args[0];

        if ("-c".equals(mode))
        {
            BufferedReader bReader = new BufferedReader(new FileReader(fileName));
            String line = "";
            long max = 0;
            while (null != (line = bReader.readLine()))
            {
                if(!"".equals(line))
                {
                    line = line.substring(0, 8).trim();
                    if (max < Long.valueOf(line))
                    {
                        max = Long.valueOf(line);
                    }
                }
            }
            max++;
            bReader.close();
            char[] productName = args[1].toCharArray();
            char[] price = args[2].toCharArray();
            char[] quantity = args[3].toCharArray();
            char[] idChars = String.valueOf(max).toCharArray();
            char[] iIdChars = Arrays.copyOf(idChars, 8);

            char[] iProductName = Arrays.copyOf(productName, 30);
            char[] iPrice = Arrays.copyOf(price, 8);
            char[] iQuantity = Arrays.copyOf(quantity, 4);

            StringBuilder builder = new StringBuilder();
            builder.append(createArray(idChars, iIdChars));
            builder.append(createArray(productName, iProductName));
            builder.append(createArray(price, iPrice));
            builder.append(createArray(quantity, iQuantity));
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(fileName,true));
            bWriter.write(builder.toString());
            bWriter.newLine();
            bWriter.close();
        }
    }

    public static  char[] createArray (char[] arr, char[] iArr){
        if(arr.length >= iArr.length){
            return iArr;
        }
        else{
            for (int i = arr.length; i < iArr.length; i++)
            {
                iArr[i] = ' ';
            }
            return iArr;
        }
    }
}
