package racingcargame.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class RandomGeneratorImplTest {

    @Test
    void _0이하의_수가_들어오면_예외를_발생한다() {
        RandomGeneratorImpl randomGenerator = new RandomGeneratorImpl();
        assertThatThrownBy(() -> {
            randomGenerator.generateWithSize(0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주어진_사이즈의_랜덤한_배열을_반환한다() {
        RandomGeneratorImpl randomGenerator = new RandomGeneratorImpl();
        int[] randoms = randomGenerator.generateWithSize(5);
        assertThat(randoms.length).isEqualTo(5);
    }

    /**
     * 신뢰성 없는 테스트
     */
    @Test
    @Disabled
    void 랜덤값의_범위는_0부터_9까지이다() {
        RandomGeneratorImpl randomGenerator = new RandomGeneratorImpl();
        for (int i = 0; i < 10000; i++) {
            int actual = randomGenerator.generateWithSize(1)[0];
            assertThat(actual).isGreaterThanOrEqualTo(0);
            assertThat(actual).isLessThanOrEqualTo(9);
        }
    }
}