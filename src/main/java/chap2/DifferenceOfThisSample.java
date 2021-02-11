package chap2;

import io.reactivex.functions.Action;

public class DifferenceOfThisSample {

    public static void main(String[] args) throws Exception {
        DifferenceOfThisSample target = new DifferenceOfThisSample();
        target.execute();
    }

    public void execute() throws Exception {
        // 익명 클래스
        Action anonymous = new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("익명 클래스의 this: " + this);
            }
        };

        Action lambda = () -> System.out.println("람다식의 this: " + this);

        anonymous.run();
        lambda.run();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
