import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4,()-> System.out.println("收集龙珠成功"));
        for (int i = 1; i<9; i++) {
            final int t=i;
            new Thread(() -> {System.out.println("线程"+Thread.currentThread().getName()+"收集到第"+t+"颗龙珠");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }},String.valueOf(i)
            ).start();

        }

    }
}
