package com.javarush.test.level18.lesson3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vladimis on 10/13/2016.
 */
public class Solution5
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        FileInputStream inputStream = new FileInputStream(fileName);
        int[] arr = new int[256];
        int[] arr2 = new int[256];
        List<Integer> numList = new ArrayList<>();
        while (inputStream.available() > 0){
            int in = inputStream.read();
            arr[in]++;
        }
        for (int i = 0; i < arr.length; i++)
        {
            if(arr[i] > 0 ){
                numList.add(i);
            }
        }

        Collections.sort(numList);
        for (int i = 0; i < numList.size(); i++)
        {
            System.out.println(numList.get(i) + " ");
        }

    }

}
