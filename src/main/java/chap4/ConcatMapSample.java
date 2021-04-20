package chap4;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class ConcatMapSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.range(10, 3)
                                            // 받은 데이터를 기반으로 통지할 데이터를 가진 flowable을 생성해 순서대로 실행
                                            .concatMap(
                                                    // 통지할 데이터가 있는 flowable을 생성한다
                                                    sourceData -> Flowable.interval(500L, TimeUnit.MILLISECONDS)
                                                                          // 2건까지 통지한다
                                                                          .take(2)
                                                                          // 원본 통지 데이터와 결과 flowable의 데이터를 조합해 문자열을 만든다
                                                                          .map(data -> {
                                                                              // 통지 시의 시스템 시각도 데이터에 추가한다
                                                                              long time = System.currentTimeMillis();
                                                                              return time + "ms: [" + sourceData + "] " + data;
                                                                          })
                                            );

        // 구독한다
        flowable.subscribe(new DebugSubscriber<>());

        // 구독한다
        Thread.sleep(4000L);
    }
}
