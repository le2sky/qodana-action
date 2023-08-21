package racingcargamev2.domain.car.policy;

public class AlwayMovePolicy implements MovePolicy{

    @Override
    public boolean isMovable() {
        return true;
    }
}
