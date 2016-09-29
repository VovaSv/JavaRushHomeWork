package com.javarush.test.Thread_Thinking_Java;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by vladimis on 9/13/2016.
 */


//http://wikijava.it-cache.net/index.php@title=Glava_17_Thinking_in_Java_4th_edition.html#.D0.9A.D1.80.D0.B8.D1.82.D0.B8.D1.87.D0.B5.D1.81.D0.BA.D0.B8.D0.B5_.D1.81.D0.B5.D0.BA.D1.86.D0.B8.D0.B8
public class MainThread
{
    public static class LiftOff implements Runnable
    {
        protected int countDown = 10;
        private static int taskCount = 0;
        private final int id = taskCount++;

        public LiftOff()
        {
        }

        public LiftOff(int countDown)
        {
            this.countDown = countDown;
        }

        public String status()
        {
            return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
        }

        @Override
        public void run()
        {
            while (countDown-- > 0)
            {
                System.out.println(status());
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
/*        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff");*/
        for (int i = 0; i < 5; i++)
        {
            Thread t = new Thread(new LiftOff());
            t.start();
            t.join();
        }
        System.out.println("Waiting for LiftOff");
        /*        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff");*/
    }

    public static class CachedThreadPool
    {
        public static void main(String[] args)
        {
            ExecutorService exec = Executors.newCachedThreadPool();
            for (int i = 0; i < 5; i++)
            {
                exec.execute(new LiftOff());
            }
            exec.shutdown();
            System.out.println("CachedThreadPool");


        }
    }

    public static class FixedThreadPool
    {
        public static void main(String[] args)
        {
            ExecutorService exec = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 5; i++)
            {
                exec.execute(new LiftOff());
            }
            System.out.println("FixedThreadPool");
            exec.shutdown();
        }
    }

    public static class SingleThreadExecutor
    {
        public static void main(String[] args)
        {
            ExecutorService exec = Executors.newSingleThreadExecutor();
            for (int i = 0; i < 5; i++)
                exec.execute(new LiftOff());
            exec.shutdown();
        }

    }

    public static class TaskWithResult implements Callable<String>
    {

        private int id;

        public TaskWithResult(int id)
        {
            this.id = id;
        }

        public String call()
        {
            return "result of TaskWithResult " + id;
        }
    }

    public static class CallableDemo
    {
        public static void main(String[] args)
        {
            ExecutorService exec = Executors.newCachedThreadPool();
            ArrayList<Future<String>> results = new ArrayList<Future<String>>();
            for (int i = 0; i < 10; i++)
            {
                results.add(exec.submit(new TaskWithResult(i)));
            }

            for (Future<String> fs : results)
                try
                {
                    //Vizov get() blokiruetsya do zaversheniya
                    System.out.println(fs.get());


                }
                catch (InterruptedException e)
                {
                    System.out.println(e);
                    return;
                }
                catch (ExecutionException e)
                {
                    System.out.println(e);
                }
                finally
                {
                    exec.shutdown();
                }
        }

    }

    public static class SleepingTask extends LiftOff
    {
        public void run()
        {
            try
            {
                while (countDown-- > 0)
                {
                    System.out.println(status());
                    //Starii stil
                    //Thread.sleep(100);
                    //New stilll
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            }
            catch (InterruptedException e)
            {
                System.out.println("Interrupted");
            }
        }

        public static void main(String[] args)
        {
            ExecutorService exec = Executors.newCachedThreadPool();
            for (int i = 0; i < 5; i++)
            {
                exec.execute(new SleepingTask());
            }
            exec.shutdown();
        }

    }


    //Priority

    public static class SimplePriorities implements Runnable
    {
        private int countDown = 5;
        private volatile double d;
        private int priority;

        public SimplePriorities(int priority)
        {
            this.priority = priority;
        }

        public String toString()
        {
            return Thread.currentThread() + ": " + countDown;
        }

        public void run()
        {
            Thread.currentThread().setPriority(priority);
            while (true)
            {

                for (int i = 0; i < 100000; i++)
                {
                    d += (Math.PI + Math.E) / (double) i;
                    if (i % 1000 == 0)
                        Thread.yield();
                }
                System.out.println(this);
                if (--countDown == 0) return;
            }
        }

