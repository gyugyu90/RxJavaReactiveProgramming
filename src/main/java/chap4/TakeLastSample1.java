package chap4;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class TakeLastSample1 {

    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(800L, TimeUnit.MILLISECONDS)
                .take(5)
                .takeLast(2);

        flowable.subscribe(new DebugSubscriber<>());

        Thread.sleep(5000L);
    }

}
