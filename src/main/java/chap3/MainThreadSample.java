package chap3;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

public class MainThreadSample {

    public static void main(String[] args) {
        System.out.println("start");

        Flowable.just(1, 2, 3)
                .subscribe(new ResourceSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
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
    }


}
