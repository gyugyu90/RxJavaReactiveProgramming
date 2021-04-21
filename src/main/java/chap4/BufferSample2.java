package chap4;

import io.reactivex.Flowable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BufferSample2 {

    public static void main(String[] args) throws InterruptedException {
        Flowable<List<Long>> flowable =
                Flowable.interval(300L, TimeUnit.MILLISECONDS)
                        // 7건까지 통지한다
                        .take(7)
                        // 1000밀리초 뒤에 데이터를 통지한다
                        .buffer(() -> Flowable.timer(1000L, TimeUnit.MILLISECONDS));

        // 구독한다
        flowable.subscribe(new DebugSubscriber<>());

        // 잠시 기다린다
        Thread.sleep(4000L);
    }

}
