package chap3;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class FlatMapSample {

    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.just("A", "B", "C")
                                            .flatMap(data -> Flowable.just(data + " - 1", data + " - 2")
                                                                     .delay(1000L, TimeUnit.MILLISECONDS));

        flowable.subscribe(data -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + data);
        });

        Thread.sleep(2000L);
    }
}
