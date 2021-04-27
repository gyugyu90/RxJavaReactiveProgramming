package chap4;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class TakeUntilSample2 {

    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .takeUntil(Flowable.timer(1000L, TimeUnit.MILLISECONDS));

        flowable.subscribe(new DebugSubscriber<>());

        Thread.sleep(2000L);
    }

}
