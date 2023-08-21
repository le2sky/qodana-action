package racingcargame.infrastructure;

import java.util.LinkedList;
import java.util.Queue;
import racingcargame.domain.RandomGenerator;

public class RandomGeneratorStub implements RandomGenerator {

    private final Queue<int[]> randomsQueue = new LinkedList<>();

    public void given(int[] randoms) {
        this.randomsQueue.add(randoms);
    }

    @Override
    public int[] generateWithSize(int size) {
        if (randomsQueue.isEmpty()) {
            return new int[]{1, 2, 3};
        }

        return randomsQueue.poll();
    }
}
