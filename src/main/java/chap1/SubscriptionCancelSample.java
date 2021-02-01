package chap1;

import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

public class SubscriptionCancelSample {

    public static void main(String[] args) throws InterruptedException {

        // 200밀리초마다 값을 통지하는 Flowable
        Flowable.interval(200L, TimeUnit.MILLISECONDS)
                .subscribe(new Subscriber<Long>() {

                    private Subscription subscription;
                    private long startTime;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.subscription = s;
                        this.startTime = System.currentTimeMillis();
                        this.subscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Long data) {
                        // 구독 시작부터 500밀리초가 지나면 구독을 해지하고 처리를 중지한다.
                        if ((System.currentTimeMillis() - startTime) > 500) {
                            subscription.cancel(); // 구독 해지
                            System.out.println("구독 해지");
                            return;
                        }

                        System.out.println("data=" + data);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        // 잠시 기다린다.
        Thread.sleep(2000L);
    }


}
