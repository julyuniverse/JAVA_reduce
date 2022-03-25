# 연산 수행에 대한 구현을 할 수 있는 reduce() 연산

## reduce() 연산

- 정의된 연산이 아닌 프로그래머가 직접 구현한 연산을 적용

```java
T reduce(T identify, BinaryOperator<T> accumulator)
```

- 최종 연산으로 스트림의 요소를 소모하며 연산을 수행
- 배열의 모든 요소의 합을 구하는 reduce() 연산 구현 예

```java
Arrays.stream(arr).reduce(0, (a, b) -> a + b));
```

- reduce() 메서드의 두 번째 요소로 전달되는 람다식에 따라 다양한 기능을 수행할 수 있음.
- 람다식을 직접 구현하거나 람다식이 긴 경우 BinaryOperator를 구현한 클래스를 사용함.

## BinaryOperator를 구현하여 배열에 여러 문자열이 있을 때 길이가 가장 긴 문자열 찾기 예

```java
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
    }
}
```