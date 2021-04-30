package chap4;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class SkipSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(1000L, TimeUnit.MILLISECONDS).skip(2);

        flowable.subscribe(new DebugSubscriber<>());

        Thread.sleep(5000L);
    }
}
