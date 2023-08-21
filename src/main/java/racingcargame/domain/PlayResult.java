package racingcargame.domain;

import java.util.List;
import java.util.Objects;

public class PlayResult {

    private final List<Description> carDescriptions;

    public PlayResult(List<Description> carDescriptions) {
        this.carDescriptions = carDescriptions;
    }

    public List<Description> getCarDescriptions() {
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

        PlayResult that = (PlayResult) o;

        return Objects.equals(carDescriptions, that.carDescriptions);
    }

    @Override
    public int hashCode() {
        return carDescriptions != null ? carDescriptions.hashCode() : 0;
    }
}
