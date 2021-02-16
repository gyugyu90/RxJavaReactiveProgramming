package chap3;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

import java.util.concurrent.TimeUnit;

public class ObserveOnSample {

    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .onBackpressureDrop(); // BackpressureMode.DROP을 설정했을 때와 마찬가지로 작동한다.

        flowable.observeOn(Schedulers.computation(), false, 1)
                .subscribe(new ResourceSubscriber<>() {
                    @Override
                    public void onNext(Long aLong) {
                        // 무거운 처리 작업을 한다고 가정하고 1000밀리초를 기다린다.
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                            System.exit(1);
                        }

                        // 실행 중인 스레드 이름을 얻는다.
                        String threadName = Thread.currentThread().getName();
                        // 받은 데이터를 출력한다.
                        System.out.println(threadName + ": " + aLong);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(7000L);

    }

}
