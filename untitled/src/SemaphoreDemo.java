import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SemaphoreDemo {
    public static void main(String[] args) {
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(4,()-> System.out.println("收集龙珠成功"));
//        for (int i = 1; i<12; i++) {
//            final int t=i;
//            new Thread(() -> {System.out.println("线程"+Thread.currentThread().getName()+"收集到第"+t+"颗龙珠");
//                try {
//                    cyclicBarrier.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (BrokenBarrierException e) {
//                    e.printStackTrace();
//                }},String.valueOf(i)
//            ).start();
//
//        }

//
//        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//        System.out.println("执行任务的时间："+LocalDateTime.now());
//
//        try {
//            service.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("执行任务："+ LocalDateTime.now());
//                }
//            },2, TimeUnit.SECONDS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//           service.shutdown();
//        }


//        Semaphore semaphore = new Semaphore(3);
//        for (int i = 0; i < 7; i++) {
//            new Thread(() -> {
//
//                try {
//                    semaphore.acquire();
//                    System.out.println(Thread.currentThread().getName() + "进来车位");
//                    TimeUnit.SECONDS.sleep(2);
//                    System.out.println(Thread.currentThread().getName() + "离开车位");
//
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    semaphore.release();
//
//                }
//
//            }).start();
////
//        }
//        BlockingQueue<String> objects = new ArrayBlockingQueue<>(3);
//        BlockingQueue<String> synchronoousQueue = new SynchronousQueue<>();
//        LinkedBlockingQueue<Object> linkedBlockingQueue = new LinkedBlockingQueue<>();
//
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//
//
//        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//        System.out.println("执行任务的时间：" + LocalDateTime.now());
//        service.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("执行任务：" + LocalDateTime.now());
//            }
//        }, 2, TimeUnit.SECONDS);
//
//        ExecutorService executor = Executors.newWorkStealingPool();
//        for (int i = 0; i < 100; i++) {
//            final int t = i;
//            executorService.submit(() -> {
//                System.out.println("任务：" + t + "线程名：" + Thread.currentThread().getName());
//            });
//        }
//        while (!executorService.isTerminated()) {
//        }

//        Function<String, Integer> stringIntegerFunction = new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                return Integer.valueOf(s);
//            }
//        };
//        System.out.println(stringIntegerFunction.apply("20"));

//
//        Function<String, Integer> stringIntegerFunction = (String s) -> {
//            return Integer.valueOf(s);
//        };
//        System.out.println(stringIntegerFunction.apply("20"));

//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.isEmpty();
//            }
//        };
//        System.out.println(predicate.test(""));

//        Supplier<String> stringSupplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                String arr = "liuliu";
//                return arr;
//            }
//        };
//        System.out.println(stringSupplier.get());

//        ArrayList<String> list = new ArrayList<>();
//        Consumer<String> stringConsumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                list.add(s);
//            }
//        };
//        stringConsumer.accept("liuliu");
//        ListIterator<String> stringListIterator = list.listIterator();
//        while (stringListIterator.hasNext()) {
//            String t = stringListIterator.next();
//            System.out.println(t);
//        }


        /**
         * 线程池的的最大线程怎么设置
         * 1.根据cpu密集型：cpu是几核就是几核，不能写死，Runtime.getRuntime().availableProcessors()
         * 2.根据io密集型，判断程序中十分耗io的线程，最大值设置为他的两倍
         */


        /**
         corePoolSize : 线程池线程初始数
         maxinumPoolSize : 线程池最大线程数
         keepAliveTime : （maxinumPoolSize - corePoolSize） 的线程存活时长
         unit : 存活时长的时间单位
         workQueue : 任务阻塞队列
         ArrayBlockingQueue：构造函数一定要传大小
         LinkedBlockingQueue：构造函数不传大小会默认为（Integer.MAX_VALUE ），当大量请求任务时，容易造成 内存耗尽。
         SynchronousQueue：同步队列，一个没有存储空间的阻塞队列 ，将任务同步交付给工作线程。
         PriorityBlockingQueue : 优先队列

         threadFactory : 线程工厂
         handler : 拒绝执行策略处理器
         拒绝策略有4种：
         AbortPolicy: 当任务数超出线程池处理数，就会抛出该异常
         DiscardPolicy: 当任务数超出线程池处理数，它会将后续提交的任务进行丢弃
         DiscardOldestPolicy: 当任务数超出线程池处理数，它会将阻塞队列中第一个位置的元素弹出
         CallerUnsPolicy:   当任务数超出线程池处理数，它会将任务抛出调用者线程进行执行
         */


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 3, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 1; i < 12; i++) {
                threadPoolExecutor.execute(() -> System.out.println(Thread.currentThread().getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

}
