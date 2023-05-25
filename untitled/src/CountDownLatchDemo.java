import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countdownlatch = new CountDownLatch(7);//从7开始
        for (int i = 0; i < 9; i++) {
            new Thread(() -> {
                System.out.println("线程"+Thread.currentThread().getName());
                countdownlatch.countDown();//代表数量减1
            },String.valueOf(i)).start();
        }

        try {
            countdownlatch.await();//意思是当数量减为0的时候下面线程被唤醒继续执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("最后执行");
        System.out.println("测试");
    }
}
