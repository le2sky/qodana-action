package racingcargame.domain;

import java.util.Optional;

class Car {

    private static final int MAXIMUM_NAME_LENGTH = 5;
    private static final int MINIMUM_NUMBER_FOR_MOVE_EXECUTE = 0;
    private static final int MAXIMUM_NUMBER_FOR_MOVE_EXECUTE = 9;
    private static final int MOVABLE_STANDARD = 4;
    private final String name;
    private int position;

    public Car(String name) {
        checkInvalidFormat(name);
        checkCarNameLength(name);
        this.name = name;
        this.position = 0;
    }

    private void checkInvalidFormat(String name) {
        if (isNullOrEmpty(name) || name.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNullOrEmpty(String name) {
        return Optional.ofNullable(name).isEmpty() || name.isEmpty();
    }

    private void checkCarNameLength(String name) {
        if (name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    public Description describeSelf() {
        return new Description(name, position);
    }

    public void move(int num) {
        checkNumberRange(num);
        if (num >= MOVABLE_STANDARD) {
            position++;
        }
    }

    private void checkNumberRange(int num) {
        if (num < MINIMUM_NUMBER_FOR_MOVE_EXECUTE || num > MAXIMUM_NUMBER_FOR_MOVE_EXECUTE) {
            throw new IllegalArgumentException();
        }
    }
}
