package chap4;

import io.reactivex.Flowable;

public class DistinctSample1 {

    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("A", "a", "B", "b", "A", "a", "B", "b")
                .distinct();

        flowable.subscribe(new DebugSubscriber<>());
    }

}
