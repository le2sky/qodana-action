package racingcargame.infrastructure;

import racingcargame.domain.RandomGenerator;

public class RandomGeneratorImpl implements RandomGenerator {

    private static final int MINIMUM_SIZE = 0;

    @Override
    public int[] generateWithSize(int size) {
        checkMinimumSize(size);
        int[] randoms = new int[size];

        for (int i = 0; i < size; i++) {
            randoms[i] = generateOne();
        }
        return randoms;
    }

    private void checkMinimumSize(int size) {
        if (size <= MINIMUM_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private int generateOne() {
        return (int) (Math.random() * 10);
    }
}
