package chap4;

import io.reactivex.Flowable;

public class ErrorSample {

    public static void main(String[] args) {
        Flowable.error(new Exception("예외 발생"))
                .subscribe(new DebugSubscriber<>());
    }

}