        public static void main(String[] args)
        {
            ExecutorService exec = Executors.newCachedThreadPool();
            for (int i = 0; i < 5; i++)
            {
                exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
            }
            exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
            exec.shutdown();
        }
    }

    public static class SimpleDaemons implements Runnable
    {
        public void run()
        {
            try
            {
                while (true)
                {
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println((Thread.currentThread() + " " + this));
                }
            }
            catch (InterruptedException e)
            {
                System.out.println("sleep() interrupted");
            }
        }

        public static void main(String[] args)
        {
            for (int i = 0; i < 10; i++)
            {
                Thread daemon = new Thread(new SimpleDaemons());
                daemon.setDaemon(true);
                daemon.start();
            }
            System.out.println("All daemons started");
            try
            {
                TimeUnit.MILLISECONDS.sleep(175);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }


    }


// Потоки, порождаемые демонами, также являются демонами
// Daemon threads spawn other daemon threads
// Учтите, что потоки-демоны завершают свои методы run() без выполнения секций finally:

    public static class Daemon implements Runnable {
        private Thread[] t = new Thread[10];
        public void run() {
            for(int i = 0; i < t.length; i++) {
                t[i] = new Thread(new DaemonSpawn());
                t[i].start();
                System.out.println("DaemonSpawn " + i + " started, ");
            }
            for(int i = 0; i < t.length; i++)
                System.out.println("t[" + i + "].isDaemon() = " +
                        t[i].isDaemon() + ", ");
            while(true)
                Thread.yield();
        }
    }

    public static  class DaemonSpawn implements Runnable {
        public void run() {
            while(true)
                Thread.yield();
        }
    }

    public static class Daemons {
        public static void main(String[] args) throws Exception {
            Thread d = new Thread(new Daemon());
            d.setDaemon(true);
            d.start();
            System.out.println("d.isDaemon() = " + d.isDaemon() + ", ");
            // ???? ???????-??????? ????????? ??????? ???????:
            TimeUnit.SECONDS.sleep(1);
        }
    }

// Прямое наследование от класса Thread..

    public static class SimpleThread extends Thread {
        private int countDown = 5;
        private static int threadCount = 0;
        public SimpleThread() {
            // Store the thread name:
            super(Integer.toString(++threadCount));
            start();
        }
        public String toString() {
            return "#" + getName() + "(" + countDown + "), ";
        }
        public void run() {
            while(true) {
                System.out.print(this);
                if(--countDown == 0)
                    return;
            }
        }
        public static void main(String[] args) {
            for(int i = 0; i < 5; i++)
                new SimpleThread();
        }
    }
// Реализация Runnable. содержащая собственный объект Thread.

    public static class SelfManaged implements Runnable {
        private int countDown = 5;
        private Thread t = new Thread(this);
        public SelfManaged() { t.start(); }
        public String toString() {
            return Thread.currentThread().getName() +
                    "(" + countDown + "), ";
        }
        public void run() {
            while(true) {
                System.out.print(this);
                if(--countDown == 0)
                    return;
            }
        }
        public static void main(String[] args) {
            for(int i = 0; i < 5; i++)
                new SelfManaged();
        }
    }
// Создание потоков с использованием внутренних классов..

    // Using a named inner class:
   public static class InnerThread1 {
        private int countDown = 5;
        private Inner inner;
        private class Inner extends Thread {
            Inner(String name) {
                super(name);
                start();
            }
            public void run() {
                try {
                    while(true) {
                        System.out.println(this);
                        if(--countDown == 0) return;
                        sleep(10);
                    }
                } catch(InterruptedException e) {
                    System.out.println("interrupted");
                }
            }
            public String toString() {
                return getName() + ": " + countDown;
            }
        }
        public InnerThread1(String name) {
            inner = new Inner(name);
        }
    }
    // Используем безымянный внутренний класс::
   public static class InnerThread2 {
        private int countDown = 5;
        private Thread t;
        public InnerThread2(String name) {
            t = new Thread(name) {
                public void run() {
                    try {
                        while(true) {
                            System.out.println(this);
                            if(--countDown == 0) return;
                            sleep(10);
                        }
                    } catch(InterruptedException e) {
                        System.out.println("sleep() interrupted");
                    }
                }
                public String toString() {
                    return getName() + ": " + countDown;
                }
            };
            t.start();
        }
    }


