package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {

    public Solution()
    {
    }
    public Solution(int i)
    {
    }
    public Solution(int i, int j)
    {
    }
    protected Solution(boolean i){

    }
    protected Solution(boolean i,boolean j){

    }
    protected Solution(float i,float j){

    }
    private Solution(boolean i,float j){

    }
    private Solution(float i,int j){

    }
    private Solution(boolean i,int j){

    }

    Solution (Object o){

    }
    Solution (Object o,Object b){

    }
    Solution (Integer o){

    }



}

