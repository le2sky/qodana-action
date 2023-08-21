package racingcargamev2.view;

import java.util.List;
import racingcargamev2.domain.car.CarDescription;
import racingcargamev2.domain.game.RoundSummary;

public class OutputView {

    private static final String RESULT_HEADER = "실행 결과";
    private static final String RESULT_FORMAT = "%s : %s";
    private static final String POSITION_SHAPE = "-";
    private static final String WINNER_POSTFIX_MESSAGE = "가 최종 우승했습니다.";
    private static final String WINNER_DIVIDER = ", ";

    public static void writeResultHeader() {
        System.out.println(RESULT_HEADER);
    }

    public static void writeRoundSummary(RoundSummary roundSummary) {
        for (CarDescription carDescription : roundSummary.getCarDescriptions()) {
            writeOne(carDescription);
        }
        System.out.println();
    }

    private static void writeOne(CarDescription carDescription) {
        System.out.println(String.format(RESULT_FORMAT, carDescription.getName(),
                makePositionLine(carDescription)));
    }

    private static String makePositionLine(CarDescription carDescription) {
        return POSITION_SHAPE.repeat(carDescription.getPosition());
    }

    public static void writeWinners(List<String> winners) {
        System.out.println((makePrefixMessage(winners) + WINNER_POSTFIX_MESSAGE));
    }

    private static String makePrefixMessage(List<String> winners) {
        return String.join(WINNER_DIVIDER, winners);
    }
}
