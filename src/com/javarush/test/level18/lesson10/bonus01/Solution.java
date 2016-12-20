package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution
{

    public static void main(String[] args) throws IOException
    {
        String mode = args[0];
        String fileInputName = args[1];
        String fileOutputName = args[2];
        File iFile = new File(fileInputName);
        File oFile = new File(fileOutputName);
        if(mode.equals("-d")){
            doDCrypto(iFile, oFile);

        }
        if(mode.equals("-e")){
            doECrypto(iFile, oFile);
        }
    }


    private static void doECrypto( File inputFile,
                                 File outputFile)
    {
        try
        {
            FileOutputStream outputFileStream = new FileOutputStream(outputFile);
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = new byte[(int) inputFile.length()];
            for (int i = 0; i < inputBytes.length; i++)
            {
                byte b = inputBytes[i];
                b+=3;
                outputBytes[i]=b;
            }

            outputFileStream.write(outputBytes);

            inputStream.close();
            outputFileStream.close();

        }
        catch (IOException e){

        }
    }
    private static void doDCrypto(File inputFile,
                                 File outputFile)
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = new byte[(int) inputFile.length()];
            for (int i = 0; i < inputBytes.length; i++)
            {
                byte b = inputBytes[i];
                b-=3;
                outputBytes[i]=b;
            }

            fileOutputStream.write(outputBytes);

            inputStream.close();
            fileOutputStream.close();

        }
        catch (IOException e){

        }
    }


}


