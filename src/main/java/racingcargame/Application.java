package racingcargame;

import racingcargame.controller.RacingCarGameController;

public class Application {

    public static void main(String[] args) {
        RacingCarGameController game = Configuration.racingCarGameController();
        game.run();
    }
}
