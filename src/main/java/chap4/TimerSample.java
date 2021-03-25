package chap4;

import io.reactivex.Flowable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TimerSample {
    public static void main(String[] args) throws InterruptedException {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss.SSS");

        // 처리 작업을 시작하기 전 시각
        System.out.println("시작 시각: " + LocalTime.now().format(formatter));

        // 1000밀리초마다 숫자를 통지하는 Flowable을 생성한다.
        Flowable<Long> flowable = Flowable.timer(1000L, TimeUnit.MILLISECONDS);

        flowable.subscribe(data -> {
            String threadName = Thread.currentThread().getName();
            String time = LocalTime.now().format(formatter);
            System.out.println(threadName + ": " + time + ": data=" + data);
        }, error -> {
            System.out.println("에러=" + error);
        }, () -> System.out.println("완료"));

        Thread.sleep(15000L);
    }
}
