package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        String mode = args[0];
        if ("-u".equals(mode))
        {
            ArrayList<String> list = new ArrayList<>();
            char[] productName = args[2].toCharArray();
            char[] price = args[3].toCharArray();
            char[] quantity = args[4].toCharArray();
            char[] idChars = args[1].toCharArray();;
            char[] iIdChars = Arrays.copyOf(idChars, 8);

            char[] iProductName = Arrays.copyOf(productName, 30);
            char[] iPrice = Arrays.copyOf(price, 8);
            char[] iQuantity = Arrays.copyOf(quantity, 4);

            StringBuilder builder = new StringBuilder();
            builder.append(createArray(idChars, iIdChars));
            builder.append(createArray(productName, iProductName));
            builder.append(createArray(price, iPrice));
            builder.append(createArray(quantity, iQuantity));
            File file = new File(fileName);

            BufferedReader bReader = new BufferedReader(new FileReader(file));
/*            File tmpFile = new File(file.getParent() + "\\tmp.txt");
            if(tmpFile.delete()){
                tmpFile.createNewFile();
            }*/

           // BufferedWriter bWriter = new BufferedWriter(new FileWriter(tmpFile));
            String line = "";

            String id = args[1];
            while (null != (line = bReader.readLine()))
            {
                if(!"".equals(line))
                {
                    String  idLine = line.substring(0, 8).trim();
                    if (idLine.equals(id) )
                    {
                        list.add(builder.toString());
                        list.add(System.getProperty("line.separator"));
                        continue;
                    }

                }
                list.add(line);
                list.add(System.getProperty("line.separator"));
            }
            bReader.close();
            FileWriter newFileWriter = new FileWriter(fileName,false);
            for (int i = 0; i < list.size(); i++)
            {
                newFileWriter.write(list.get(i));
            }
            newFileWriter.close();
        }

        else if("-d".equals(mode)){
            ArrayList<String> list = new ArrayList<>();
            char[] idChars = args[1].toCharArray();;
            char[] iIdChars = Arrays.copyOf(idChars, 8);
            String id = createArray(idChars, iIdChars).toString();
            File file = new File(fileName);
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            String line = "";
            while (null != (line = bReader.readLine()))
            {
                if(!"".equals(line))
                {
                    String  idLine = line.substring(0, 8).trim();
                    if (idLine.equals(id) )
                    {
                        continue;
                    }

                }
                list.add(line);
                list.add(System.getProperty("line.separator"));
            }
            bReader.close();
            FileWriter newFileWriter = new FileWriter(fileName,false);
            for (int i = 0; i < list.size(); i++)
            {
                newFileWriter.write(list.get(i));
            }
            newFileWriter.close();
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

