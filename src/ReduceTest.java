import java.util.Arrays;
import java.util.function.BinaryOperator;

class CompareString implements BinaryOperator<String> { // BinaryOperator 인터페이스를 구현한 클래스
    @Override
    public String apply(String s, String s2) {
        if (s.getBytes().length >= s2.getBytes().length) return s;
        else return s2;
    }
}

public class ReduceTest {
    public static void main(String[] args) {
        String greetings[] = {"안녕하세요", "hello", "Good morning", "반갑습니다^^"};

        System.out.println(Arrays.stream(greetings).reduce("", (s, s2) -> {
            if (s.getBytes().length >= s2.getBytes().length) return s;
            else return s2;
        }));

        System.out.println();

        // 위와 같이 reduce()를 직접 프로그래밍할 때 구현부의 내용이 너무 길면 BinaryOperator 인터페이스를 구현한 클래스를 만들어서 stream()에 심어 줘도 된다.
        String str = Arrays.stream(greetings).reduce(new CompareString()).get(); // BinaryOperator를 구현한 클래스를 이용
        System.out.println(str);

        // 결과
        // 반갑습니다^^
        //
        // 반갑습니다^^
    }
}
