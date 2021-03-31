package chap4;

import io.reactivex.Flowable;

import java.util.Locale;

public class MapSample {

    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("A", "B", "C", "D", "E") // 1
                                            .map(data -> data.toLowerCase(Locale.ROOT)); // 2

        flowable.subscribe(new DebugSubscriber<>());
    }

}
