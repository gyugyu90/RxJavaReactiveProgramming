package chap1;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ObservableSample {
    public static void main(String[] args) throws InterruptedException {
        // 인사말을 통지하는 Observable 생성
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {

                // 통지 데이터
                String[] data = {"Hello, World", "Hello, RxJava"};

                for (String datum : data) {
                    // 구독이 해지되면 처리를 중단한다.
                    if (emitter.isDisposed()) {
                        return;
                    }

                    // 데이터를 통지한다.
                    emitter.onNext(datum);
                }

                // 완료를 통지한다.
                emitter.onComplete();
            }
        });

        observable.observeOn(Schedulers.computation()) // 소비하는 측의 처리를 개별 스레드로 실행한다.
                  .subscribe(new Observer<String>() {
                      @Override
                      public void onSubscribe(@NonNull Disposable d) {
                          // do nothing
                      }

                      @Override
                      public void onNext(@NonNull String s) {
                          // 실행하는 스레드 이름을 얻는다.
                          String threadName = Thread.currentThread().getName();
                          // 받은 데이터를 출력한다.
                          System.out.println(threadName + ": " + s);
                      }

                      @Override
                      public void onError(@NonNull Throwable e) {
                          e.printStackTrace();
                      }

                      @Override
                      public void onComplete() {
                          // 실행하는 스레드 이름을 얻는다.
                          String threadName = Thread.currentThread().getName();
                          System.out.println(threadName + ": 완료");
                      }
                  });

        Thread.sleep(500L);

    }
}
