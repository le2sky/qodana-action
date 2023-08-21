package racingcargamev2.domain.car.policy;

public class NeverMovePolicy implements MovePolicy {

    @Override
    public boolean isMovable() {
        return false;
    }
}