    // Используем именованную реализацию Runnable:
    public static class InnerRunnable1 {
        private int countDown = 5;
        private Inner inner;
        private class Inner implements Runnable {
            Thread t;
            Inner(String name) {
                t = new Thread(this, name);
                t.start();
            }
            public void run() {
                try {
                    while(true) {
                        System.out.println(this);
                        if(--countDown == 0) return;
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                } catch(InterruptedException e) {
                    System.out.println("sleep() interrupted");
                }
            }
            public String toString() {
                return t.getName() + ": " + countDown;
            }
        }
        public InnerRunnable1(String name) {
            inner = new Inner(name);
        }
    }

    // Используем анонимную реализацию Runnable:
    public static class InnerRunnable2 {
        private int countDown = 5;
        private Thread t;
        public InnerRunnable2(String name) {
            t = new Thread(new Runnable() {
                public void run() {
                    try {
                        while(true) {
                            System.out.println(this);
                            if(--countDown == 0) return;
                            TimeUnit.MILLISECONDS.sleep(10);
                        }
                    } catch(InterruptedException e) {
                        System.out.println("sleep() interrupted");
                    }
                }
                public String toString() {
                    return Thread.currentThread().getName() +
                            ": " + countDown;
                }
            }, name);
            t.start();
        }
    }

    // Отдельный метод для выполнения кода в потоке:
    public static class ThreadMethod {
        private int countDown = 5;
        private Thread t;
        private String name;
        public ThreadMethod(String name) { this.name = name; }
        public void runTask() {
            if(t == null) {
                t = new Thread(name) {
                    public void run() {
                        try {
                            while(true) {
                                System.out.println(this);
                                if(--countDown == 0) return;
                                sleep(10);
                            }
                        } catch(InterruptedException e) {
                            System.out.println("sleep() interrupted");
                        }
                    }
                    public String toString() {
                        return getName() + ": " + countDown;
                    }
                };
                t.start();
            }
        }
    }
    public static class ThreadVariations {
        public static void main(String[] args) {
            new InnerThread1("InnerThread1");
            new InnerThread2("InnerThread2");
            new InnerRunnable1("InnerRunnable1");
            new InnerRunnable2("InnerRunnable2");
            new ThreadMethod("ThreadMethod").runTask();
        }
    }


    //Присоединение к потоку
    // Демонстрация join().
//Когда другой поток вызывает interrupt() для данного потока, устанавливается флаг, показывающий, что поток был прерван.
// Однако этот флаг сбрасывается при обработке исключения, поэтому внутри секции catch результатом всегда будет false.
// Флаг используется в других ситуациях, где поток может исследовать свое прерванное состояние в стороне от исключения
    public static class Sleeper extends Thread {
        private int duration;
        public Sleeper(String name, int sleepTime) {
            super(name);
            duration = sleepTime;
            start();
        }
        public void run() {
            try {
                sleep(duration);
            } catch(InterruptedException e) {
                System.out.println(getName() + " was interrupted. " +
                        "isInterrupted(): " + isInterrupted());
                return;
            }
            System.out.println(getName() + " has awakened");
        }
    }

    public static class Joiner extends Thread {
        private Sleeper sleeper;
        public Joiner(String name, Sleeper sleeper) {
            super(name);
            this.sleeper = sleeper;
            start();
        }
        public void run() {
            try {
                sleeper.join();
            } catch(InterruptedException e) {
                System.out.println("Interrupted");
            }
            System.out.println(getName() + " join completed");
        }
    }

    public static class Joining {
        public static void main(String[] args) {
            Sleeper
                    sleepy = new Sleeper("Sleepy", 1500),
                    grumpy = new Sleeper("Grumpy", 1500);
            Joiner
                    dopey = new Joiner("Dopey", sleepy),
                    doc = new Joiner("Doc", grumpy);
            grumpy.interrupt();
        }
    }


// Совместное использование ресурсов
//: concurrency/IntGenerator.java
    public static abstract class IntGenerator {
        private volatile boolean canceled = false;
        public abstract int next();
        // Allow this to be canceled:
        public void cancel() { canceled = true; }
        public boolean isCanceled() { return canceled; }
    }



