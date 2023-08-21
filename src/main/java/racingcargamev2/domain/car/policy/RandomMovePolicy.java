package racingcargamev2.domain.car.policy;

import java.security.SecureRandom;
import java.util.Date;

public class RandomMovePolicy implements MovePolicy {

    private static final int MIN_MOVE_COND = 4;
    private static final int MIN_RAND_BOUND = 0;
    private static final int MAX_RAND_BOUND = 9;

    @Override
    public boolean isMovable() {
        int random = generateRandom();

        return isGraterEqualThenMinCondition(random);
    }

    private boolean isGraterEqualThenMinCondition(int random) {
        return random >= MIN_MOVE_COND;
    }

    private int generateRandom() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(new Date().getTime());

        return secureRandom.nextInt((MAX_RAND_BOUND - MIN_RAND_BOUND) + 1) + MIN_RAND_BOUND;
    }
}
