package chap4;

import io.reactivex.Flowable;

import java.math.BigDecimal;

public class DistinctUntilChangedSample2 {

    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("1", "1.0", "0.1", "0.10", "1")
                .distinctUntilChanged((s1, s2) -> {
                    BigDecimal convert1 = new BigDecimal(s1);
                    BigDecimal convert2 = new BigDecimal(s2);

                    return convert1.compareTo(convert2) == 0;
                });

        flowable.subscribe(new DebugSubscriber<>());
    }

}
