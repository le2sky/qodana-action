package racingcargame.controller;

class InputMapper {

    private static final String SPLIT_DELIMITER = ",";

    static String[] mapToNames(String input) {
        return input.split(SPLIT_DELIMITER);
    }

    static int mapToRound(String input) {
        return Integer.parseInt(input);
    }
}
