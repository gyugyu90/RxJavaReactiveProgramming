package chap1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CounterSample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 순차적으로 값을 증가시키는 Count 객체
        final Counter counter = new Counter();
        // 10,000번 계산하는 비동기 처리 작업
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Boolean> future1 = executorService.submit(task, true);

        Future<Boolean> future2 = executorService.submit(task, true);

        if (future1.get() && future2.get()) {
            System.out.println(counter.get());
        } else {
            System.err.println("실패");
        }

        executorService.shutdown();
    }

    static class Counter {
        private volatile int count;

        void increment() {
            count++;
        }

        int get() {
            return count;
        }
    }


}
