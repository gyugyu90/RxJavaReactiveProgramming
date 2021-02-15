package chap3;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class SubscribeOnSample {

    public static void main(String[] args) throws InterruptedException {
        Flowable.just(1, 2, 3, 4, 5) // flowable 설정
                .subscribeOn(Schedulers.computation()) // RxComputationThreadPool
                .subscribeOn(Schedulers.io()) // RxCachedThreadScheduler
                .subscribeOn(Schedulers.single()) // RxSingleScheduler
                .subscribe(data -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ": " + data);
                });

        // 잠시 기다린다.
        Thread.sleep(500);
    }

}
