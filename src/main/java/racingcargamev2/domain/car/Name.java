package racingcargamev2.domain.car;

import java.util.Objects;

class Name {

    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    private Name(final String name) {
        checkInvalidName(name);

        this.name = name;
    }

    public static Name from(final String name) {
        return new Name(name);
    }

    private void checkInvalidName(final String name) {
        checkNull(name);
        checkEmptyName(name);
        checkNameLength(name);
    }

    private void checkNull(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("자동차 이름에 널값을 허용하지 않습니다.");
        }
    }

    private void checkEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("자동차 이름은 빈 문자열이 될 수 없습니다.");
        }
    }

    private void checkNameLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
