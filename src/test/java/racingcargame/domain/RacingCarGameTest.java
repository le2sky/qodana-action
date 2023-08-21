package racingcargame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import racingcargame.infrastructure.RandomGeneratorImpl;
import racingcargame.infrastructure.RandomGeneratorStub;

class RacingCarGameTest {

    public final String[] carNames = new String[]{"pobi", "lsky", "ksky"};

    @Test
    void 자동차_게임은_라운드의_횟수를_알고있어야_한다() {
        RacingCarGame game = new RacingCarGame(5, carNames, new RandomGeneratorImpl());
        assertThat(game.getRoundCount()).isEqualTo(5);
    }

    @Test
    void 라운드_횟수가_0_이하인_경우_예외를_발생한다() {
        assertThatThrownBy(() -> {
            new RacingCarGame(0, carNames, new RandomGeneratorImpl());
            new RacingCarGame(-1, carNames, new RandomGeneratorImpl());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 자동차_게임의_참여자가_null인_경우_예외를_발생한다() {
        assertThatThrownBy(() -> {
            new RacingCarGame(5, null, new RandomGeneratorImpl());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 자동차_게임의_참여자가_1명_미만일_경우_예외를_발생한다() {
        assertThatThrownBy(() -> {
            new RacingCarGame(5, new String[]{}, new RandomGeneratorImpl());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 잔여_라운드_수가_없을_때_플레이_요청을_하면_예외를_발생한다() {
        assertThatThrownBy(() -> {
            RacingCarGame game =
                    new RacingCarGame(1, carNames, new RandomGeneratorImpl());
            game.play();
            game.play();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 랜덤_생성기가_null이면_예외를_발생한다() {
        assertThatThrownBy(() -> {
            new RacingCarGame(1, carNames, null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 게임_플레이_로직_검증() {
        PlayResult expected = new PlayResult(List.of(
                new Description("pobi", 1),
                new Description("lsky", 2),
                new Description("ksky", 0)
        ));

        RandomGeneratorStub stub = new RandomGeneratorStub();
        stub.given(new int[]{9, 4, 1});
        stub.given(new int[]{3, 6, 2});
        RacingCarGame game = new RacingCarGame(2, carNames, stub);
        game.play();
        assertThat(game.play()).isEqualTo(expected);
    }

    @Test
    void 게임의_상태가_종료되기_이전에_우승자를_조회하면_예외를_발생한다() {
        RacingCarGame game = new RacingCarGame(1, carNames, new RandomGeneratorImpl());
        assertThatThrownBy(game::showWinner)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 게임의_승리자를_보여준다() {
        List<String> expected = List.of("pobi");
        RandomGeneratorStub stub = new RandomGeneratorStub();
        stub.given(new int[]{6, 3, 1});
        RacingCarGame game = new RacingCarGame(1, carNames, stub);
        game.play();
        assertThat(game.showWinner()).isEqualTo(expected);
    }

    @Test
    void 게임의_우승자는_여러명이_될_수있다() {
        List<String> expected = List.of("pobi", "lsky");
        RandomGeneratorStub stub = new RandomGeneratorStub();
        stub.given(new int[]{6, 6, 1});
        RacingCarGame game = new RacingCarGame(1, carNames, stub);
        game.play();
        assertThat(game.showWinner()).isEqualTo(expected);
    }

    @Test
    void 게임의_종료_여부를_확인할_수_있다() {
        RandomGeneratorStub stub = new RandomGeneratorStub();
        stub.given(new int[]{6, 6, 1});
        RacingCarGame game = new RacingCarGame(1, carNames, stub);
        assertThat(game.isGameEnd()).isFalse();
        game.play();
        assertThat(game.isGameEnd()).isTrue();
    }
}
