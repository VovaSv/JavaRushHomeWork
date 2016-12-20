package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static  Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException,InterruptedException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "";
        Thread t1 ;
        while (!"exit".equals(fileName = reader.readLine())){
            t1 = new ReadThread(fileName);
            t1.start();
            t1.join();

        }
        reader.close();
    }

    public static class ReadThread extends Thread {
        private String name;
        public ReadThread(String fileName) {
            //implement constructor body
            super(fileName);
            this.name = fileName;
        }
        @Override
        public void run(){
            Map<Integer,Integer> tmpMap = new HashMap<>();
            FileInputStream fileInputStream = null;
            try
            {
                fileInputStream = new FileInputStream(name);
                while (fileInputStream.available() > 0) {
                    int intch = fileInputStream.read();
                    if(!tmpMap.containsKey(intch)){
                        tmpMap.put(intch,1);
                    }
                    else{
                        int tmp = tmpMap.get(intch).intValue();
                        tmpMap.put(intch,++tmp);
                    }
                }
                int max = 0;
                int key = 0;

                for (Map.Entry<Integer,Integer> entry : tmpMap.entrySet()){
                    if(entry.getValue() > max){
                        max = entry.getValue();
                        key = entry.getKey();
                    }
                }
                resultMap.put(name,key);
            }
            catch (IOException e){

            }
            finally
            {
                if(null != fileInputStream){
                    try
                    {
                        fileInputStream.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }

            }

        }
        // implement file reading here - реализуйте чтение из файла тут
    }
}
