package racingcargame.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class CarsTest {

    @Test
    void 자동차를_생성한다() {
        Cars cars = Cars.with(new String[]{"a", "b", "c"});
        assertThat(cars.size()).isEqualTo(3);

        Cars cars2 = Cars.with(new String[]{"a", "b", "c", "d"});
        assertThat(cars2.size()).isEqualTo(4);
    }

    @Test
    void null이나_빈문자열_혹은_블랭크가_들어오면_예외를_발생한다() {
        assertThatThrownBy(() -> {
            Cars.with(new String[]{"", "A"});
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            Cars.with(new String[]{"B", null});
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            Cars.with(new String[]{"  ", "C"});
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 자동차_이름_배열이_null인_경우_예외를_발생한다() {
        assertThatThrownBy(() -> {
            Cars.with(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 자동차를_전진시킬때_랜덤_배열의_수와_자동차의_수가_일치하지_않으면_예외_발생() {
        assertThatThrownBy(() -> {
            Cars cars = Cars.with(new String[]{"a", "b"});
            cars.playRound(new int[]{1});
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 자동차를_전진시킬때_랜덤_배열이_null이라면_예외_발생() {
        assertThatThrownBy(() -> {
            Cars cars = Cars.with(new String[]{"a", "b"});
            cars.playRound(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주어진_랜덤_배열을_받아서_자동차를_전진시킨다() {
        Cars cars = Cars.with(new String[]{"a"});
        PlayResult expected = new PlayResult(List.of(new Description("a", 1)));
        PlayResult result = cars.playRound(new int[]{6});
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 더욱_복잡하게_주어진_랜덤_배열을_받아서_자동차_전진_기능_검증하기() {
        Cars cars = Cars.with(new String[]{"a", "b", "c"});
        PlayResult expected = new PlayResult(
                List.of(new Description("a", 1),
                        new Description("b", 2),
                        new Description("c", 0)));

        cars.playRound(new int[]{5, 6, 1});
        PlayResult result = cars.playRound(new int[]{2, 8, 2});
        assertThat(result).isEqualTo(expected);
    }
}
