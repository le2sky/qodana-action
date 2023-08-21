package racingcargame;

import racingcargame.controller.RacingCarGameController;
import racingcargame.domain.RandomGenerator;
import racingcargame.infrastructure.RandomGeneratorImpl;
import racingcargame.view.InputView;
import racingcargame.view.OutputView;

public class Configuration {

    public static RandomGenerator randomGenerator() {
        return new RandomGeneratorImpl();
    }

    public static RacingCarGameController racingCarGameController() {
        return new RacingCarGameController(inputView(), outputView());
    }

    private static OutputView outputView() {
        return new OutputView();
    }

    private static InputView inputView() {
        return new InputView();
    }
}
