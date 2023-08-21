package racingcargame.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    void 자동차_이름이_5자_초과인_경우_예외를_발생한다() {
        assertThrowingException("abcdef");
    }

    @Test
    void 자동차_이름이_null이거나_빈_문자열인_경우_예외를_발생한다() {
        assertThrowingException(null);
        assertThrowingException("");
    }

    @Test
    public void 자동차_이름이_블랭크인_경우_예외를_발생한다() {
        assertThrowingException("\n");
        assertThrowingException("  ");
        assertThrowingException(" ");
    }

    @Test
    void 자동차_이름이_5자_이하인_경우_예외를_발생하지_않는다() {
        checkCarName("abcde");
        checkCarName("a");
    }

    @Test
    void 자동차에_랜덤값이_4이상일_경우_전진이_가능하다() {
        Car car = new Car("test");
        car.move(4);
        car.move(9);
        car.move(3);
        car.move(0);

        assertThat(car.describeSelf().getPosition()).isEqualTo(2);
    }

    @Test
    void 자동차에_들어올_수_있는_숫자가_0부터_9사이의_숫자가_아니면_예외를_발생한다() {
        Car car = new Car("test");
        assertThatThrownBy(() -> {
            car.move(-1);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            car.move(10);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 자동차의_현재_정보를_조회한다() {
        Car car = new Car("test");
        Description expected = new Description("test", 0);
        assertThat(car.describeSelf()).isEqualTo(expected);
    }

    private void assertThrowingException(String carName) {
        assertThatThrownBy(() -> {
            checkCarName(carName);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    private void checkCarName(String carName) {
        new Car(carName);
    }
}
