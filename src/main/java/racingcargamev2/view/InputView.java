package racingcargamev2.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String READ_CAR_NAMES_MESSAGE =
            "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String READ_GAME_ROUND_MESSAGE = "시도할 회수는 몇회인가요?";

    public static List<String> readCarNames() {
        System.out.println(READ_CAR_NAMES_MESSAGE);

        return Arrays.stream(readLine().split(","))
                .collect(Collectors.toList());
    }

    public static int readRound() {
        System.out.println(READ_GAME_ROUND_MESSAGE);

        return Integer.parseInt(readLine());
    }

    private static String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}
