package chap4;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class DebugSingleObserver<T> implements SingleObserver<T> {

    public DebugSingleObserver() {
        super();
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        System.out.println(Thread.currentThread().getName() + ": subscribed " + d);
    }

    @Override
    public void onSuccess(@NonNull T data) {
        System.out.println(Thread.currentThread().getName() + ": " + data);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        System.err.println(Thread.currentThread().getName() + " error: " + e);
    }
}