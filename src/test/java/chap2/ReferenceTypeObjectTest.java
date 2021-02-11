package chap2;

import chap2.ReferenceTypeObject;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ReferenceTypeObjectTest {

    @Test
    void final로_선언해도_상태_변경이_가능() {

        // final이 붙은 참조 변수
        final ReferenceTypeObject instance = new ReferenceTypeObject();

        // 참조를 변경하면 컴파일 에러가 발생한다.
        // instance = new ReferenceTypeObject();

        // 변경 전 객체 상태를 확인한다.
        assertThat(instance.getValue(), is("A"));

        // instance의 상태를 변경할 수 있다.
        instance.setValue("B");

        // 상태를 변경한 객체를 확인한다.
        assertThat(instance.getValue(), is("B"));

    }
}