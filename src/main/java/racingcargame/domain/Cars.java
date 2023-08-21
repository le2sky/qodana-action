package racingcargame.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Cars {

    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    public static Cars with(String[] carNames) {
        checkCarNames(carNames);
        return new Cars(Arrays.stream(carNames)
                .map(Car::new)
                .collect(Collectors.toList()));
    }

    private static void checkCarNames(String[] carNames) {
        if (Optional.ofNullable(carNames).isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public int size() {
        return cars.size();
    }

    public PlayResult playRound(int[] randoms) {
        checkInvalidRandoms(randoms);
        checkRandomsLength(randoms);
        moveAllCars(randoms);
        return buildPlayResult();
    }

    private void checkInvalidRandoms(int[] randoms) {
        if (Optional.ofNullable(randoms).isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private void checkRandomsLength(int[] randoms) {
        if (randoms.length != cars.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void moveAllCars(int[] randoms) {
        for (int i = 0; i < randoms.length; i++) {
            Car car = cars.get(i);
            car.move(randoms[i]);
        }
    }

    private PlayResult buildPlayResult() {
        return new PlayResult(getCarDescriptions());
    }

    public List<Description> getCarDescriptions() {
        return cars.stream()
                .map(Car::describeSelf)
                .collect(Collectors.toList());
    }
}
