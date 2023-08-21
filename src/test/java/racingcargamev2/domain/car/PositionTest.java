package racingcargamev2.domain.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @DisplayName("포지션은 음수일 수 없다.")
    @Test
    void negativePosition() {
        assertThatThrownBy(() -> Position.from(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치는 음수일 수 없습니다.");
    }

    @DisplayName("포지션을 증가시킨다.")
    @Test
    void increasePosition() {
        Position position = Position.from(0);

        position.move();
        position.move();

        assertThat(position).isEqualTo(Position.from(2));
    }
}
