package chap3;

import io.reactivex.Flowable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class ConcatMapEagerSample {

    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.just("A", "B", "C")
                                            .concatMapEager(data -> { // 받은 데이터로 Flowable을 생성하고 이 Flowable이 가진 데이터를 통지한다.
                                                return Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS);
                                            });

        // 구독한다.
        flowable.subscribe(data -> {
            String threadName = Thread.currentThread().getName();
            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("ss.SSS"));
            System.out.println(threadName + ": data=" + data + ", time=" + time);
        });

        Thread.sleep(2000L);
    }

}
