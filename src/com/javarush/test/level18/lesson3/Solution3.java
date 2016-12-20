package com.javarush.test.level18.lesson3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by vladimis on 10/13/2016.
 */
public class Solution3
{

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        FileInputStream inputStream = new FileInputStream(fileName);
        int[] arr = new int[256];
        while (inputStream.available() > 0){
            int in = inputStream.read();
            arr[in]++;
        }
        int max = arr[0];
        for (int i = 0; i < arr.length; i++)
        {
            if(arr[i] > max)
                max = arr[i];
        }
        for (int i = 0; i < arr.length; i++)
        {
            if(arr[i] == max)
                System.out.print(i + " ");
        }
    }
}
