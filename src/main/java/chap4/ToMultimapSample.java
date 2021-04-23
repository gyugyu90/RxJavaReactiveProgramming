package chap4;

import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ToMultimapSample {

    public static void main(String[] args) throws InterruptedException {

        Single<Map<String, Collection<Long>>> single = Flowable.interval(50L, TimeUnit.MILLISECONDS)
                .take(5) // 5건까지 통지한다
                .toMultimap(data -> {
                    if (data %2 == 0) {
                        return "짝수";
                    } else {
                        return "홀수";
                    }
                });

        // 구독한다
        single.subscribe(new DebugSingleObserver<>());

        // 잠시 기다린다
        Thread.sleep(3000L);
    }

}
