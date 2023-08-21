package racingcargamev2.domain.game;

import java.util.List;
import java.util.Objects;
import racingcargamev2.domain.car.CarDescription;

public class RoundSummary {

    private final List<CarDescription> carDescriptions;

    private RoundSummary(final List<CarDescription> carDescriptions) {
        this.carDescriptions = carDescriptions;
    }

    public static RoundSummary from(final List<CarDescription> carDescriptions) {
        return new RoundSummary(carDescriptions);
    }

    public List<CarDescription> getCarDescriptions() {
        return carDescriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoundSummary that = (RoundSummary) o;
        return Objects.equals(carDescriptions, that.carDescriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carDescriptions);
    }
}
