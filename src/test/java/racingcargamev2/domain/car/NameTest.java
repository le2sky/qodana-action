package racingcargamev2.domain.car;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameTest {

    @DisplayName("자동차 이름은 5자를 초과할 수 없다.")
    @Test
    void carNameLength() {
        assertThatThrownBy(() -> Name.from("123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 5자를 초과할 수 없습니다.");
    }

    @DisplayName("자동차 이름은 빈문자열이 될 수 없다.")
    @Test
    void carNameIsNotEmptyString() {
        assertThatThrownBy(() -> Name.from(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 빈 문자열이 될 수 없습니다.");
    }

    @DisplayName("자동차 이름에 널값을 허용하지 않는다.")
    @Test
    void carNameIsNotAllowNull() {
        assertThatThrownBy(() -> Name.from(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름에 널값을 허용하지 않습니다.");
    }
}
