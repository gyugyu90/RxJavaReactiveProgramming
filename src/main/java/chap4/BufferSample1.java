package chap4;

import io.reactivex.Flowable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BufferSample1 {
    public static void main(String[] args) throws InterruptedException {
        Flowable<List<Long>> flowable =
                Flowable.interval(100L, TimeUnit.MILLISECONDS)
                        .take(10) // 10건까지 통지
                        .buffer(3); // 3건씩 모아 통지

        flowable.subscribe(new DebugSubscriber<>());

        Thread.sleep(3000L);
    }
}