    public static class EvenChecker implements Runnable {
        private IntGenerator generator;
        private final int id;
        public EvenChecker(IntGenerator g, int ident) {
            generator = g;
            id = ident;
        }
        public void run() {
            while(!generator.isCanceled()) {
                int val = generator.next();
                if(val % 2 != 0) {
                    System.out.println(val + " not even!");
                    generator.cancel(); // Отмена всех EvenChecker
                }
            }
        }
        // Тестирование произвольного типа IntGenerator:
        public static void test(IntGenerator gp, int count) {
            System.out.println("Press Control-C to exit");
            ExecutorService exec = Executors.newCachedThreadPool();
            for(int i = 0; i < count; i++)
                exec.execute(new EvenChecker(gp, i));
            exec.shutdown();
        }
        // Значение по умолчанию для count:
        public static void test(IntGenerator gp) {
            test(gp, 10);
        }
    }

    // Конфликт потоков.

    public static class EvenGenerator extends IntGenerator {
        private  int currentEvenValue = 0;
        public synchronized int next() {
            ++currentEvenValue;
            // Опасная точка!
            Thread.yield();
            ++currentEvenValue;
            return currentEvenValue;
        }
        public static void main(String[] args) {
            EvenChecker.test(new EvenGenerator());
        }
    }


    //Объекты Lock

    public static class MutexEvenGenerator extends IntGenerator {
        private int currentEvenValue = 0;
        private Lock lock = new ReentrantLock();
        public int next(){
            lock.lock();
            try
            {
                ++currentEvenValue;
                Thread.yield();
                ++currentEvenValue;
                return currentEvenValue;
            }finally
            {
                lock.unlock();
            }
        }

        public static void main(String[] args)
        {
            EvenChecker.test(new MutexEvenGenerator());
        }

    }


    //
    public static class AtomicityTest implements Runnable {
        private int i = 0;
        public  int getValue() { return i; }
        private synchronized void evenIncrement() { i++; i++; }
        public void run() {
            while(true)
                evenIncrement();
        }
        public static void main(String[] args) {
            ExecutorService exec = Executors.newCachedThreadPool();
            AtomicityTest at = new AtomicityTest();
            exec.execute(at);
            while(true) {
                int val = at.getValue();
                if(val % 2 != 0) {
                    System.out.println(val);
                    System.exit(0);
                }
            }
        }
    }

    public static class SerialNumberGenerator {
        private static volatile int serialNumber = 0;
        public static synchronized  int  nextSerialNumber() {
            return serialNumber++; // ???????? ?? ???????? ????????-??????????
        }
    }


    // Reuses storage so we don't run out of memory:
   static  class CircularSet {
        private int[] array;
        private int len;
        private int index = 0;
        public CircularSet(int size) {
            array = new int[size];
            len = size;
            // Инициализируем значением, которое не производится
            // классом SerialNumberGenerator:
            for(int i = 0; i < size; i++)
                array[i] = -1;
        }
        public synchronized void add(int i) {
            array[index] = i;
            // Возврат индекса к началу с записью поверх старых значений:
            index = ++index % len;
        }
        public synchronized boolean contains(int val) {
            for(int i = 0; i < len; i++)
                if(array[i] == val) return true;
            return false;
        }
    }

    public static class SerialNumberChecker {
        private static final int SIZE = 10;
        private static CircularSet serials =
                new CircularSet(1000);
        private static ExecutorService exec =
                Executors.newCachedThreadPool();
        static class SerialChecker implements Runnable {
            public void run() {
                while(true) {
                    int serial =
                            SerialNumberGenerator.nextSerialNumber();
                    if(serials.contains(serial)) {
                        System.out.println("Duplicate: " + serial);
                        System.exit(0);
                    }
                    serials.add(serial);
                }
            }
        }
        public static void main(String[] args) throws Exception {
            for(int i = 0; i < SIZE; i++)
                exec.execute(new SerialChecker());
            // Остановиться после n секунд при наличии аргумента:
            if(args.length > 0) {
                TimeUnit.SECONDS.sleep(new Integer(args[0]));
                System.out.println("No duplicates detected");
                System.exit(0);
            }
        }
    }

