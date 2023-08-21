package racingcargamev2.domain.car;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class Cars {

    private static final int MIN_CARS_SIZE = 1;

    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        checkCarsSize(cars);

        this.cars = cars;
    }

    public static Cars from(final List<String> carNames) {
        return new Cars(carNames.stream()
                .map(Car::from)
                .collect(toList()));
    }

    public static Cars valueOf(final List<Car> cars) {
        return new Cars(cars);
    }

    private void checkCarsSize(final List<Car> cars) {
        if (cars.size() < MIN_CARS_SIZE) {
            throw new IllegalArgumentException("자동차는 적어도 하나 이상이어야 합니다.");
        }
    }

    public void moveAll() {
        cars.forEach(Car::move);
    }

    public List<CarDescription> describeAll() {
        return cars.stream()
                .map(Car::describeSelf)
                .collect(toList());
    }

    public List<String> findWinners() {
        return findWinnerNames(findWinnerPosition());
    }

    private Position findWinnerPosition() {
        return Position.from(cars.stream()
                .map(Car::describeSelf)
                .map(CarDescription::getPosition)
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow());
    }

    private List<String> findWinnerNames(final Position winnerPosition) {
        return cars.stream()
                .filter(car -> car.isWinner(winnerPosition))
                .map(Car::describeSelf)
                .map(CarDescription::getName)
                .collect(toList());
    }
}
