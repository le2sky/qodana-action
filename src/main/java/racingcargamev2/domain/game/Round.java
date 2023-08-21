package racingcargamev2.domain.game;

import java.util.Objects;

class Round {

    private static final int MIN_GAME_ROUND = 1;

    private int round;

    private Round(final int round) {
        checkGameRoundSize(round);

        this.round = round;
    }

    public static Round from(final int round) {
        return new Round(round);
    }

    private void checkGameRoundSize(final int round) {
        if (round < MIN_GAME_ROUND) {
            throw new IllegalArgumentException("게임 라운드 수는 적어도 1회 이상입니다.");
        }
    }

    public void race() {
        checkIsGameEnd();

        round--;
    }

    private void checkIsGameEnd() {
        if (isEnd()) {
            throw new IllegalStateException("잔여 라운드가 존재하지 않습니다.");
        }
    }

    public boolean isEnd() {
        return round == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Round round1 = (Round) o;
        return round == round1.round;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round);
    }
}
