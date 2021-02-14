package chap3;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

import java.util.concurrent.TimeUnit;

public class NonMainThreadSample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");

        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .subscribe(new ResourceSubscriber<Long>() {
                    @Override
                    public void onNext(Long integer) {
                        var threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        var threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": 완료");
                    }
                });
        System.out.println("end");
        Thread.sleep(1000L);
    }
}