    /*
    Атомарные классы
В Java SE5 появились специальные классы для выполнения атомарных операций с переменными — Atomiclnteger, AtomicLong, AtomicReference и т. д.
 Эти классы содержат атомарную операцию условного обновления в форме
 boolean compareAndSer(expectedValue, updateValue);
Эти классы предназначены для оптимизации с целью использования атомарности на машинном уровне на некоторых современных процессорах,
 поэтому в общем случае вам они не понадобятся. Иногда они применяются и в повседневном программировании, но только при оптимизации производительности.
 Например, версия AtomicityTest.java, переписанная для использования AtomicInteger, выглядит так:
     */

    public static  class AtomicIntegerTest implements Runnable {
        private AtomicInteger i = new AtomicInteger(0);
        public int getValue() { return i.get(); }
        private void evenIncrement() { i.addAndGet(2); }
        public void run() {
            while(true)
                evenIncrement();
        }
        public static void main(String[] args) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    System.err.println("Aborting");
                    System.exit(0);
                }
            }, 5000); // Завершение через 5 секунд
            ExecutorService exec = Executors.newCachedThreadPool();
            AtomicIntegerTest ait = new AtomicIntegerTest();
            exec.execute(ait);
            while(true) {
                int val = ait.getValue();
                if(val % 2 != 0) {
                    System.out.println(val);
                    System.exit(0);
                }
            }
        }
    }

  //  Вот как выглядит пример MutexEvenGenerator.java, переписанный для использования класса Atomiclnteger:
    // Атомарные классы иногда используются в обычном коде.
// {RunByHand}


    public static class AtomicEvenGenerator extends IntGenerator {
        private AtomicInteger currentEvenValue =
                new AtomicInteger(0);
        public int next() {
            return currentEvenValue.addAndGet(2);
        }
        public static void main(String[] args) {
            EvenChecker.test(new AtomicEvenGenerator());
        }
    }



    //: concurrency/CriticalSection.java
// Синхронизация блоков вместо целых методов. Также демонстрирует защиту
// неприспособленного к многопоточности класса другим классом
// with a thread-safe one.

    public static class Pair { // Not thread-safe
        private int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Pair() { this(0, 0); }
        public int getX() { return x; }
        public int getY() { return y; }
        public void incrementX() { x++; }
        public void incrementY() { y++; }
        public String toString() {
            return "x: " + x + ", y: " + y;
        }
        public class PairValuesNotEqualException
                extends RuntimeException {
            public PairValuesNotEqualException() {
                super("Pair values not equal: " + Pair.this);
            }
        }
        // Произвольный инвариант - обе переменные должны быть:
        public void checkState() {
            if(x != y)
                throw new PairValuesNotEqualException();
        }
    }

    // Защита класса Pair внутри приспособленного к потокам класса:
    abstract static class PairManager {
        AtomicInteger checkCounter = new AtomicInteger(0);
        protected Pair p = new Pair();
        private List<Pair> storage =
                Collections.synchronizedList(new ArrayList<Pair>());
        public synchronized Pair getPair() {
            // Создаем копию, чтобы сохранить оригинал в безопасности:
            return new Pair(p.getX(), p.getY());
        }
        // Предполагается, что операция занимает некоторое время
        protected void store(Pair p) {
            storage.add(p);
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch(InterruptedException ignore) {}
        }
        public abstract void increment();
    }

    // Синхронизация всего метода:
   public static class PairManager1 extends PairManager {
        public synchronized void increment() {
            p.incrementX();
            p.incrementY();
            store(getPair());
        }
    }

    // Использование критической секции:
    public static class PairManager2 extends PairManager {
        public void increment() {
            Pair temp;
            synchronized(this) {
                p.incrementX();
                p.incrementY();
                temp = getPair();
            }
            store(temp);
        }
    }

   public static class PairManipulator implements Runnable {
        private PairManager pm;
        public PairManipulator(PairManager pm) {
            this.pm = pm;
        }
        public void run() {
            while(true)
                pm.increment();
        }
        public String toString() {
            return "Pair: " + pm.getPair() +
                    " checkCounter = " + pm.checkCounter.get();
        }
    }

