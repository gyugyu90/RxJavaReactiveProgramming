package chap4;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class TakeUntilSample1 {

    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .takeUntil(data -> data == 3);

        flowable.subscribe(new DebugSubscriber<>());

        Thread.sleep(2000L);
    }

}
