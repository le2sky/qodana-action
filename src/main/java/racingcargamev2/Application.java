package racingcargamev2;

import java.util.List;
import racingcargamev2.domain.game.RacingGame;
import racingcargamev2.view.InputView;
import racingcargamev2.view.OutputView;

public class Application {

    public static void main(String[] args) {
        List<String> carNames = InputView.readCarNames();
        int round = InputView.readRound();

        RacingGame game = RacingGame.of(round, carNames);
        OutputView.writeResultHeader();
        while (!game.isEnd()) {
            game.race();
            OutputView.writeRoundSummary(game.generateRoundSummary());
        }
        OutputView.writeWinners(game.showWinners());
    }
}
