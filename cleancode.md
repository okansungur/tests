##  Concurency in Java

4 conditions required for deadlock to occur:

1- Mutual exclusion
    
2- Hold and wait 
    
3- No preemption
    
4- Circular wait

In a multithreaded application, two or more threads access a shared resource at the same time the results are confusing.
This situation is caled a race condition. To overcome this problem we use  synchronized  keyword for the resource
to implement a mutex we use synchronized  keyword. Only one thread can access a resource at a time.

## ReentrantLock : Provides more flexibility and control than the synchronized

```

import java.util.concurrent.locks.ReentrantLock;

class SayHello {

    private ReentrantLock retrantLock = new ReentrantLock();


    void greeting(String threadName) {
        try {
            //with or without ReentrantLock
            //retrantLock.lock();
            for (int i = 1; i <= 5; i++) {
                System.out.println("hello " + threadName);
            }
        } finally {
            // retrantLock.unlock();
        }

    }

}

class MyThread extends Thread {

    SayHello hello;
    String threadName;

    MyThread(SayHello hello, String nameThread) {
        this.hello = hello;
        this.threadName = nameThread;
    }

    public void run() {

        hello.greeting(threadName);

    }

}

public class JavaApplication31 {

    public static void main(String[] args) {

        SayHello obj = new SayHello();//only one resource
        MyThread t1 = new MyThread(obj, "thread-1");
        MyThread t2 = new MyThread(obj, "thread-2");
        t1.start();
        t2.start();

    }

}


```

## Mutex(Syncronized)

 ```
 class SayHello {

//with or without keyword
//  synchronized 
    void greeting(String threadName) {
        for (int i = 1; i <= 5; i++) {
            System.out.println("hello " + threadName);
        }
    }

}

class MyThread extends Thread {

    SayHello hello;
    String threadName;

    MyThread(SayHello hello, String nameThread) {
        this.hello = hello;
        this.threadName = nameThread;
    }

    public void run() {

        hello.greeting(threadName);

    }

}

public class MutexTest {

    public static void main(String[] args) {
        SayHello obj = new SayHello();//only one resource
        MyThread t1 = new MyThread(obj, "thread-1");
        MyThread t2 = new MyThread(obj, "thread-2");
        t1.start();
        t2.start();

    }

}
 
 ```
 
 
 ## Semaphore: It has 3 types. Binary,Counting and mutex ex:printers, browser tabs
 
  ```
  
import java.util.concurrent.Semaphore;

class SayHello {

    private Semaphore semaphore = new Semaphore(1); //can increase the number

    void greeting(String threadName) {
        try {
            //with or without Semaphore
            semaphore.acquire();
            for (int i = 1; i <= 5; i++) {
                System.out.println("hello " + threadName);
            }
        } catch (Exception ex) {
        } finally {
            semaphore.release();
        }

    }

}

class MyThread extends Thread {

    SayHello hello;
    String threadName;

    MyThread(SayHello hello, String nameThread) {
        this.hello = hello;
        this.threadName = nameThread;
    }

    public void run() {

        hello.greeting(threadName);

    }

}

public class JavaApplication1 {

    public static void main(String[] args) {

       SayHello obj = new SayHello();//only one resource
       MyThread t1 = new MyThread(obj, "thread-1");
       MyThread t2 = new MyThread(obj, "thread-2");
	     	MyThread t2 = new MyThread(obj, "thread-3");
		     MyThread t2 = new MyThread(obj, "thread-4");
       t1.start();
       t2.start();
		

    }

}
  
   ```
 
 
 
