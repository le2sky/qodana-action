package racingcargamev2.domain.car;

import java.util.Objects;

public class CarDescription {

    private final String name;
    private final int position;

    private CarDescription(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public static CarDescription of(final String name, final int position) {
        return new CarDescription(name, position);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarDescription that = (CarDescription) o;
        return Objects.equals(name, that.name) && Objects.equals(position,
                that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}
