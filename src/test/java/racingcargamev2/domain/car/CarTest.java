package racingcargamev2.domain.car;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcargamev2.domain.car.policy.AlwayMovePolicy;
import racingcargamev2.domain.car.policy.NeverMovePolicy;

public class CarTest {

    @DisplayName("정책이 성공하면 자동차를 움직일 수 있다.")
    @Test
    void move() {
        Car car = Car.of("lee", new AlwayMovePolicy());

        car.move();
        car.move();

        assertThat(car.describeSelf())
                .isEqualTo(CarDescription.of("lee", 2));
    }

    @DisplayName("정책이 실패하면 자동차를 움직일 수 없다.")
    @Test
    void noMove() {
        Car car = Car.of("lee", new NeverMovePolicy());

        car.move();
        car.move();

        assertThat(car.describeSelf())
                .isEqualTo(CarDescription.of("lee", 0));
    }

    @DisplayName("자동차의 현재 정보를 가져올 수 있다.")
    @Test
    void describeSelf() {
        Car car = Car.of("lee", new AlwayMovePolicy());

        car.move();

        assertThat(car.describeSelf())
                .isEqualTo(CarDescription.of("lee", 1));
    }

    @DisplayName("우승자의 위치가 주어지면, 자신이 우승 대상인지 검사한다.")
    @Test
    void isWinner() {
        Position winnerPosition = Position.from(3);
        Car car = Car.of("lee", 3);

        boolean result = car.isWinner(winnerPosition);

        assertThat(result).isTrue();
    }

    @DisplayName("자신이 우승자의 위치와 다른 경우, 우승자가 아니라고 판단한다.")
    @Test
    void isNotWinner() {
        Position winnerPosition = Position.from(3);
        Car car = Car.of("lee", 2);

        boolean result = car.isWinner(winnerPosition);

        assertThat(result).isFalse();
    }
}
