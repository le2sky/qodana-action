package racingcargame.view;

import java.util.Scanner;

public class InputView {

    private static final String READ_CAR_NAMES_MESSAGE =
            "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String READ_GAME_ROUND_MESSAGE = "시도할 회수는 몇회인가요?";

    public String readNames() {
        System.out.println(READ_CAR_NAMES_MESSAGE);
        return readLineWithOutBlank();
    }

    public String readRound() {
        System.out.println(READ_GAME_ROUND_MESSAGE);
        return readLineWithOutBlank();
    }

    private String readLineWithOutBlank() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}
