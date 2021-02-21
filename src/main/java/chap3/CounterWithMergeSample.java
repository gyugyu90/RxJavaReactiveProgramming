package chap3;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class CounterWithMergeSample {

    public static void main(String[] args) throws InterruptedException {
        final CounterSample.Counter counter = new CounterSample.Counter();

        Flowable<Integer> source1 = Flowable.range(1, 10000)
                                            .subscribeOn(Schedulers.computation())
                                            .observeOn(Schedulers.computation());

        Flowable<Integer> source2 = Flowable.range(1, 10000)
                                            .subscribeOn(Schedulers.computation())
                                            .observeOn(Schedulers.computation());

        Flowable.merge(source1, source2)
                .subscribe(
                        data -> counter.increment(), // 데이터 받을 때
                        error -> System.out.println("에러=" + error), // 에러 받을 때
                        () -> System.out.println("counter.get()=" + counter.get()) // 완료 통지를 받을 때
                );

        // 잠시 기다린다
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
