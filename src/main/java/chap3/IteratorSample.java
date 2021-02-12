package chap3;

import io.reactivex.Flowable;

import java.util.List;

public class IteratorSample {

    public static void main(String[] args) {
        // 리스트에서 Iterator를 얻는다
        var list = List.of("a", "b", "c");
        var iterator = list.iterator();

        while (iterator.hasNext()) {
            // 데이터를 받는다
            var value = iterator.next();
            // 얻은 데이터를 출력한다.
            System.out.println(value);
        }

        Flowable<String> flowable = Flowable.fromIterable(list);
        flowable.subscribe(System.out::println);

    }

}
