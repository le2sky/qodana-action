package racingcargamev2.domain.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcargamev2.domain.car.policy.AlwayMovePolicy;
import racingcargamev2.domain.car.policy.NeverMovePolicy;

public class CarsTest {

    @DisplayName("자동차는 적어도 하나 이상이어야 한다.")
    @Test
    void carNamesLength() {
        assertThatThrownBy(() -> Cars.from(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차는 적어도 하나 이상이어야 합니다.");
    }

    @DisplayName("모든 자동차의 현재 정보를 가져온다")
    @Test
    void describeAll() {
        Cars cars = Cars.from(List.of("lee", "kim"));

        List<CarDescription> carDescriptions = cars.describeAll();

        assertThat(carDescriptions).isEqualTo(List.of(
                CarDescription.of("lee", 0),
                CarDescription.of("kim", 0)
        ));
    }

    @DisplayName("모든 자동차를 움직일 수 있다.")
    @Test
    void moveAll() {
        Cars cars = Cars.valueOf(
                List.of(Car.of("lee", new AlwayMovePolicy()),
                        Car.of("kim", new NeverMovePolicy())));

        cars.moveAll();
        cars.moveAll();

        List<CarDescription> carDescriptions = cars.describeAll();
        assertThat(carDescriptions).hasSize(2)
                .containsAnyElementsOf(List.of(
                        CarDescription.of("lee", 2),
                        CarDescription.of("kim ", 0)
                ));
    }

    @DisplayName("우승자의 이름을 찾는다.")
    @Test
    void findWinner() {
        Cars cars = Cars.valueOf(
                List.of(Car.of("lee", new AlwayMovePolicy()),
                        Car.of("kim", new NeverMovePolicy())));

        cars.moveAll();
        cars.moveAll();

        List<String> winnerNames = cars.findWinners();

        assertThat(winnerNames).hasSize(1)
                .contains("lee");
    }

    @DisplayName("우승자는 여러명일 수 있다.")
    @Test
    void findWinners() {
        Cars cars = Cars.valueOf(
                List.of(Car.of("lee", new AlwayMovePolicy()),
                        Car.of("kim", new AlwayMovePolicy())));

        cars.moveAll();
        cars.moveAll();

        List<String> winnerNames = cars.findWinners();

        assertThat(winnerNames).hasSize(2)
                .contains("lee", "kim");
    }
}
