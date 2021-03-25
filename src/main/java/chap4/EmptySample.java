package chap4;

import io.reactivex.Flowable;

public class EmptySample {

    public static void main(String[] args) {
        Flowable.empty().subscribe(new DebugSubscriber<>());
    }

}
