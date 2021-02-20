package chap3;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class CounterSample {

    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();

        Flowable.range(1, 10000)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())
                .subscribe(
                        data -> counter.increment(), // 데이터 받을 때
                        error -> System.out.println("에러=" + error), // 에러 받을 때
                        () -> System.out.println("counter.get()=" + counter.get()) // 완료 통지를 받을 때
                );

        Flowable.range(1, 10000)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())
                .subscribe(
                        data -> counter.increment(), // 데이터 받을 때
                        error -> System.out.println("에러=" + error), // 에러 받을 때
                        () -> System.out.println("counter.get()=" + counter.get()) // 완료 통지를 받을 때
                );

        Thread.sleep(1000L);
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
