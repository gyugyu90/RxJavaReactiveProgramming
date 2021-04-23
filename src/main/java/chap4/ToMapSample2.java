package chap4;

import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.Map;

public class ToMapSample2 {

    public static void main(String[] args) {

        Single<Map<Long, String>> single = Flowable.just("1A", "2B", "3C", "1D", "2E")
                // 데이터로 생성한 키와 데이터 쌍을 담은 Map을 통지한다
                .toMap(
                        data -> Long.valueOf(data.substring(0, 1)), // key selector
                        data -> data.substring(1)); // value selector

        // 구독한다
        single.subscribe(new DebugSingleObserver<>());
    }

}
