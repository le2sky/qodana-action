package racingcargame.view;

import java.util.List;
import racingcargame.domain.Description;
import racingcargame.domain.PlayResult;

public class OutputView {

    private static final String RESULT_HEADER = "실행 결과";
    private static final String RESULT_FORMAT = "%s : %s";
    private static final String POSITION_SHAPE = "-";
    private static final String WINNER_POSTFIX_MESSAGE = "가 최종 우승했습니다.";
    private static final String WINNER_DIVIDER = ", ";

    public void writeResultHeader() {
        System.out.println(RESULT_HEADER);
    }

    public void writeResult(PlayResult result) {
        for (Description description : result.getCarDescriptions()) {
            writeOne(description);
        }
        System.out.println();
    }

    private void writeOne(Description description) {
        System.out.println(String.format(RESULT_FORMAT, description.getName(),
                makePositionLine(description)));
    }

    private String makePositionLine(Description description) {
        return POSITION_SHAPE.repeat(description.getPosition());
    }

    public void writeWinner(List<String> winners) {
        System.out.println((makePrefixMessage(winners) + WINNER_POSTFIX_MESSAGE));
    }

    private String makePrefixMessage(List<String> winners) {
        return String.join(WINNER_DIVIDER, winners);
    }
}
