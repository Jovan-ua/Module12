package task1;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    private static AtomicLong startTime = new AtomicLong(0);
    private static Timer timer = new Timer();

    public static void main(String[] args) {

        Thread timeThread = new Thread(() -> {
            while (true) {
                long elapsedTime = startTime.get() / 1000;
                System.out.println("Прошло " + elapsedTime + " секунд");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startTime.addAndGet(1000);
            }
        });
        timeThread.start();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Прошло 5 секунд");
            }
        }, 0, 5000);
    }
}

