package racingcargamev2.domain.car;

import java.util.List;
import racingcargamev2.domain.car.policy.AlwayMovePolicy;
import racingcargamev2.domain.car.policy.NeverMovePolicy;

/**
 * Cars 테스트 과정에서 개별 자동차의 이동 정책을 조작하기 위해서
 * Cars.valueOf(final List cars)를 열어놨음. <br>
 * RacingGame에서 valueOf를 이용해, 복합적인 테스트를 수행하기 위해서는
 * Car를 public으로 열어놨어야 함 ㅠㅠ <br>
 * 이를 위해서 CarsData를 두어 RacingGame에게 Car의 존재를 숨겼음
 */
public class CarsData {

    public static Cars createCars() {
        return Cars.valueOf(
                List.of(Car.of("lee", new AlwayMovePolicy()),
                        Car.of("kim", new NeverMovePolicy())));
    }
}
