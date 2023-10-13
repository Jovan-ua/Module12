package task2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Task2 {
    private int n;
    private int current = 1;
    private CyclicBarrier barrier = new CyclicBarrier(4);

    public Task2(int n) {
        this.n = n;
    }

    public void fizz() {
        while (current <= n) {
            if (current % 3 == 0 && current % 5 != 0) {
                System.out.println("fizz");
                current++;
            }
            await();
        }
    }

    public void buzz() {
        while (current <= n) {
            if (current % 5 == 0 && current % 3 != 0) {
                System.out.println("buzz");
                current++;
            }
            await();
        }
    }

    public void fizzbuzz() {
        while (current <= n) {
            if (current % 3 == 0 && current % 5 == 0) {
                System.out.println("fizzbuzz");
                current++;
            }
            await();
        }
    }

    public void number() {
        while (current <= n) {
            if (current % 3 != 0 && current % 5 != 0) {
                System.out.println(current);
                current++;
            }
            await();
        }
    }

    private void await() {
        try {
          barrier.await();
        } catch (InterruptedException | BootstrapMethodError e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        int n = 20;
        Task2 task2 = new Task2(n);

        Thread threadA = new Thread(task2::fizz);
        Thread threadB = new Thread(task2::buzz);
        Thread threadC = new Thread(task2::fizzbuzz);
        Thread threadD = new Thread(task2::number);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}
