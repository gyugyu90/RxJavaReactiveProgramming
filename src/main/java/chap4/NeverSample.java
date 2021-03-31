package chap4;

import io.reactivex.Flowable;

public class NeverSample {

    public static void main(String[] args) {
        Flowable.never()
                .subscribe(new DebugSubscriber<>());
        System.out.println("완료");
    }

}
