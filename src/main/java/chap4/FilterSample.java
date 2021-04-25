package chap4;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class FilterSample {

    public static void main(String[] args) throws InterruptedException {

        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .filter(data -> data % 2 == 0);

        flowable.subscribe(new DebugSubscriber<>());


        Thread.sleep(3000L);

    }

}
