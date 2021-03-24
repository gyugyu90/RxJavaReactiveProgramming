package chap4;

import io.reactivex.Flowable;

public class FromCallableSample {

    public static void main(String[] args) {
        // Callable의 결과를 통지하는 Flowable을 생성한다.
        Flowable<Long> flowable = Flowable.fromCallable(System::currentTimeMillis);
        flowable.subscribe(new DebugSubscriber<>());
    }
}
