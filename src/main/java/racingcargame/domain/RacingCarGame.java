package racingcargame.domain;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class RacingCarGame {

    private static final int MINIMUM_PARTICIPANT_COUNT = 1;
    private static final int MINIMUM_ROUND_COUNT = 1;
    private final RandomGenerator randomGenerator;
    private final Cars cars;
    private int roundCount;

    public RacingCarGame(int roundCount, String[] carNames, RandomGenerator randomGenerator) {
        checkRoundCountRange(roundCount);
        checkCarNames(carNames);
        checkRandomGenerator(randomGenerator);

        this.roundCount = roundCount;
        this.cars = Cars.with(carNames);
        this.randomGenerator = randomGenerator;
    }

    private void checkRoundCountRange(int roundCount) {
        if (roundCount < MINIMUM_ROUND_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private void checkCarNames(String[] carNames) {
        if (isInvalidCarNames(carNames)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isInvalidCarNames(String[] carNames) {
        return Optional.ofNullable(carNames).isEmpty()
                || carNames.length < MINIMUM_PARTICIPANT_COUNT;
    }

    private void checkRandomGenerator(RandomGenerator randomGenerator) {
        if (Optional.ofNullable(randomGenerator).isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public PlayResult play() {
        checkRoundCountRange(roundCount);
        decreaseCount();
        return cars.playRound(randomGenerator.generateWithSize(cars.size()));
    }

    private void decreaseCount() {
        roundCount--;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public List<String> showWinner() {
        checkGameEnd();
        return findWinnersName(findWinnerPosition());
    }

    private void checkGameEnd() {
        if (roundCount >= MINIMUM_ROUND_COUNT) {
            throw new IllegalStateException();
        }
    }

    private int findWinnerPosition() {
        return cars.getCarDescriptions().stream()
                .max(Comparator.comparingInt(Description::getPosition))
                .orElseThrow(NoSuchElementException::new)
                .getPosition();
    }

    private List<String> findWinnersName(int winnerPosition) {
        return cars.getCarDescriptions().stream()
                .filter(car -> car.getPosition() == winnerPosition)
                .map(Description::getName)
                .collect(Collectors.toList());
    }

    public boolean isGameEnd() {
        return roundCount < MINIMUM_ROUND_COUNT;
    }
}
