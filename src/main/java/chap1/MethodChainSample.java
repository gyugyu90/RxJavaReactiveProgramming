package chap1;

import io.reactivex.Flowable;

public class MethodChainSample {
    public static void main(String[] args) {

        Flowable<Integer> flowable = Flowable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) // flowable 생성
                .filter(data -> data % 2 == 0) // 짝수만 통지
                .map(data -> data * 100); // 데이터를 100배로 변환

        flowable.subscribe(data -> System.out.println("data=" + data)); // 구독해서 받은 데이터 출력
    }
}
