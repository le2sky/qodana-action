package racingcargame.controller;

import racingcargame.Configuration;
import racingcargame.domain.RacingCarGame;
import racingcargame.view.InputView;
import racingcargame.view.OutputView;

public class RacingCarGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public RacingCarGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String[] carNames = InputMapper.mapToNames(inputView.readNames());
        int round = InputMapper.mapToRound(inputView.readRound());
        play(new RacingCarGame(round, carNames, Configuration.randomGenerator()));
    }

    private void play(RacingCarGame racingCarGame) {
        outputView.writeResultHeader();
        while (!racingCarGame.isGameEnd()) {
            outputView.writeResult(racingCarGame.play());
        }
        outputView.writeWinner(racingCarGame.showWinner());
    }
}