    public static class PairChecker implements Runnable {
        private PairManager pm;
        public PairChecker(PairManager pm) {
            this.pm = pm;
        }
        public void run() {
            while(true) {
                pm.checkCounter.incrementAndGet();
                pm.getPair().checkState();
            }
        }
    }

     public static class CriticalSection {
        // Сравнение двух подходов:
        static void
        testApproaches(PairManager pman1, PairManager pman2) {
            ExecutorService exec = Executors.newCachedThreadPool();
            PairManipulator
                    pm1 = new PairManipulator(pman1),
                    pm2 = new PairManipulator(pman2);
            PairChecker
                    pcheck1 = new PairChecker(pman1),
                    pcheck2 = new PairChecker(pman2);
            exec.execute(pm1);
            exec.execute(pm2);
            exec.execute(pcheck1);
            exec.execute(pcheck2);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch(InterruptedException e) {
                System.out.println("Sleep interrupted");
            }
            System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
            System.exit(0);
        }
        public static void main(String[] args) {
            PairManager
                    pman1 = new PairManager1(),
                    pman2 = new PairManager2();
            testApproaches(pman1, pman2);
        }
    }

    //Для создания критических секций также можно воспользоваться явно созданными объектами Lock:
//: concurrency/ExplicitCriticalSection.java
// Использование объектов Lock для создания критических секций..
    // Синхронизация целого метода:
    public static class ExplicitPairManager1 extends PairManager {
        private Lock lock = new ReentrantLock();
        public synchronized void increment() {
            lock.lock();
            try {
                p.incrementX();
                p.incrementY();
                store(getPair());
            } finally {
                lock.unlock();
            }
        }
    }

    // Использование критической секции:
    public static class ExplicitPairManager2 extends PairManager {
        private Lock lock = new ReentrantLock();
        public void increment() {
            Pair temp;
            lock.lock();
            try {
                p.incrementX();
                p.incrementY();
                temp = getPair();
            } finally {
                lock.unlock();
            }
            store(temp);
        }
    }

    public static class ExplicitCriticalSection {
        public static void main(String[] args) throws Exception {
            PairManager
                    pman1 = new ExplicitPairManager1(),
                    pman2 = new ExplicitPairManager2();
            CriticalSection.testApproaches(pman1, pman2);
        }
    }


//Синхронизация по другим объектам
    public static class DualSynch {
        private Object syncObject = new Object();
        public synchronized void f() {
            for(int i = 0; i < 5; i++) {
                System.out.println("f()");
                Thread.yield();
            }
        }
        public void g() {
            synchronized(syncObject) {
                for(int i = 0; i < 5; i++) {
                    System.out.println("g()");
                    Thread.yield();
                }
            }
        }
    }

    public static class SyncObject {
        public static void main(String[] args) {
            final DualSynch ds = new DualSynch();
            new Thread() {
                public void run() {
                    ds.f();
                }
            }.start();
            ds.g();
        }
    }

//Локальная память потока
//Если вы создаете объект ThreadLocal, для обращения к содержимому объекта можно использовать только методы get() и set().
//Метод get() возвращает копию объекта, ассоцииро­ванного с потоком, a set() сохраняет свой аргумент в объекте потока, возвращая ранее хранившийся объект
static class Accessor implements Runnable {
    private final int id;
    public Accessor(int idn) { id = idn; }
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }
    public String toString() {
        return "#" + id + ": " +
                ThreadLocalVariableHolder.get();
    }
}

    public static class ThreadLocalVariableHolder {
        private static ThreadLocal<Integer> value =
                new ThreadLocal<Integer>() {
                    private Random rand = new Random(47);
                    protected synchronized Integer initialValue() {
                        return rand.nextInt(10000);
                    }
                };
        public static void increment() {
            value.set(value.get() + 1);
        }
        public static int get() { return value.get(); }
        public static void main(String[] args) throws Exception {
            ExecutorService exec = Executors.newCachedThreadPool();
            for(int i = 0; i < 5; i++)
                exec.execute(new Accessor(i));
            TimeUnit.SECONDS.sleep(3);   // Небольшая задержка
            exec.shutdownNow();          // Выход из всех объектов Accessor
        }
    }



}

