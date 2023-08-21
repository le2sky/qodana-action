package racingcargamev2.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcargamev2.domain.car.CarDescription;
import racingcargamev2.domain.car.CarsData;

public class RacingGameTest {

    @DisplayName("잔여 라운드가 없다면 게임을 종료한다.")
    @Test
    void isGameEnd() {
        RacingGame game = RacingGame.of(1, List.of("lee"));

        game.race();

        assertThat(game.isEnd()).isTrue();
    }

    @DisplayName("실시간 자동차 현황을 제공한다.")
    @Test
    void generateRoundSummary() {
        RacingGame game = RacingGame.of(2, List.of("lee"));

        RoundSummary roundSummary = game.generateRoundSummary();

        assertThat(roundSummary)
                .isEqualTo(RoundSummary.from(List.of(CarDescription.of("lee", 0))));
    }

    @DisplayName("잔여 라운드가 존재하지 않으면, 레이스를 할 수 없다.")
    @Test
    void raceWhenNoRoundExist() {
        RacingGame game = RacingGame.of(1, CarsData.createCars());

        game.race();

        assertThatThrownBy(game::race).isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료되어 더 이상 경주를 할 수 없습니다.");
    }

    @DisplayName("모든 자동차를 이동시키면, 라운드를 감소 시켜야 한다.")
    @Test
    void race() {
        RacingGame game = RacingGame.of(2, CarsData.createCars());

        game.race();
        game.race();

        List<CarDescription> carDescriptions = game.generateRoundSummary().getCarDescriptions();
        assertThat(carDescriptions).hasSize(2)
                .containsAnyElementsOf(List.of(
                        CarDescription.of("lee", 2),
                        CarDescription.of("kim ", 0)
                ));

        assertThat(game.isEnd()).isTrue();
    }

    @DisplayName("게임이 종료되기 이전에 우승자를 조회할 수 없다.")
    @Test
    void showWinnersWhenPlaying() {
        RacingGame game = RacingGame.of(2, CarsData.createCars());

        assertThatThrownBy(game::showWinners)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임 종료 이전에 우승자를 조회할 수 없습니다.");
    }

    @DisplayName("게임이 종료되면 우승자를 조회할 수 있다.")
    @Test
    void showWinners() {
        RacingGame game = RacingGame.of(2, CarsData.createCars());
        game.race();
        game.race();

        List<String> winners = game.showWinners();

        assertThat(winners).hasSize(1)
                .contains("lee");
    }
}
