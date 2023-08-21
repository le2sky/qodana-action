package racingcargamev2.domain.car;

import racingcargamev2.domain.car.policy.MovePolicy;
import racingcargamev2.domain.car.policy.RandomMovePolicy;

class Car {

    private final Name name;
    private final Position position;
    private final MovePolicy movePolicy;

    private Car(final String name) {
        this.name = Name.from(name);
        this.position = Position.zero();
        this.movePolicy = new RandomMovePolicy();
    }

    private Car(final String name, final MovePolicy movePolicy) {
        this.name = Name.from(name);
        this.position = Position.zero();
        this.movePolicy = movePolicy;
    }

    private Car(final String name, final int position) {
        this.name = Name.from(name);
        this.position = Position.from(position);
        this.movePolicy = new RandomMovePolicy();
    }

    public static Car from(final String name) {
        return new Car(name);
    }

    public static Car of(final String name, final MovePolicy movePolicy) {
        return new Car(name, movePolicy);
    }

    public static Car of(final String name, final int position) {
        return new Car(name, position);
    }

    public void move() {
        if (movePolicy.isMovable()) {
            position.move();
        }
    }

    public CarDescription describeSelf() {
        return CarDescription.of(name.getName(), position.getPosition());
    }

    public boolean isWinner(final Position winnerPosition) {
        return position.equals(winnerPosition);
    }
}
