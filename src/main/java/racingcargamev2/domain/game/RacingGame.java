package racingcargamev2.domain.game;

import java.util.List;
import racingcargamev2.domain.car.Cars;

public class RacingGame {

    private final Round round;
    private final Cars cars;

    private RacingGame(final int round, final List<String> carNames) {
        this.round = Round.from(round);
        this.cars = Cars.from(carNames);
    }

    private RacingGame(final int round, final Cars cars) {
        this.round = Round.from(round);
        this.cars = cars;
    }

    public static RacingGame of(final int round, final List<String> carNames) {
        return new RacingGame(round, carNames);
    }

    public static RacingGame of(final int round, final Cars cars) {
        return new RacingGame(round, cars);
    }

    public void race() {
        checkIsGameEnd();

        round.race();
        cars.moveAll();
    }

    private void checkIsGameEnd() {
        if (isEnd()) {
            throw new IllegalStateException("게임이 종료되어 더 이상 경주를 할 수 없습니다.");
        }
    }

    public boolean isEnd() {
        return round.isEnd();
    }

    public List<String> showWinners() {
        checkIsGameEndBeforeFindWinners();

        return cars.findWinners();
    }

    private void checkIsGameEndBeforeFindWinners() {
        if (!isEnd()) {
            throw new IllegalStateException("게임 종료 이전에 우승자를 조회할 수 없습니다.");
        }
    }

    public RoundSummary generateRoundSummary() {
        return RoundSummary.from(cars.describeAll());
    }
}
