package PP;

import java.util.concurrent.locks.ReentrantLock;

public class Program {
    public static void main(String[] args) {

        CommonResource commonResource= new CommonResource();
        ReentrantLock locker = new ReentrantLock();
        for (int i = 0; i < 4; i++){
            Thread t = new Thread(new CountThread(commonResource, locker));
            t.start();
        }
    }
}
class CommonResource{
}
class CountThread implements Runnable{

    ReentrantLock locker;
    CountThread(CommonResource res, ReentrantLock lock){
        locker = lock;
    }
    public void run(){

        locker.lock(); // устанавливаем блокировку
        try{
            System.out.printf("Ping ");
            Thread.sleep(100);
            locker.lock(); // устанавливаем блокировку
            try{
                System.out.printf("Pong ");
                Thread.sleep(100);
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
            finally{
                locker.unlock(); // снимаем блокировку
            }
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            locker.unlock(); // снимаем блокировку
        }
    }
}