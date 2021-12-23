package PingPong;


public class Solution {

    public static void main(String[] args) {
        Object object = new Object();
        Thread ping = new Thread(new PingPongThread(object, "Ping"));
        ping.start();
        Thread pong = new Thread(new PingPongThread(object, "Pong"));
        pong.start();
    }

}

class PingPongThread implements Runnable{
    private Object object;
    private String action;
    public PingPongThread(Object object, String action) {
        this.object = object;
        this.action = action;
    }
    @Override
    public void run() {
        synchronized (object) {
            for(int i = 0; i < 3; i++) {
                System.out.print(action);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                object.notify(); // Продолжает работу иного потока
                try {
                    object.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}