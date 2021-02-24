package chap3;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

public class MissingBackpressureFlowableSample {

    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(10L, TimeUnit.MICROSECONDS)
                .doOnNext(value -> System.out.println("emit: " + value));

        flowable.observeOn(Schedulers.computation())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Long value) {
                        try {
                            System.out.println("waiting......");
                            Thread.sleep(1000L);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("received: " + value);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("에러=" + t);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("종료");
                    }
                });

        Thread.sleep(5000L);
    }

}
