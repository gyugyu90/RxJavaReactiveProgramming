package chap1;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FlowableSample {

    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.create(emitter -> {
            String[] data = {"Hello, World", "Hello, RxJava"};

            for (String datum : data) {
                // 구독이 해지되면 처리를 중단한다.
                if (emitter.isCancelled()) {
                    return;
                }

                emitter.onNext(datum); // 데이터를 통지한다.
            }

            emitter.onComplete();
        }, BackpressureStrategy.BUFFER); // 초과한 데이터는 버퍼링한다.

        flowable.observeOn(Schedulers.computation()) // Subscriber 처리를 개별 스레드에서 실행한다.
                .subscribe(new Subscriber<>() {

                    // 데이터 갯수 요청과 구독 해지를 하는 객체
                    private Subscription subscription;

                    // 구독 시작 시 처리
                    @Override
                    public void onSubscribe(Subscription s) {
                        // Subscription을 Subscriber에 보관한다.
                        this.subscription = s;

                        // 받을 데이터 갯수를 요청한다.
                        this.subscription.request(1L);
                    }

                    // 데이터를 받을 때 처리
                    @Override
                    public void onNext(String s) {
                        // 실행 중인 스레드 이름을 얻는다.
                        String threadName = Thread.currentThread().getName();
                        // 받은 데이터를 출력한다.
                        System.out.println(threadName + ": " + s);
                        // 다음에 받을 데이터 갯수를 요청한다.
                        this.subscription.request(1L);
                    }

                    // 에러 통지 시 처리
                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println(Thread.currentThread().getName() + ": 완료");
                    }
                });

        System.out.println(Thread.currentThread().getName() + ": 실행 중");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + ": 완료");
    }


}
